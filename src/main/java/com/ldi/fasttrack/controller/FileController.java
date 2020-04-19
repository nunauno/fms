package com.ldi.fasttrack.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ldi.fasttrack.dto.FileDto;
import com.ldi.fasttrack.model.MyFile;
import com.ldi.fasttrack.repository.FileRepository;
import com.ldi.fasttrack.service.FileService;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;
	
	@Autowired
	private FileRepository fileRepository;

	private Logger logger = LoggerFactory.getLogger(FileController.class);

	@GetMapping("/file")
	public String homepage(@RequestParam(defaultValue = "0") String pageNo,
			@RequestParam(defaultValue = "10") String pageSize, @RequestParam(defaultValue = "id") String sortBy,
			Model model) {

		FileDto fileDto = fileService.viewFiles(pageNo, pageSize, sortBy);		

		model.addAttribute("fileDto", fileDto);
		model.addAttribute("listRowspan", fileRepository.findRowspans());

		return "file";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("fileContent") MultipartFile file) {

		fileService.uploadFile(file);

		return "redirect:/file";
	}

	@GetMapping("/download/{id}")
	public void downloadFile(@PathVariable Integer id, HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		// If user is not authorized - he should be thrown out from here itself

		// Authorized user will download the file
		// String dataDirectory =
		// request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/");

		// 1. get file by id
		MyFile myFile = fileService.findById(id);

		if (myFile != null) {
			String dataDirectory = System.getProperty("user.home") + "/Uploads"; // this should be in constants
			Path file = Paths.get(dataDirectory, myFile.getName());
			if (Files.exists(file)) {
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.addHeader("Content-Disposition", "attachment; filename=" + myFile.getName());
				response.setHeader("Refresh", "1; url = index");
				try {
					Files.copy(file, response.getOutputStream());
					response.getOutputStream().flush();
					// update total download
					if (Files.isExecutable(file)) {
						// to do here
						fileService.handleDownload(myFile);
					}

				} catch (IOException ex) {
					logger.info("Cannot Download File: " + myFile.getName());
					ex.printStackTrace();
				}
			}
		}
		
	}

	@GetMapping("/delete")
	public String deleteFile(@RequestParam Integer id) {

		fileService.delete(id);
		return "redirect:/file";
	}
}