package by.ghoncharko.restservice.integration.repository;

import by.ghoncharko.restservice.entity.Order;
import by.ghoncharko.restservice.entity.OrderStatus;
import by.ghoncharko.restservice.repository.OrderRepository;
import by.ghoncharko.restservice.repository.OrderStatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Test
    void testInsertIntoOrderRepository() {
        Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findByOrderStatusName("removed");
        OrderStatus orderStatus = orderStatusOptional.orElseThrow(RuntimeException::new);
        Order order = Order.builder()
                .orderName("test")
                .orderDescription("test")
                .orderCost(22.2)
                .orderStatus(orderStatus)
                .dateCreated(new Timestamp(new Date().getTime()))
                .lastUpdateDate(new Timestamp(new Date().getTime()))
                .build();
        Order orderAfterSave = orderRepository.save(order);
        Optional<Order> orderOptional = orderRepository.findById(orderAfterSave.getId());
        Order orderFromOptional = orderOptional.orElseThrow(RuntimeException::new);
        Assertions.assertEquals(orderFromOptional, orderAfterSave);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void testFindAllWithoutLazy() {
        Page<Order> orderPage = orderRepository.findAllWithoutLazy(Pageable.ofSize(1));
        orderPage.getContent().forEach(order -> {
            assertDoesNotThrow(() -> order.getOrderStatus().getOrderStatusName());
        });
    }
}
