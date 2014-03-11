package model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Johan Dahlkar 
 * @author Markus Ebbesson 
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 *
 */
public class ToDo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name, category;
	private int priority;
	private Date due;
	private boolean done;
	
	public ToDo(String name, String category, int priority, Date due) {
		this.setName(name);
		this.setCategory(category);
		this.setPriority(priority);
		this.setDue(due);
		this.done = false;
	}
	/**
	 * Sets this objects name to name
	 * @param name New name
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * Returns this objects name
	 * @return name
	 */
	public String getName(){
		if(name.equals(null)) this.name = " ";
		return this.name;
	}
	/**
	 * Sets this objects priority to priority
	 * @param priority New priority
	 */
	public void setPriority(int priority){
		this.priority = priority;
	}
	/**
	 * Returns this objects priority
	 * @return priority
	 */
	public int getPriority(){
		return this.priority;
	}
	/**
	 * Sets this objects due to due
	 * @param due New due
	 */
	public void setDue(Date due){
		this.due = due;
	}
	/** 
	 * Returns this objects due
	 * @return due
	 */
	public Date getDue(){
		return this.due;
	}
	/**
	 * Sets this objects category to category
	 * @param category New category
	 */
	public void setCategory(String category){
		this.category = category;
	}
	/**
	 * Returns this objects category
	 * @return category
	 */
	public String getCategory(){
		return this.category;
	}
	/**
	 * Sets done to true
	 */
	public void setDone(){
		this.done = true;
	}
	/**
	 * Gets value of done
	 * @return
	 */
	public boolean isDone(){
		return this.done;
	}
}
