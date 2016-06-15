package gui.history;

import code.TableModel.DefaultModel;
import code.behavior.Table;
import code.customer.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExpendProduct extends JDialog implements Table {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTable table;

	public ExpendProduct(Window page, Object obj, int index) {
		setContentPane(contentPane);
		setModal(true);

		updateTable(obj, index);

		buttonCancel.addActionListener(e -> onCancel());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		// call onCancel() on ENTER
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void updateTable(Object obj, int index) {
		Object[][] temp = null;
		if (obj instanceof Customer) {
			Customer shopper = (Customer) obj;
			temp = shopper.getHistoryListInform(index);
		}

		DefaultModel model = new DefaultModel(temp, new String[]{"Order ID", "Name", "Type", "Size", "Weight", "Num", "Price"}, false);
		table.setModel(model);
		settingTable(table, null);
	}

	private void onCancel() {
		// add your code here if necessary
		dispose();
	}

	public void run(Point point) {
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void createUIComponents() {
		// make table fit size by contact
		table = fitSize(table);

	}
}