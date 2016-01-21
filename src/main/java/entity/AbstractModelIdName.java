package entity;


public abstract class AbstractModelIdName extends AbstractModelBean {
	private static final long serialVersionUID = 1044362348404780110L;
	private Integer id;
	private String name;

	public AbstractModelIdName() {
		super();
	}
	
	public AbstractModelIdName(String name) {
		super();
		this.name = name;
	}

	public AbstractModelIdName(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	protected Object getIdModel() {
		return getId();
	}
}
