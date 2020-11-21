package com.�ϵ»����.menu;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.�ϵ»����.account.Account;
import com.�ϵ»����.activity.Activity;
import com.�ϵ»����.combo.ComboFood;
import com.�ϵ»����.print.PrintReceipt;
import com.�ϵ»����.shop.ShoppingCart;
import com.�ϵ»����.single.SingleFood;
//��ҳ��

public class MainInterface implements ActionListener{
	private JButton singleFood, comboFood, account, activity, shoppingCart, printReceipt, logout;
	private JPanel p;
	private JFrame app;
	
    private static MainInterface mf = new MainInterface();

    // ָ���Լ�ʵ����˽�о�̬���ã���������
 
    // ���Լ�ʵ��Ϊ����ֵ�ľ�̬�Ĺ��з�������̬��������
    public static MainInterface getMF(){
        return mf;
    }
	
	public MainInterface() {
		
		app = new JFrame( "ϵͳ������" );
		app.setDefaultCloseOperation( app.EXIT_ON_CLOSE );
		Container c = app.getContentPane();
		c.setLayout( new BorderLayout(0 ,0) );
		
		JPanel p1 = new JPanel();
		p1.setBackground( Color.WHITE );
		c.add( p1, "West" );
		p1.setLayout( new GridLayout() );
		
		JPanel p2 = new JPanel( new GridLayout(7, 0, 0, 0) );
		p1.add( p2 );
		
		singleFood = new JButton( "��Ʒ" );
		p2.add( singleFood );
		singleFood.addActionListener(this);
		
		comboFood = new JButton("�ײ�");
		p2.add(comboFood);
		comboFood.addActionListener(this);
		
		activity = new JButton("���ڻ");
		p2.add(activity);
		activity.addActionListener(this);
		
		shoppingCart = new JButton("���ﳵ");
		p2.add(shoppingCart);
		shoppingCart.addActionListener(this);
		
		account = new JButton("����");
		p2.add(account);
		account.addActionListener(this);
		
		printReceipt = new JButton("��ӡСƱ");
		p2.add(printReceipt);
		printReceipt.addActionListener(this);
		
		logout = new JButton("�˳�ϵͳ");
		p2.add(logout);
		logout.addActionListener(this);
		
		p = new JPanel();
		ImageIcon icon1 = new ImageIcon("image/bg.jpg");
		icon1.setImage( icon1.getImage().getScaledInstance(580, 490,Image.SCALE_DEFAULT));
		
		JLabel cp1 = new JLabel(icon1);
		//icon1.setImage( icon1.getImage().getScaledInstance(, Image.SCALE_DEFAULT));
		cp1.setSize(200, 500);
		//
		
		p.add(cp1);
		
		JScrollPane spane = new JScrollPane(p);
		c.add(spane, "Center");
		
		JPanel p3 = new JPanel(new GridLayout(3, 1));
		p3.setBackground(Color.GREEN);
		c.add(p3, "South");
		p3.add(new JLabel("�����Ƽ���ѧ", JLabel.CENTER));
		p3.add(new JLabel("�������", JLabel.CENTER));
		p3.add(new JLabel("Copyright 2020", JLabel.CENTER));
		
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4.setBackground(Color.YELLOW);
		c.add(p4, "North");
		
		JLabel welcome = new JLabel("��ӭ����ϵ»�����ϵͳ");
		welcome.setFont(new Font("�п�", 1, 30));
		welcome.setForeground(Color.RED);
		p4.add(welcome, JLabel.CENTER);
		
		
		app.setSize(900, 840);
		app.setLocation(500, 80);
		app.setVisible(true);
		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if( e.getSource() == singleFood ) {
			
			p.setVisible(false);
			p.removeAll();
			
			try {
				
				p.add( new SingleFood() );
				app.setTitle("��Ʒ");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			p.setVisible(true);
			
		}
		
		if( e.getSource() == comboFood ) {
			
			p.setVisible(false);
			p.removeAll();
			
			try {
				
				p.add( new ComboFood() );
				app.setTitle("�ײ�");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			p.setVisible(true);
			
			
		}
		
		if( e.getSource() == activity ) {
			
			new Activity();
			app.setTitle("���ڻ");
			
		}
		
		if( e.getSource() == shoppingCart ) {
			

			p.setVisible(false);
			p.removeAll();
			
			try {
				
				p.add( new ShoppingCart() );
				app.setTitle("���ﳵ");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
				
			}
			
			p.setVisible(true);
			
			
		}
		
		if( e.getSource() == account ) {
			
			try {
				
				app.setTitle("����");
				new Account();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		if( e.getSource() == printReceipt ) {
			
			new PrintReceipt();
			app.setTitle("��ӡСƱ");
		}
		
		
		if( e.getSource() == logout ) {
			
			System.exit(0);
			
		}
	}
	
	/*
	public static void playMusic() {
		
		try {
			
			URL cb;
			File f = new File("music/bg.mp3");
			cb = f.toURL();
			AudioClip aau;
			aau = Applet.newAudioClip(cb);
			aau.play();
			aau.loop();
			
		} catch ( MalformedURLException e ) {
			
			e.printStackTrace();
		}
		
	}
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new MainInterface();

	}

}
