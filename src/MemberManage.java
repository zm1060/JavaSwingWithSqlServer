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

import org.eclipse.jface.dialogs.MessageDialog;

import com.util.DButil;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemberManage extends JFrame {

	private JPanel contentPane;
	private JTextField input_member_money;
	private JTextField input_member_name;
	private JTextField input_member_id;
	private JTextField query_id;
	private JTextField query_name;
	private JTextField query_money;
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
					MemberManage frame = new MemberManage();
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
	public MemberManage() {
		setTitle("\u4F1A\u5458\u7BA1\u7406");
		list = new JList(dlm);
		record = new ArrayList<String>();
		s_record = new ArrayList<String>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("\u4F1A\u5458\u53F7");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u4F1A\u5458\u540D");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 24, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, lblNewLabel);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u91D1\u989D");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 37, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String smemberid = input_member_id.getText();
				String smembername = input_member_name.getText();
				String smembermoney = input_member_money.getText();
				dlm.removeAllElements();
				String data = smemberid + "," + smembername + "," + smembermoney +"\n";
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
				sql = "insert into s_member(memberid,membername,membermoney) values(" + "'" + smemberid + "',"+ "'" + smembername + "'," + smembermoney +");";
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
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 10, SpringLayout.WEST, contentPane);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_1, 19, SpringLayout.EAST, btnNewButton);
		contentPane.add(btnNewButton_1);
		
		input_member_money = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 22, SpringLayout.SOUTH, input_member_money);
		sl_contentPane.putConstraint(SpringLayout.NORTH, input_member_money, -3, SpringLayout.NORTH, lblNewLabel_2);
		contentPane.add(input_member_money);
		input_member_money.setColumns(10);
		
		input_member_name = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, input_member_name, 29, SpringLayout.EAST, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, input_member_money, 0, SpringLayout.WEST, input_member_name);
		sl_contentPane.putConstraint(SpringLayout.NORTH, input_member_name, 0, SpringLayout.NORTH, lblNewLabel_1);
		contentPane.add(input_member_name);
		input_member_name.setColumns(10);
		
		input_member_id = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, input_member_id, 29, SpringLayout.EAST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, input_member_id, 0, SpringLayout.SOUTH, lblNewLabel);
		contentPane.add(input_member_id);
		input_member_id.setColumns(10);
		
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 12, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 68, SpringLayout.EAST, btnNewButton_1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, 0, SpringLayout.SOUTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, -15, SpringLayout.EAST, contentPane);
		contentPane.add(list);
		
		JButton button_query = new JButton("\u67E5\u8BE2");
		button_query.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String smember_id = query_id.getText();
				String smember_name = query_name.getText();
				String smember_money = query_money.getText();
				dlm.removeAllElements();

				Connection con = DButil.getConnect();
				Statement stmt = null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String sql = "select * from s_member where ";
				if(smember_id.isBlank()) {
					
				}else {
					sql = sql +  "memberid = "+"'"+smember_id +"' ";
				}
				
				if(smember_name.isBlank()) {
					
				}else {
					if(!smember_id.isBlank()) sql = sql +  "and memberid = "+"'"+smember_id +"' ";
					else sql = sql +  "membername = "+"'"+smember_name +"' ";
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
									query_money.setText(obj);
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
		sl_contentPane.putConstraint(SpringLayout.WEST, button_query, 0, SpringLayout.WEST, list);
		contentPane.add(button_query);
		
		JButton btnNewButton_3 = new JButton("\u4FEE\u6539");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_3, 0, SpringLayout.NORTH, button_query);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String m_id = query_id.getText();
				String m_name = query_name.getText();
				String m_money = query_money.getText();
				dlm.removeAllElements();
				Connection con = DButil.getConnect();
				Statement stmt = null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sql;
				sql = "update s_member set memberid= '" + m_id + "', membername= '" +m_name+"', membermoney= '"+m_money+"' where memberid ='" + m_id + "';";
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
		contentPane.add(btnNewButton_3);
		
		query_id = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, button_query, 8, SpringLayout.SOUTH, query_id);
		sl_contentPane.putConstraint(SpringLayout.NORTH, query_id, 6, SpringLayout.SOUTH, list);
		sl_contentPane.putConstraint(SpringLayout.WEST, query_id, 0, SpringLayout.WEST, list);
		contentPane.add(query_id);
		query_id.setColumns(10);
		
		query_name = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, query_name, 6, SpringLayout.SOUTH, list);
		contentPane.add(query_name);
		query_name.setColumns(10);
		
		query_money = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, query_money, -6, SpringLayout.NORTH, btnNewButton_3);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_3, 0, SpringLayout.WEST, query_money);
		contentPane.add(query_money);
		query_money.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u4F1A\u5458\u53F7");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, 0, SpringLayout.SOUTH, query_id);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_3, -6, SpringLayout.WEST, query_id);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u4F1A\u5458\u540D");
		sl_contentPane.putConstraint(SpringLayout.WEST, query_name, 6, SpringLayout.EAST, lblNewLabel_4);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 3, SpringLayout.NORTH, query_id);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_4, 47, SpringLayout.EAST, query_id);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u91D1\u989D");
		sl_contentPane.putConstraint(SpringLayout.WEST, query_money, 23, SpringLayout.EAST, lblNewLabel_5);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 6, SpringLayout.SOUTH, list);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_5, 30, SpringLayout.EAST, query_name);
		contentPane.add(lblNewLabel_5);
	}
	
	

	public void getData() throws SQLException {
		Connection con = DButil.getConnect();
		// 执行查询
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from s_user");
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
