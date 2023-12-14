package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Record {
    public int indexId = 0;
    public String fileName;
    public String filePath;
    public boolean selectedId;


}