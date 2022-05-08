package kz.masa.photobook.photobookserver.controller;

import kz.masa.photobook.photobookserver.model.Order;
import kz.masa.photobook.photobookserver.service.impl.OrderService;
import kz.masa.photobook.photobookserver.util.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController extends CommonService {

    private final OrderService orderService;

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return builder(success(orderService.getOrderById(id)));
    }

    @RequestMapping(value = "/read/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAll() {
        return builder(success(orderService.getAllOrders()));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> create(@RequestPart Order order, @RequestPart("file") MultipartFile receipt, @RequestHeader(value = "email", required = false) String email) {
        return builder(success(orderService.createOrder(order, receipt, email)));
    }
}