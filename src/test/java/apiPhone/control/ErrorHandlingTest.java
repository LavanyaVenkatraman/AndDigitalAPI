package apiPhone.control;

import apiPhone.control.ErrorHandling;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Error handling check
 */

@RunWith(MockitoJUnitRunner.class)
public class ErrorHandlingTest {

    @InjectMocks
    ErrorHandling errorHandling;

    @Mock
    HttpServletRequest httpServletRequest;

    @Test
    public void handleError_test(){
        httpServletRequest.setAttribute("javax.servlet.error.status_code",404);
        httpServletRequest.setAttribute("javax.servlet.error.exception",new Exception());
        assertThat("No value returned",errorHandling.handleError(httpServletRequest),notNullValue());
    }

    @Test
    public void customError_test(){
        assertThat("No value returned",errorHandling.customError(LocalDateTime.now(),"testMessage"),notNullValue());
    }

    @Test
    public void getErrorPath_test(){
        assertThat("No value returned",errorHandling.getErrorPath(),notNullValue());
    }

}
