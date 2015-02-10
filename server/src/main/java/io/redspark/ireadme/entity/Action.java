package io.redspark.ireadme.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "action")
public class Action extends AbstractEntity {

	@ManyToOne
	private Tool tool;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "action")
	private List<Step> steps = new ArrayList<>();
	
	public Tool getTool() {
		return tool;
	}
	
	public void setTool(Tool tool) {
		this.tool = tool;
	}
	
	public List<Step> getSteps() {
		return steps;
	}
	
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
}
