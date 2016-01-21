package entity;


public class Discipline extends AbstractModelIdName {
	private static final long serialVersionUID = -5136131961951508181L;

	public Discipline() {
		super();
	}

	public Discipline(String name) {
		super(name);
	}

	public Discipline(Integer id, String name) {
		super(id, name);
	}

}
