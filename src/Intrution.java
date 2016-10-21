import java.io.*;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.util.*;
import java.util.regex.Pattern;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;


public class Intrution {
	
	private static List<String[]> splitArray(String[] originalArray, int chunkSize) { //This function is used to split the array into no. of arrays

        List<String[]> listOfArrays = new ArrayList<String[]>();
        int totalSize = originalArray.length;
        if(totalSize < chunkSize )
        {
            chunkSize = totalSize;
        }
        int from = 0;
        int to = chunkSize;

        while(from < totalSize)
        {
            String[] partArray = Arrays.copyOfRange(originalArray, from, to);
            listOfArrays.add(partArray);

            from+= chunkSize;
            to = from + chunkSize;
            if(to>totalSize)
            {
                to = totalSize;
            }
        }
        return listOfArrays;
    }




	
	
	 public static void main(String[] args) throws IOException {
		 
		 Scanner inFile[] = new Scanner[50];
		 String FName[] = {"User1","User2","User3","User4","User5","User6","User7","User8","User9","User10",
				 "User11","User12","User13","User14","User15","User16","User17","User18","User19","User20",
				 "User21","User22","User23","User24","User25","User26","User27","User28","User29","User30",
				 "User31","User32","User33","User34","User35","User36","User37","User38","User39","User40",
				 "User41","User42","User43","User44","User45","User46","User47","User48","User49","User50"};
		 List<String> temps = new ArrayList<String>();
		 Scanner in = new Scanner(System.in);
		 
		 int no_of_user;
		 System.out.println("enter the user no:");
	        no_of_user = in.nextInt();
	        no_of_user--;
		 int t_data = 100;
		 int temp=5000;
	//	 for(int i=0; i<no_of_user; i++)
	//	 {
			 inFile[0] = new Scanner(new File(FName[no_of_user])).useDelimiter("\\n+");
			 String token;
		 
			 int i1=0;
			 while (i1!=temp)
			 {
				 token = inFile[0].next();
				 if(!token.equals("["))
				 {
					 temps.add(token);
					 i1++;
				 }
				 
			 }
			 inFile[0].close();
	//	 }
	//		 System.out.println("#no of commands(value of m)-"+temps.size());
		 String [] Final = new String[temps.size()];
		 for(int i=0; i<temps.size();i++)
			 Final[i] = temps.get(i);
		 
		 List<String[]> user_segment = splitArray(Final, t_data);
		System.out.println("Answer of (a)\n---------------------------\nuser data size(value of n)-"+user_segment.size()); 
		 
	/*	 for(int i=0; i<user_segment.size();i++)
		 {
			 System.out.println("USER-"+i);
			 for(int j=0; j<t_data; j++)
				 System.out.println(user_segment.get(i)[j]);
		 }*/
		 
		 temps.clear();
	
		// System.out.println("#no of commands(value of m)-"+temps.size());
		 for(int i=0; i<user_segment.size(); i++)
		 {
			 for(int j=0; j<t_data; j++)
			 {
				 if(!temps.contains(user_segment.get(i)[j]) && user_segment.get(i)[j]  != Character.toString('['))
				 	temps.add(user_segment.get(i)[j]);
			 }
		 }
	//	 temps.remove("[");
		 System.out.println("#no of commands(value of m)-"+(temps.size()));
		 FileWriter file = new FileWriter("/home/nishit/workspace/CS631_Project_new/A/training.csv");
		 
		 for(int i=0;i<temps.size();i++)
		 {
			 file.append(temps.get(i));
			 file.append("\n");
		 }
		 file.flush();
		 file.close();
		 
		 
		 int M[][][] = new int[user_segment.size()][temps.size()][temps.size()];
		 
		 int s=6;
		 System.out.println("window size-"+s);
		 for(int k=0; k<user_segment.size(); k++)
		 {
			 System.out.println("process for user_segment-"+(k+1));
			 for(int i=0; i<temps.size(); i++)
			 {
				 
				 for(int j=0; j<temps.size(); j++)
				 {
					 M[k][i][j]=0;
					 for(int x=0; x<user_segment.get(k).length-1;x++)
					 {
						// System.out.println("temps.get="+temps.get(i)+" user_segment.get="+user_segment.get(k)[x]);
						 if(temps.get(i).matches( user_segment.get(k)[x]))
						 {
							 for(int y=x+1; y<Math.min(x+s, user_segment.get(k).length);y++)
							 {
								 if(user_segment.get(k)[y].equals(Pattern.quote("[")))
									 continue;
								 if(temps.get(j).matches(user_segment.get(k)[y]))
								 {
									 M[k][i][j]++;
							//		 System.out.println("i-->"+i+" j-->"+j+"count++");
								 }
							 }
						 }
					 }
					 
				 }
			 }
			 if(k<5)
			 {//System.out.println("user_segment-->"+(k+1));
		
				 
				for(int i=0; i<temps.size();i++)
				{
						if(temps.get(i).matches("rm"))
						{
								for(int j=0; j<temps.size();j++)
								{
									if(temps.get(j).matches("ls"))
									{
										System.out.println("for sequence-"+(k+1)+" value of co-occurence of rm with ls-"+M[k][i][j]);
									}
								}
						}
				}
				 
	
			 }
		 }
		
		 
		 double Mean[][] = new double[temps.size()][temps.size()];
		 System.out.println("Mean");
		 
		 for(int i=0; i<temps.size(); i++)
		 {
			 for(int j=0;j<temps.size(); j++)
			 {
				 int sum=0;
				for(int k=0; k<user_segment.size() ;k++)
				{
					sum = sum + M[k][i][j];
				}
				Mean[i][j] = sum/user_segment.size();
			 }
		//	 System.out.printf("\n");
		 }
		 
		 double A[][][] = new double [user_segment.size()][temps.size()][temps.size()];
		 
		 System.out.println("A");
		 for(int i=0; i<temps.size(); i++)
		 {
			 for(int j=0; j<temps.size(); j++)
			 {
				 for(int k=0; k<user_segment.size(); k++){
					 A[k][i][j] = M[k][i][j] - Mean[i][j];
					 
				 }
					 
			 }
		 }
		 
		 for(int i=0; i<user_segment.size(); i++)
		 {
			 FileWriter fileWriter = new FileWriter("/home/nishit/workspace/CS631_Project_new/A/"+i+".csv");
			 for(int j=0; j<temps.size();j++)
			 {
				 for(int k=0;k<temps.size();k++)
				 {
					 fileWriter.append(String.valueOf(A[i][j][k]));
						fileWriter.append(","); 
				 }
				 fileWriter.append("\n");
			 }
			 fileWriter.flush();
			 fileWriter.close();
		 }
		 
		 for(int k=0; k<5; k++)
		 {
			 for(int i=0; i<temps.size(); i++)
			 {
				 if(!temps.get(i).matches("rm"))
				 {
					 continue;
				 }
				 else
				 {
					 for(int j=0; j<temps.size(); j++)
					 {
						 if(!temps.get(j).matches("ls"))
						 {
							 continue;
						 }
						 else
							 System.out.println("For user_segment-"+(k+1)+", After mean value of ls following rm:"+A[k][i][j]);
					 }
				 }
			 }
		 }
		 
	
		 
		
		 
		 
		 
		 
		
	 }
	 
	 
 }
	 
	 
	 

