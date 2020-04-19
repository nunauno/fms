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
@Table(name = "setting")
@NoArgsConstructor
@AllArgsConstructor
public class Setting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	private int maxFileSize;
	private int itemPerPage;
	private String mimeTypeAllowed;
	private Date updateDateTime;
	

}
