package sample.cafekiosk.spring.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * @since       2023.08.03
 * @author      jerry
 * @description product type
 **********************************************************************************************************************/
@Getter
@RequiredArgsConstructor
public enum ProductType {

    HANDMADE("제조 음료"),
    BOTTLE("병 음료"),
    BAKERY("베이커리");

    private final String text;

    public static boolean containsStockType(ProductType type) {
        return Arrays.asList(BOTTLE, BAKERY).contains(type);
    }
}
