package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.service.mail.MailService;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.order.OrderStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * @since       2023.08.09
 * @author      jerry
 * @description order statistics service
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderStatisticsService {

    private final OrderRepository orderRepository;
    private final MailService mailService;

    public void sendOrderStatisticsMail(LocalDate orderDate, String email) {
        List<Order> orders = orderRepository.findOrdersBy(
                orderDate.atStartOfDay(),
                orderDate.plusDays(1).atStartOfDay(),
                OrderStatus.PAYMENT_COMPLETED);

        int totalAmount = orders.stream().mapToInt(Order::getTotalPrice).sum();

        boolean result = mailService.sendMail("no-reply@cafekiosk.com",
                email,
                String.format("[매출통계] %s", orderDate),
                String.format("총 매출 합계는 %s 원입니다.", totalAmount));

        if (!result) {
            throw new IllegalArgumentException("매출 통계 메일 전송에 실패했습니다.");
        }
    }
}