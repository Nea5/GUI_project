package model;

import java.io.Serializable;
import java.util.*;
/**
 * @author Johan Dahlkar 
 * @author Markus Ebbesson 
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class ToDoModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<ToDo> todos = new ArrayList<ToDo>();//List of our Todos
	/**
	 * Adds a ToDo object to the todos list.
	 * 
	 * @param t ToDo Object to be added
	 */
	public void add(ToDo t){
		todos.add(t);
	}
	/**
	 * Gets the ToDo object on index i from the todos list
	 * 
	 * @param i The index
	 * @return ToDo object on index i
	 */
	public ToDo get(int i){
		return todos.get(i);
	}
	
	/**
	 * Sets a new ToDo object t on index i in todos list
	 * 
	 * @param t The ToDo object to be set
	 * @param i The index
	 */
	public void edit(ToDo t, int i){
		todos.set(i, t);
	}
	
	/**
	 * Deletes the ToDo object on index i from todos list
	 * 
	 * @param i The index
	 */
	public void delete(int i){
		todos.remove(i);
	}
	/**
	 * Converts the todos list to a Object[][] array to be 
	 * used as data for a JTable
	 * 
	 * @return todos list as Object[][]
	 */
	public Object[][] getData(){
		int s = todos.size();
		Object[][] data = new Object[s][6];
		Iterator<ToDo> it = todos.iterator();
		int i = 0;
		while(it.hasNext()){
			ToDo t = (ToDo)it.next();
			data[i] = (new Object[]{Boolean.FALSE, t.getName(), t.getCategory(),
					t.getDue(), t.getPriority(), i});
			i++;
		}
		return data;
	}
	/**
	 * Returns a ArrayList of ToDo objects
	 * 
	 * @return
	 */
	public ArrayList<ToDo> getList(){
		return todos;
	}
	/**
	 * Prints everything in todos list.
	 */
	public void print(){
		Iterator<ToDo> it = todos.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	/**
	 * Gets the ToDo items that is due this week
	 * @return Iterator of ToDos this week 
	 */
	public Iterator<ToDo> getWeek(){
		Calendar currentTime = new GregorianCalendar();
		currentTime.setTime(new Date());
		currentTime.setFirstDayOfWeek(Calendar.MONDAY);
		int week = currentTime.get(Calendar.WEEK_OF_YEAR); //Gets the week of year
		int year = currentTime.get(Calendar.YEAR);
		List<ToDo> thisWeek = new ArrayList<ToDo>();
		for(int i = 0; i<todos.size();i++){
			ToDo t = todos.get(i);
			Calendar due = new GregorianCalendar();
			due.setTime(t.getDue());
			due.setFirstDayOfWeek(Calendar.MONDAY);
			int dueWeek = due.get(Calendar.WEEK_OF_YEAR);
			int dueYear = due.get(Calendar.YEAR);
			if(dueWeek == week && dueYear == year){ //Checks if it is due this week
				thisWeek.add(t);
			}
		}
		return thisWeek.iterator();
	}
	/**
	 * Gets the ToDo items that is due within month and year
	 * @param month
	 * @param year
	 * @return Iterator of ToDos in month and year
	 */
	public Iterator<ToDo> getMonth(int month, int year){
		List<ToDo> thisMonth = new ArrayList<ToDo>();
		for(int i = 0; i<todos.size();i++){
			ToDo t = todos.get(i);
			Calendar due = new GregorianCalendar();
			due.setTime(t.getDue());
			int dueWeek = due.get(Calendar.MONTH);
			int dueYear = due.get(Calendar.YEAR);
			if(dueWeek == month && dueYear == year){
				thisMonth.add(t);
			}
		}
		return thisMonth.iterator();
	}
	/**
	 * Gets the ToDo items that is not done and due date has passed
	 * @return Iterator of overdue ToDos
	 */
	public Iterator<ToDo> getOverdue(){
		Date currentTime = new Date();
		List<ToDo> overdue = new ArrayList<ToDo>();
		for(int i = 0; i<todos.size();i++){
			ToDo t = todos.get(i);
			Date due = t.getDue();
			if(currentTime.compareTo(due) >= 0){
				overdue.add(t);
			}
		}
		return overdue.iterator();
	}
	/**
	 * Returns all ToDo items that are done
	 * @return Iterator of ToDos that are done
	 */
	public Iterator<ToDo> getDone(){
		List<ToDo> done = new ArrayList<ToDo>();
		for(int i = 0; i<todos.size();i++){
			ToDo t = todos.get(i);
			if(t.isDone()){
				done.add(t);
			}
		}
		return done.iterator();
	}
}
