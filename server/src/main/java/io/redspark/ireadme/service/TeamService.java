package io.redspark.ireadme.service;

import io.redspark.ireadme.entity.Team;
import io.redspark.ireadme.exceptions.IReadmeException;
import io.redspark.ireadme.exceptions.IReadmeExceptionMessage;
import io.redspark.ireadme.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

	@Autowired
	private IReadmeRepository repository;
	
	public Team findTeamById(Long teamId) {
		
		Team team = repository.getTeamRepository().findOne(teamId);
		
		if(team == null) {
			throw new IReadmeException(HttpStatus.NOT_FOUND, IReadmeExceptionMessage.notFound("team"));
		}
		
		return team;
	} 
	
	public void exist(Long teamId) {
		
		if(!repository.getTeamRepository().exists(teamId)) {
			throw new IReadmeException(HttpStatus.NOT_FOUND, IReadmeExceptionMessage.notFound("team"));
		}
	} 
	
	public TeamRepository getRepository() {
		return repository.getTeamRepository();
	}
	
}
