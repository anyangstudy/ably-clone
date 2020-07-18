package com.study.anyang.service;

import com.study.anyang.domain.ImageFile;
import com.study.anyang.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public Long uploadFileDb(String savedName, String fileExt) {
        ImageFile imgFile = new ImageFile();
        imgFile.setFullName(savedName);
        imgFile.setFileExt(fileExt);

        imageRepository.save(imgFile);

        return imgFile.getImageFileId();
    }

    public ImageFile getImageFile(Long imageFileId) throws Exception {
        return imageRepository.getOne(imageFileId);
    }
}
