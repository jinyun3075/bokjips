package com.bokjips.server.service;


import com.bokjips.server.util.dto.UploadResultDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {
    List<UploadResultDTO> uploadFile(MultipartFile[] uploadFiles) throws Exception;
}

