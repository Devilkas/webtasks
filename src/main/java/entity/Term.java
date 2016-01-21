package entity;

import java.util.List;

public class Term extends AbstractModelIdName {
	private static final long serialVersionUID = -5830571334355482968L;
	private Integer duration;
	private List<Discipline> disciplines;

	public Term() {
		super();
	}

	public Term(Integer id, String name, Integer duration,
			List<Discipline> disciplines) {
		super(id, name);
		this.duration = duration;
		this.disciplines = disciplines;
	}

	public Term(String name, Integer duration, List<Discipline> disciplines) {
		super(name);
		this.duration = duration;
		this.disciplines = disciplines;
	}
	
	public Term(Integer id, String name, Integer duration) {
		super(id, name);
		this.duration = duration;
		
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

}
