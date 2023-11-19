import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.*;

public class ValidateAddressTest {
    
    private PlaceOrderController placeOrderController;

    
    /** 
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
        "Lê Thanh Nghị Hai Bà Trưng Hà Nội, true",
        "@Hà Nội, false",
        "Bách Khoa Hà Nội, true",
        "123 Lê Thanh Nghị, true"
    })

    public void test(String address, boolean expected) {
        boolean isValid = placeOrderController.validateAddress(address);
        assertEquals(expected, isValid);
    }
}
