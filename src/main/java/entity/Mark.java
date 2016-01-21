package entity;


public class Mark extends AbstractModelBean {
	private static final long serialVersionUID = 7209705985007464505L;
	private Discipline discipline;
	private Integer value;
	private Term term;
	private int id;

	public Mark() {
		super();
	}

	public Mark(int id, Integer value) {
		super();
		this.id = id;
		this.value = value;
		
	}

	public Mark(int id, Discipline discipline, Integer value, Term term) {
		super();
		this.id = id;
		this.discipline = discipline;
		this.value = value;
		this.term = term;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}
	
	@Override
	protected Object getIdModel() {
		return this.getValue();
	}

}
