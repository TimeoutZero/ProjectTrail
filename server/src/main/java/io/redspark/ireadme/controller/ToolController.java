package io.redspark.ireadme.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import io.redspark.ireadme.dto.ToolDTO;
import io.redspark.ireadme.entity.Team;
import io.redspark.ireadme.entity.Tool;
import io.redspark.ireadme.form.GenericForm;
import io.redspark.ireadme.service.IReadmeService;
import io.redspark.ireadme.service.ToolService;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team/{teamId}/tool")
public class ToolController {

	@Autowired 
	private IReadmeService service;

	@Transactional(readOnly = true)
	@RequestMapping(method = GET)
	public Collection<ToolDTO> list(@PathVariable("teamId") Long teamId) {
		
		Team team = service.getTeamService().findTeamById(teamId);
		
		return team.getTools()
				.stream().map(ToolDTO::new)
				.collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/{id}", method = GET)
	public ToolDTO get(@PathVariable("teamId") Long teamId, @PathVariable("id") Long id) {
		
		service.getTeamService().exist(teamId);
		
		Tool tool = service.getToolService().findToolById(id);
		
		return new ToolDTO(tool);
	}
 
	@ResponseStatus(CREATED)
	@RequestMapping(method = POST)
	public ToolDTO create(@PathVariable("teamId") Long teamId, @Valid @ModelAttribute GenericForm form) {
  
		Team team = service.getTeamService().findTeamById(teamId);
		
		Tool tool = form.toToolEntity();
		tool.setTeam(team);
		
		tool = service.getToolService().getRepository().save(tool);
		
		return new ToolDTO(tool);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	public ToolDTO update(
			@PathVariable("teamId") Long teamId, 
			@PathVariable("id") Long id, 
			@Valid @ModelAttribute GenericForm form) {
		
		Team team = service.getTeamService().findTeamById(teamId);
		Tool tool = service.getToolService().findToolById(id);

		tool.setName(form.getName());
		tool.setDescription(form.getDescription());
		tool.setImage(form.getImage());
		tool.setTeam(team);
		
		tool = service.getToolService().getRepository().save(tool);
		
		return new ToolDTO(tool);
	}
	
	@RequestMapping(value = "/{id}", method = DELETE)
	public ToolDTO delete(@PathVariable("teamId") Long teamId, @PathVariable("id") Long id) {
		
		service.getTeamService().exist(teamId);

		ToolService toolService = service.getToolService();
		Tool tool = toolService.findToolById(id);
		
		toolService.getRepository().delete(tool);
		
		return new ToolDTO(tool);
	}

	
}
