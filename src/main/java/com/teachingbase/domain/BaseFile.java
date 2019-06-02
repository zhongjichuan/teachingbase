package com.teachingbase.domain;

public class BaseFile {
    private String baseId;
    private String filePath;

    @Override
    public String toString() {
        return "BaseFile{" +
                "baseId='" + baseId + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }
}
