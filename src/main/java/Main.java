import Exceptions.*;
import Models.VendingMachine;

public class Main {


    public static void main(String[] args) throws InsufficientUserBalance, InsufficientBalanseToBuyProduct, OutOfStockException, InvalidChoiceException, InvalidCoinExeption, ExchangeException {

        VendingMachine vm=new VendingMachine();


        vm.reset(5,5,5,5,5,0,0,0,10,5);
        vm.start();

    }
}
