package com.ldi.fasttrack.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ldi.fasttrack.model.Setting;
import com.ldi.fasttrack.repository.SettingRepository;
import com.ldi.fasttrack.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService {

	Logger logger = LoggerFactory.getLogger(SettingServiceImpl.class);
	@Autowired
	private SettingRepository settingRepo;
	
	@Override
	public Setting saveSetting(Setting setting) {
		
		if(ObjectUtils.isEmpty(setting)) {
			return null;
		}
		settingRepo.save(setting);
		logger.info("================SETTING VALUE: "+setting.toString());
		
		
		return setting;
	}

}
