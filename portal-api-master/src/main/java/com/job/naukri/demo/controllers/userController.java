package com.job.naukri.demo.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.job.naukri.demo.models.job;
import com.job.naukri.demo.models.uploadFileResponse;
import com.job.naukri.demo.models.user;
import com.job.naukri.demo.services.jobService;
import com.job.naukri.demo.services.userService;
@CrossOrigin("http://localhost:3000")
@RestController
public class userController {
	
	@Autowired
	private userService service;
	
	@Autowired
	private jobService jobservice;
	
	@GetMapping("/test")
	public String Test()
	{
		return "test";
	}
	
	@PostMapping(path="/register",consumes="application/json")
	public Map<String, Object> registerUser(@RequestBody user u) throws NoSuchAlgorithmException
	{
		
		return service.registerUser(u);
	}
	@PostMapping("/login")
	public user loginUser(@RequestBody user u) throws NoSuchAlgorithmException{
		return service.loginUser(u);
	}
	
	@PostMapping("/uploadresume")
	public uploadFileResponse uploadResume(@RequestParam("file") MultipartFile file,Integer id) throws IOException
	{
		return service.uploadResume(file,id );
	}
	
	@GetMapping("/all")
	public List<user> getAllUsers()
	{
		return service.fetchUsers();
	}

	@PostMapping("/save")
	public String saveJob(@RequestParam Map<String, String> params)
	{
		return jobservice.saveToJob(Integer.parseInt(params.get("userId")),Integer.parseInt(params.get("jobId")));
	}
	
	@GetMapping("/fetchSaved/{userId}")
	public List<job> getSaved(@PathVariable String userId)
	{
		return service.getSavedJobs(Integer.parseInt(userId));
	}

	@PostMapping("/apply")
	public String applyJob(@RequestParam Map<String, String> params)
	{
		return jobservice.applyToJob(Integer.parseInt(params.get("userId")),Integer.parseInt(params.get("jobId")));
	}
	
	@GetMapping("/fetchApplied/{userId}")
	public List<job> getApplied(@PathVariable String userId)
	{
		return service.getAppliedJobs(Integer.parseInt(userId));
	}
	

	@PostMapping("/blocked")
	public String blockedJob(@RequestParam Map<String, String> params)
	{
		return jobservice.blockToJob(Integer.parseInt(params.get("userId")),Integer.parseInt(params.get("jobId")));
	}
	
	@GetMapping("/fetchBlocked/{userId}")
	public List<job> getBlocked(@PathVariable String userId)
	{
		return service.getBlockedJobs(Integer.parseInt(userId));
	}
	

}
