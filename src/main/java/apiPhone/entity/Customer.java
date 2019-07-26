/**
 * Contains customer object
 */
package apiPhone.entity;

import java.util.List;

/**
 * Class represents Customer Details
 */
public class Customer {

    private String customerId;
    private String customerName;
    private String address;
    private List<String> phoneNumber;
//private List<String> phoneNumber;
    /**
     *
     * @param customerId - Unique customer ID
     * @param customerName - Customer Name
     * @param phoneNumber - Phone numbers associated to a customer
     */
   public Customer(String customerId, String customerName,List<String> phoneNumber,String address){
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     *  gets customer phone number
     * @return
     */
//    public String[] getPhoneNumber() {
//        return phoneNumber;
//    }

    /**
     *  set customer phone number
     * @return
     */
//    public void setPhoneNumber(String[] phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }

    /**
     *  get customer ID
     * @return
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *  set customer ID
     *
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     *  get customer Name
     * @return - returns customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *  set customer Name
     *
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * gets customer's Address
     * @return - address
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets the customer's address
     * @param address - customer's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return - List of customer's phone number
     *
     */
    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber - Customer's list of phoneNumber
     */
    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
