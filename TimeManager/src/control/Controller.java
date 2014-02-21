package control;

import gui.EditPanel;
import gui.NewTaskPanel;
import gui.TimeManager;

import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import todo.*;
import gui.*;

public class Controller {
	private MainFrame view;
	private ListsPanel pLists;
	private TimeMenuBar menubar;
	private TodoTable t_tasks;
	private JButton b_new, b_edit, b_delete;
	private JMenuItem m_new, m_edit, m_delete, m_help;
	
	
	public Controller(MainFrame view) {
		this.view = view;
		
		pLists = view.getTabPanel().getListPanel();
		menubar = view.getMenu();
		
		t_tasks = pLists.getTable();
		
		b_new = pLists.getNewButton();
		b_edit = pLists.getEditButton();
		b_delete = pLists.getDeleteButton();
		
		m_new = menubar.getNewItem();
		m_edit = menubar.getEditItem();
		m_delete = menubar.getDeleteItem();
		m_help = menubar.getHelpItem();
		
		b_new.addActionListener(new NewTaskListener());
		m_new.addActionListener(new NewTaskListener());
		
		b_edit.addActionListener(new EditTaskListener());
		m_edit.addActionListener(new EditTaskListener());
		
		b_delete.addActionListener(new DeleteListener());
		m_delete.addActionListener(new DeleteListener());
		
		m_help.addActionListener(new HelpListener());
		
		ListSelectionModel listSelectModel = t_tasks.getSelectionModel();
		listSelectModel.addListSelectionListener(new SelectedListener());
		t_tasks.getModel().addTableModelListener(new CheckedListener());
	}
	
	public class NewTaskListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			pLists.newTask();
		}	
	}

	
	public class EditTaskListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			pLists.editTask();
		}
		
	}
	public class DeleteListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			pLists.deleteTasks();
		}
		
	}
	public class SelectedListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            b_edit.setEnabled(!lsm.isSelectionEmpty());
            m_edit.setEnabled(!lsm.isSelectionEmpty());
		}
	}
	
	public class CheckedListener implements TableModelListener{

		@Override
		public void tableChanged(TableModelEvent e) {
			b_delete.setEnabled(t_tasks.getMarked());
		}
		
	}
	
	public class HelpListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel p_help = new HelpPanel();
			JOptionPane.showMessageDialog(pLists, p_help, 
					TimeManager.rb.getString("help_window"), JOptionPane.PLAIN_MESSAGE);
		}
		
	}
}
