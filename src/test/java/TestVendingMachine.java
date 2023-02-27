import Exceptions.*;
import Models.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static Enums.Coins.HALF_ONE_COIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestVendingMachine {




    private VendingMachine vendingMachine;

    @BeforeEach
    public void setUp() {
        vendingMachine = new VendingMachine(10, 10, 10, 10, 10);
    }

    @Test
    public void testValidPurchase() throws Exception {
        String input = "1\n5\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        vendingMachine.start();

        assertEquals(9, vendingMachine.getMirendinaCount());
        assertEquals(10, vendingMachine.getTangoCount());
        assertEquals(10, vendingMachine.getSnickersCount());
        assertEquals(0, vendingMachine.getUserBalance());
    }

    @Test
    public void testInvalidCoin() {
        String input = "4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThrows(InvalidCoinExeption.class, () -> vendingMachine.start());
    }



    @Test
    public void testInvalidChoice() {
        String input = "5\n7\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThrows(InvalidChoiceException.class, () -> vendingMachine.start());
    }

    @Test
    public void testInsufficientUserBalance() {
        String input = "2\n1\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThrows(InsufficientUserBalance.class, () -> vendingMachine.start());
    }

    @Test
    public void testExchangeNotAvailable() {
        vendingMachine.reset(5,5,5,5,5,0,0,0,10,5);
        String input = "2\n5\n2\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThrows(ExchangeException.class, () -> vendingMachine.start());
    }

        @Test
    public void testOutOfStock() {
        vendingMachine.setMirendinaCount(0);

        String input = "1\n5\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThrows(OutOfStockException.class, () -> vendingMachine.start());
    }

}
