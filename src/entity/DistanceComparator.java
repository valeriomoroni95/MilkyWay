package entity;

import java.util.Comparator;

public class DistanceComparator implements Comparator{  
	
	public int compare(Object o1,Object o2){  
	  
		String[] d1 = (String[]) o1;
		String[] d2 = (String[]) o2;
		Double e1 = Double.parseDouble(d1[3]);
		Double e2 = Double.parseDouble(d2[3]);

		if(e1==e2)  
			return 0;  
		else if(e1>e2)  
			return 1;  
		else  
			return -1;  
	}  
}  