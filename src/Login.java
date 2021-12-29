import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel {
	private JTextField input_username;
	private JPasswordField input_password;

	
	/**
	 * Create the panel.
	 */
	public Login() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("username");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 104, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 53, SpringLayout.WEST, this);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 48, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		add(lblNewLabel_1);
		
		input_username = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, input_username, 0, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, input_username, 31, SpringLayout.EAST, lblNewLabel);
		add(input_username);
		input_username.setColumns(10);
		
		input_password = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, input_password, 42, SpringLayout.SOUTH, input_username);
		springLayout.putConstraint(SpringLayout.WEST, input_password, 0, SpringLayout.WEST, input_username);
		springLayout.putConstraint(SpringLayout.EAST, input_password, 0, SpringLayout.EAST, input_username);
		add(input_password);
		
		JButton btnNewButton = new JButton("Cancle");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 48, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -37, SpringLayout.SOUTH, this);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = input_username.getText();
				String password = input_password.getText();
				if(username.equals("admin") && password.equals("admin")) {
					JFrame index = new Index();
					index.setVisible(true);
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 0, SpringLayout.WEST, input_username);
		add(btnNewButton_1);

	}
}
