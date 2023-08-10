package sample.cafekiosk.spring.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @since       2023.08.08
 * @author      jerry
 * @description stock repository
 **********************************************************************************************************************/
@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAllByProductNumberIn(List<String> stockProductNumbers);
}
