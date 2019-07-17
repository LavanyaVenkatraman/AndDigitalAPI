package apiPhone;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class ErrorHandling implements ErrorController {
    Integer statusCode;
    Exception exception;
    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception==null? "N/A": exception.getMessage());
    }

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

    @Override
    public String toString()
    {
        return "[statusCode="
                + statusCode
                + ", Exception="
                + exception + "]";
    }
}