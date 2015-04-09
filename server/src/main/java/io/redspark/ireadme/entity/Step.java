package io.redspark.ireadme.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "step")
public class Step extends AbstractFormEntity{

	@ManyToOne
	private Action action;
	private Integer index;
	
}
