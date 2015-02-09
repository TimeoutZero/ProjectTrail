package io.redspark.ireadme.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import io.redspark.ireadme.dto.StepDTO;
import io.redspark.ireadme.entity.Step;
import io.redspark.ireadme.form.StepForm;
import io.redspark.ireadme.service.IReadmeService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team/{teamId}/tool/{toolId}/action/{actionId}/step")
public class StepController {

	@Autowired
	private IReadmeService service;
	
	@RequestMapping(method = POST)
	public StepDTO create(
			@PathVariable("teamId") Long teamId,
			@PathVariable("toolId") Long toolId,
			@PathVariable("actionId") Long actionId,
			@Valid @ModelAttribute StepForm form) {
		
		service.getTeamService().exist(teamId);
		service.getToolService().exist(toolId);
		service.getActionService().exist(actionId);
		
		Step step = form.toEntity();
		step.setName(form.getName());
		step.setDescription(form.getDescription());
		step.setIndex(form.getIndex());
		
		step = service.getStepService().getRepository().save(step);
		
		return new StepDTO(step);
	}
}
