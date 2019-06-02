package com.teachingbase.service.impl;

import com.teachingbase.dao.BaseFileMapper;
import com.teachingbase.domain.BaseFile;
import com.teachingbase.service.BaseFileService;
import com.teachingbase.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class BaseFileServiceImpl implements BaseFileService {

    @Autowired
    public BaseFileMapper baseFileMapper;

    @Transactional
    @Override
    public boolean addBaseFile(BaseFile baseFile, MultipartFile file) throws IOException {

        String filePath = ExcelUtil.PATH;//绝对路径
        File dest = new File(filePath);//目录
        if (!dest.exists()) {
            dest.mkdir();//创建目录
        }
        File file1 = new File(filePath+baseFile.getFilePath());//文件名
        if (file1.exists()) {
            file1.delete();
        }
        file.transferTo(file1);

        return baseFileMapper.addBaseFile(baseFile)>0?true:false;
    }

    @Override
    public BaseFile getBaseFileByBaseId(String baseId) {
        return baseFileMapper.getBaseFileByBaseId(baseId);
    }
}
