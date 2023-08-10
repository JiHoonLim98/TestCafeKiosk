package sample.cafekiosk.spring.api.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingType;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.* ;

@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("원하는 판매상태를 가진 상품 조회")
    void findAllBySellingTypeIn(){
        // given
        Product product1 = createProduct("001", ProductType.HANDMADE, ProductSellingType.SELLING, "아메리카노", 2000);
        Product product2 = createProduct("002", ProductType.HANDMADE, ProductSellingType.HOLD, "카페라떼", 3000);
        Product product3 = createProduct("003", ProductType.HANDMADE, ProductSellingType.STOP, "팥빙수", 4000);
        productRepository.saveAll(Arrays.asList(product1, product2, product3));

        // when
        List<Product> products = productRepository.findAllBySellingTypeIn(Arrays.asList(ProductSellingType.SELLING, ProductSellingType.HOLD));

        // then
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingType")
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", ProductSellingType.SELLING),
                        tuple("002", "카페라떼", ProductSellingType.HOLD)
                );

    }

    @Test
    @DisplayName("가장 마지막으로 저장한 상품의 상품 번호를 읽어옴")
    void findLatestProductNumber(){
        // given
        String targetProductNumber = "003";

        Product product1 = createProduct("001", ProductType.HANDMADE, ProductSellingType.SELLING, "아메리카노", 2000);
        Product product2 = createProduct("002", ProductType.HANDMADE, ProductSellingType.HOLD, "카페라떼", 3000);
        Product product3 = createProduct("003", ProductType.HANDMADE, ProductSellingType.STOP, "팥빙수", 4000);
        productRepository.saveAll(Arrays.asList(product1, product2, product3));

        // when
        String latestProductNumber = productRepository.findLatestProductNumber();

        // then
        assertThat(latestProductNumber).isEqualTo(targetProductNumber);
    }

    @Test
    @DisplayName("가장 마지막으로 저장한 상품의 상품 번호가 없으면 Null 반환")
    void findLatestProductNumberWhenProductIsEmpty(){
        // given

        // when
        String latestProductNumber = productRepository.findLatestProductNumber();

        // then
        assertThat(latestProductNumber).isNull();
    }
    private Product createProduct(String productNumber, ProductType type, ProductSellingType sellingStatus, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingType(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}