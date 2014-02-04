package todo;
//
public class ToDo {
	private String name, description;
	private int priority;
	
	public ToDo(String name, String description, int priority) {
		this.setName(name);
		this.setDescription(description);
		this.setPriority(priority);
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
	public void setPriority(int priority){
		this.priority = priority;
	}
	public int getPriority(){
		return this.priority;
	}
}
