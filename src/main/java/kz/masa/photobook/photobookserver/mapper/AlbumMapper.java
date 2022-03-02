package kz.masa.photobook.photobookserver.mapper;

import kz.masa.photobook.photobookserver.dto.AlbumDTO;
import kz.masa.photobook.photobookserver.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    @Mappings({
    })
    AlbumDTO entityToApi(Album entity);

    @Mappings({
    })
    Album apiToEntity(AlbumDTO api);
}
