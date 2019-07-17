package apiPhone;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.theInstance;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCustomerDetails_test() throws IOException, JSONException {
        assertThat("No value returned",customerController.getCustomerDetails(),notNullValue());
    }

    @Test
    public void getCustomer_test() throws IOException, JSONException {
        assertThat("No value returned",customerController.getCustomerDetails("100"),notNullValue());
    }

    @Test
    public void activatePhone_test() throws IOException, JSONException {
        assertThat("No value returned",customerController.activatePhone("100","123","321"),notNullValue());
    }


}
