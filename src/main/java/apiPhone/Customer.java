package apiPhone;


public class Customer {

    private String customerId;
    private String customerName;
    private String[] phoneNumber;

   public Customer(String customerId, String customerName,String[] phoneNumber){
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }

    public String[] getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString()
    {
        return "[customerId="
                + customerId
                + ", customerName="
                + customerId
                + ", phoneNumber="
                + phoneNumber + "]";
    }
}
