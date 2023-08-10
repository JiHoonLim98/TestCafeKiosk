package sample.cafekiosk.spring.api.service.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingType;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("신규 상품을 등록. 상품번호는 최근 상품의 상품번호 + 1 이다.")
    void createProduct(){
        // given
        Product product = createProduct("001", ProductType.HANDMADE, ProductSellingType.SELLING, "아메리카노", 2000);
        productRepository.save(product);

        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(ProductType.HANDMADE)
                .sellingType(ProductSellingType.SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        // when
        ProductResponse productResponse = productService.createProduct(request);

        // then
        assertThat(productResponse)
                .extracting("productNumber", "type", "sellingType", "price", "name")
                .contains("002", ProductType.HANDMADE, ProductSellingType.SELLING, 5000, "카푸치노");

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2)
                .extracting("productNumber", "type", "sellingType", "price", "name")
                .containsExactlyInAnyOrder(
                        tuple("001", ProductType.HANDMADE, ProductSellingType.SELLING, 2000, "아메리카노"),
                        tuple("002", ProductType.HANDMADE, ProductSellingType.SELLING, 5000, "카푸치노")
                );
    }

    @Test
    @DisplayName("신규 상품을 등록. 상품이 하나도 없는 경우")
    void createProductWhenProductsIsEmpty(){
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(ProductType.HANDMADE)
                .sellingType(ProductSellingType.SELLING)
                .name("카푸치노")
                .price(5000)
                .build();

        // when
        ProductResponse productResponse = productService.createProduct(request);

        // then
        assertThat(productResponse)
                .extracting("productNumber", "type", "sellingType", "price", "name")
                .contains("001", ProductType.HANDMADE, ProductSellingType.SELLING, 5000, "카푸치노");

        List<Product> products = productRepository.findAll();
        assertThat(products)
                .extracting("productNumber", "type", "sellingType", "price", "name")
                .containsExactlyInAnyOrder(
                        tuple("001", ProductType.HANDMADE, ProductSellingType.SELLING, 5000, "카푸치노")
                );
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