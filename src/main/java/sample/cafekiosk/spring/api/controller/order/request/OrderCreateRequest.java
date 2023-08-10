package sample.cafekiosk.spring.api.controller.order.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.api.service.order.request.OrderCreateServiceRequest;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @since       2023.08.04
 * @author      jerry
 * @description order create request
 **********************************************************************************************************************/
@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    @NotEmpty(message = "상품 번호 리스트는 필수다.")
    private List<String> productsNumbers;

    @Builder
    public OrderCreateRequest(List<String> productsNumbers) {
        this.productsNumbers = productsNumbers;
    }

    public OrderCreateServiceRequest toServiceRequest() {
        return OrderCreateServiceRequest.builder()
                .productsNumbers(productsNumbers)
                .build();
    }
}
