package todo;

import java.io.Serializable;
import java.util.*;

import todo.*;

public class ToDoManager implements Serializable{

	private ArrayList<ToDo> todos = new ArrayList<ToDo>();
	
	public void add(ToDo t){
		todos.add(t);
	}
	
	public Object[][] getData(){
		int s = todos.size();
		Object[][] data = new Object[s][5];
		Iterator<ToDo> it = todos.iterator();
		int i = 0;
		while(it.hasNext()){
			ToDo t = (ToDo)it.next();
			data[i] = (new Object[]{Boolean.FALSE, t.getName(), t.getCategory(),
					t.getDue(), t.getPriority()});
		}
		return data;
	}
	
	public ArrayList<ToDo> getList(){
		return todos;
	}
	
	public void print(){
		Iterator it = todos.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
}