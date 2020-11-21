package com.肯德基点餐.account;
import java.awt.BorderLayout;         //边界布局
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;           //数据库连接
import java.sql.DriverManager;         //管理一组JDBC驱动程序的基本服务
import java.sql.ResultSet;             //缓存数据结果集
import java.sql.SQLException;          //只在执行SQL时才有的
import java.sql.Statement;             //执行不带参数的SQL语句

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//共计

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
				
				JOptionPane.showMessageDialog( this, "您查询的表为空表", "提示", JOptionPane.WARNING_MESSAGE);
				
			}
			
			rs.beforeFirst();
			
			
			while( rs.next() ) {
				
				 money += Double.parseDouble(rs.getString("money"));
				
			}
		
			JOptionPane.showMessageDialog( null, "共花费" + String.valueOf(this.money) + "元");
			
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
