package sample.cafekiosk.spring.domain.orderproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @since       2023.08.08
 * @author      jerry
 * @description order product repository
 **********************************************************************************************************************/
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
