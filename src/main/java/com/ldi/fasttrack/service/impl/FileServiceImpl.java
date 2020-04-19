package com.ldi.fasttrack.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ldi.fasttrack.dto.FileDto;
import com.ldi.fasttrack.model.MyFile;
import com.ldi.fasttrack.repository.FileRepository;
import com.ldi.fasttrack.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ServletContext context;

	@Value("${app.resources.file}")
	private String resourcesImages;

	@Override
	public FileDto viewFiles(String pageNo, String pageSize, String sortBy) {

		Pageable pageable = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(sortBy));

		Page<MyFile> pagedResult = fileRepository.findAll(pageable);

		FileDto fileDto = new FileDto();

		if (pagedResult.hasContent()) {

			fileDto.setCurrentPage(Integer.parseInt(pageNo));
			fileDto.setTotalPage(pagedResult.getTotalPages() - 1);
			fileDto.setFiles(pagedResult.getContent());
			fileDto.setTotalFiles(pagedResult.getContent().size());
			fileDto.setPageSize(Integer.parseInt(pageSize));

			return fileDto;
		} else {
			return fileDto;
		}
	}

	@Override
	public MyFile uploadFile(MultipartFile multipartFile) {
		if (multipartFile == null) {
			return null;
		}

		// todo: hardcode
		List<String> contentTypes = new ArrayList<>();
		contentTypes.add("image/jpeg"); // .jpeg
		contentTypes.add("application/msword"); // .doc
		contentTypes.add("text/plain"); // .txt
		contentTypes.add("application/pdf");

		MyFile myFile = new MyFile();
		try {
			/* uploadFileDTO.setMultipartFile(multipartFile); */

			int version = 0;

			String contentType = multipartFile.getContentType();
			if (contentTypes.contains(contentType)) {
				logger.info("===============Value: " + contentType);
				String fileName = multipartFile.getOriginalFilename();

				List<MyFile> listFile = fileRepository.findByName(fileName);
				logger.info("===============Value: " + listFile.size());
				if (listFile.size() != 0) {
					myFile.setVersion(listFile.size());
				} else {
					myFile.setVersion(version);
				}
				myFile.setName(fileName);
				File file = new File(this.getFolderUpload(), fileName);
				multipartFile.transferTo(file);
				myFile.setPathFile(multipartFile.getOriginalFilename());
				myFile.setCreateDateTime(new Date());
				myFile.setFileSize(multipartFile.getSize() / 1024);
				// uploadFileDTO.set
				fileRepository.save(myFile);
			} else {
				logger.info("Content Type: INVALID " + contentType);
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return myFile;
	}

	public File getFolderUpload() {
		File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}

	public MyFile findById(Integer id) {
		if (id == null) {
			return null;
		}
		return fileRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {

		MyFile file = findById(id);
		fileRepository.delete(file);
		logger.info("Delete file has ID: " + id);
	}

	@Override
	public MyFile handleDownload(MyFile file) {
		// TODO Auto-generated method stub
		if (file == null) {
			return null;
		}
		file.setNumberOfDownload(file.getNumberOfDownload() + 1);
		file.setUpdateDateTime(new Date());
		logger.info("UPDATE VALUE: " + file.toString());
		return fileRepository.save(file);

	}

}