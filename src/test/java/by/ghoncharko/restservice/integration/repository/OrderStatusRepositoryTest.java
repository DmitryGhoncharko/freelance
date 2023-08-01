package by.ghoncharko.restservice.integration.repository;

import by.ghoncharko.restservice.entity.OrderStatus;
import by.ghoncharko.restservice.repository.OrderStatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@DataJpaTest(properties = {
        "spring.test.database.replace=NONE",
        "spring.datasource.url=jdbc:tc:postgresql:12:///springboot"
})
@Sql("test/sql/initOrderStatus.sql")
class OrderStatusRepositoryTest {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Test
    void testInsertInOrderStatusRepository(){
        OrderStatus orderStatus = OrderStatus.builder().
                orderStatusName("test6").
                build();
        OrderStatus orderStatusAfterSave = orderStatusRepository.save(orderStatus);
        Optional<OrderStatus> orderStatusFromDatabaseOptional = orderStatusRepository.findByOrderStatusName("test6");
        OrderStatus orderStatusFromDatabase = orderStatusFromDatabaseOptional.orElseThrow(RuntimeException::new);
        Assertions.assertEquals(orderStatusAfterSave,orderStatusFromDatabase);
    }
}
