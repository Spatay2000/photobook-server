package kz.masa.photobook.photobookserver.controller;

import kz.masa.photobook.photobookserver.enums.AlbumStatus;
import kz.masa.photobook.photobookserver.enums.AlbumType;
import kz.masa.photobook.photobookserver.model.Album;
import kz.masa.photobook.photobookserver.service.impl.AlbumService;
import kz.masa.photobook.photobookserver.util.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/album")
@AllArgsConstructor
public class AlbumController extends CommonService {

    private final AlbumService albumService;

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return builder(success(albumService.getAlbumById(id)));
    }

    @RequestMapping(value = "/read/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAll() {
        return builder(success(albumService.getAllAlbums()));
    }

    @RequestMapping(value = "/read/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllFiltered(@RequestParam AlbumStatus albumStatus) {
        return builder(success(albumService.getAllFiltered(albumStatus)));
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> publish(@RequestBody Album album, @RequestHeader(value = "email", required = false) String email) {
        return builder(success(albumService.publishAlbum(album, email)));
    }

    @RequestMapping(value = "/draft", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> draft(@RequestBody Album album, @RequestHeader(value = "email", required = false) String email) {
        return builder(success(albumService.draftAlbum(album, email)));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> create(@RequestBody Album album, @RequestHeader(value = "email", required = false) String email) {
        return builder(success(albumService.createAlbum(album, email)));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> update(@RequestBody Album album, @RequestHeader(value = "email", required = false) String email) {
        return builder(success(albumService.updateAlbum(album, email)));
    }

    @RequestMapping(value = "/upload-files", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFiles(@RequestPart("file") List<MultipartFile> multipartFiles, @RequestParam Long albumId) {
        return builder(success(albumService.uploadFiles(multipartFiles, albumId)));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id, @RequestHeader(value = "email", required = false) String email) {
        albumService.deleteAlbumById(id);
        return builder(success("success"));
    }
}