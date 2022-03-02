package kz.masa.photobook.photobookserver.controller;

import kz.masa.photobook.photobookserver.model.FileStorage;
import kz.masa.photobook.photobookserver.service.impl.FileStorageService;
import kz.masa.photobook.photobookserver.util.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file-storage")
@AllArgsConstructor
public class FileStorageController extends CommonService {

    private final FileStorageService fileStorageService;

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return builder(success(fileStorageService.getFileStorageById(id)));
    }

    @RequestMapping(value = "/read/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAll() {
        return builder(success(fileStorageService.getAllFileStorages()));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> add(@RequestPart("file") MultipartFile multipartFile) {
        return builder(success(fileStorageService.addFileStorage(multipartFile)));
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        fileStorageService.removeFileStorageById(id);
        return builder(success("success"));
    }
}
