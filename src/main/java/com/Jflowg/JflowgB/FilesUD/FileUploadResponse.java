package com.Jflowg.JflowgB.FilesUD;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileUploadResponse {
    
    private String fileName;
    private String downloadUri;
    private long size;
 
}
