package com.study.anyang.web;

import com.study.anyang.common.util.FileUtils;
import com.study.anyang.domain.ImageFile;
import com.study.anyang.service.ImageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Value("${upload.path}")
    private String uploadPath;

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadAjaxFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
        String filePath = FileUtils.calcPath(uploadPath);

        int index = 0;
        String imageFileIds = "";
        String fileExt = "";

        for(MultipartFile file : files) {
            fileExt = FileUtils.fileExt(file.getOriginalFilename());
            Long imageFileNo = imageService.uploadFileDb(filePath, fileExt);
            String imageFileId = String.valueOf(imageFileNo);

            if(index != 0) {
                imageFileIds += ",";
            }
            index++;

            FileUtils.uploadFile(filePath, imageFileId + "." + fileExt, file.getBytes());
            imageFileIds += imageFileId;
        }
        return new ResponseEntity<String>(imageFileIds, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping("/display/{itemId}")
    public ResponseEntity<byte[]> displayFile(@PathVariable Long itemId) throws Exception {
        ImageFile imageFile = imageService.getImageFile(itemId);
        return FileUtils.dataUrl(imageFile.getFullName(), String.valueOf(itemId), imageFile.getFileExt() );
    }

    @ResponseBody
    @RequestMapping("/download")
    public ResponseEntity<byte[]> downloadFile(String fullName) throws Exception {
        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();
        String fileName = "";

        try (
            InputStream in = new FileInputStream(uploadPath + fullName);
        ) {
            fileName = fullName.substring(fullName.indexOf("_") + 1);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String itemNo) {
        //@Todo itemNo로 경로 조회

        String fileName = "";
        new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();

        //@Todo itemNo로 파일 DB 삭제

        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }

}
