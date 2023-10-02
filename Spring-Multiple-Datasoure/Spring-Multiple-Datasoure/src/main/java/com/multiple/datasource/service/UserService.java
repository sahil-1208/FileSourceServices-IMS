package com.multiple.datasource.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multiple.datasource.entity.user.UserDetails;
import com.multiple.datasource.excelconnect.UserExcel;
import com.multiple.datasource.repository.user.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	public void save(MultipartFile file) {
		try {
			List<UserDetails> listUser =UserExcel.convertExcelToListOfUser(file.getInputStream());
			userRepo.saveAll(listUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<UserDetails> getAllUserDetails(){
		return userRepo.findAll();
	}
}

