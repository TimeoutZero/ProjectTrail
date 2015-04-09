package io.redspark.ireadme.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "action")
public class Action extends AbstractFormEntity {

	@ManyToOne
	private Tool tool;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "action")
	private List<Step> steps = new ArrayList<>();
	
}
