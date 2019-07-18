package apiPhone.control;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
public class ErrorHandling implements ErrorController {
    Integer status;
    Exception exception;


    //Error Page - Default
    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                status, exception==null? "N/A": exception.getMessage());
    }

    //Custom error for data not found
    public String customError(LocalDateTime dateTime,String message) {

        return "[Date/Time= "
                + dateTime
                + ", Error Message= "
                + message + "]";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}