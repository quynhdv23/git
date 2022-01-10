package com.example.computershop.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
    private Integer mediaId;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private byte[] data;

}
