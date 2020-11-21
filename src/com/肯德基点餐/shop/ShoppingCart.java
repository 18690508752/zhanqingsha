package com.�ϵ»����.shop;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.�ϵ»����.account.Account;
//���ﳵ

public class ShoppingCart extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton statistic;
	private JTable jt;
	private JPanel p1;
	


	public ShoppingCart() throws SQLException {
		
		p1 = new JPanel(new BorderLayout());
		p1.add(new JLabel("���ﳵ", JLabel.CENTER), "North");
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.microsoft.ssqlserver.jdbc.SQLServerDriver");
			
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
			
			String[][] str = new String[num][3];
			
			for( int i = 0 ; ( i < num ) && ( rs.next() ) ; i++ ) {
				
				str[i][0] = rs.getString("foodName");
				str[i][1] = rs.getString("money");
				str[i][2] = rs.getString("buyTime");
			}
			
			String[] s = { "ʳƷ����" , "ʳƷ�۸�" , "����ʱ��" };
			jt = new JTable( str, s );
			jt.setSize( 600, 500 );
			jt.setRowHeight(25);
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();
			render.setHorizontalAlignment(SwingConstants.CENTER);
			jt.getColumn("ʳƷ����").setCellRenderer(render);
			jt.getColumn("ʳƷ�۸�").setCellRenderer(render);
			jt.getColumn("����ʱ��").setCellRenderer(render);
			
			JScrollPane jsp = new JScrollPane( jt );
			jsp.setPreferredSize( new Dimension( jt.getWidth() - 100, jt.getHeight() - 100 ));
			
			p1.add( jsp, "Center");
			
			con.close();
			
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
		
		
		statistic = new JButton("����");

	
		JPanel list = new JPanel( new FlowLayout() );
		list.add(statistic);
		statistic.addActionListener(this);
		
		p1.add( list, "South" );
		
		
		this.add(p1);
	
		
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if( e.getSource() == statistic ) {
			
			try {
				
				new Account();
	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

}
