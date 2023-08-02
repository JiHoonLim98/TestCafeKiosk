package sample.cafekiosk.unit.beverage;

/**
 * @since       2023.08.02
 * @author      jerry
 * @description americano
 **********************************************************************************************************************/
public class Americano implements Beverage{


    @Override
    public String getName() {
        return "아메리카노";
    }

    @Override
    public int getPrice() {
        return 2000;
    }
}
