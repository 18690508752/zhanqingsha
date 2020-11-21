package com.肯德基点餐.print;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
//打印小票
public class PrintReceipt extends JPanel implements ActionListener{
public PrintReceipt() {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch ( ClassNotFoundException c ) {
			
			System.out.println("error:" + c);
			
		}
		
		try {
			
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=kfc","sa","woaini9798");
			stmt = con.createStatement( ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY );
			rs = stmt.executeQuery("select * from shoppingCart");
			rs.last();
			
			int num = rs.getRow();
			
			if( num == 0 ) {
				
				JOptionPane.showMessageDialog( this, "您查询的表为空表", "提示", JOptionPane.WARNING_MESSAGE);
				
			}
			
			rs.beforeFirst();
		
			
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				FileWriter writer = new FileWriter("小票.txt");
				writer.write("\tKFC" + "\n\n");
				writer.write("食品名称\t" + "数量\t" + "价格\t" + "\n");
				
				while( rs.next() ) {
					
					writer.write(rs.getString("foodName") + "\t");
					writer.write(rs.getString("money") + "\t");
					writer.write("\n");
						
				}
			
				
				writer.write("\n日期：" + df.format(new Date()));
				writer.write("\n\n\n\n\n");
				writer.close();
				
			} catch (IOException e) {
				
				JOptionPane.showMessageDialog( this, "打印小票失败", "提示", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
				
			}
			
			JOptionPane.showMessageDialog( this, "成功打印小票");
			
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
