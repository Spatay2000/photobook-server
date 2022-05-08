package kz.masa.photobook.photobookserver.repository;

import kz.masa.photobook.photobookserver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndDeletedAtIsNull(Long id);
    Order findOrderByIdAndDeletedAtIsNull(Long id);
    List<Order> findAllByDeletedAtIsNull();
}
