package com.ldi.fasttrack.dto;

import java.util.List;

import com.ldi.fasttrack.model.MyFile;

import lombok.Data;

@Data	
public class FileDto {
		
	private int currentPage;
	private int totalPage;
	private List<MyFile> files ;
	private int pageSize;
	private int totalFiles;
	
}