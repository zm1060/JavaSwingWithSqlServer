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

public class ReturnClothing extends JFrame {

	private JPanel contentPane;
	private JTextField input_record_id;
	private JTextField input_member_id;

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
					ReturnClothing frame = new ReturnClothing();
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
	public ReturnClothing() {
		setTitle("\u8FD8\u8863");
		list = new JList(dlm);
		record = new ArrayList<String>();
		s_record = new ArrayList<String>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 624, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("\u4EA4\u6613\u53F7");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 30, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		input_record_id = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, input_record_id, 0, SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, input_record_id, 6, SpringLayout.EAST, lblNewLabel);
		contentPane.add(input_record_id);
		input_record_id.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u8863\u670D\u7C7B\u578B");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 34, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_1);
		
		JComboBox input_clothing_type = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.NORTH, input_clothing_type, 0, SpringLayout.NORTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, input_clothing_type, 0, SpringLayout.EAST, input_record_id);
		contentPane.add(input_clothing_type);
		
		JLabel lblNewLabel_2 = new JLabel("\u4F1A\u5458\u53F7");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 48, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_2);
		
		input_member_id = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, input_member_id, 0, SpringLayout.WEST, input_record_id);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, input_member_id, 0, SpringLayout.SOUTH, lblNewLabel_2);
		contentPane.add(input_member_id);
		input_member_id.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u6D17\u8863\u7C7B\u578B");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 39, SpringLayout.SOUTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_3);
		
		JComboBox input_wash_type = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, input_wash_type, 0, SpringLayout.SOUTH, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.EAST, input_wash_type, 0, SpringLayout.EAST, input_record_id);
		contentPane.add(input_wash_type);
		
		input_wash_type.addItem("水洗");
		input_wash_type.addItem("干洗");
		input_wash_type.addItem("洗鞋");
		input_wash_type.addItem("速洗");
		
		input_clothing_type.addItem("毛衣");
		input_clothing_type.addItem("衬衫");
		input_clothing_type.addItem("棉袄");
		
		JButton button_query = new JButton("\u67E5\u8BE2");
		button_query.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String q_rid = input_record_id.getText();
				String q_ctype = input_clothing_type.getSelectedItem().toString();
				String q_mid = input_member_id.getText();
				String q_wtype = input_wash_type.getSelectedItem().toString();
			
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
								JOptionPane.showMessageDialog(null,"请输入查询信息!");	
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
		sl_contentPane.putConstraint(SpringLayout.WEST, button_query, 10, SpringLayout.WEST, contentPane);
		contentPane.add(button_query);
		
		sl_contentPane.putConstraint(SpringLayout.SOUTH, button_query, 0, SpringLayout.SOUTH, list);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 29, SpringLayout.EAST, input_record_id);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, 268, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, -39, SpringLayout.EAST, contentPane);
		contentPane.add(list);
		
		JButton button_return = new JButton("\u53D6\u8863");
		button_return.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mid = list.getSelectedValue().toString();
				System.out.println(mid);
				String[] id = mid.split(",");
				Connection con = DButil.getConnect();
				Statement stmt = null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sql;
				sql  = "update s_state set status='" + "1' where recordid='"+ id[0]+"';";
				try {
					stmt.executeUpdate(sql);
					System.out.println(sql);
					System.out.println("取衣成功！");
					JOptionPane.showMessageDialog(null,"取衣成功！");	
					dlm.removeAllElements();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"取衣失败！");				
					e1.printStackTrace();
				}

			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, button_return, 6, SpringLayout.SOUTH, list);
		sl_contentPane.putConstraint(SpringLayout.EAST, button_return, -35, SpringLayout.EAST, contentPane);
		contentPane.add(button_return);
	}

}
