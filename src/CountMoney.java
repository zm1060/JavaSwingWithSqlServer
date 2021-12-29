
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class CountMoney extends JFrame {
    private JTextField time;
    SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private JTextField textField;

    public CountMoney(){
        super();
        setBounds(100, 100, 337, 329);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        time = new JTextField();
        getContentPane().add(time,BorderLayout.NORTH);
        
        textField = new JTextField();
        textField.setText("2345.7");
        getContentPane().add(textField, BorderLayout.CENTER);
        textField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("\u4ECA\u65E5\u4EA4\u6613\u91D1\u989D");
        getContentPane().add(lblNewLabel, BorderLayout.WEST);
        time.addActionListener(new TimeActionListener());
        setVisible(true);

    }

    //添加状态栏“时间文本框”的事件监听器，用来实现动态刷新时间
    class TimeActionListener implements ActionListener{
        public TimeActionListener(){
            javax.swing.Timer t=new javax.swing.Timer(1000,this);
            t.start();
        }

        @Override
        public void actionPerformed(ActionEvent ae){
            time.setText(myfmt.format(new java.util.Date()).toString());
        }
    }

    public static void main(String[] args){
        new CountMoney();
    }

}
