package control;

import gui.TimeManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import gui.*;
/**
 * Test
 * @author Johan Dahlkar
 * @author Markus Ebbesson
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class Controller {
	private View view;
	private AbstractAction newAction, deleteAction, editAction, helpAction,
							doneAction, nextMonthAction, prevMonthAction;
	private Icon iNew, iEdit, iDelete, iDone, iNext, iPrev;
	
	
	public Controller(View view) {
		this.view = view;
		this.getResources();
		this.createActions();
		
		view.addNewTaskAction(newAction);
		view.addEditTaskAction(editAction);
		view.addDeleteTaskAction(deleteAction);
		view.addHelpAction(helpAction);
		view.addMarkDoneAction(doneAction);
		view.addNextMonthAction(nextMonthAction);
		view.addPrevMonthAction(prevMonthAction);
		view.addChangeYearListener(new ChangeYearListener());
		view.addSelectionListener(new SelectedListener());
		view.addTableModelListener(new CheckedListener());
		view.addLanguageListener(new LanguageListener());
		view.addFilterListener(new FilterListener());
		view.addListListener(new ListListener());
		view.addRightClickListener(new RightClickListener());
	
	}
	
	private void createActions(){
		newAction = new NewTaskAction();
		editAction = new EditTaskAction();
		deleteAction = new DeleteTaskAction();
		helpAction = new HelpAction();
		doneAction = new MarkDoneAction();
		nextMonthAction = new NextMonthAction();
		prevMonthAction = new PrevMonthAction();
	}
	
	private void getResources(){
		ClassLoader cl = getClass().getClassLoader();
		iNew = new ImageIcon(cl.getResource("resources/Add24.gif"));
		iEdit = new ImageIcon(cl.getResource("resources/Edit24.gif"));
		iDelete = new ImageIcon(cl.getResource("resources/Delete24.gif"));
		iDone = new ImageIcon(cl.getResource("resources/tick24.png"));
		iNext = new ImageIcon(cl.getResource("resources/FastForward24.gif"));
		iPrev = new ImageIcon(cl.getResource("resources/Rewind24.gif"));
	}
	
	public class NewTaskAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public NewTaskAction() {
			super(TimeManager.rb.getString("new_task_button"), iNew);
			
			// TODO Auto-generated constructor stub
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			view.newTask();
		}

	}
	
	public class EditTaskAction extends AbstractAction{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public EditTaskAction() {
			super(TimeManager.rb.getString("edit_task_button"), iEdit);
			this.setEnabled(false);
		}
		@Override
		/**
		 * Performs the editTask() method in ListsPanel
		 */
		public void actionPerformed(ActionEvent e) {
			System.out.println();
			view.editTask();
		}
		
	}
	public class DeleteTaskAction extends AbstractAction{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public DeleteTaskAction() {
			super(TimeManager.rb.getString("delete_task_button"), iDelete);
			this.setEnabled(false);
		}
		@Override
		/**
		 * Performs the deleteTasks() method in ListsPanel
		 */
		public void actionPerformed(ActionEvent e) {
			view.deleteTasks();
		}
		
	}
	public class MarkDoneAction extends AbstractAction{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MarkDoneAction() {
			super(TimeManager.rb.getString("mark_done_button"), iDone);
			this.setEnabled(false);
		}
		@Override
		/**
		 * Performs the markDone() method in view
		 */
		public void actionPerformed(ActionEvent e) {
			view.markDone();
		}
		
	}
	public class NextMonthAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public NextMonthAction() {
			super("", iNext);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			view.nextMonth();
		}	
	}
	public class PrevMonthAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public PrevMonthAction() {
			super("", iPrev);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			view.prevMonth();
		}	
	}
	public class ChangeYearListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			view.changeYear();
		}
	}
	public class SelectedListener implements ListSelectionListener{
		@Override
		/**
		 * Sets b_edit and m_edit to enabled/disabled depending on
		 * if there is any row selected
		 */
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            editAction.setEnabled(!lsm.isSelectionEmpty());
            doneAction.setEnabled(!lsm.isSelectionEmpty());
            deleteAction.setEnabled(!lsm.isSelectionEmpty());
		}
	}
	
	public class CheckedListener implements TableModelListener{

		@Override
		/**
		 * Performes the getMarked() method in TodoTable and uses the
		 * returned value to set m_delete and d_delete enabled/disabled
		 */
		public void tableChanged(TableModelEvent e) {
			boolean b = view.getMarked();
			deleteAction.setEnabled(b);
			doneAction.setEnabled(b);
		}
		
	}
	
	public class HelpAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public HelpAction(){
			super(TimeManager.rb.getString("mi_help"));
		}
		@Override
		/**
		 * Opens a JOptionPane with a HelpPanel
		 */
		public void actionPerformed(ActionEvent e) {
			JPanel p_help = new MessagePanel(TimeManager.rb.getString("help_info"));
			JOptionPane.showMessageDialog(view, p_help, 
			TimeManager.rb.getString("help_window"), JOptionPane.PLAIN_MESSAGE);
		}
		
	}
	
	public class FilterListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			view.newFilter((String)e.getItem());
		}
		
	}
	
	public class LanguageListener implements ItemListener{
        @Override
        /**
         * Changes language
         */
        public void itemStateChanged(ItemEvent e) { 	
        	Properties props = new Properties();
        	try {
				FileInputStream in = new FileInputStream("settings.properties");
				props.load(in);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            if (e.getStateChange() == ItemEvent.SELECTED) {
            	try {
            		FileOutputStream out = new FileOutputStream("settings.properties");
                 	props.setProperty("language", "Svenska");
                    props.store(out, null);
                    out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else
            {
            	try {
             		FileOutputStream out = new FileOutputStream("settings.properties");
                  	props.setProperty("language", "English");
                    props.store(out, null);
	                    out.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            } 
    		JPanel p_msg = new MessagePanel(TimeManager.rb.getString("changed_lang_msg"));
			JOptionPane.showMessageDialog(view, p_msg, 
			TimeManager.rb.getString("msg_title"), JOptionPane.PLAIN_MESSAGE);
		
        }
    }

	public class LAFListener implements ItemListener{
        @Override
        /**
         * Changes look and feel of the application
         */
        public void itemStateChanged(ItemEvent e) { 
        	Properties props = new Properties();
        	try {
				FileInputStream in = new FileInputStream("settings.properties");
				props.load(in);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            if (e.getStateChange() == ItemEvent.SELECTED) {
            	
            	try {
            		FileOutputStream out = new FileOutputStream("settings.properties");
                    
                 	props.setProperty("look_and_feel", "Nimbus");
                    props.store(out, null);
                    out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else
            {

            	try {
             		FileOutputStream out = new FileOutputStream("settings.properties");
                  	props.setProperty("look_and_feel", "Metal");
                    props.store(out, null);
	                out.close();
					
            	} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            } 
        }
    }
	
	public class ListListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
	        String selected = (String)cb.getSelectedItem();
	        view.list(selected);
		}
		
	}
	public class RightClickListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			view.rightClickMenu(e);
		}
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			view.rightClickMenu(e);
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}	
	}
	
	
	
}
