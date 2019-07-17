package apiPhone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
@RunWith(MockitoJUnitRunner.class)
public class CustomerTest {

    @InjectMocks
    Customer customer;

    @Test
    public void customerId_test(){
        String customerId = "100";
        customer.setCustomerId(customerId);
        assertThat("Wrong Customer ID returned",customer.getCustomerId(),equalTo(customerId));
    }

    @Test
    public void customerName_test(){
        String customerName = "testName";
        customer.setCustomerName(customerName);
        assertThat("Wrong Customer Name returned",customer.getCustomerName(),equalTo(customerName));
    }

    @Test
    public void phoneNumber_test(){
        String[] phoneNumber = new String[]{"123","456"};
        customer.setPhoneNumber(phoneNumber);
        assertThat("Wrong Customer Name returned",customer.getPhoneNumber(),equalTo(phoneNumber));
    }

}
