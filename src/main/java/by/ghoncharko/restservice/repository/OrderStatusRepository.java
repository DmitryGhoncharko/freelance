package by.ghoncharko.restservice.repository;

import by.ghoncharko.restservice.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, BigInteger> {
    Page<OrderStatus> findAll(Pageable pageable);

    Optional<OrderStatus> findByOrderStatusName(String orderStatusName);

    Optional<OrderStatus> findById(BigInteger id);
}
