package testPCW;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class file_fixer {
	
	static ArrayList <String> list = new ArrayList<String>();//to hold the file cases
	//use to store the characteristics of the matching array
	static int ID;
	static int ScoreA;
	static int IndexStart;
	static int IndexEnd;
	
	public static void main(String[] args) {
		//for loop to loop through every args given
		for(int x=0;x<args.length; x++){
			//to read the file
			try{
				Scanner inputFile; // to scan file
				File brokenFile = new File(args[0].toString());
				inputFile = new Scanner(brokenFile);
				inputFile.useDelimiter(";");
				//to convert the file into an array list
				
				while(inputFile.hasNext()){
					String record = inputFile.next();
					//to add to array List and ignore line gaps
					list.add(record.replace("\n", "").replace("\r", ""));
				}
				inputFile.close();
				//file exceptions
			}catch(ArithmeticException e){
				System.out.println(e);
			}catch(InputMismatchException e){
				System.out.println(e);
			}catch(Exception e){
				System.out.println(e);
			}finally{
				
			}//try/catch exception
			
			//to loop through the array list until only one string is left
			while(list.size()!=1){
				String primary = list.get(0); //to store the array
				ID = 0; //to record which array matched the primary best
				ScoreA = 0; //to store the degree to which the array matched
				IndexStart = 0;//to locate where the primary starts to match the other string
				IndexEnd= 0;//to locate where the primary ends matching to the other string
				
				//to check which line is being read:-> System.out.println("\nreading line "+ a+"\t"+primary);
				
				//loop for the size of the array list so all the arrays can be counter-checked
				for(int b=0;b<list.size();b++){
						
						String cont = list.get(b); // store the string that would be counter-checked with
						char[] Box = primary.toCharArray();//change the primary string to a char array so we can check letter by letter 
						String word="";//to hold the summation of the letters
						int ScoreB=0;//score for the checked line
						
						// to check every time the loop jumps to a new string:-> System.out.print("Now working with "+ cont +"\n");
						
						//for every char loop to check for a match and add the next char if match is found
						for(char letter: Box){
							if((primary.contains(cont))==true){ //to check every time the two strings are the same;-> System.out.print("braking sequence\n");
							//if both strings are the same we have to ignore them
							break;}
							word = word+letter; //take the new char and add it to the word
							// to check which char was added and the total :-> System.out.println("the letter is: "+letter+ "\t the word is; "+ word);
							
							//check if a match is found or not
							if((cont.indexOf(word))==-1){
									break;//to stop it from running extra code if no match is found
							}else if((cont.indexOf(word))!=-1){
								//if match is found update scoreB
								ScoreB++;
								//to check every time the ScoreB is updated:-> System.out.println("match found \t scoreB moves up to: "+ ScoreB);
								
								//if scoreA is less that ScoreB, update scoreA and the ID and indexes 
								//(we only do this if the match is strong enough hence score needs to be more that 2)
								if((ScoreA<ScoreB) && (ScoreB>2) ){
									ScoreA=ScoreB;
									ID = b;
									IndexStart = cont.indexOf(word);
									IndexEnd = IndexStart+word.length();
									
									/*to check if the scoreA is being updated 
									 * System.out.println("\nrunning score change \nScore A: " + ScoreA + "\nScore b: " + ScoreB + "\nprimary String : " + primary 
									+ "\nE: " + cont + "\nword: " + word + "\nend\n\n");*/
								}
							}/*else*/ 
							
						}//for loop 3 char box
						
					}//for loop int b
					/*to check the best pairing
					 * System.out.println( "\nthe No is: "+ID+
										"\nthe best match is: "+ list.get(ID)+ 
										"\nthe score is: "+ ScoreA +
										"\nthe Start Index is: "+ IndexStart+
										"\nthe End index is: "+ IndexEnd+
										"\nthe lenght is: "+ list.get(ID).length());*/
					
					// adjustments for the arrayList
					//if no match is found
					if(ScoreA==0){		
						String New = list.get(0);
						list.remove(0); 
						list.add(New);
						//to check what string was added:-> System.out.println("new: "+New);
					} else
					//if a substring need to be added to the front
					if((IndexStart!=0) && (IndexEnd==list.get(ID).length())){
						String Sub = list.get(ID).substring(0, IndexStart);
						String New = Sub+list.get(0);
						list.remove(ID);
						list.remove(0); 
						list.add(New);
						//to check what string was added:-> System.out.println("new: "+New);
					} else
					//if a substring needs to be added to the end
					if((IndexStart==0) && (IndexEnd!=list.get(ID).length())){
						String Sub = list.get(ID).substring(IndexEnd, list.get(ID).length());
						String New = list.get(0)+Sub;
						list.remove(ID);
						list.remove(0); 
						list.add(New);
						//to check what string was added:-> System.out.println("new: "+New);
					} else
					//if the primary string is with in the ID string
					if((IndexStart!=0) && (IndexEnd!=list.get(ID).length())){
						list.remove(0);
						//to check what string was removed:-> System.out.println("removed: "+a);	
					}
			}//while
			System.out.println(list.get(0));
		}//for loop int x args[]
	}//main
}//class