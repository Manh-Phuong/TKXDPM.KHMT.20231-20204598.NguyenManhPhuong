package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import entity.cart.Cart;
import entity.cart.CartMedia;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import views.screen.popup.PopupScreen;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController{

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
    	
    }

    /**
     * Validates a phone number based on the following criteria:
     *
     * Invalid:
     * - Does not start with 0.
     * - Has fewer than 10 characters.
     * - Has more than 10 characters.
     * - Contains non-digit characters.
     *
     * Valid:
     * - Starts with 0 and has exactly 10 digits.
     *
     * @param phoneNumber The phone number to be validated.
     * @return true if the phone number is valid, false otherwise.
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        // Check if the phone number starts with 0
        if (!phoneNumber.startsWith("0")) {
            return false;
        }

        // Check the length of the phone number
        if (phoneNumber.length() != 10) {
            return false;
        }

        // Check if all characters are digits
        if (!Pattern.matches("\\d+", phoneNumber)) {
            return false;
        }

        return true;
    }

    /**
     * Validates a name based on the following criteria:
     *
     * Invalid:
     * - Contains special characters or numbers.
     * - Is null.
     * - Has more than 50 characters.
     *
     * Valid:
     * - Contains only letters (a-z and A-Z).
     * - Is not null.
     * - Has a character count less than or equal to 50.
     *
     * @param name The name to be validated.
     * @return true if the name is valid, false otherwise.
     */
    public boolean validateName(String name) {
        // Check if the name is null
        if (name == null) {
            return false;
        }

        // Check if the name contains only letters
        if (!name.matches("[a-zA-Z]+")) {
            return false;
        }

        // Check if the length of the name is within the valid range
        if (name.length() > 50) {
            return false;
        }

        return true;
    }

    /**
     * Validates an address based on the following criteria:
     *
     * Invalid:
     * - Contains special characters (~, !, @, #, $, %, ...).
     * - Is null.
     * - Has more than 250 characters.
     *
     * Valid:
     * - Does not contain special characters.
     * - Is not null.
     * - Has a character count less than or equal to 250.
     *
     * @param address The address to be validated.
     * @return true if the address is valid, false otherwise.
     */
    public boolean validateAddress(String address) {
        // Check if the address is null
        if (address == null) {
            return false;
        }

        // Check if the address contains special characters
        if (address.matches(".*[~!@#$%^&*()_+{}|\":<>?].*")) {
            return false;
        }

        // Check if the length of the address is within the valid range
        if (address.length() > 250) {
            return false;
        }

        return true;
    }

    /**
     * Validates an address based on the following criteria:
     *
     * Invalid:
     * - Contains special characters (~, !, @, #, $, %, ...).
     * - Is null.
     * - Has more than 250 characters.
     *
     * Valid:
     * - Does not contain special characters.
     * - Is not null.
     * - Has a character count less than or equal to 250.
     *
     * @param Delivery Instructions The Delivery Instructions to be validated.
     * @return true if the Delivery Instructions is valid, false otherwise.
     */
    public boolean validateDeliveryInstructions(String  instrucions) {
        // Check if the address is null
        if (instrucions == null) {
            return false;
        }

        // Check if the address contains special characters
        if (instrucions.matches(".*[~!@#$%^&*()_+{}|\":<>?].*")) {
            return false;
        }

        // Check if the length of the address is within the valid range
        if (instrucions.length() > 250) {
            return false;
        }

        return true;
    }
    

    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
