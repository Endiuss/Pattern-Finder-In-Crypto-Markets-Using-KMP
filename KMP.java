package sda;

import java.util.ArrayList;

public class KMP {
	
	
	 static ArrayList<Interval>  KMPSearch(String pat, TimeFrame txt)
	    {ArrayList<Interval> arr=new ArrayList<Interval>();
	        int M = pat.length();
	        int N = txt.getPivotData().size();
	  
	        
	        int lps[] = new int[M];
	        int j = 0; 
	        System.out.println(txt.getTimeFrame());
	       
	        computeLPSArray(pat, M, lps);
	  
	        int i = 0; 
	        while (i < N) {
	            if (pat.charAt(j) == txt.getPivotData().get(i).getType().charAt(0)) {
	                j++;
	                i++;
	            }
	            if (j == M) {
	            	PinPoint startPin=txt.getPivotData().get(i-j);
	            	PinPoint endPin=txt.getPivotData().get(i-1);
	            	System.out.println("Start:"+startPin.getCandlestick().getCloseTime()+" End:"+endPin.getCandlestick().getCloseTime());
	               Interval inter = new Interval(startPin,endPin,txt.getAvgPriceOfInterval(startPin.getPozInPriceData(),endPin.getPozInPriceData()));
	               arr.add(inter);
	               
	                j = lps[j - 1];
	            }
	  
	          
	            else if (i < N && pat.charAt(j) != txt.getPivotData().get(i).getType().charAt(0)) {
	               
	                if (j != 0)
	                    j = lps[j - 1];
	                else
	                    i = i + 1;
	            }
	        }
	        return arr;
	    }
	  
	    static void computeLPSArray(String pat, int M, int lps[])
	    {
	        
	        int len = 0;
	        int i = 1;
	        lps[0] = 0; 
	   while (i < M) {
	            if (pat.charAt(i) == pat.charAt(len)) {
	                len++;
	                lps[i] = len;
	                i++;
	            }
	            else 
	            {
	          if (len != 0) {
	                    len = lps[len - 1];
	     }
	                else 
	                {
	                    lps[i] = len;
	                    i++;
	                }
	            }
	        }
	    }
	  
	
	}


