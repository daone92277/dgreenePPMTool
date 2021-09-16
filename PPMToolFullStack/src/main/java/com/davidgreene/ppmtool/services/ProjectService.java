package com.davidgreene.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidgreene.ppmtool.domain.Project;
import com.davidgreene.ppmtool.exceptions.ProjectIdException;
import com.davidgreene.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch (Exception e) {
			throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() +"' already exisits" );
		}
		
	}
	
	
	public Project findByProjectIdentifier(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Project ID '" + projectId +"' Does not exist" );
 
		}
		
		return projectRepository.findByProjectIdentifier(projectId.toUpperCase());
	}
	
	public Iterable<Project> findAllProjects(){
		
		return projectRepository.findAll();
		
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) {
			throw new ProjectIdException("Cannot delete project Id '" + projectId + "'+ This Project doesnt exist ");
 
		}
		projectRepository.delete(project);
	}
	
}
