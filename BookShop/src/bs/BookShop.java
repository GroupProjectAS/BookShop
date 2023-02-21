package bs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BookShop extends JFrame {

	private JPanel contentPane;
	private JTextField bname;
	private JTextField bedition;
	private JTextField bprice;
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
	
	public BookShop()
	{
		init();
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
		
		bname = new JTextField();
		bname.setBounds(180, 33, 151, 31);
		panel.add(bname);
		bname.setColumns(10);
		
		bedition = new JTextField();
		bedition.setBounds(180, 97, 151, 31);
		panel.add(bedition);
		bedition.setColumns(10);
		
		bprice = new JTextField();
		bprice.setBounds(180, 151, 151, 31);
		panel.add(bprice);
		bprice.setColumns(10);
		
		JButton btnsave = new JButton("SAVE");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnsave.setBounds(29, 346, 85, 33);
		contentPane.add(btnsave);
		
		JButton btnexit = new JButton("EXIT");
		btnexit.setBounds(174, 346, 85, 33);
		contentPane.add(btnexit);
		
		JButton btnclear = new JButton("CLEAR");
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
		scrollPane_1.setViewportView(table);
		
		JButton btnupdate = new JButton("UPDATE");
		btnupdate.setBounds(569, 458, 85, 33);
		contentPane.add(btnupdate);
		
		JButton btndelete = new JButton("DELETE");
		btndelete.setBounds(714, 458, 85, 33);
		contentPane.add(btndelete);
	}
}
