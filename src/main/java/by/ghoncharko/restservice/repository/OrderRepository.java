package by.ghoncharko.restservice.repository;

import by.ghoncharko.restservice.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, BigInteger> {
    Page<Order> findAll(Pageable pageable);

    Optional<Order> findById(BigInteger id);
    @Query("SELECT ord from Order ord left join fetch ord.orderStatus")
    Page<Order> findAllWithoutLazy(Pageable pageable);
}
