package apiPhone.entity;

import apiPhone.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        List<String> phoneNumber = new ArrayList<>();
        phoneNumber.add("123");
        phoneNumber.add("456");
        customer.setPhoneNumber(phoneNumber);
        assertThat("Wrong Customer Name returned",customer.getPhoneNumber(),equalTo(phoneNumber));
    }

}
