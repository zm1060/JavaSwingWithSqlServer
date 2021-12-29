import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.util.DButil;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserManage extends JFrame {

	private JPanel contentPane;
	private JTextField add_id;
	private JTextField add_name;
	private JPasswordField add_password;
	private JTextField add_level;
	private JTextField query_id;
	private JTextField query_name;
	private JPasswordField query_password;
	private JTextField query_level;
	private JButton button_query;
	
	public String titles[];
	public String s_titles[];
	public List<String> record;
	public List<String> s_record;
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManage frame = new UserManage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserManage() {
		setTitle("\u7528\u6237\u7BA1\u7406");
		list = new JList(dlm);
		record = new ArrayList<String>();
		s_record = new ArrayList<String>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237id");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 24, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 36, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u7528\u6237\u5BC6\u7801");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 50, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u7528\u6237\u7B49\u7EA7");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 44, SpringLayout.SOUTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_3);
		
		add_id = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, add_id, 23, SpringLayout.EAST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, add_id, 0, SpringLayout.SOUTH, lblNewLabel);
		contentPane.add(add_id);
		add_id.setColumns(10);
		
		add_name = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, add_name, 0, SpringLayout.NORTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, add_name, 0, SpringLayout.WEST, add_id);
		contentPane.add(add_name);
		add_name.setColumns(10);
		
		add_password = new JPasswordField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, add_password, -3, SpringLayout.NORTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, add_password, 23, SpringLayout.EAST, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.EAST, add_password, 0, SpringLayout.EAST, add_id);
		contentPane.add(add_password);
		
		add_level = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, add_level, 0, SpringLayout.SOUTH, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.EAST, add_level, 0, SpringLayout.EAST, add_id);
		contentPane.add(add_level);
		add_level.setColumns(10);
		
		JButton button_add = new JButton("\u6DFB\u52A0");
		button_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String a_id = add_id.getText();
				String a_name = add_name.getText();
				String a_password = add_password.getText();
				String a_level = add_level.getText();
				String data = a_id + "," + a_name + "," + a_password +","+a_level +"\n";
				record.add(data);
				dlm.addElement(data);
				

				Connection con = DButil.getConnect();
				Statement stmt = null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sql;
				sql = "insert into s_user(userid,username,password,level) values(" + "'" + a_id + "', '" + a_name + "', '" + a_password + "'," + a_level +");";
				try {
					stmt.executeUpdate(sql);
					System.out.println(sql);
					System.out.println("插入成功！");
					JOptionPane.showMessageDialog(null,"插入成功！");	
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"插入失败！");				
					e1.printStackTrace();
				}

			}
			
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, button_add, 84, SpringLayout.SOUTH, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.WEST, button_add, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(button_add);
		
		JButton button_cancle = new JButton("\u53D6\u6D88");
		sl_contentPane.putConstraint(SpringLayout.NORTH, button_cancle, 0, SpringLayout.NORTH, button_add);
		sl_contentPane.putConstraint(SpringLayout.WEST, button_cancle, 19, SpringLayout.EAST, button_add);
		contentPane.add(button_cancle);
		
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 28, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 44, SpringLayout.EAST, add_id);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, 226, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, -71, SpringLayout.EAST, contentPane);
		contentPane.add(list);
		
		JLabel lblNewLabel_4 = new JLabel("\u7528\u6237id");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 15, SpringLayout.SOUTH, list);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_4, 0, SpringLayout.WEST, list);
		contentPane.add(lblNewLabel_4);
		
		query_id = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, query_id, 6, SpringLayout.EAST, lblNewLabel_4);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, query_id, 0, SpringLayout.SOUTH, lblNewLabel_4);
		contentPane.add(query_id);
		query_id.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u7528\u6237\u540D");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 21, SpringLayout.SOUTH, lblNewLabel_4);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_5, 0, SpringLayout.EAST, lblNewLabel_4);
		contentPane.add(lblNewLabel_5);
		
		query_name = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, query_name, 0, SpringLayout.WEST, query_id);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, query_name, 0, SpringLayout.SOUTH, lblNewLabel_5);
		contentPane.add(query_name);
		query_name.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("\u7528\u6237\u5BC6\u7801");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_6, 34, SpringLayout.SOUTH, lblNewLabel_5);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_6, 0, SpringLayout.EAST, lblNewLabel_4);
		contentPane.add(lblNewLabel_6);
		
		query_password = new JPasswordField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, query_password, 34, SpringLayout.SOUTH, query_name);
		sl_contentPane.putConstraint(SpringLayout.WEST, query_password, 6, SpringLayout.EAST, lblNewLabel_6);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, query_password, 57, SpringLayout.SOUTH, query_name);
		sl_contentPane.putConstraint(SpringLayout.EAST, query_password, 72, SpringLayout.EAST, lblNewLabel_6);
		contentPane.add(query_password);
		
		query_level = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, query_level, 23, SpringLayout.SOUTH, query_password);
		sl_contentPane.putConstraint(SpringLayout.EAST, query_level, 0, SpringLayout.EAST, query_id);
		contentPane.add(query_level);
		query_level.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("\u7528\u6237\u7B49\u7EA7");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_7, 0, SpringLayout.SOUTH, query_level);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_7, 0, SpringLayout.EAST, lblNewLabel_4);
		contentPane.add(lblNewLabel_7);
		
		button_query = new JButton("\u67E5\u8BE2");
		button_query.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String suser_id = query_id.getText();
				String suser_name = query_name.getText();
				String suser_password = query_password.getText();
				String suser_level = query_level.getText();
				dlm.removeAllElements();

				Connection con = DButil.getConnect();
				Statement stmt = null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String sql = "select * from s_user where ";
				if(suser_id.isBlank()) {
					
				}else {
					sql = sql +  "userid = "+"'"+suser_id +"' ";
				}
				
				if(suser_name.isBlank()) {
					
				}else {
					if(!suser_id.isBlank()) sql = sql +  "and userid = "+"'"+suser_id +"' ";
					else sql = sql +  "username = "+"'"+suser_name +"' ";
				}
				sql = sql + ";";
				
				try {
					
					ResultSet rs = stmt.executeQuery(sql);
					// 得到元数据
					ResultSetMetaData meta = rs.getMetaData();
					// 返回列的数量
					int colCnt = meta.getColumnCount();
					System.out.println(colCnt);
					s_titles = new String[colCnt];
					
					while (rs.next()) {
//						每一行
						String temp = "";
//						 从结果集中取数据放入向量rec_vector中
						for (int i = 0; i < s_titles.length; i++) {
							String obj = rs.getString(i+1);
							//System.out.print(obj + ";");
							temp+=obj;
							switch(i) {
								case(0):
									query_id.setText(obj);
									break;
								case(1):
									query_name.setText(obj);
									break;
								case(2):
									query_password.setText(obj);
									break;
								case(3):
									query_level.setText(obj);
									break;
							}
							//rec_vector.addElement(obj == null ? null : StringUtils.strip(obj.toString(),"[]"));
							if(i == s_titles.length - 1) {
								temp+="\n";
							}else {
								temp+=",";
							}
						}
						System.out.print(temp);
						dlm.addElement(temp);
						s_record.add(temp);
						//System.out.println();
						
					}
					System.out.println(sql);
					System.out.println("查询成功！");
					JOptionPane.showMessageDialog(null,"查询成功！");	
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"查询失败！");				
					e1.printStackTrace();
				}
				

			
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, button_query, 6, SpringLayout.SOUTH, list);
		sl_contentPane.putConstraint(SpringLayout.WEST, button_query, 44, SpringLayout.EAST, query_id);
		contentPane.add(button_query);
		
		JButton button_motify = new JButton("\u4FEE\u6539");
		button_motify.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String m_id = query_id.getText();
				String m_name = query_name.getText();
				String m_password = query_password.getText();
				String m_level = query_level.getText();
				
				Connection con = DButil.getConnect();
				Statement stmt = null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sql;
				sql = "update s_user set userid= '" + m_id + "', username= '" +m_name+"', password= '"+ m_password+"', level= '"+m_level + "' where userid ='" + m_id + "';";
				try {
					stmt.executeUpdate(sql);
					System.out.println(sql);
					System.out.println("修改成功！");
					JOptionPane.showMessageDialog(null,"修改成功！");	
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"修改失败！");				
					e1.printStackTrace();
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, button_motify, 6, SpringLayout.SOUTH, list);
		sl_contentPane.putConstraint(SpringLayout.WEST, button_motify, 70, SpringLayout.EAST, button_query);
		contentPane.add(button_motify);
	}
	

	public void getData() throws SQLException {
		Connection con = DButil.getConnect();
		// 执行查询
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from s_member");
		// 得到元数据
		ResultSetMetaData meta = rs.getMetaData();
		// 返回列的数量
		int colCnt = meta.getColumnCount();
		System.out.println(colCnt);
		titles = new String[colCnt];
		
		while (rs.next()) {
//			每一行
			String temp = "";
//			 从结果集中取数据放入向量rec_vector中
			for (int i = 0; i < titles.length; i++) {
				String obj = rs.getString(i+1);
				//System.out.print(obj + ";");
				temp+=obj;
				//rec_vector.addElement(obj == null ? null : StringUtils.strip(obj.toString(),"[]"));
				if(i == titles.length - 1) {
					temp+="\n";
				}else {
					temp+=",";
				}
			}
			System.out.print(temp);
			dlm.addElement(temp);
			record.add(temp);
			//System.out.println();
			
		}
		
	}
}
