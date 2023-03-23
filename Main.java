package homework1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		
		Stack S1=new Stack(1000);			//creating S1 stack for countries
		Stack TempS1=new Stack(1000);		//creating TempS1 stack for temporary
		Stack SortS1=new Stack(1000);		//creating SortS1 stack for sorting alphabetically
		
		
		
		Stack S2=new Stack(100);			//creating S2 stack for letters
		Stack TempS2=new Stack(100);		//creating S2 stack for temporary
		
		
		
		Stack S3=new Stack(100);			//creating S3 stack for High Score Table's names
		Stack TempS3=new Stack(100);		//creating TempS3 stack for temporary
		Stack SortS3=new Stack(1000);		//creating SortS3 stack for sorting
		
		Stack S4=new Stack(100);			//creating S4 stack for High Score Table's scores
		Stack TempS4=new Stack(100);		//creating TempS4 stack for temporary
		Stack SortS4=new Stack(1000);		//creating SortS4 stack for sorting
		
		
		
		CircularQueue Q1=new CircularQueue(100);	//creating Q1 Circular queue for store chosen country
		
		CircularQueue Q2=new CircularQueue(100);	//creating Q2 Circular queue for game board
		
		
		
		
		//for reading unsorted file countries.txt and push countries to S1
		File CountryFile = new File("countries.txt");
		Scanner scanner1 = new Scanner(CountryFile);


		while(scanner1.hasNextLine()) {
			S1.push(scanner1.nextLine());
			String Country=(String) S1.peek();
			String UpperCaseCountry=Country.toUpperCase();		//converting to UpperCase
			
			for(int j=0;j<UpperCaseCountry.length();j++) {		//converting "İ" to "I" 
				if(UpperCaseCountry.substring(j,j+1).equals("İ")) {
					String ch=UpperCaseCountry.substring(j,j+1);
					ch="I";
					UpperCaseCountry=UpperCaseCountry.substring(0,j) + ch + UpperCaseCountry.substring(j+1);
				}
			}
			
			S1.pop();
			S1.push(UpperCaseCountry);
			
		    }
		
		
		
		
		//for reading unsorted file HighScoreTable.txt
		File HighScoreTableFile = new File("HighScoreTable.txt");
		Scanner scanner2 = new Scanner(HighScoreTableFile);


		while(scanner2.hasNextLine()) {
			
			//Using indexof and substring, their names are push to S3, their scores are push to S4
			String ScoreTableLine=scanner2.nextLine();
			int index=ScoreTableLine.indexOf(' '); 
			
			S3.push(ScoreTableLine.substring(0,index));
			S4.push(Integer.parseInt(ScoreTableLine.substring(index+1)));
			
		    }
		
		
		
		
		//for reading alphabet.txt
		File AlphabetFile = new File("alphabet.txt");
		Scanner scanner3 = new Scanner(AlphabetFile);

		//pushing TempS2
		while(scanner3.hasNextLine()) {
			TempS2.push(scanner3.nextLine());
		}
		
		//transferring from TempS2 to S2
		while(!TempS2.isEmpty()) {
			
			S2.push(TempS2.pop());
			}
		
		
		
		
		//sorting countries
		boolean isSortedCountry=false;
		
		while(!isSortedCountry) {
		
		int counter1=0;	
			
		while(!S1.isEmpty()) {
			
			
			if(S1.size()!=1) {	
			
			String Country=(String) S1.pop();
					
				if(Country.compareTo((String) S1.peek())>0) {		//using compare to for comparing 
					
					SortS1.push(Country);
					
				}
				else {			//if not in alphabetical order; counter++ 
					
					SortS1.push(S1.pop());
					S1.push(Country);
					counter1++;
				}
			
			}
			
			else {
				SortS1.push(S1.pop());
			}
				
			
		}
		
			if(counter1!=0) {		
				while(!SortS1.isEmpty()) {
					S1.push(SortS1.pop());
					}
			}
			else if(counter1==0) {		//if in alphabetical order; counter=0 
				isSortedCountry=true;	//if counter=0 loop ends
				
			}
		
		
		}	
		
		
		//transferring from SortS1 to TempS1
		while(!SortS1.isEmpty()) {		
			TempS1.push(SortS1.pop());
		}
		//transferring from TempS1 to S1
		while(!TempS1.isEmpty()) {
			S1.push(TempS1.pop());
			}
	
		
		
		//generating random integer for choosing country
		int RandomCountry=(int)(Math.random()*S1.size()+1);
		
		//printing generated number
		System.out.println("Randomly generated number: "+RandomCountry);
		
		//finding chosen country
		for(int i=0;i<RandomCountry-1;i++) {		
			TempS1.push(S1.pop());
		}
		
		String Word=(String) S1.peek();
		
		//using substring for add the chosen country to the Q1 and Q2(-)
		for(int i=0;i<Word.length();i++) {		
			Q1.enqueue(Word.substring(i,i+1));
			Q2.enqueue('-');
		}
		
		
		System.out.println();
		
		boolean game=true;			//creating for game loop
		int Score=0;				//creating for total score
		int Step=1;					//creating for step
		
		
		

		while(game) {
			
			//generating random integer for wheel and printing
			int Wheel=(int)(Math.random()*8+1);
			
			if(Wheel==1) {
				System.out.print("Whell: 10");				
			}
			else if(Wheel==2) {
				System.out.print("Whell: 50");
				
			}
			else if(Wheel==3) {
				System.out.print("Whell: 100");
				
			}
			else if(Wheel==4) {
				System.out.print("Whell: 250");
				
			}
			else if(Wheel==5) {
				System.out.print("Whell: 500");
				
			}
			else if(Wheel==6) {
				System.out.print("Whell: 1000");
				
			}
			else if(Wheel==7) {
				System.out.print("Whell: Double Money");
				
			}
			else if(Wheel==8) {
				System.out.print("Whell: Bankrupt");
				Score=0;
			}
			
			
			System.out.println();
			
			//printing letters
			while(!S2.isEmpty()) {		
					System.out.print(S2.peek());
					TempS2.push(S2.pop());
				}
			while(!TempS2.isEmpty()) {
				S2.push(TempS2.pop());
				}
		
			
		int counter2=0;  //for game loop
		
		//generating random integer for Guess letter
		int RandomLetter=(int)(Math.random()*S2.size()+1);
		
		//finding Guess letter
		for(int i=0;i<RandomLetter-1;i++) {					
			TempS2.push(S2.pop());
				}
		
		String Guess=(String) S2.pop();
		
		
		while(!TempS2.isEmpty()) {
			S2.push(TempS2.pop());
			}
		
		
		
		//for organized writing
		int size=S2.size();
		int startingcursor=32;
		
		for(int i=0;i<startingcursor-size;i++) {
			System.out.print(" ");
		}
		
		
		
		//printing Guess and Word
		System.out.print("Guess: "+Guess);
		System.out.print("        ");
		System.out.print("Word:  ");
		
		
		
		//playing the game
		
		for(int j=0;j<Q1.size();j++) {
			if(!Q1.peek().equals(Guess)) {
				Q1.enqueue(Q1.dequeue());
				Q2.enqueue(Q2.dequeue());
			}
			//If there is selected letter in the country, the score increases
			else {
				Q2.dequeue();
				Q2.enqueue(Guess);
				Q1.enqueue(Q1.dequeue());
				
				if(Wheel==1) {
					Score+=10;
				}
				else if(Wheel==2) {
					Score+=50;
				}
				else if(Wheel==3) {
					Score+=100;
				}
				else if(Wheel==4) {
					Score+=250;
				}
				else if(Wheel==5) {
					Score+=500;
				}
				else if(Wheel==6) {
					Score+=1000;
				}
				else if(Wheel==7) {
					Score=Score*2;
				}
				
				
			}
			
		}
		
		
		     
		
		for(int j=0;j<Q2.size();j++) {
		if(Q2.peek().equals('-')) {		//if there is still '-' ;  the counter increases
			counter2++;
		}
		Q2.enqueue(Q2.dequeue());
		
		}								//if counter=0 ; game loop ends
		if(counter2==0) {
			game=false;
		}
		
		//printing game board (Word)
			for(int i=0;i<Q2.size();i++) {
				System.out.print(Q2.peek());
				Q2.enqueue(Q2.dequeue());
				}
			
			System.out.print("      ");
			
			
			//printing score and step
			System.out.print("Score: "+Score);
			
			System.out.print("      ");
			
			System.out.println("Step: "+Step);
			Step++;
			System.out.println();
			System.out.println();
		}
		
		//printing total score
		System.out.println("You win $"+Score+"  !!!!!");
		
		//pushing name and score to S3 and S4
		S3.push("You");
		S4.push(Score);
		
		System.out.println();
		
			
		//sorting high score table
		//Comparing S4(scoreStack) and sorting
		//at the same time sorting S3(nameStack)
		boolean isSortedScoreTable=false;
		
		while(!isSortedScoreTable) {
		
		int counter3=0;	
			
		while(!S4.isEmpty()) {
			
			
			if(S4.size()!=1) {	
			
			int Point=(int) S4.pop();
			String Name=(String) S3.pop();
			
			
				if(Point>(int) S4.peek()) {
					
					SortS4.push(Point);
					
					SortS3.push(Name);
					
				}
				else if(Point==(int) S4.peek()) {
					
					SortS4.push(S4.pop());
					S4.push(Point);
					
					SortS3.push(S3.pop());
					S3.push(Name);
					
				}
				else {
					
					SortS4.push(S4.pop());
					S4.push(Point);
					
					SortS3.push(S3.pop());
					S3.push(Name);
					
					counter3++;	//if not in order counter++
				}
			
			}
			else {
				SortS4.push(S4.pop());
				
				SortS3.push(S3.pop());
			}
				
			
		}
		
		if(counter3!=0) {
			while(!SortS4.isEmpty()) {
				
				S4.push(SortS4.pop());
				
				S3.push(SortS3.pop());
				}
		}
		else if(counter3==0) {			//if counter=0; loop ends
			isSortedScoreTable=true;
			
		}
		
		
		}	
		
		//transferring from SortS3 to S3 
		//transferring from SortS4 to S4 
		while(!SortS3.isEmpty()) {
						
			S3.push(SortS3.pop());
			S4.push(SortS4.pop());
		}
						
			
		//printing High Score Table
		System.out.println("High Score Table");
							
		
		while(!S3.isEmpty()) {
			for(int i=0;i<10;i++) {
					
				
			System.out.println(S3.peek()+" "+S4.peek());
			TempS3.push(S3.pop());
				
			TempS4.push(S4.pop());
			}
			TempS3.push(S3.pop());
				
			TempS4.push(S4.pop());
		}
					
		while(!TempS3.isEmpty()) {
								
			S3.push(TempS3.pop());
			S4.push(TempS4.pop());
		}
					
		

		//writing the top 10 to HighScoreTable.txt 
		try {
			FileWriter HighScoreTableWriter = new FileWriter("HighScoreTable.txt");
			      
			while(!S3.isEmpty()) {
				for(int i=0;i<10;i++) {
							
				HighScoreTableWriter.write(S3.peek()+" "+S4.peek()+"\n");
				TempS3.push(S3.pop());
					
				TempS4.push(S4.pop());
					}

				HighScoreTableWriter.close();
						
				TempS3.push(S3.pop());
					
				TempS4.push(S4.pop());
				}
			      
			while(!TempS3.isEmpty()) {
						
				S3.push(TempS3.pop());
				S4.push(TempS4.pop());
				}
							 
			      
			      
		   } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		   }
			
			
		
		
	}
}

