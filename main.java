package sda;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.JSONArray;
import org.w3c.dom.events.MouseEvent;

public class main {
	public static ArrayList<TimeFrame> tf=new ArrayList<TimeFrame>();
	public static IntervalMap<String,ArrayList<Interval>> mp=new IntervalMap<String,ArrayList<Interval>>();
	public static ArrayList<String> Pairs=new ArrayList<String>();
	public static ArrayList<String> TimeFrameList=new ArrayList<String>();
	public static ArrayList<String> patterns = new ArrayList<String>();
	public static RBTree PairsData = new RBTree();
	public static CryptoPair selectedPair=null;
	public static String selectedTimeFrame=null;
	public static String selectedPattern=null;
	public static JSONArray precisions=null;
	public static void main(String[] args) {
		precisions=DataRequest.GetPrecision();
	
	TimeFrameList.add("1m");
	TimeFrameList.add("5m");
	TimeFrameList.add("15m");
	TimeFrameList.add("30m");
	TimeFrameList.add("1h");
	TimeFrameList.add("4h");
	TimeFrameList.add("1d");
	TimeFrameList.add("1w");
	
     

    
     for(int i=0;i<tf.size();i++) {
    	for(int j=0;j<patterns.size();j++) {
    	 String key=tf.get(i).getTimeFrame()+patterns.get(j);
    	 
    	 mp.add(key,KMP.KMPSearch(patterns.get(j), tf.get(i)));}
    	 
     }

    	 for(int i=0;i<tf.size();i++) {
    		 System.out.println(tf.get(i).getTimeFrame());
    	    	for(int j=0;j<patterns.size();j++) {
    	    		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+patterns.get(j));
    	    		String key=tf.get(i).getTimeFrame()+patterns.get(j);
    	    		ArrayList<Interval> aux=mp.get(key);
    	    		
    	    		for(int k=0;k<aux.size();k++) {
    	    			System.out.println(aux.get(k).getAvgPrice());
    	    			
    	    		}
    	    		}
    	    	}
    	 showWindowPairs();
	
	
	}
public static int PairNameToInt(String pair) {
	
	int s=0;
	String str= pair.toLowerCase();
	for(int i=0;i<str.length();i++) {
		if(Character.isDigit(str.charAt(i))) {
			s=s*10+Integer.parseInt(String.valueOf(str.charAt(i)));  
			
			
		}
		else {
			int aux=(int) str.charAt(i);
			while (aux!=0) {
				s=(s*10)+(aux%10);
				aux=aux/10;
				
				
			}
			
			
		}
		
	}
	return s;
	
	
}
public static void addCryptoPair(String PairName) {
	CryptoPair c=new CryptoPair(PairName.toUpperCase());
	PairsData.insertNode(PairNameToInt(PairName), c);
	Pairs.add(PairName);
	
}
public static void addPattern(String pattern) {
	patterns.add(pattern);
	for(int i=0;i<Pairs.size();i++) {
		PairsData.searchNode(PairNameToInt(Pairs.get(i))).CryptoData.addPattern(pattern);
	}
}
public static void deletePattern(String pattern) {
	for(int i=0;i<patterns.size();i++) {if(patterns.get(i).equals(pattern)){patterns.remove(i);}}
	
}
public static void deleteCryptoPair(String pair) {
	for(int i=0;i<Pairs.size();i++) {if(Pairs.get(i).equals(pair)) {Pairs.remove(i);}}
	PairsData.deleteNode(PairNameToInt(pair));
	}


public static void showWindowPairs() {
	
	
	
	
	final JFrame frame = new JFrame("Pairs");
	final JEditorPane editorPane = new JEditorPane();
	JButton btnNewButton = new JButton("Add");
	JButton btnNewButton_1 = new JButton("Next");
	JButton btnNewButton_2 = new JButton("Delete");
	frame.setBounds(200, 300,460,649);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JList list = new JList();
	list.setBounds(345, 177, -322, -153);
	frame.getContentPane().add(list);
	final DefaultListModel<String> model = new DefaultListModel<>();
	final JList<String> list_1 = new JList<>( model );
	for(int i=0;i<Pairs.size();i++) {model.addElement( Pairs.get(i) );
}
	list_1.setBounds(10, 11, 426, 207);
	frame.getContentPane().add(list_1);
	
	
	
	btnNewButton.setBounds(10, 229, 89, 23);
	frame.getContentPane().add(btnNewButton);
	btnNewButton.addActionListener(new ActionListener() {

		public void actionPerformed(java.awt.event.ActionEvent e) {
			String newPair=editorPane.getText();
			addCryptoPair(newPair);
			model.addElement(newPair);
			
		}});
	
	
	editorPane.setBounds(109, 229, 291, 20);
	frame.getContentPane().add(editorPane);
	
	
	btnNewButton_1.setBounds(109, 279, 89, 23);
	frame.getContentPane().add(btnNewButton_1);
	btnNewButton_1.addActionListener(new ActionListener() {

		public void actionPerformed(java.awt.event.ActionEvent e) {
			selectedPair=PairsData.searchNode(PairNameToInt(list_1.getSelectedValue())).CryptoData;
			frame.setVisible(false);
			ShowWindowTimeFrame();
			
			
		}});
	

	btnNewButton_2.setBounds(245, 279, 89, 23);
	frame.getContentPane().add(btnNewButton_2);
	btnNewButton_2.addActionListener(new ActionListener() {

		public void actionPerformed(java.awt.event.ActionEvent e) {
			deleteCryptoPair(list_1.getSelectedValue());
		model.remove(list_1.getSelectedIndex());
			
		}});
	

	
	
	
		
			//w1.showWindow();
			
		
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
}
	








public static void ShowWindowTimeFrame() {
	final JFrame frame = new JFrame("w1");
	frame.setBounds(200, 300,460,649);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JButton btnNewButton = new JButton("Next");
	btnNewButton.setBounds(79, 262, 131, 36);
	frame.getContentPane().add(btnNewButton);
	
	
	JButton BackToBudapest = new JButton("Back");
	BackToBudapest.setBounds(220, 262, 131, 36);
    frame.getContentPane().add(BackToBudapest);
    BackToBudapest.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			showWindowPairs();
		}});

	
	
    final DefaultListModel<String> model = new DefaultListModel<>();
	final JList<String> list = new JList<>( model );
	frame.getContentPane().add(list);
	list.setBounds(10, 11, 426, 240);
	frame.getContentPane().add(list);
	
	
	for(int i=0;i<TimeFrameList.size();i++) {model.addElement( TimeFrameList.get(i) );
}
	
	
	
	
	btnNewButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			selectedTimeFrame=list.getSelectedValue();
			frame.setVisible(false);
			
			showWindowPatterns();
			
		}});
	
	
	
	
	
	frame.setLocationRelativeTo(null);

	frame.setVisible(true);
	
}






public static void showWindowPatterns() {
	
	
	
	
	final JFrame frame = new JFrame("Patterns");
	final JEditorPane editorPane = new JEditorPane();
	JButton btnNewButton = new JButton("Add");
	JButton btnNewButton_1 = new JButton("Next");
	JButton btnNewButton_2 = new JButton("Delete");
	frame.setBounds(200, 300,460,649);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JList list = new JList();
	list.setBounds(345, 177, -322, -153);
	frame.getContentPane().add(list);
	final DefaultListModel<String> model = new DefaultListModel<>();
	final JList<String> list_1 = new JList<>( model );
	for(int i=0;i<patterns.size();i++) {model.addElement( patterns.get(i) );
}
	list_1.setBounds(10, 11, 426, 207);
	frame.getContentPane().add(list_1);
	
	
	
	btnNewButton.setBounds(10, 229, 89, 23);
	frame.getContentPane().add(btnNewButton);
	btnNewButton.addActionListener(new ActionListener() {

		public void actionPerformed(java.awt.event.ActionEvent e) {
			String newPair=editorPane.getText();
		addPattern(newPair);
			model.addElement(newPair);
			
		}});
	
	
	editorPane.setBounds(109, 229, 291, 20);
	frame.getContentPane().add(editorPane);
	
	
	btnNewButton_1.setBounds(44, 279, 89, 23);
	frame.getContentPane().add(btnNewButton_1);
	btnNewButton_1.addActionListener(new ActionListener() {

		public void actionPerformed(java.awt.event.ActionEvent e) {
			selectedPattern=list_1.getSelectedValue();
			
			frame.setVisible(false);
			showSelectPriceLevel();
			//ShowWindowTimeFrame();
			
			
		}});
	

	btnNewButton_2.setBounds(311, 279, 89, 23);
	frame.getContentPane().add(btnNewButton_2);
	btnNewButton_2.addActionListener(new ActionListener() {

		public void actionPerformed(java.awt.event.ActionEvent e) {
			deletePattern(list_1.getSelectedValue());
		model.remove(list_1.getSelectedIndex());
			
		}});
	
	JButton BackToBudapest = new JButton("Back");
	BackToBudapest.setBounds(174, 279, 89, 23);
    frame.getContentPane().add(BackToBudapest);
    BackToBudapest.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			ShowWindowTimeFrame();
		}});
	
	
		
			//w1.showWindow();
			
		
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
}




public static void showSelectPriceLevel() {
	final JFrame frame = new JFrame("PeiceLevel");
	frame.setBounds(200, 300,460,649);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	final JButton btnNewButton = new JButton("Buy");
	btnNewButton.setBounds(72, 244, 89, 23);
	frame.getContentPane().add(btnNewButton);
	btnNewButton.setEnabled(false);
	
	final JButton btnNewButton_1 = new JButton("Sell");
	btnNewButton_1.setBounds(171, 244, 89, 23);
	frame.getContentPane().add(btnNewButton_1);
	btnNewButton_1.setEnabled(false);
	
	JButton btnNewButton_2 = new JButton("Back");
	btnNewButton_2.setBounds(270, 244, 89, 23);
	frame.getContentPane().add(btnNewButton_2);
	
	
	ArrayList<Interval> pricelist=selectedPair.GetPriceListByKey(selectedTimeFrame+selectedPattern);
	final DefaultListModel<Double> model = new DefaultListModel<>();
	for(int i=0;i<pricelist.size();i++) {model.addElement( BigDecimal.valueOf(pricelist.get(i).getAvgPrice()).setScale(selectedPair.getPricePrecision(),RoundingMode.HALF_UP).doubleValue() );
	}
	final JList<Double> list_1 = new JList<>( model );
	
	list_1.setBounds(10, 11, 426, 217);
	
	

	System.out.println(pricelist.size());
	JScrollPane jsp=new JScrollPane(list_1);
	jsp.setBounds(10, 11, 426, 217);
	frame.getContentPane().add(jsp);
	
	
	btnNewButton_2.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			// TODO Auto-generated method stub
			frame.setVisible(false);
		
			showWindowPatterns();
			
		}});
	
	
	
	btnNewButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
		SendOrders.BuyOrderGenerator(list_1.getSelectedValue(), selectedPair.getName());
			
		}});
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
			SendOrders.SellOrderGenerator(list_1.getSelectedValue(), selectedPair.getName());
				
			}});
		
	

	list_1.addListSelectionListener(new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent arg0) {
        	if(list_1.getSelectedValue()>selectedPair.getLastPrice()) {
        		btnNewButton_1.setEnabled(true);
        		btnNewButton.setEnabled(false);
        	}
        	else {
        		btnNewButton_1.setEnabled(false);
        		btnNewButton.setEnabled(true);
        		
        		
        	}
     
        }
    });
	
	
	
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
	
}}





































     
     

		
		
		
		
		
