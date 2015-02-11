package io.redspark.ireadme.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tool")
public class Tool extends AbstractFormEntity {

	@ManyToOne
	private Team team;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "tool")
	private Collection<Action> actions = new ArrayList<>();
	
	public Tool() {
		super();
	}

	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public Collection<Action> getActions() {
		return actions;
	}
	
	public void setActions(Collection<Action> actions) {
		this.actions = actions;
	}
}
