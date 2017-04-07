package com.tech.member.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tech.member.service.impl.DBQueryLogSerImpl;
import com.tech.member.util.Md5Encrypt;

public class Test1 extends JFrame implements ActionListener{
	
	// 一个显示时间的JLabel  
    private JLabel jlTime = new JLabel();  
    private Timer timer;  
  
    public Test1() {  
        setTitle("Timer测试");  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setSize(180, 80);  
        add(jlTime);  
          
        //设置Timer定时器，并启动  
        timer = new Timer(500, this);  
        timer.start();  
        setVisible(true);  
    }  
  
    /**  
     * 执行Timer要执行的部分，  
     */  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date = new Date();  
        jlTime.setText(format.format(date));  
  
    }  
    
    public static void main(String[] args) throws SQLException {  
    	System.out.println(Md5Encrypt.md5("654321"));
    }
}
