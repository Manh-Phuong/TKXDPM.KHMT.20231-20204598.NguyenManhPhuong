import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import controller.*;

class ValidateNameTest {
    
    private PlaceOrderController placeOrderController;

    
    /** 
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
        " , false",
        "1, false",
        "ManhPhuong, true",
        "Nguyen Manh Phuong, true",
        "Nguyễn Mạnh Phương, false",
        "manhphuong@, false",
        "Manh Phuong 11, false"
    })

    public void test(String name, boolean expected) {
        boolean isValid = placeOrderController.validateName(name);
        assertEquals(expected, isValid);
    }

}
