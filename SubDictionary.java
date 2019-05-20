
/**
* 
* Jinchen Hu ID#40080398
* <p>COMP 249</p>
* <p>Assignment #4 Part_1</p>
* <p>Due 11:59 PM - Monday, April 8, 2019</p>
*
*/
//-------------------------------
//Assignment #4 Part_1
//Question: write a program that will accept any text file, 
//as input, and creates a sub-dictionary that includes all 
//the words found in that input file based on some rules.
//Written by: Jinchen Hu ID#40080398
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class SubDictionary {
	public static void main(String[] args) {
		//creates ArrayList to store input data
		ArrayList<String> list = new ArrayList<>();
		System.out.print("Please input the file name: ");
		Scanner keyboard = new Scanner(System.in);
		//declare a String to store path name of input file
		String path = keyboard.next()+".txt";
		//create File object
		//File f = new File(path);
		//creates FileInputStream object
		FileInputStream fis = null;
		//creates Scanner object
		Scanner sc = null;
		
		try {
			fis = new FileInputStream(path);
			sc = new Scanner(fis);
			//read the file
			String s = "";
			while(sc.hasNext()) {
				s=sc.next();
				//if the String is a single character a or i, then capitalizes and add it to ArrayList
				if(s.length() == 1) {
					if(s.equalsIgnoreCase("a")||s.equalsIgnoreCase("i")) {
						list.add(s.toUpperCase());
					}
				//if the length is greater than 1, checks whether the String contains digit(s)
				}else {
					int i,len;
					boolean flag = true;
					//if the String contains a digit, assigns false to flag and breaks the for loop
					for(i = 0, len = s.length(); i < len; i++) {
						if(Character.isDigit(s.charAt(i))) {
							flag = false;
							break;
						}
					}
					//if the flag is true, capitalizes and add the String to list
					if(flag) {
						//remove 's 'S 'm 'M
						s = s.replaceAll("’[m,M,s,S]", "");
						//remove the punctuation
						if(!Character.isLetter(s.charAt(s.length()-1))) 
							s = s.substring(0, s.length()-1);
//						if(s.substring(s.length()-2).equalsIgnoreCase("\s") || s.substring(s.length()-2).equalsIgnoreCase("\'m"))
//							s = s.substring(0, s.length()-2);
						//add trimmed String to list
						list.add(s.toUpperCase());
					}
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			//close fis
			if(fis != null) {
				try {
					fis.close();
				}catch(IOException e) {
					System.out.println("Input file cannot be closed");
				}
			}
			sc.close();
			keyboard.close();
		}//end of read input file
		
		//ultimate manipulation on ArrayList
		//deletes repeated words and remain single one only, and sorts the list alphabetically
		int i,j;
		//removes redundant data from the last element of the list to front
		//i to control the number of loop
		for(i = 0; i < list.size()-1; i++) {
			for(j = list.size()-1; j > i; j--) {
				if(list.get(j).equals(list.get(i)))
					//if there exists any value that equals to the key value (list.get(i)), removes it
					list.remove(j);
			}
		}
		//declare an integer and assign the size of list to it
		int count = list.size();
		//sorts by alphabetic order
		int x,y;
		//x to control the number of loop
		for(x=0; x < count-1; x++) {
			//y to control the number of comparison
			for(y = 0; y < count - 1 -x; y++ ) {
				if (list.get(y).compareTo(list.get(y+1)) > 0) {
					//exchanges the data of two adjacent String if the former one is greater than the later
					String temp = list.get(y+1);
					list.set(y+1, list.get(y));
					list.set(y, temp);
				}
			}
		}	
		
		//add the indications
		String indication = list.get(0).substring(0, 1);
		//add indication to list
		list.add(0, "==");
		list.add(0, indication);
		list.add(0, "");
		for(int index = 4; index < list.size(); index ++) {
			if(!indication.equals(list.get(index).substring(0, 1)) && !list.get(index).equals("==") && !list.get(index).equals("")) {
				//if the indication is different from the initial of the present word
				//assign the initial to the it
				indication = list.get(index).substring(0, 1);
				//add the new indication to list
				list.add(index,"==");
				list.add(index,indication);
				list.add(index,"");
			}
		}
		
		//write the output file
		FileOutputStream fos = null;
		PrintWriter out = null;
		try {
			fos = new FileOutputStream("SubDictionary.txt");
			out = new PrintWriter(fos);
			out.println("The document produced this sub-dictionary, which includes " + count + " entries.");
			for(String s : list) {
				out.println(s);
			}
			out.flush();
			System.out.println("Write successfully");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(fos != null) {
				try {
					fos.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null) {
				out.close();
			}
			System.out.println("\nThank you very much for using. The program has terminated");
		}
		
		
		
		
	}//end of main
	
}//end of class
