package application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoOrdinateTest {

    @Test
    void emptyStringIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new CoOrdinate(" "));
    }

    @Test
    void sixDecimalPlacesIsValid() {
       assertEquals("1.123456", new CoOrdinate("1.123456").toString());
    }

    @Test
    void sevenDecimalPlacesIsInValid() {
        assertThrows(IllegalArgumentException.class, () -> new CoOrdinate("1.1234567"));
    }


}