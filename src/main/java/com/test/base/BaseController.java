package com.test.base;

import com.test.exceptions.InvalidUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public abstract class BaseController {


    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse response, Exception e) throws IOException {
        log.debug("Exception while processing the request: ", e);
        response.sendError(getHttpStatus(e).value(), e.getMessage());
    }
    private HttpStatus getHttpStatus(Exception e) {
        ResponseStatus responseStatus = e.getClass().getAnnotation(ResponseStatus.class);
        return responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
    }
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Invalid client credentials")
    @ExceptionHandler({InvalidUserException.class, InvalidUserException.class})
    public void clientConfigurationNotFoundError(HttpServletRequest httpServletRequest, Exception e) {
        log.error("Invalid client credentials. Info", e);
    }

}
