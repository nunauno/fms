package com.ldi.fasttrack.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "file")
@NoArgsConstructor
@AllArgsConstructor
public class Files {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
}
