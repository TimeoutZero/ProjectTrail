package io.redspark.ireadme.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "tool")
public class Tool extends AbstractFormEntity {

	@ManyToOne
	private Team team;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "tool")
	private Collection<Action> actions = new ArrayList<>();
	
	public Tool() {
		super();
	}
}
