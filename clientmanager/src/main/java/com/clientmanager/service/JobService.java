package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import com.clientmanager.model.Job;

public interface JobService {
	public List<Job> getAllJobs();
	public Optional<Job> getJobById(int id);
	public Job createJob(Job job);
	public Job updateJob(Job job);
	public void deleteJob(int id);
	
}
