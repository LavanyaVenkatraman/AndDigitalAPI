package apiPhone;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

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
    public void activatePhone_test() {
        assertThat("No value returned",customerController.activatePhone("100","123","321"),notNullValue());
    }


}
