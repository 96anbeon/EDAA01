package phonebook;

import javax.swing.*;
import java.awt.event.*;

public class RemoveMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public RemoveMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Remove");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String name = JOptionPane.showInputDialog("Enter name to remove");
		if (name == null)
			return;
		phoneBook.remove(name);
		gui.print(phoneBook.names());
	}
}
