package comparescvfiles;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class CompareCsvFileColumns 
{
	public static void main(String[] args) throws IOException 
	{
	  FileReader file1 = new FileReader("resources/file1.csv");
	  HashMap<String,ArrayList<String>> csvfile1  = new HashMap<String,ArrayList<String>>();
	  csvfile1 = convertCsvToHashMap(file1);
	  System.out.println("file1  ="+csvfile1);
	  FileReader file2 = new FileReader("resources/file2.csv");
	  HashMap<String,ArrayList<String>> csvfile2  = new HashMap<String,ArrayList<String>>();
	  csvfile2 = convertCsvToHashMap(file2);
	  System.out.println("file2  ="+csvfile2);
	  CompareCsvFileColumns(csvfile1,csvfile2,"Period","Period");
	  
	
	}
	 private static void CompareCsvFileColumns(HashMap<String, ArrayList<String>> csvfile1,
			HashMap<String, ArrayList<String>> csvfile2,String file1Column,String file2Column) 
	 {
		// TODO Auto-generated method stub
		ArrayList file1Data = csvfile1.get(file1Column);
		ArrayList file2Data = csvfile2.get(file2Column);
		System.out.println(file1Column+"="+file1Data);
		System.out.println(file2Column+"="+file2Data);
		ArrayList file1Diff = new ArrayList();
		for(int i=0; i<file1Data.size();i++)
		{
			int flag = findData(file1Data.get(i),file2Data);
			if(flag==-1)
			{
				file1Diff.add(file1Data.get(i));
			}
			else
			{
				file2Data.remove(flag);
			}
		}
		
		System.out.println("file1 Diff ="+file1Diff);
		System.out.println("file2 Diff ="+file2Data);
		
		
		
		/*
		 * ArrayList file2Diff = new ArrayList(); for(int i=0; i<file2Data.size();i++) {
		 * int flag = findData(file2Data.get(i),file1Data); if(flag==0) {
		 * file2Diff.add(file2Data.get(i)); } } System.out.println(file2Diff);
		 */
	 }
	private static int findData(Object string,ArrayList fileData) {
		// TODO Auto-generated method stub
		for(int i=0;i<fileData.size();i++)
		{
			if(string.equals(fileData.get(i)))
			{
				return i;
			}
		}

		return -1;
	}
	public static HashMap<String,ArrayList<String>> convertCsvToHashMap(FileReader file) throws IOException
	 {
		 BufferedReader CSVFile1 = new BufferedReader(file);
		 String dataRow = CSVFile1.readLine();
		 int count = 0;
		 HashMap<String,ArrayList<String>> csvfile  = new HashMap<String,ArrayList<String>>();
		 ArrayList<String> header = new ArrayList<String>();
		 while(dataRow != null)
		 {
			 String[] dataArray = dataRow.split(",");
			 if(count == 0)
			 {
				 for(int i=0;i<dataArray.length;i++)
				 {
					 header.add(dataArray[i]);
					 csvfile.put(dataArray[i],null); 
				 }
				 count=1;
			 }
			 else
			 {
				 for(int i=0;i<dataArray.length;i++)
				 {
					String head = header.get(i);
					ArrayList data = new ArrayList();
					if(csvfile.get(head)!=null)
					{
						data = csvfile.get(head);
					}
					
					data.add(dataArray[i]);
					csvfile.replace(head, data);
				 } 
			 }

		     dataRow = CSVFile1.readLine();
		 }
		return csvfile;

	 }
	 
	 
}



