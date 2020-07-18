package com.study.anyang.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

public class FileUtils {

    public static String fileExt(String orginalFileName) throws Exception {
        return orginalFileName.substring(orginalFileName.lastIndexOf(".") + 1);
    }

    public static void uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
        File target = new File(uploadPath, originalName);

        FileCopyUtils.copy(fileData, target);
    }

    private static String makeUploadedFileName(String uploadPath, String path, String fileName) throws Exception {
        String uploadedFileName = uploadPath + path + File.separator + fileName;

        return uploadedFileName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

    public static String calcPath(String uploadPath) {
        Calendar cal = Calendar.getInstance();

        String yearPath = File.separator + cal.get(Calendar.YEAR);

        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        return uploadPath + File.separator + datePath;
    }

    private static void makeDir(String uploadPath, String... paths) {
        if (new File(paths[paths.length - 1]).exists()) {
            return;
        }

        for (String path : paths) {
            File dirPath = new File(uploadPath + path);

            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }

    public static ResponseEntity<byte[]> dataUrl(String fullName, String imageFileId, String fileExt) throws Exception {
        ResponseEntity<byte[]> entity = null;

        MediaType mType = getMediaType(fileExt);

        HttpHeaders headers = new HttpHeaders();

        if (mType != null) {
            headers.setContentType(mType);
        }

        try (
                InputStream in = new FileInputStream(fullName + File.separator + imageFileId + "." + fileExt);
        ) {
            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    private static MediaType getMediaType(String formatName){
        if(formatName != null) {
            if("JPG".equals(formatName.toUpperCase())) {
                return MediaType.IMAGE_JPEG;
            }

            if("GIF".equals(formatName.toUpperCase())) {
                return MediaType.IMAGE_GIF;
            }

            if("PNG".equals(formatName.toUpperCase())) {
                return MediaType.IMAGE_PNG;
            }
        }

        return null;
    }

}
