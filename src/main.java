package project2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class main {

	private static void readFile1(File fin) throws IOException 
	{
		String eqni2=null;
		String tempni2=null;
		String tempni22=null;
		String tempni3=null;
		
		
		int i=0;
		int	j=0;
		int invcounter=0;
		
		int tempx=0;
		int tempy=0;
		
		int maxint=0;
		int tempi=0;
	
		int space=0;
		
		int maxb=0;
		int tempb=0;
		
		String[] Eqin1 = new String[10];
		int eqin1c = 0;
		int eqin1ctmp = 0;
		
		
		String[] newB = new String[10];
		int bcounter=0;
		String[] newC = new String[10];
		int ccounter=0;
		String[][] newA = new String[10][10];
		
		
		int minmaxflag=0;
		String minmax="min";
		
		FileInputStream fis1 = new FileInputStream(fin);
		FileInputStream fis2 = new FileInputStream(fin);
		FileInputStream fis3 = new FileInputStream(fin);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
		BufferedReader br3 = new BufferedReader(new InputStreamReader(fis3));
	    String line = null;
	    
	    //finding if the first problem had the max or the min word in the beginning
	    //filling the "new" B array with the elements from the "old" C array
	    while((line = br1.readLine()).contains("x"))
	    {
	    	Scanner ni1 = new Scanner(line);
			
	    	if(minmaxflag==0)
	    	{
	    		if(line.contains("min"))
	    		{
	    			minmax="max";
	    			minmaxflag=1;
	    		}
	    		
	    	}
	    	
				while(ni1.hasNext())
				{
					if(ni1.hasNextInt())
						newB[bcounter++]=ni1.next();
					else ni1.next();
				}	
	    }
	    
	    //Creating an Eqin table based on the constraints
	    while((line = br1.readLine())!=null)
	    {
	    	Scanner ni2 = new Scanner(line);
			
				while(ni2.hasNext())
				{
					eqni2=ni2.next();
					if(eqni2.matches("\\<=|\\>=|="))
					{
						Eqin1[eqin1c++]=eqni2;
					}
				}	
	    }
	    br1.close();
	    
	    ccounter=0;
	    
	 //Creating the "new" C table based on the elements of the "old" B table  
	    while((line = br2.readLine())!=null)
	    {
	    	Scanner ni2 = new Scanner(line);
			
				while(ni2.hasNext())
				{
					tempni2=ni2.next();
					if(tempni2.matches("\\>=||\\<=||\\="))
						{
							tempni22=ni2.next();
							if(tempni22.matches("-"))
							{
								newC[ccounter]="-" + ni2.next();
							}
							else
							{
								newC[ccounter]= tempni22;
							}
						}
				}	
					
				if(line.contains("<=") || line.contains(">=") || line.contains("=") && !line.contains("x"))
					ccounter++;
	    }
	    br2.close();
	    
	    //Creating the inverted A table
	    while((line = br3.readLine())!=null)
	    {
	    	Scanner ni3 = new Scanner(line);
	    	
	    	while(ni3.hasNext() && (line.contains("<=") || line.contains("=") || line.contains(">=")))
	    	{
	    		tempni3=ni3.next();
	    		if(tempni3.equals("<=") || tempni3.equals("=") || tempni3.equals(">=") || tempni3.equals(">=0") || tempni3.matches("x\\d+"))
	    		{
	    			break;
	    		}
	    		else
	    		{
	    			newA[i++][j]=tempni3;
	    		}
	    	}
	    	i=0;
	    	if(line.contains("<=") || line.contains(">=") || line.contains("=") && !line.contains("x"))
	    		j++;
	    	
	    }
	    br3.close();
	    
	    eqin1c=0;
	    eqin1ctmp=eqin1c;
	    try
		{
		    PrintWriter pr = new PrintWriter("output.txt");
		    
		  //printing the new C table with the first y variable
		    pr.print(minmax + " ");
		    pr.print("[( ");
		    for (i=0; newC[i]!=null; i++)
		    {
		        pr.print(newC[i] + " ");
		        invcounter++;   
		    }
		    pr.print(")]");
		    pr.format("^T ");
		    
		    pr.format("y1");
		    pr.println();
		    for(i=2; i<=invcounter; i++)
		    {
		    	pr.format("            ");
		    	for(tempx=0; newC[tempx]!=null; tempx++)
		    	{
		    		for(tempy=1; tempy<=newC[tempx].length(); tempy++)
		    		{
		    			pr.print(" ");
		    		}
		    		pr.print(" ");
		    	}
		    	pr.format("y%d", i);
		    	pr.println();
		    }
		    
		    maxb=newB[0].length();
		    //finding largest b element
		    for(tempb=0; newB[tempb]!=null; tempb++)
			{
		    	if(maxb<newB[tempb].length())
		    		maxb=newB[tempb].length();
			}
		   
		    //printing A table
		    i=0;
		    j=0;
		    for(i=0; newA[i][j]!=null; i++)
			{
				for(j=0; newA[i][j]!=null; j++)
				{
					for(tempi=0; newA[tempi][j]!=null; tempi++)
					{
						if(newA[tempi][j].length()>=maxint)
						{
							maxint=newA[tempi][j].length();
						}	
					}
					
					for(space=1; space<=(maxint-newA[i][j].length()); space++)
					{
						pr.print(" ");
					}
							
					pr.print(newA[i][j] + " ");
					
				}
				
				j=0;
				maxint=0;
				
				//we only take into account the: x>=0 condition
				if(minmax=="min")
					pr.print(" >= ");
				else
					pr.print(" <= ");
			
			//Printing the B table
			if(newB[i]!=null)
			{
				for(space=1; space<=(maxb-newB[i].length()); space++)
				{
					pr.print(" ");
				}
				pr.print(newB[i]);
				pr.println();
			}
				
				
			}
		    
		    pr.println();
		    
		    //Printing the y constraints
		    if(minmax=="min")
		    {	
		    	for(i=1; i<=invcounter; i++)
		    	{
					pr.format("y%d", i);
					if(Eqin1[eqin1ctmp].matches(">="))
						pr.println(" <= 0");
					else if(Eqin1[eqin1ctmp].matches("<="))
						pr.println(" >= 0");
					else if(Eqin1[eqin1ctmp].matches("="))
						pr.println(" free");
					eqin1ctmp++;
		    	}
		    }
		    else
		    {
		    	for(i=1; i<=invcounter; i++)
    			{
					pr.format("y%d", i);
					if(Eqin1[eqin1ctmp].matches(">="))
						pr.println(" >= 0");
					else if(Eqin1[eqin1ctmp].matches("<="))
						pr.println(" <= 0");
					else if(Eqin1[eqin1ctmp].matches("="))
						pr.println(" free");
					eqin1ctmp++;
    			}
		    }
		    pr.close();
		}
		
	    catch (Exception e1)
		{
		    e1.printStackTrace();
		    System.out.println("No such file exists.");
		}
	}
	
	
	
	
	public static void main(String[] args) throws IOException{
		File fin = new File("input.txt");
		readFile1(fin);
		
	}
}
