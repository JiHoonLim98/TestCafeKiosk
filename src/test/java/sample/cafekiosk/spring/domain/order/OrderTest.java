package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingType;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    @DisplayName("상품 리스트에서 추문의 총 금액을 계산한다.")
    void calculateTotalPrice(){
        // given
        List<Product> products = Arrays.asList(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );
        LocalDateTime registeredDateTime = LocalDateTime.now();

        // when
        Order order = Order.create(products, registeredDateTime);

        // then
        assertThat(order.getTotalPrice()).isEqualTo(3000);
    }

    @Test
    @DisplayName("주문 생성 시 주문 상태는 INIT 이다.")
    void init(){
        // given
        List<Product> products = Arrays.asList(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );
        LocalDateTime registeredDateTime = LocalDateTime.now();

        // when
        Order order = Order.create(products, registeredDateTime);

        // then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.INIT);
    }

    @Test
    @DisplayName("주문 생성 시 주문 등록 시간을 기록한다.")
    void registeredDateTime(){
        // given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        List<Product> products = Arrays.asList(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        // when
        Order order = Order.create(products, registeredDateTime);

        // then
        assertThat(order.getRegisteredDateTime()).isEqualTo(registeredDateTime);
    }


    private Product createProduct(String productNumber, int price) {
        return Product.builder()
                .type(ProductType.HANDMADE)
                .productNumber(productNumber)
                .price(price)
                .sellingType(ProductSellingType.SELLING)
                .name("이름")
                .build();
    }
}