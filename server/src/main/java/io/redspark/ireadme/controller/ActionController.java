package io.redspark.ireadme.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import io.redspark.ireadme.dto.ActionDTO;
import io.redspark.ireadme.dto.ToolDTO;
import io.redspark.ireadme.entity.Action;
import io.redspark.ireadme.entity.Team;
import io.redspark.ireadme.entity.Tool;
import io.redspark.ireadme.form.GenericForm;
import io.redspark.ireadme.service.IReadmeService;

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
@RequestMapping("/team/{teamId}/tool/{toolId}/action")
public class ActionController {

	@Autowired 
	private IReadmeService service;

	@Transactional(readOnly = true)
	@RequestMapping(method = GET)
	public Collection<ToolDTO> list(
		@PathVariable("teamId") Long teamId,
		@PathVariable("toolId") Long toolId) {

		service.getToolService().exist(toolId);
		
		Team team = service.getTeamService().findTeamById(teamId);		
		
		return team.getTools()
				.stream().map(ToolDTO::new)
				.collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/{id}", method = GET)
	public ActionDTO get(
			@PathVariable("teamId") Long teamId,
			@PathVariable("toolId") Long toolId,
			@PathVariable("id") Long id) {
	
		service.getTeamService().exist(teamId);
		service.getToolService().exist(toolId);
		
		Action action = service.getActionService().findActionById(id);
		
		return new ActionDTO(action);
	}
 
	@ResponseStatus(CREATED)
	@RequestMapping(method = POST)
	public ActionDTO create(
			@PathVariable("teamId") Long teamId, 
			@PathVariable("toolId") Long toolId, 
			@Valid @ModelAttribute GenericForm form) {
  
		service.getTeamService().exist(teamId);
		Tool tool = service.getToolService().findToolById(toolId);
		
		Action action = form.toActionEntity();
		action.setTool(tool);
		action = service.getActionService().getRepository().save(action);
		
		return new ActionDTO(action);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	public ActionDTO update(
			@PathVariable("teamId") Long teamId, 
			@PathVariable("toolId") Long toolId, 
			@PathVariable("id") Long id, 
			@Valid @ModelAttribute GenericForm form) {
		
		service.getTeamService().exist(teamId);
		service.getToolService().exist(toolId);
		
		Action action = service.getActionService().findActionById(id);

		action.setName(form.getName());
		action.setDescription(form.getDescription());
		action.setImage(form.getImage());
		
		action = service.getActionService().getRepository().save(action);
		
		return new ActionDTO(action);
	}
	
	@RequestMapping(value = "/{id}", method = DELETE)
	public ActionDTO delete(
			@PathVariable("teamId") Long teamId, 
			@PathVariable("toolId") Long toolId,  
			@PathVariable("id") Long id ) {
		
		service.getTeamService().exist(teamId);
		service.getToolService().exist(toolId);
		
		Action action = service.getActionService().findActionById(id);
		
		service.getActionService().getRepository().delete(action);
		
		return new ActionDTO(action);
	}

}
