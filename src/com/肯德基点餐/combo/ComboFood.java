package com.肯德基点餐.combo;
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
import java.text.SimpleDateFormat;       //允许你开始选择任何用户定义的模式为日期时间模式
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
//套餐
public class ComboFood extends JPanel implements ActionListener {
	private JButton buy, jb2, jb3;
	private JTable jt;
	private JPanel p1, p2, p3;
	private JTextField jf1, jf2;
	private double money;
	private int num;
	private int id;
	
	public ComboFood() throws SQLException {
		
		p1 = new JPanel(new BorderLayout());
		p1.add(new JLabel("套餐列表", JLabel.CENTER), "North");
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.microsft.sqlserver.jdbc.SQLServerDriver");
			
		} catch ( ClassNotFoundException c ) {
			
			System.out.println("error:" + c);
			
		}
		
		try {
			
			
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=kfc", "sa", "woaini9798");
			stmt = con.createStatement( ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY );
			rs = stmt.executeQuery("select * from combo");
			rs.last();
			
			int num = rs.getRow();
			
			if( num == 0 ) {
				
				JOptionPane.showMessageDialog( this, "您查询的表为空表", "提示", JOptionPane.WARNING_MESSAGE);
				
			}
			
			rs.beforeFirst();
			
			String[][] str = new String[num][3];
			
			for( int i = 0 ; ( i < num ) && ( rs.next() ) ; i++ ) {
				
				str[i][0] = rs.getString("id");
				str[i][1] = rs.getString("name");
				str[i][2] = rs.getString("price");
			}
			
			String[] s = { "食品编号" , "食品名称" , "食品价格" };
			jt = new JTable( str, s );
			jt.setSize( 600, 300 );
			jt.setRowHeight(30);
			
			DefaultTableCellRenderer render = new DefaultTableCellRenderer();
			render.setHorizontalAlignment(SwingConstants.CENTER);
			jt.getColumn("食品编号").setCellRenderer(render);
			jt.getColumn("食品名称").setCellRenderer(render);
			jt.getColumn("食品价格").setCellRenderer(render);
			
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
		
		
		buy = new JButton("购买");

		
		p2 = new JPanel();
		p2.add( new JLabel("食品编号" ));
		
		p3 = new JPanel();
		p3.add( new JLabel("食品数量") );
		
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
				String sql1="select * from combo where id='"+jf1.getText()+"'";
				rs = stmt.executeQuery(sql1);
				
				if ( rs.next() ) {
					
					if( !jf2.getText().equals("")) {
						
						SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
						money = Double.parseDouble(rs.getString("price")) * Double.parseDouble(jf2.getText());
						String sql2 = "insert into shoppingCart(buytime,foodname,foodprice,foodnum,money) values('"+df.format(new Date())+"','"+rs.getString("name")+"','"+rs.getString("price")+"','"+jf2.getText()+"','"+money+"')";
						stmt = con.createStatement();
						stmt.executeUpdate(sql2);
						JOptionPane.showMessageDialog( null, "成功添加到购物车");
						id++;
						
					} else {
						
						JOptionPane.showMessageDialog( null, "请输入食品数量");
						return;
					}
					
				} else {
					
					JOptionPane.showMessageDialog( null, "您输入的食品编号不存在");
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
						}catch(SQLException e1) {
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

	
