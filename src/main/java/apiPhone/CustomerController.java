package apiPhone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javax.inject.Inject;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
public class CustomerController {

    @Inject
    ErrorHandling errorHandling;

    @Inject
    ObjectMapper mapper;

    private Customer[] customerList=new Customer[3];

    //Initialize the phone array for customers - This data can be fetched from the database(not used as part of this challenge)
    String[] customer1Phone = new String[]{"123","456","789"};
    String[] customer2Phone = new String[]{"111","222","333"};
    String[] customer3Phone = new String[]{"666","777"};
    String jsonStr;


//retrieve details of all customers along with their phone numbers
    @RequestMapping("/allCustomers")
    public String getCustomerDetails() {
        return createCustomerList();
    }

//retrieve details of a customer based on their customer id along with their phone numbers
    @RequestMapping("/customerDetail")
        public String getCustomerDetails(@RequestParam(value = "customerID") String id) throws IOException, JSONException {
        int i;
        createCustomerList();
        for(i = 0 ; i<customerList.length;i++)
        {
            //if the request parameter matches the customer id in array. This can be fetched using SQL query
            if(id.equals(customerList[i].getCustomerId())){
                try {
                    mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                    jsonStr = mapper.writeValueAsString(customerList[i]);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                return jsonStr;
            }
        }

        //Error when customer ID is not found
        return errorHandling.customError(LocalDateTime.now(),"CustomerID not found");
    }


/*modifies phone number of a customer based on their customer Id, Old phone number.
When the phone number is modified, the changes remain until a session is active.
If the program is re-run the changes are lost. This will be avoided when we use a permanent storage
such as database/file.
*/
    @RequestMapping("/activatePhone")
    public String activatePhone(@RequestParam(value = "customerID") String customerId, @RequestParam(value = "oldPhone") String oldPhone,@RequestParam(value = "newPhone") String newPhone)  {
        createCustomerList();
        int i = 0;
        for (i = 0; i < customerList.length; i++) {

            //if the customer array contains the customerID request parameter
            if (customerId.equals(customerList[i].getCustomerId())) {

                //if oldPhone request parameter matches the customer phone number then it gets replaced
                if (Arrays.asList(customerList[i].getPhoneNumber()).contains(oldPhone)) {
                    customerList[i].getPhoneNumber()[Arrays.asList(customerList[i].getPhoneNumber()).indexOf(oldPhone)] = newPhone;

                    try {
                         mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                        jsonStr = mapper.writeValueAsString(customerList[i]);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    return jsonStr;
                }
                }
            }

        //If customerID or phone number is not found. The oldPhone should correspond to customerID
        return errorHandling.customError(LocalDateTime.now(),"Record not found. Please try with different values");
    }

    //Initializing the customer object - This data can be fetched from telecom database(not used as part of this challenge)
    private String createCustomerList() {
        customerList[0] = new Customer("100", "Lavanya",customer1Phone);
        customerList[1] = new Customer("200", "Ramesh",customer2Phone);
        customerList[2]= new Customer("300", "Nithya",customer3Phone);
        try {
            mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            jsonStr = mapper.writeValueAsString(customerList);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return jsonStr;
    }
}