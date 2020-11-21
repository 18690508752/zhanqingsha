package com.�ϵ»����.account;
import java.awt.BorderLayout;         //�߽粼��
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;           //���ݿ�����
import java.sql.DriverManager;         //����һ��JDBC��������Ļ�������
import java.sql.ResultSet;             //�������ݽ����
import java.sql.SQLException;          //ֻ��ִ��SQLʱ���е�
import java.sql.Statement;             //ִ�в���������SQL���

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//����

public class Account extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JPanel p1;
	private double money;


	public Account() throws SQLException {
	
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch ( ClassNotFoundException c ) {
			
			System.out.println("error:" + c);
			
		}
		
		try {
			
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=kfc", "sa", "woaini9798");
			stmt = con.createStatement( ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY );
			rs = stmt.executeQuery("select * from shoppingCart");
			rs.last();
			
			int num = rs.getRow();
			
			if( num == 0 ) {
				
				JOptionPane.showMessageDialog( this, "����ѯ�ı�Ϊ�ձ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
				
			}
			
			rs.beforeFirst();
			
			
			while( rs.next() ) {
				
				 money += Double.parseDouble(rs.getString("money"));
				
			}
		
			JOptionPane.showMessageDialog( null, "������" + String.valueOf(this.money) + "Ԫ");
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
			
		} finally {
			
			if( rs != null ) {
				
				rs.close();
				
			} 
			
			if( stmt != null ) {
				
				stmt.close();
				
			}
			
			if( con != null ) {
				
				con.close();
				
			}
			
		}
		
		this.setVisible(true);
		
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
