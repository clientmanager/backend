package com.clientmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientmanager.dao.JobDAO;
import com.clientmanager.model.Job;

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	JobDAO jobDAO;
	
	@Transactional
	@Override
	public List<Job> getAllJobs() {
		return jobDAO.findAll();
	}

	@Transactional
	@Override
	public Job getJobById(int id) {
		return jobDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Job createJob(Job job) {
		return jobDAO.save(job);
	}

	@Override
	public Job updateJob(Job job) {
		if(jobDAO.existsById(job.getId())) {
			return jobDAO.save(job);
		} else {
			return null;
		}
	}

	@Override
	public void deleteJob(int id) {		
		jobDAO.deleteById(id);
	}


}
