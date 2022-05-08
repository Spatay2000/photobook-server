package kz.masa.photobook.photobookserver.service;

import kz.masa.photobook.photobookserver.dto.OrderDTO;
import kz.masa.photobook.photobookserver.model.Order;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IOrderService {
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getAllOrders();
    OrderDTO createOrder(Order order, MultipartFile receipt, String email);
    OrderDTO updateOrder(Order order, String email);
    void deleteOrderById(Long id);
}
