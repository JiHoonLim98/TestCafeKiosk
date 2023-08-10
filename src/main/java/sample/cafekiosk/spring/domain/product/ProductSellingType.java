package sample.cafekiosk.spring.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @since       2023.08.03
 * @author      jerry
 * @description product selling type
 **********************************************************************************************************************/
@Getter
@RequiredArgsConstructor
public enum ProductSellingType {

    SELLING("판매중"),
    HOLD("판매보류"),
    STOP("판매중지");

    private final String text;

    public static List<ProductSellingType> forDisplay() {
        return Arrays.asList(SELLING, HOLD);
    }
}
