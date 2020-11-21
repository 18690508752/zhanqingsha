package com.�ϵ»����.single;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;



//��Ʒ
public class SingleFood extends JPanel implements ActionListener{
	private JButton buy, jb2, jb3;
	private JTable jt;
	private JPanel p1, p2, p3;
	private JTextField jf1, jf2;
	private double money;
	private int id;
	
	
	public SingleFood() throws SQLException {
		
		p1 = new JPanel(new BorderLayout());
		p1.add(new JLabel("��Ʒ�б�", JLabel.CENTER), "North");
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.microfost.sqlserver.jdbc.SQLServerDriver");
			
		} catch ( ClassNotFoundException c ) {
			
			System.out.println("error:" + c);
			
		}
		
		try {
			
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=kfc", "sa", "woaini9798");
			stmt = con.createStatement( ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY );
			rs = stmt.executeQuery("select * from single");
			rs.last();
			
			int num = rs.getRow();
			
			if( num == 0 ) {
				
				JOptionPane.showMessageDialog( this, "����ѯ�ı�Ϊ�ձ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
				
			}
			
			rs.beforeFirst();
			
			String[][] str = new String[num][3];
			
			for( int i = 0 ; ( i < num ) && ( rs.next() ) ; i++ ) {
				
				str[i][0] = rs.getString("id");
				str[i][1] = rs.getString("name");
				str[i][2] = rs.getString("price");
			}
			
			String[] s = { "ʳƷ���" , "ʳƷ����" , "ʳƷ�۸�" };
			jt = new JTable( str, s );
			jt.setSize( 600, 500 );
			jt.setRowHeight(25);
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();
			render.setHorizontalAlignment(SwingConstants.CENTER);
			jt.getColumn("ʳƷ���").setCellRenderer(render);
			jt.getColumn("ʳƷ����").setCellRenderer(render);
			jt.getColumn("ʳƷ�۸�").setCellRenderer(render);
			
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
		
		
		buy = new JButton("����");

		
		p2 = new JPanel();
		p2.add( new JLabel("ʳƷ���" ));
		
		p3 = new JPanel();
		p3.add( new JLabel("ʳƷ����") );
		
		jf1 = new JTextField(10);
		jf2 = new JTextField(10);
		
		JPanel list = new JPanel( new FlowLayout() );
		
		list.add(p2);
		list.add(jf1);
		list.add(p3);
		list.add(jf2);
		list.add(buy);
		buy.addActionListener(this);
		
		p1.add( list, "South" );
		
		
		this.add(p1);
	
		
		this.setVisible(true);
	}

	@SuppressWarnings("resource")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if ( e.getSource() == buy ) {
			
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				
			} catch ( ClassNotFoundException c) {
				
				System.out.println(c.getLocalizedMessage());
			}
			
			try {
				
				con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=kfc", "sa", "woaini9798");
				stmt = con.createStatement();
				String sql1="select * from single where id='"+jf1.getText()+"'";
				rs = stmt.executeQuery(sql1);
				
				if ( rs.next() ) {
					
					if( !jf2.getText().equals("")) {
						
						SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
						money = Double.parseDouble(rs.getString("price")) * Double.parseDouble(jf2.getText());
						stmt = con.createStatement();
						JOptionPane.showMessageDialog( null, "�ɹ���ӵ����ﳵ");
						id++;
						
					} else {
						
						JOptionPane.showMessageDialog( null, "������ʳƷ����");
						return;
					}
					
				} else {
					
					JOptionPane.showMessageDialog( null, "�������ʳƷ��Ų�����");
					return;
				}
				
				jf1.setText("");
				jf2.setText("");
				
				
			} catch( SQLException s) {
				
				System.out.println(s);
				
			} finally {
				
				if( rs != null ) {
					
					try {
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} 
				
				if( stmt != null ) {
					
					try {
						stmt.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				if( con != null ) {
					
					try {
						
						con.close();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
						
					}
					
				}
			}
		}
	}
	

}
