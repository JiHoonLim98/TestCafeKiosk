package sample.cafekiosk.spring.api.service.order.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @since       2023.08.04
 * @author      jerry
 * @description order create request
 **********************************************************************************************************************/
@Getter
@NoArgsConstructor
public class OrderCreateServiceRequest {

    private List<String> productsNumbers;

    @Builder
    public OrderCreateServiceRequest(List<String> productsNumbers) {
        this.productsNumbers = productsNumbers;
    }
}
