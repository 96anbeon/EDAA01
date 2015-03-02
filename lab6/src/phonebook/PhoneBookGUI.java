package phonebook;
import javax.swing.*;

import java.awt.*;
import java.util.*;

public class PhoneBookGUI extends JFrame {
	private PhoneBook phoneBook;
	private JTextArea messageArea;
		
	public PhoneBookGUI(PhoneBook pb) {
		super("PhoneBook");
		phoneBook = pb;
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		Locale.setDefault(new Locale("en"));
		/* To avoid hardcoded Swedish text on OptionPane dialogs */
		UIManager.put("OptionPane.cancelButtonText","Cancel");
		
		setLayout(new BorderLayout());
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu editMenu = new JMenu("Edit");
		JMenu findMenu = new JMenu("Find");
		JMenu viewMenu = new JMenu("View");
		menubar.add(editMenu);
		menubar.add(findMenu);
		menubar.add(viewMenu);
		editMenu.add(new AddMenu(phoneBook,this));
		editMenu.add(new RemoveMenu(phoneBook,this));
		viewMenu.add(new ShowAllMenu(phoneBook,this));
		findMenu.add(new FindNamesMenu(phoneBook,this));
		findMenu.add(new FindNumbersMenu(phoneBook,this));
		
		JPanel southPanel = new JPanel();
		messageArea = new JTextArea(4,25);
		messageArea.setEditable(false);
		southPanel.add(new JScrollPane(messageArea));
		southPanel.add(new QuitButton(phoneBook,this));
		add(southPanel,BorderLayout.CENTER);
			
		pack();
		setVisible(true);
	}
	

		
	public void print(Collection<String> toPrint){
		messageArea.setText("");
		for(String s : toPrint){
			messageArea.append(s + ", ");
		}
		repaint();
	}
}
