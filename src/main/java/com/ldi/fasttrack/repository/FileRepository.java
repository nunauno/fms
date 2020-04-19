package com.ldi.fasttrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ldi.fasttrack.model.MyFile;

@Repository
public interface FileRepository extends PagingAndSortingRepository<MyFile, Integer>{
	
	@Query(value = "Select * from file u where u.name like :name",nativeQuery = true)
	List<MyFile> findByName(@Param("name") String name);
	
	@Query(value = "SELECT count(id) FROM fmn.file group by name",nativeQuery = true)
	List<Integer> findRowspans();

}
