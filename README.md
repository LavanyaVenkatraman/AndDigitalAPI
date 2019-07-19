# AndDigitalAPI
Technical Test - AND Digital

API Specifications (also covered in swagger-ui): http://localhost:8080/swagger-ui.html#/

API Description: Gets the details of the customers 
API Endpoint : http://localhost:8080/api/customers/allCustomers 
Request URL Sample: http://localhost:8080/api/customers/allCustomers 
Parameters : None

API Description: Gets specific customer detail 
API Endpoint : http://localhost:8080/api/customers/customerId={customerId} 
Request URL Sample: http://localhost:8080/api/customers/customerDetail?customerId=100 
Parameters Required : customerId

API Description: Modifies a customer's phone number 
API Endpoint: http://localhost:8080/api/customers/activatePhone?customerID={customerId}&oldPhone={oldPhone}&newPhone={newPhone} 
Request URL Sample : http://localhost:8080/api/customers/activatePhone?customerID=100&oldPhone=123&newPhone=321 
Parameters Required : customerId,oldPhonenumber,NewPhoneNumber
