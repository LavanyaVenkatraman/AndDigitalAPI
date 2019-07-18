package apiPhone.control;

import apiPhone.control.CustomerController;
import apiPhone.control.ErrorHandling;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    ObjectMapper mapper;

    @Mock
    ErrorHandling errorHandling;

    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCustomerDetails_test() {
        assertThat("No value returned",customerController.getAllCustomerDetails(),notNullValue());
    }

    @Test
    public void getCustomerDetail_test() throws IOException, JSONException {
        assertThat("No value returned",customerController.getCustomerDetails("100"),notNullValue());
    }

    @Test
    public void getCustomerDetail_errorCheck() throws IOException, JSONException {
        when(errorHandling.customError(ArgumentMatchers.any(LocalDateTime.class),ArgumentMatchers.any(String.class))).thenReturn("[Date/Time= "
                + LocalDateTime.now()
                + ", Error Message= "
                + "testCheck" + "]");
        assertThat("No value returned",customerController.getCustomerDetails("101"),notNullValue());
    }

    @Test
    public void activatePhone_test() {
        assertThat("No value returned",customerController.activatePhone("100","123","321"),notNullValue());
    }

    @Test
    public void activatePhone_errorCheck() {
        when(errorHandling.customError(ArgumentMatchers.any(LocalDateTime.class),ArgumentMatchers.any(String.class))).thenReturn("[Date/Time= "
                + LocalDateTime.now()
                + ", Error Message= "
                + "testCheck" + "]");
        assertThat("No value returned",customerController.activatePhone("101","123","321"),notNullValue());
    }


}
