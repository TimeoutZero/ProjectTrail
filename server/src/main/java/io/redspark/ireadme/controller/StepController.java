package io.redspark.ireadme.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import io.redspark.ireadme.dto.StepDTO;
import io.redspark.ireadme.entity.Action;
import io.redspark.ireadme.entity.Step;
import io.redspark.ireadme.form.StepForm;
import io.redspark.ireadme.service.IReadmeService;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team/{teamId}/tool/{toolId}/action/{actionId}/step")
public class StepController {

	@Autowired
	private IReadmeService service;
	
	@Transactional(readOnly = true)
	@RequestMapping(method = GET)
	public List<StepDTO> list(
			@PathVariable("teamId") Long teamId,
			@PathVariable("toolId") Long toolId,
			@PathVariable("actionId") Long actionId
			) {
		
		service.getTeamService().exist(teamId);
		service.getToolService().exist(toolId);
		
		Action action = service.getActionService().findActionById(actionId);
	
		return action.getSteps()
				.stream().map(StepDTO::new)
				.collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/{id}", method = GET)
	public StepDTO get(
			@PathVariable("teamId") Long teamId,
			@PathVariable("toolId") Long toolId,
			@PathVariable("actionId") Long actionId,
			@PathVariable("id") Long id
			) {

		service.getTeamService().exist(teamId);
		service.getToolService().exist(toolId);
		service.getActionService().exist(actionId);
		
		Step step = service.getStepService().findById(id);
		
		return new StepDTO(step);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = POST)
	public StepDTO create(
			@PathVariable("teamId") Long teamId,
			@PathVariable("toolId") Long toolId,
			@PathVariable("actionId") Long actionId,
			@Valid @ModelAttribute StepForm form) {
		
		service.getTeamService().exist(teamId);
		service.getToolService().exist(toolId);
		
		Action action = service.getActionService().findActionById(actionId);
		
		Step step = form.toEntity();
		step.setName(form.getName());
		step.setDescription(form.getDescription());
		step.setIndex(form.getIndex());
		step.setAction(action);
		
		step = service.getStepService().getRepository().save(step);
		
		return new StepDTO(step);
	}
}
