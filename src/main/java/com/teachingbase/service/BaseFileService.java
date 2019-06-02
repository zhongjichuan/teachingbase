package com.teachingbase.service;

import com.teachingbase.dao.BaseFileMapper;
import com.teachingbase.domain.BaseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BaseFileService {

    public boolean addBaseFile(BaseFile baseFile, MultipartFile file)throws IOException;

    public BaseFile getBaseFileByBaseId(String baseId);
}
