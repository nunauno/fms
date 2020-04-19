package com.ldi.fasttrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ldi.fasttrack.model.Setting;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Integer> {

}
