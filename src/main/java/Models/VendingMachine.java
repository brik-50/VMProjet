package Models;

import Exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Enums.Coins.*;

public class VendingMachine {


    private int mirendinaCount;
    private int tangoCount;
    private int kitkatCount;
    private int snickersCount;
    private int milkaCount;
    private int halfMadCount;
    private int oneMadCount;

    public int getHalfMadCount() {
        return halfMadCount;
    }

    public int getOneMadCount() {
        return oneMadCount;
    }

    public int getTwoMadCount() {
        return twoMadCount;
    }

    public int getFiveMadCount() {
        return fiveMadCount;
    }

    public int getTenMadCount() {
        return tenMadCount;
    }

    private int twoMadCount;
        private int fiveMadCount;
        private int tenMadCount;
        private int userBalance;

        //  Getters
        public int getUserBalance() {
        return userBalance;
        }

        public int getMirendinaCount() {
        return mirendinaCount;
    }

        public int getTangoCount() {
        return tangoCount;
    }

        public int getKitkatCount() {
        return kitkatCount;
    }

        public int getSnickersCount() {
        return snickersCount;
    }

        public int getMilkaCount() {
        return milkaCount;
    }

       //  Setters
        public void setMirendinaCount(int mirendinaCount) {
            this.mirendinaCount = mirendinaCount;
        }

        public void setTangoCount(int tangoCount) {
            this.tangoCount = tangoCount;
        }

        public void setKitkatCount(int kitkatCount) {
            this.kitkatCount = kitkatCount;
        }

        public void setSnickersCount(int snickersCount) {
            this.snickersCount = snickersCount;
        }

        public void setMilkaCount(int milkaCount) {
            this.milkaCount = milkaCount;
        }

        public VendingMachine() {

        }

            public VendingMachine(int mirendinaCount, int tangoCount, int kitkatCount, int snickersCount, int milkaCount) {
                this.mirendinaCount = mirendinaCount;
                this.tangoCount = tangoCount;
                this.kitkatCount = kitkatCount;
                this.snickersCount = snickersCount;
                this.milkaCount = milkaCount;
                halfMadCount = 0;
                oneMadCount = 0;
                twoMadCount = 0;
                fiveMadCount = 0;
                tenMadCount = 0;
                userBalance = 0;
            }

            public void start() throws InsufficientUserBalance, InsufficientBalanseToBuyProduct, OutOfStockException, InvalidChoiceException, InvalidCoinExeption, ExchangeException {
                Scanner scanner = new Scanner(System.in);
                List<Double> listUserCoin=new ArrayList<>();
                int i=0;


                System.out.println("how much of coin you will put in the machine ");
                int numberOfCoin=scanner.nextInt();
                while (numberOfCoin>0){
                    System.out.println("Please insert a coin (0.5/1/2/5/10 MAD):");
                    double a=scanner.nextDouble();
                    listUserCoin.add(a);
                    userBalance+=a;
                    numberOfCoin--;

                }
                for (Double  coin : listUserCoin ){
                    insertCoin(coin ,scanner);
                }
                System.out.println("user balance is :"+userBalance +" MAD");

                // Check if user has enough balance to purchase a product
                if(userBalance>= 3)selectProduct(scanner);
                else{
                    throw new InsufficientUserBalance("Insufficient balance you have to be more than 3MAD to Purchase") ;
                }

            }



            private void insertCoin(double coin, Scanner scanner) throws InsufficientUserBalance, InsufficientBalanseToBuyProduct, OutOfStockException, InvalidChoiceException, InvalidCoinExeption {
                if (coin == HALF_ONE_COIN.getValue()) {
                    oneMadCount++;
                } else if (coin == ONE_COIN.getValue()) {
                    oneMadCount++;
                } else if (coin == TWO_COIN.getValue()) {
                    halfMadCount++;
                } else if (coin == FIVE_COIN.getValue()) {
                    fiveMadCount++;
                } else if (coin == TEN_COIN.getValue()) {
                    tenMadCount++;
                } else {
                    throw new InvalidCoinExeption("Invalid coin");
                }
            }

            private void selectProduct(Scanner scanner) throws InsufficientBalanseToBuyProduct, OutOfStockException, InvalidChoiceException, ExchangeException {
                System.out.println("Please select a product:");
                System.out.println("1. Mirendina -> 5 MAD");
                System.out.println("2. Tango     -> 3 MAD");
                System.out.println("3. KitKat    -> 13 MAD ");
                System.out.println("4. Snickers  -> 15 MAD");
                System.out.println("5. Milka     -> 26 MAD");
                System.out.println("6. Cancel your Request");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        dispenseProduct("Mirendina", 5, mirendinaCount);
                        break;
                    case 2:
                        dispenseProduct("Tango", 3, tangoCount);
                        break;
                    case 3:
                        dispenseProduct("KitKat", 13, kitkatCount);
                        break;
                    case 4:
                        dispenseProduct("Snickers", 15, snickersCount);
                        break;
                    case 5:
                        dispenseProduct("Milka", 26, milkaCount);
                        break;
                    case 6:
                        cancelRequest();
                        break;
                    default:
                        throw new InvalidChoiceException("Invalid choice");
                }
            }

            private void cancelRequest() {
                System.out.println("Request cancelled.");
                refund();
            }

            public void refund(){
                System.out.println("Returning coins :"+userBalance);
            }

            public void reset(int mirendinaCount, int tangoCount, int kitkatCount, int snickersCount,
                              int milkaCount, int halfMadCount, int oneMadCount, int twoMadCount, int fiveMadCount, int tenMadCount) {
                System.out.println("Resetting vending machine...\n");
                this.mirendinaCount=mirendinaCount ;
                this.tangoCount=tangoCount;
                this.kitkatCount =kitkatCount;
                this.snickersCount =snickersCount;
                this.milkaCount = milkaCount;
                this.halfMadCount =halfMadCount;
                this.oneMadCount = oneMadCount;
                this.twoMadCount = twoMadCount;
                this.fiveMadCount = fiveMadCount;
                this.tenMadCount = tenMadCount;
                userBalance = 0;
                System.out.println("Vending machine reset complete.\n");
            }

            private void dispenseProduct(String productName, int productCost, int productCount) throws InsufficientBalanseToBuyProduct, OutOfStockException, ExchangeException {
                if (productCount <= 0){
                    throw new OutOfStockException(productName + " is out of stock");
                }

                if (userBalance < productCost) {
                    throw new InsufficientBalanseToBuyProduct("Please insert more coins to purchase " + productName);
                }

                System.out.println("Dispensing " + productName);


                userBalance -= productCost;
                System.out.println("Quantity of : "+productName + " is "+decreaseQuantity(productName));
                if (userBalance > 0) {
                    System.out.println("Returning change: " + exchangeMoney(userBalance) );
                    userBalance = 0;
                }

                System.out.println(productName + " dispensed. Thank you for your purchase!");
            }

            public int decreaseQuantity(String name){
                switch (name){
                    case "Mirendina" :
                        mirendinaCount--;
                        return mirendinaCount;
                    case "Tango" :
                        tangoCount--;
                        return tangoCount;
                    case "KitKat" :
                        kitkatCount--;
                        return kitkatCount;
                    case "Snickers" :
                        snickersCount--;
                        return snickersCount;
                    case "Milka" :
                        milkaCount--;
                        return milkaCount;
                    default:
                        return 0;

                }


            }

            public String exchangeMoney(double amount) throws ExchangeException {
                int countCoinTen=0;
                int countCoinFive=0;
                int countCoinTwo=0;
                int countCoinOne=0;
                int countHalfCoin=0;


                while (amount>0){
                    if(amount >= TEN_COIN.getValue() && tenMadCount>0){
                        countCoinTen++;
                        amount=amount-10;
                    }else if(amount >= FIVE_COIN.getValue() && fiveMadCount>=amount/5){
                        System.out.println("\n number coin of five "+fiveMadCount);
                        countCoinFive++;
                        amount=amount-5;
                    }else if(amount >= TWO_COIN.getValue() && twoMadCount>=amount/2){
                        countCoinTwo++;
                        amount=amount-2;
                    }else if(amount >= ONE_COIN.getValue() && oneMadCount>amount){
                        countCoinOne++;
                        amount=amount-1;
                    }else if(amount >= HALF_ONE_COIN.getValue() && halfMadCount>amount*2){
                        countHalfCoin++;
                        amount=amount-0.5;
                    }else{
                        throw new ExchangeException("change is not available");
                    }

                }

                System.out.println( "coin exist in the machine is : Coin Of Ten ->"+tenMadCount
                        + "| Coin Of five ->"+fiveMadCount
                        + "| Coin Of two ->"+twoMadCount
                        +"| Coin Of One ->"+oneMadCount
                        +"| Coin Of halfCoin ->"+halfMadCount+"\n");


                return "change coin is "+userBalance+": Coin Of Ten ->"+countCoinTen
                        + "| Coin Of five ->"+countCoinFive
                        + "| Coin Of two ->"+countCoinTwo
                        +"| Coin Of One ->"+countCoinOne
                        +"| Coin Of halfCoin ->"+countHalfCoin+"\n";


            }








}
