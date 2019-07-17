package apiPhone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javax.inject.Inject;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
public class CustomerController {

    @Inject
    ErrorHandling errorHandling;

    private Customer[] customerList=new Customer[2];
    String[] customer1Phone = new String[]{"123","456","789"};
    String[] customer2Phone = new String[]{"111","222","333"};
    String jsonStr;

    @RequestMapping("/allCustomerDetails")
    public String getCustomerDetails() throws IOException, JSONException {
        return createCustomerList();
    }

    @RequestMapping("/customer")
        public String getCustomerDetails(@RequestParam(value = "customerID") String id) throws IOException, JSONException {
        int i;
        createCustomerList();
        for(i = 0 ; i<customerList.length;i++)
        {
            if(id.equals(customerList[i].getCustomerId())){
                try {
                    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                    jsonStr = mapper.writeValueAsString(customerList[i]);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                return jsonStr;
            }
        }
        return errorHandling.customError(LocalDateTime.now(),"CustomerID not found");
    }

    @RequestMapping("/activatePhone")
    public String activatePhone(@RequestParam(value = "customerID") String customerId, @RequestParam(value = "oldPhone") String oldPhone,@RequestParam(value = "newPhone") String newPhone) throws IOException, JSONException {
        createCustomerList();
        int i = 0;
        for (i = 0; i < customerList.length; i++) {
            if (customerId.equals(customerList[i].getCustomerId())) {
                if (Arrays.asList(customerList[i].getPhoneNumber()).contains(oldPhone)) {
                    customerList[i].getPhoneNumber()[Arrays.asList(customerList[i].getPhoneNumber()).indexOf(oldPhone)] = newPhone;
                    try {
                        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                        jsonStr = mapper.writeValueAsString(customerList[i]);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    return jsonStr;
                }
                }
            }
        return errorHandling.customError(LocalDateTime.now(),"Record not found. Please try with different values");
    }

    private String createCustomerList() throws IOException, JSONException {
        customerList[0] = new Customer("100", "Lavanya",customer1Phone);
        customerList[1] = new Customer("200", "Ramesh",customer2Phone);
        try {
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            jsonStr = mapper.writeValueAsString(customerList);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return jsonStr;
    }
}

//                String[] phoneList = new String[customerList[i].getPhoneNumber().length];
//                for (j = 0; j < customerList[i].getPhoneNumber().length; j++) {
//                    phoneList[j] = customerList[i].getPhoneNumber()[j];
//                }
//                    if (oldPhone.equals(phoneList[j])) {
//                        phoneList[j] = newPhone;
//                        customerList[i].getPhoneNumber()[j] = phoneList[j];
//                    }