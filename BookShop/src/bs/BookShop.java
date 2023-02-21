package bs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookShop extends JFrame {

	private JPanel contentPane;
	private JTextField txtbname;
	private JTextField txtbedition;
	private JTextField txtbprice;
	private JTextField txtsearch;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookShop frame = new BookShop();
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
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void  connect()
	{
		try 
		{

			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/bookshop","root","");
			
			System.out.println("success");


		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			
		}
	}
	
	
	
	public void LoadTable()
	{
		try 
		{
			
			pst=(PreparedStatement) con.prepareStatement("select * from books");

			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} 
			catch (Exception e) 
				{
					// TODO: handle exception
					System.out.println(e);
				}
	}
	public BookShop()
	{
		connect();
		init();
		LoadTable();
	}
	
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1045, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel BookShop = new JLabel("Book Shop");
		BookShop.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		BookShop.setBounds(415, 20, 234, 33);
		contentPane.add(BookShop);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(29, 79, 365, 217);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel BookName = new JLabel("BookName");
		BookName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		BookName.setBounds(25, 34, 118, 31);
		panel.add(BookName);
		
		JLabel Edition = new JLabel("Edition");
		Edition.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Edition.setBounds(25, 86, 118, 31);
		panel.add(Edition);
		
		JLabel Price = new JLabel("Price");
		Price.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Price.setBounds(25, 150, 118, 31);
		panel.add(Price);
		
		txtbname = new JTextField();
		txtbname.setBounds(180, 33, 151, 31);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtbedition = new JTextField();
		txtbedition.setBounds(180, 97, 151, 31);
		panel.add(txtbedition);
		txtbedition.setColumns(10);
		
		txtbprice = new JTextField();
		txtbprice.setBounds(180, 151, 151, 31);
		panel.add(txtbprice);
		txtbprice.setColumns(10);
		
		JButton btnsave = new JButton("SAVE");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String name,edition,price;
				
				name=txtbname.getText();
				edition=txtbedition.getText();
				price=txtbprice.getText();
				
				
				try
				{
					pst=(PreparedStatement) con.prepareStatement("insert into books(name,edition,price) values (?,?,?)");
					pst.setString(1,name);
					pst.setString(2, edition);
					pst.setString(3, price);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record addded");
					LoadTable();
					
					txtbname.setText("");
					txtbedition.setText("");
					txtbprice.setText("");
				} 
					catch (Exception e2) 
					{
						// TODO: handle exception
						System.out.println(e2);
					}
			}
		});
		btnsave.setBounds(29, 346, 85, 33);
		contentPane.add(btnsave);
		
		JButton btnexit = new JButton("EXIT");
		btnexit.setBounds(174, 346, 85, 33);
		contentPane.add(btnexit);
		
		JButton btnclear = new JButton("CLEAR");
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txtbname.setText("");
				txtbedition.setText("");
				txtbprice.setText("");
			}
		});
		btnclear.setBounds(309, 346, 85, 33);
		contentPane.add(btnclear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(29, 405, 365, 111);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel bid = new JLabel("Book ID");
		bid.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bid.setBounds(10, 37, 118, 31);
		panel_1.add(bid);
		
		txtsearch = new JTextField();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				
				try 
				{
					String id=txtsearch.getText();
					pst=(PreparedStatement) con.prepareStatement("select * from books");
					pst.setString(1, id);
					rs=pst.executeQuery();
					
					if(rs.next()==true)
					{
						String name,edition,price;
						name=rs.getString(2);
						edition=rs.getString(3);
						price=rs.getString(4);
						
						txtbname.setText(name);
						txtbedition.setText(edition);
						txtbprice.setText(price);
					}
					else
					{
						txtbname.setText("");
						txtbedition.setText("");
						txtbprice.setText("");
					}
					
		
				}
					catch (Exception e2) 
						{
							// TODO: handle exception
						}
			}
		});
		txtsearch.setColumns(10);
		txtsearch.setBounds(160, 44, 151, 31);
		panel_1.add(txtsearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(458, 86, 532, 316);
		contentPane.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 530, 304);
		panel_2.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Edition", "Price"
			}
		));
		scrollPane_1.setViewportView(table);
		
		JButton btnupdate = new JButton("UPDATE");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				String name,edition,price,id;
				
				name=txtbname.getText();
				edition=txtbedition.getText();
				price=txtbprice.getText();
				id=txtsearch.getText();
				
				try 
				{
					
					pst=(PreparedStatement)  con.prepareStatement("update books (name,edition,price) values(?,?,?) where id=? " );
					
					pst.setString(1, name);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, id);
					
					pst.executeUpdate();
					
					LoadTable();
					
					
					
					
				}
					catch (SQLException e1) 
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
			}
		});
		btnupdate.setBounds(569, 458, 85, 33);
		contentPane.add(btnupdate);
		
		JButton btndelete = new JButton("DELETE");
		btndelete.setBounds(714, 458, 85, 33);
		contentPane.add(btndelete);
	}
}
