package sample.cafekiosk.unit.beverage;

/**
 * @since       2023.08.02
 * @author      jerry
 * @description latte
 **********************************************************************************************************************/
public class Latte implements Beverage{


    @Override
    public String getName() {
        return "라떼";
    }

    @Override
    public int getPrice() {
        return 3000;
    }
}
