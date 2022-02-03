package sda;

public class Node {

	
	  int data;
	  CryptoPair CryptoData;
	  Node left;
	  Node right;
	  Node parent;

	 
	  boolean color; 


	  public Node(int data,CryptoPair cd) {
		  
	    this.data = data;
	    this.CryptoData=cd;
	  }

	  public int getData() {
	    return data;
	  }
	}