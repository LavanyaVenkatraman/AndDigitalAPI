/**
 * Control of the application
 */
package apiPhone.control;

import apiPhone.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javax.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer Controller contains endpoints to three APIs - getCustomer,getCustomerDetails,activatePhone
 */
@RestController
@RequestMapping(path="/api/customers/")
@Api(value="TelecomAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Inject
    ErrorHandling errorHandling;

    @Inject
    ObjectMapper mapper;

    private Customer[] customerList=new Customer[3];

    //Initialize the phone list for customers - This data can be fetched from the database(not used as part of this challenge)
    private List<String> customer1Phone = new ArrayList<>();
    private List<String> customer2Phone = new ArrayList<>();
    private List<String> customer3Phone = new ArrayList<>();
    private String jsonStr;


    private void setUpCustomerPhone()
    {
        customer1Phone.clear();
        customer1Phone.add("123");
        customer1Phone.add("456");
        customer1Phone.add("789");
        customer2Phone.clear();
        customer2Phone.add("111");
        customer2Phone.add("222");
        customer2Phone.add("333");
        customer3Phone.clear();
        customer3Phone.add("666");
        customer3Phone.add("777");
    }

    /**
     * Getting details of all customers
     */
    @RequestMapping(path="/allCustomers",method = RequestMethod.GET)
    @ApiOperation("Get details of all customers")
    public String getAllCustomerDetails() {
        return createCustomerList();
    }

    /**
     * Retrieve details of a customer based on their customer id along with their phone numbers
     */
    @RequestMapping(path = "/customerDetail", method = RequestMethod.GET)
    @ApiOperation("Gets specific customer detail")
    public String getCustomerDetails(@RequestParam(value = "customerId") String id) throws IOException, JSONException {
        int i;
        createCustomerList();
        for(i = 0 ; i<customerList.length;i++) {
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


/*modifies phone number of a customer based on their customer Id and Old phone number.
When the phone number is modified, the changes remain until a run session is active.
If the program is re-run the changes are lost. This will be avoided when we use a permanent storage
such as database/file.
*/
    /**
     *Modifies phone number of a customer based on their customer Id and Old phone number.
     */
    @RequestMapping(path = "/modifyPhone", method = RequestMethod.GET)
    @ApiOperation("Modify a phone number of a customer")
    public String modifyPhone(@RequestParam(value = "customerID") String customerId, @RequestParam(value = "oldPhone") String oldPhone,@RequestParam(value = "newPhone") String newPhone)  {
        createCustomerList();
        int i = 0;
        for (i = 0; i < customerList.length; i++) {

            //if the customer array contains the customerID request parameter
            if (customerId.equals(customerList[i].getCustomerId())) {

                //if oldPhone request parameter matches the customer phone number then it gets replaced
                if (customerList[i].getPhoneNumber().contains(oldPhone)) {
                    customerList[i].getPhoneNumber().remove(customerList[i].getPhoneNumber().indexOf(oldPhone));
                    customerList[i].getPhoneNumber().add(customerList[i].getPhoneNumber().size(),newPhone);
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

    @RequestMapping(path = "/activatePhone", method = RequestMethod.GET)
    @ApiOperation("Add a phone number of a customer")
    public String activatePhone(@RequestParam(value = "customerID") String customerId, @RequestParam(value = "newPhone") String newPhoneNumber)  {
       // setUp();
        createCustomerList();
        int i = 0;
        for (i = 0; i < customerList.length; i++) {

            //if the customer array contains the customerID request parameter
            if (customerId.equals(customerList[i].getCustomerId())) {

                //if oldPhone request parameter matches the customer phone number then it gets replaced
                if (!(customerList[i].getPhoneNumber()).contains(newPhoneNumber)) {

                    customerList[i].getPhoneNumber().add(customerList[i].getPhoneNumber().size(),newPhoneNumber);
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
        return errorHandling.customError(LocalDateTime.now(),"Phone Number exists already. Please try with different values");
    }

    //Initializing the customer object - This data can be fetched from telecom database(not used as part of this challenge)
    private String createCustomerList() {

        if(customer1Phone.size()==0||customer2Phone.size()==0||customer3Phone.size()==0) {
        setUpCustomerPhone();
        }
            customerList[0] = new Customer("100", "Lavanya", customer1Phone, "347 Moss Lane East");
            customerList[1] = new Customer("200", "Ramesh", customer2Phone, "Picadally Square");
            customerList[2] = new Customer("300", "Nithya", customer3Phone, "467 Grafton Street");
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