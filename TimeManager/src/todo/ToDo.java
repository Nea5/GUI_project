package todo;

import java.io.Serializable;
import java.util.Date;

//
public class ToDo implements Serializable{
	private String name, category;
	private int priority, progress;
	private Date due;
	
	public ToDo(String name, String category, int priority, Date due) {
		this.setName(name);
		this.setCategory(category);
		this.setPriority(priority);
		this.setProgress(0);
		this.setDue(due);
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPriority(int priority){
		this.priority = priority;
	}
	public int getPriority(){
		return this.priority;
	}
	public void setProgress(int progress){
		this.progress = progress;
	}
	public int getProgress(){
		return this.progress;
	}
	public void setDue(Date due){
		this.due = due;
	}
	public Date getDue(){
		return this.due;
	}
	public String toString(){
		String s = "Name: " + name;
		return s;
	}
	public void setCategory(String category){
		this.category = category;
	}
	public String getCategory(){
		return this.category;
	}
}
