package todo;
//
public class ToDo {
	private String name, description;
	public ToDo(String name, String description) {
		this.setName(name);
		this.setDescription(description);
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
}
