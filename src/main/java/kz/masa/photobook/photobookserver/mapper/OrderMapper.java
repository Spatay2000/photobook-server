package kz.masa.photobook.photobookserver.mapper;

import kz.masa.photobook.photobookserver.dto.OrderDTO;
import kz.masa.photobook.photobookserver.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mappings({
    })
    OrderDTO entityToApi(Order entity);

    @Mappings({
    })
    Order apiToEntity(OrderDTO api);
}
