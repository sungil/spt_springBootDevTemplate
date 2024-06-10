package com.sptek.webfw.config.aspect;

import com.sptek.webfw.common.code.ServiceErrorCodeEnum;
import com.sptek.webfw.common.exception.DuplicatedRequestException;
import com.sptek.webfw.common.exception.ServiceException;
import com.sptek.webfw.util.ReqResUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/*
정상적인 동작이 되려면 해당 사용자에 대한 session이 이미 존재한 상태여야 함(대부분의 경우 이미 존재할듯)
 */

@Slf4j
@Aspect
@Component
public class RequestDeduplicationAspect
{
    private long DEDUPLICATION_DEFAULT_MS = 1000L*10; //해당 호출이 언제 끝날지 모르기 때문에 최대 방어값을 설정해놈
    private long DEDUPLICATION_EXTRA_MS = 1000L; //해당 호출이 종료되면 방어값을 변경해줌(최대값만큼 기다릴 필요가 없음)

    @Pointcut("(@within(org.springframework.stereotype.Controller) || " +
            "@within(org.springframework.web.bind.annotation.RestController)) && " +
            "(@within(com.sptek.webfw.anotation.AnoRequestDeduplication) || " +
            "@annotation(com.sptek.webfw.anotation.AnoRequestDeduplication))")
    public void myPointCut() {}

    @Before("myPointCut()")
    public void beforeRequest(JoinPoint joinPoint) {
        log.debug("AOP order : 3");
        //to do what you need.
    }

    @After("myPointCut()")
    public void AfterRequest(JoinPoint joinPoint) {
        log.debug("AOP order : 5 (APO order 4 was caller)");
        //to do what you need.
    }

    @Around("myPointCut()")
    public Object duplicateRequestCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("AOP order : 1");
        //log.debug("sessionAttributeAll : {}", ReqResUtil.getSessionAttributesAll(true));
        HttpServletRequest currentRequest = ReqResUtil.getRequest();

        // GET 요청을 제외 하고 싶을때
        //if ("GET".equalsIgnoreCase(currentRequest.getMethod())) {
        //    log.debug("duplicateRequestCheck : GET,  just pass through");
        //    return joinPoint.proceed();
        //}

        String requestSignature = joinPoint.getSignature().toLongString();
        if(isDuplicatationCase(requestSignature)) {
            if(joinPoint.getTarget().getClass().isAnnotationPresent(RestController.class)) {
                return handleDuplicationForRestController();
            } else {
                //todo: view 컨트롤러에서의 동작에 대해 고민 필요 (view컨트롤러에서는 사용이 어려울듯...)
                return handleDuplicationForViewController(currentRequest);
            }
            
        } else {
            HttpSession currentSession = currentRequest.getSession(true);
            currentSession.setAttribute(requestSignature, System.currentTimeMillis() + DEDUPLICATION_DEFAULT_MS);
            log.debug("save new requestSignature ({}), currentTimeMillis ({})", requestSignature, System.currentTimeMillis());

            try {
                log.debug("AOP order : 2");
                return joinPoint.proceed(); //here!! return to caller method !! AOP order 4 is caller

            } finally {  //exception 상황에서도 반드시 expire Ms 업데이트 필요
                long newExpireMs = System.currentTimeMillis() + DEDUPLICATION_EXTRA_MS;
                currentSession.setAttribute(requestSignature, newExpireMs);
                log.debug("my request is done, set new expiryTime ({})", newExpireMs);
            }
        }
    }

    private Object handleDuplicationForRestController() throws ServiceException {
        throw new ServiceException(ServiceErrorCodeEnum.SERVICE_DUPLICATION_REQUEST_ERROR);
    }

    private Object handleDuplicationForViewController(HttpServletRequest request) {
        throw new DuplicatedRequestException(request);
    }

    private boolean isDuplicatationCase(String requestSignature) {
        Long expiryTime = ReqResUtil.getSessionAttribute(requestSignature) == null ? 0: (Long)ReqResUtil.getSessionAttribute(requestSignature);

        if(expiryTime == 0) {
            log.debug("has no same requestSignature. this request will be accepted.");
            return false;

        } else if (expiryTime < System.currentTimeMillis()){
            log.debug("expiryTime({}) is smaller than curTime({}). this request will be skiped.", expiryTime, System.currentTimeMillis());
            return false;

        } else {
            log.debug("expiryTime({}) is bigger than curTime({}). this request will be accepted.", expiryTime, System.currentTimeMillis());
            return true;
        }
    }
}