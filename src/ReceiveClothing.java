import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.util.DButil;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ReceiveClothing extends JFrame {

	private JPanel contentPane;
	private JTextField input_record_id;
	private JTextField input_member_id;

	public String titles[];
	public String s_titles[];
	public List<String> record;
	public List<String> s_record;
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	JList list;
	private JTextField should_pay;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceiveClothing frame = new ReceiveClothing();
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
	public ReceiveClothing() {
		setTitle("\u6536\u8863");
		list = new JList(dlm);
		record = new ArrayList<String>();
		s_record = new ArrayList<String>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 790, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.EAST, list, -10, SpringLayout.EAST, contentPane);
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("\u4EA4\u6613\u53F7");
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, -1, SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		input_record_id = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, input_record_id, 6, SpringLayout.EAST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, input_record_id, 0, SpringLayout.SOUTH, lblNewLabel);
		contentPane.add(input_record_id);
		input_record_id.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u8863\u670D\u79CD\u7C7B");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 36, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_1);
		
		JComboBox input_clothing_type = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, input_clothing_type, 0, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, input_clothing_type, 0, SpringLayout.EAST, input_record_id);
		contentPane.add(input_clothing_type);
		
		JLabel lblNewLabel_2 = new JLabel("\u4F1A\u5458\u53F7");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 42, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_2);
		
		input_member_id = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, input_member_id, 0, SpringLayout.WEST, input_record_id);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, input_member_id, 0, SpringLayout.SOUTH, lblNewLabel_2);
		contentPane.add(input_member_id);
		input_member_id.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u6D17\u8863\u7C7B\u578B");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_3);
		
		JComboBox input_wash_type = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.NORTH, input_wash_type, 37, SpringLayout.SOUTH, input_member_id);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 4, SpringLayout.NORTH, input_wash_type);
		sl_contentPane.putConstraint(SpringLayout.WEST, input_wash_type, 0, SpringLayout.WEST, input_clothing_type);
		contentPane.add(input_wash_type);
		
		input_wash_type.addItem("水洗");
		input_wash_type.addItem("干洗");
		input_wash_type.addItem("洗鞋");
		input_wash_type.addItem("速洗");
		
		input_clothing_type.addItem("毛衣");
		input_clothing_type.addItem("衬衫");
		input_clothing_type.addItem("棉袄");
		
		JButton button_add = new JButton("\u6DFB\u52A0");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, -11, SpringLayout.SOUTH, button_add);
		button_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String a_rid = input_record_id.getText();
				String a_ctype = input_clothing_type.getSelectedItem().toString();
				String a_mid = input_member_id.getText();
				String a_wtype = input_wash_type.getSelectedItem().toString();
				String should_p;
				
				String data = a_rid + "," + a_ctype + "," + a_mid + ","+ a_wtype +"\n";
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
				String sql_query;
				sql_query = "select * from s_wash where type='" + a_wtype+"';";
				
				should_p = "";
				try {
					
					ResultSet rs = stmt.executeQuery(sql_query);
					// 得到元数据
					ResultSetMetaData meta = rs.getMetaData();
					// 返回列的数量
					int colCnt = meta.getColumnCount();
					System.out.println(colCnt);
					while(rs.next()) {
						System.out.println(rs.getString(2));
						should_p = rs.getString(2);
					}


					should_pay.setText(should_p);
					System.out.println(sql_query);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block			
					e1.printStackTrace();
				}
				
				sql = "insert into s_records(recordid,clothingtype,memberid,washtype,shouldmoney) values(" + "'" + a_rid + "',"+ "'" + a_ctype + "','" + a_mid+"','" + a_wtype +"','"+should_p +"');";
				try {
					stmt.executeUpdate(sql);
					System.out.println(sql);
					System.out.println("插入成功！");
					JOptionPane.showMessageDialog(null,"添加成功！");	
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"添加失败！");				
					e1.printStackTrace();
				}
				
			
				

			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, button_add, 44, SpringLayout.SOUTH, input_wash_type);
		sl_contentPane.putConstraint(SpringLayout.WEST, button_add, 0, SpringLayout.WEST, contentPane);
		contentPane.add(button_add);
		
		JButton button_cancle = new JButton("\u53D6\u6D88");
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 68, SpringLayout.EAST, button_cancle);
		sl_contentPane.putConstraint(SpringLayout.WEST, button_cancle, 7, SpringLayout.EAST, button_add);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, button_cancle, 0, SpringLayout.SOUTH, button_add);
		contentPane.add(button_cancle);
		
		JButton button_query = new JButton("\u67E5\u8BE2");
		button_query.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String q_rid = input_record_id.getText();
				String q_ctype = input_clothing_type.getSelectedItem().toString();
				String q_mid = input_member_id.getText();
				String q_wtype = input_wash_type.getSelectedItem().toString();
				String q_shouldp = "";
				dlm.removeAllElements();

				Connection con = DButil.getConnect();
				Statement stmt = null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String sql = "select * from s_records where ";
				if(!q_rid.isBlank()) {
					sql = "select * from s_records where recordid ='" + q_rid + "';";
				}else {
					if(!q_mid.isBlank()) {
						sql = "select * from s_records where memberid ='" + q_mid + "';";
					}else {
						if(!q_ctype.isBlank()) {
							sql = "select * from s_records where clothingtype ='" + q_ctype + "';";
						}else {
							if(!q_wtype.isBlank()) {
								sql = "select * from s_records where washtype ='" + q_wtype + "';";
							}else {
								if(!q_shouldp.isBlank()) {
									sql = "select * from s_records where shouldmoney ='" + q_shouldp + "';";
								}else {
									JOptionPane.showMessageDialog(null,"请输入查询信息!");	
								}
							}
						}
					}
					
				}
				
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
									input_record_id.setText(obj);
									break;
								case(1):
									input_clothing_type.setSelectedItem(obj);;
									break;
								case(2):
									input_member_id.setText(obj);
									break;
								case(3):
									input_wash_type.setSelectedItem(obj);
									break;
								case(4):
									should_pay.setText(obj);
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
		sl_contentPane.putConstraint(SpringLayout.NORTH, button_query, 30, SpringLayout.SOUTH, button_add);
		sl_contentPane.putConstraint(SpringLayout.WEST, button_query, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(button_query);
		contentPane.add(list);
		
		JButton button_motify = new JButton("\u4FEE\u6539");
		button_motify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String q_rid = input_record_id.getText();
				String q_ctype = input_clothing_type.getSelectedItem().toString();
				String q_mid = input_member_id.getText();
				String q_wtype = input_wash_type.getSelectedItem().toString();
				String q_shouldp = "";
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
				String sql_query;
				sql_query = "select * from s_wash where type='" + q_wtype+"';";
				
				
				try {
					
					ResultSet rs = stmt.executeQuery(sql_query);
					// 得到元数据
					ResultSetMetaData meta = rs.getMetaData();
					// 返回列的数量
					int colCnt = meta.getColumnCount();
					System.out.println(colCnt);
					while(rs.next()) {
						System.out.println(rs.getString(2));
						q_shouldp = rs.getString(2);
					}


					should_pay.setText(q_shouldp);
					System.out.println(sql_query);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block			
					e1.printStackTrace();
				}
				sql = "update s_records set recordid= '" + q_rid + "', clothingtype= '" +q_ctype+"', memberid= '"+q_mid +"', washtype='"+q_wtype+"', shouldmoney='"+ q_shouldp  +"' where recordid ='" + q_rid + "';";
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
		sl_contentPane.putConstraint(SpringLayout.NORTH, button_motify, 0, SpringLayout.NORTH, button_query);
		sl_contentPane.putConstraint(SpringLayout.WEST, button_motify, 0, SpringLayout.WEST, button_cancle);
		contentPane.add(button_motify);
		
		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String d_rid = input_record_id.getText();
				Connection con = DButil.getConnect();
				Statement stmt = null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sql;
				sql = "delete from s_record where recordid ='" + d_rid + "';";
				try {
					stmt.executeUpdate(sql);
					System.out.println(sql);
					System.out.println("删除成功！");
					JOptionPane.showMessageDialog(null,"删除成功！");	
					dlm.removeAllElements();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"删除失败！");				
					e1.printStackTrace();
				}
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, button_delete, 0, SpringLayout.NORTH, button_query);
		sl_contentPane.putConstraint(SpringLayout.WEST, button_delete, 6, SpringLayout.EAST, button_motify);
		contentPane.add(button_delete);
		
		JLabel s_pay = new JLabel("\u5E94\u4ED8\u91D1\u989D");
		sl_contentPane.putConstraint(SpringLayout.WEST, s_pay, 0, SpringLayout.WEST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, s_pay, -9, SpringLayout.NORTH, button_add);
		contentPane.add(s_pay);
		
		should_pay = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, should_pay, -97, SpringLayout.WEST, list);
		sl_contentPane.putConstraint(SpringLayout.EAST, should_pay, -10, SpringLayout.WEST, list);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, should_pay, -6, SpringLayout.NORTH, button_cancle);
		contentPane.add(should_pay);
		should_pay.setColumns(10);
		

		
	}

}
