package by.ghoncharko.restservice.integration.repository;

import by.ghoncharko.restservice.entity.OrderStatus;
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

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class OrderStatusRepositoryTest {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Test
    void testInsertInOrderStatusRepository() {
        OrderStatus orderStatus = OrderStatus.builder().orderStatusName("test6").build();
        OrderStatus orderStatusAfterSave = orderStatusRepository.save(orderStatus);
        Optional<OrderStatus> orderStatusFromDatabaseOptional = orderStatusRepository.findByOrderStatusName("test6");
        OrderStatus orderStatusFromDatabase = orderStatusFromDatabaseOptional.orElseThrow(RuntimeException::new);
        Assertions.assertEquals(orderStatusAfterSave, orderStatusFromDatabase);
    }

    @Test
    void testUpdateInOrderStatusRepository() {
        Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findByOrderStatusName("removed");
        OrderStatus orderStatusFromOptional = orderStatusOptional.orElseThrow(RuntimeException::new);
        OrderStatus orderStatusForUpdate = OrderStatus.builder()
                .id(orderStatusFromOptional.getId())
                .orderStatusName("test")
                .build();
        OrderStatus orderStatusAfterUpdate = orderStatusRepository.save(orderStatusForUpdate);
        Assertions.assertEquals(orderStatusForUpdate, orderStatusAfterUpdate);
    }

    @Test
    void testDeleteByIdInOrderStatusRepository() {
        orderStatusRepository.deleteById(BigInteger.valueOf(4));
        Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(BigInteger.valueOf(4));
        if (orderStatusOptional.isPresent()) {
            Assertions.fail();
        }
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void testFindAllWithoutLazy() {
        Page<OrderStatus> orderStatusPage = orderStatusRepository.findAllWithoutLazy(Pageable.ofSize(1));
        orderStatusPage.getContent().forEach(orderStatus -> {
            assertDoesNotThrow(() -> orderStatus.getOrders().size());
        });
    }
}
