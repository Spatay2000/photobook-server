package kz.masa.photobook.photobookserver.service.impl;

import kz.masa.photobook.photobookserver.dto.OrderDTO;
import kz.masa.photobook.photobookserver.mapper.OrderMapper;
import kz.masa.photobook.photobookserver.model.FileStorage;
import kz.masa.photobook.photobookserver.model.Order;
import kz.masa.photobook.photobookserver.repository.FileStorageRepository;
import kz.masa.photobook.photobookserver.repository.OrderRepository;
import kz.masa.photobook.photobookserver.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final FileStorageService fileStorageService;

    @Override
    public OrderDTO getOrderById(Long id) {
        OrderDTO response = orderMapper.entityToApi(orderRepository.findOrderByIdAndDeletedAtIsNull(id));
        response.setReceipt(fileStorageService.getFileStorageById(response.getReceiptId()));
        return response;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAllByDeletedAtIsNull().stream()
                .map(orderMapper::entityToApi)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO createOrder(Order order, MultipartFile receipt, String email) {
        FileStorage savedReceipt = fileStorageService.addFileStorage(receipt);
        order.setReceiptId(savedReceipt.getId());
        return orderMapper.entityToApi(orderRepository.save(order));
    }

    @Override
    public OrderDTO updateOrder(Order order, String email) {
        return null;
    }

    @Override
    public void deleteOrderById(Long id) {

    }
}
