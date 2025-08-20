package com.wzy.friend.service.file;

import com.wzy.common.file.domain.OSSResult;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    OSSResult upload(MultipartFile file);
}