package com.ldi.fasttrack.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ldi.fasttrack.dto.FileDto;
import com.ldi.fasttrack.model.MyFile;

@Service
public interface FileService {
		
	public FileDto viewFiles(String pageNo, String pageSize, String sortBy);

	public MyFile uploadFile(MultipartFile multipartFile);

	public void delete(Integer id);

	public MyFile findById(Integer id);
	
	public MyFile handleDownload(MyFile myFile);
	

}
