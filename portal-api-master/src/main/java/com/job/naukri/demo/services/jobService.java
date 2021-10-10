package com.job.naukri.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.naukri.demo.models.job;
import com.job.naukri.demo.models.user;
import com.job.naukri.demo.repository.jobRepository;
import com.job.naukri.demo.repository.userRepository;

@Service
public class jobService {
	
	@Autowired
	private jobRepository jobRepo;
	
	@Autowired
	private userRepository userRepo;
	
	
	public List<job> getJobs()
	{
		return jobRepo.findAll();
	}
	
	public job postJob(job j)
	{
		return jobRepo.save(j);
	}
	
	public String applyToJob(Integer userId,Integer jobId)
	{
		job jobToApply= jobRepo.getOne(jobId);
		user applier=userRepo.getById(userId);
		List<job> jobs=applier.getApplied();
		jobs.add(jobToApply);
		applier.setApplied(jobs);
		userRepo.save(applier);
		List<user> candidates=jobToApply.getCandidates();
		System.out.println(candidates);
		candidates.add(userRepo.getOne(userId));
		System.out.println(candidates);
		jobToApply.setCandidates(candidates);
		jobRepo.save(jobToApply);
		return "Job Apply Success";
		
	}
	
	public List<job> getCandidateJobs(Integer userId)
	{
		return userRepo.getById(userId).getApplied();
	}

	public String saveToJob(Integer userId,Integer jobId) {
		job jobToSave= jobRepo.getOne(jobId);
		user savier=userRepo.getById(userId);
		List<job> jobs=savier.getSaved();
		jobs.add(jobToSave);
		savier.setSaved(jobs);
		userRepo.save(savier);
		List<user> candidates=jobToSave.getCandidates();
		System.out.println(candidates);
		candidates.add(userRepo.getOne(userId));
		System.out.println(candidates);
		jobToSave.setCandidates(candidates);
		jobRepo.save(jobToSave);
		return "Job Save Success";
	}

	public String blockToJob(Integer userId,Integer jobId) {
		job jobToBlock = jobRepo.getOne(jobId);
		user blocker=userRepo.getById(userId);
		List<job> jobs=blocker.getBlocked();
		jobs.add(jobToBlock);
		blocker.setBlocked(jobs);
		userRepo.save(blocker);
		List<user> candidates=jobToBlock.getCandidates();
		System.out.println(candidates);
		candidates.add(userRepo.getOne(userId));
		System.out.println(candidates);
		jobToBlock.setCandidates(candidates);
		jobRepo.save(jobToBlock);
		return "Job Save Success";
	}	
	
	
	
}
