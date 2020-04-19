package com.ldi.fasttrack.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileDTO {
	
	
	private int id;
	private String name;
	private String pathFile;
	private Long fileSize;
	private String mime;
	private int numberOfDownload;
	private int version;
	private boolean status;
	private Date createDateTime ;
	private Date updateDateTime ;
	private String versionIds;
	private MultipartFile multipartFile;
	

}
