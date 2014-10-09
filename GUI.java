import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.*;
//Student number: 18151450			Name: BANELE MATSEBULA			O:100%		Class: GUI 1 of  
public class GUI {

	public static int[][] DrawMyGrid(boolean DefaultColors,boolean UserDefined,int[][] DefaultArray)
	{
		int a = 0;
		double squareRadius = 0.5;
		int SizeOfGrid = DefaultArray.length;
		int[][] ArrayOf_A = new int[SizeOfGrid][SizeOfGrid];
		// Setting the x and y scale, the y scale should accomodate the extra features like the six color buttons, different color grids etc...
		StdDraw.setXscale(0, SizeOfGrid);
		StdDraw.setYscale(-5, SizeOfGrid + 2);
		// Draw the grid by drawing coloured filled squares. The colors will be determined by the value a, which can have only 6 different values. For example if a = 1, I want my program to draw a red square, if it is a = 0, I want it to draw a blue square... etc
		if (DefaultColors) {
			// Default color buttons
			double circleRadius = 0.75;
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.filledCircle(2, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledCircle(4, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.filledCircle(6, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.GREEN);
			StdDraw.filledCircle(8, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.PINK);
			StdDraw.filledCircle(10, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.CYAN);
			StdDraw.filledCircle(12, -2, circleRadius);
			StdDraw.setPenColor();
			// Drawing circles around buttons
			for (int i = 2; i < 13; i += 2) {
				StdDraw.circle(i, -2, 0.75);
			}
			//Drawing the grid, m is the "constant" we use to define our range of random values that can be produced, now our range changes as size of grid cahnges to accomdate for probability of the same colour being chosen. 
			int m = 6;
			for (double i = 0.5; i < SizeOfGrid; i++) {
				for (double j = 0.5; j < SizeOfGrid; j++) {
					if (SizeOfGrid == 15) {
						m = 13;
					} else if (SizeOfGrid == 20) {
						m = 9;
					} else {
						m = m;
					}
					if(UserDefined){
						a = DefaultArray[(SizeOfGrid - 1) - (int) (j)][(int) (i)];
					}
					else{
						a = (int) (m * Math.random());
					}
					if (a == 0 || a == 6 || a == 10 || a == 12) {
						StdDraw.setPenColor(StdDraw.BLUE);
						a = 0;
					} else if (a == 1 || a == 7 || a == 11) {
						StdDraw.setPenColor(StdDraw.RED);
						a = 1;
					} else if (a == 2) {
						StdDraw.setPenColor(StdDraw.YELLOW);
					} else if (a == 3 || a == 8) {
						StdDraw.setPenColor(StdDraw.GREEN);
						a = 3;
					} else if (a == 4) {
						StdDraw.setPenColor(StdDraw.PINK);
					} else {
						StdDraw.setPenColor(StdDraw.CYAN);
						a = 5;
					}
					StdDraw.filledSquare(i, j, squareRadius);
					StdDraw.setPenColor();
					StdDraw.square(i, j, squareRadius);
					ArrayOf_A[(SizeOfGrid - 1) - (int) (j)][(int) (i)] = a;
				}
			}
		} else {
			double circleRadius = 0.75;
			// The buttons
			StdDraw.setPenColor(StdDraw.BOOK_BLUE);
			StdDraw.filledCircle(2, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.BOOK_RED);
			StdDraw.filledCircle(4, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.MAGENTA);
			StdDraw.filledCircle(6, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledCircle(8, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.DARK_GRAY);
			StdDraw.filledCircle(10, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.GREEN);
			StdDraw.filledCircle(12, -2, circleRadius);
			// dark borders around circles
			StdDraw.setPenColor();
			for (int i = 2; i < 13; i += 2) {
				StdDraw.circle(i, -2, 0.75);
			}
			// The Grid
			int m = 6;
			for (double i = 0.5; i < SizeOfGrid; i++) {
				for (double j = 0.5; j < SizeOfGrid; j++) {
					if (SizeOfGrid == 15) {
						m = 12;
					} else if (SizeOfGrid == 20) {
						m = 9;
					} else {
						m = m;
					}
					a = (int) (m * Math.random());
					if(UserDefined){
						a = DefaultArray[(SizeOfGrid - 1) - (int) (j)][(int) (i)];
					}
					if (a == 0 || a == 6 || a == 12) {
						StdDraw.setPenColor(StdDraw.BOOK_BLUE);
					} else if (a == 1 || a == 7 || a == 11) {
						StdDraw.setPenColor(StdDraw.BOOK_RED);
					} else if (a == 2) {
						StdDraw.setPenColor(StdDraw.MAGENTA);
					} else if (a == 3 || a == 8) {
						StdDraw.setPenColor(StdDraw.ORANGE);
					} else if (a == 4) {
						StdDraw.setPenColor(StdDraw.DARK_GRAY);
					} else {
						StdDraw.setPenColor(StdDraw.GREEN);
					}
					StdDraw.filledSquare(i, j, squareRadius);
					StdDraw.setPenColor();
					StdDraw.square(i, j, squareRadius);
					if (a == 12 || a == 6) {
						a = 0;
					}
					if (a == 7 || a == 11) {
						a = 1;
					}
					if (a == 8) {
						a = 3;
					}
					if (a == 9 || a == 10) {
						a = 5;
					}
					ArrayOf_A[SizeOfGrid - 1 - (int) (j)][(int) (i)] = a;
				}
			}
		}
		// I have a feeling I'll need my values of a
		return ArrayOf_A;
	}
	public static void DrawMyFeatures(int SizeOfGrid,boolean DefaultColors) {
		StdDraw.setPenColor();
		double circleRadius = 0.75;
		// The circles representing the buttons
		if (DefaultColors) {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.filledCircle(2, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledCircle(4, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.filledCircle(6, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.GREEN);
			StdDraw.filledCircle(8, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.PINK);
			StdDraw.filledCircle(10, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.CYAN);
			StdDraw.filledCircle(12, -2, circleRadius);
		} else {
			// The other buttons
			StdDraw.setPenColor(StdDraw.BOOK_BLUE);
			StdDraw.filledCircle(2, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.BOOK_RED);
			StdDraw.filledCircle(4, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.MAGENTA);
			StdDraw.filledCircle(6, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.ORANGE);
			StdDraw.filledCircle(8, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.DARK_GRAY);
			StdDraw.filledCircle(10, -2, circleRadius);
			StdDraw.setPenColor(StdDraw.GREEN);
			StdDraw.filledCircle(12, -2, circleRadius);
			// dark borders around circles
			StdDraw.setPenColor();
			for (int i = 2; i < 13; i += 2) {
				StdDraw.circle(i, -2, 0.75);
			}
		}
		// Draw the black borders around those circles
		StdDraw.setPenColor();
		for (int i = 2; i < 13; i += 2) {
			StdDraw.circle(i, -2, 0.75);
		}
		// The circles representing the different levels
		for (double i = (SizeOfGrid / 2); i < ((SizeOfGrid / 2) + 3); i += 1) {
			StdDraw.setPenColor(StdDraw.GRAY);
			StdDraw.filledCircle(i, (SizeOfGrid + 1.5), 0.4);
			StdDraw.setPenColor();
			StdDraw.circle(i, (SizeOfGrid + 1.5), 0.4);
			if (i == (SizeOfGrid / 2)) {
				StdDraw.text(i, (SizeOfGrid + 1.5), "S");
			} else if (i == (SizeOfGrid / 2) + 1) {
				StdDraw.text(i, (SizeOfGrid + 1.5), "M");
			} else {
				StdDraw.text(i, (SizeOfGrid + 1.5), "L");
			}
		}
		// The two color grids (first of the current color grid)
		double gridSquareRadius = 0.25;
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledSquare(SizeOfGrid - 3.5, (SizeOfGrid + 1.5),
				gridSquareRadius);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledSquare(SizeOfGrid - 3, (SizeOfGrid + 1.5),
				gridSquareRadius);
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledSquare(SizeOfGrid - 2.5, (SizeOfGrid + 1.5),
				gridSquareRadius);
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledSquare(SizeOfGrid - 3.5, (SizeOfGrid + 1),
				gridSquareRadius);
		StdDraw.setPenColor(StdDraw.PINK);
		StdDraw.filledSquare(SizeOfGrid - 3, (SizeOfGrid + 1), gridSquareRadius);
		StdDraw.setPenColor(StdDraw.CYAN);
		StdDraw.filledSquare(SizeOfGrid - 2.5, (SizeOfGrid + 1),
				gridSquareRadius);
		// 2by3 grid2
		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		StdDraw.filledSquare(SizeOfGrid - 1.5, (SizeOfGrid + 1.5),gridSquareRadius);
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.filledSquare(SizeOfGrid - 1, (SizeOfGrid + 1.5),gridSquareRadius);
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.filledSquare(SizeOfGrid - 0.5, (SizeOfGrid + 1.5),gridSquareRadius);
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledSquare(SizeOfGrid - 1.5, (SizeOfGrid + 1),gridSquareRadius);
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.filledSquare(SizeOfGrid - 1, (SizeOfGrid + 1), gridSquareRadius);
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledSquare(SizeOfGrid - 0.5, (SizeOfGrid + 1),gridSquareRadius);
		//Save Button
		StdDraw.setPenColor(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
		StdDraw.filledRectangle(SizeOfGrid/2, -4, SizeOfGrid/2, 0.75);
		StdDraw.setPenRadius(0.015);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.rectangle(SizeOfGrid/2, -4, SizeOfGrid/2, 0.75);
		Font f = new Font ("sansserif",Font.BOLD,25);
		StdDraw.setFont(f);
		StdDraw.text(SizeOfGrid/2, -4.2, "SAVE");
		//Solver Buton
		StdDraw.setPenColor((new Color((int)(254*Math.random())+1,(int)(254*Math.random())+1,(int)(254*Math.random())+1)));
		StdDraw.setPenRadius();
		f = new Font ("sansserif",Font.BOLD,15);
		StdDraw.setFont(f);
		double SolverRadius = 1;
		StdDraw.filledSquare(SizeOfGrid-1,-1.7,SolverRadius);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(SizeOfGrid-1,-0.5,"SOLVER");
		StdDraw.square(SizeOfGrid-1,-1.7,SolverRadius);
		StdDraw.setPenColor();
	}
	public static void FloodMyGrid(int[][] Array, int[] ChangeListI, int[] ChangeListJ, int Button, boolean DefaultColor, int m) {
		for (int k = 0; k < m + 1; k++) {
			if (DefaultColor) {
				if (Button == 0) {
					StdDraw.setPenColor(StdDraw.BLUE);
				}
				if (Button == 1) {
					StdDraw.setPenColor(StdDraw.RED);
				}
				if (Button == 2) {
					StdDraw.setPenColor(StdDraw.YELLOW);
				}
				if (Button == 3) {
					StdDraw.setPenColor(StdDraw.GREEN);
				}
				if (Button == 4) {
					StdDraw.setPenColor(StdDraw.PINK);
				}
				if (Button == 5) {
					StdDraw.setPenColor(StdDraw.CYAN);
				}
				StdDraw.filledSquare(ChangeListJ[k] + 0.5,(Array.length - 1 - ChangeListI[k]) + 0.5, 0.5);
				StdDraw.setPenColor();
				StdDraw.square(ChangeListJ[k] + 0.5, (Array.length - 1 - ChangeListI[k]) + 0.5, 0.5);
			}
			else {
				if (Button == 0) {
					StdDraw.setPenColor(StdDraw.BOOK_BLUE);
				}
				if (Button == 1) {
					StdDraw.setPenColor(StdDraw.BOOK_RED);
				}
				if (Button == 2) {
					StdDraw.setPenColor(StdDraw.MAGENTA);
				}
				if (Button == 3) {
					StdDraw.setPenColor(StdDraw.ORANGE);
				}
				if (Button == 4) {
					StdDraw.setPenColor(StdDraw.DARK_GRAY);
				}
				if (Button == 5) {
					StdDraw.setPenColor(StdDraw.GREEN);
				}
				StdDraw.filledSquare(ChangeListJ[k] + 0.5,(Array.length - 1 - ChangeListI[k]) + 0.5, 0.5);
				StdDraw.setPenColor();
				StdDraw.square(ChangeListJ[k] + 0.5,(Array.length - 1 - ChangeListI[k]) + 0.5, 0.5);
			}
		}
	}
	public static void Score(int SizeOfGrid, int CurrentScore) {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setPenRadius(0.025);
		StdDraw.line(0, (SizeOfGrid + 1), (SizeOfGrid / 2) - 1,(SizeOfGrid + 1));
		Font f = new Font("monospaced", Font.BOLD + Font.ITALIC, 14);
		StdDraw.setFont(f);
		StdDraw.setPenColor();
		StdDraw.textLeft(0, (SizeOfGrid + 1), "Your Score:" + " "+ CurrentScore + " / " + (2 * SizeOfGrid));
		StdDraw.setPenRadius();
	}
	public static boolean HighScore(int[][] DefaultArray,int score) throws IOException
	{
		 //Now we'll search the highscore file using a buffered reader 
		 boolean Highscore = false;
		 File file = new File("Highscore.txt");
		 Scanner Scan = new Scanner(file);
		 int x = Scan.nextInt();
		 //Check whether the file has any value stored
		 if(Scan != null){	 
			 FileReader fileScan = new FileReader(file);
			 BufferedReader br = new BufferedReader(fileScan);
			 String NotConvertedFile = "";
			 for(String NewLine = ""; NewLine != null; NewLine = br.readLine()){
				NotConvertedFile = NotConvertedFile + NewLine;
			 }
			 br.close();
			 //Make an array of strings of the integer values captured
			 String[] ArrayFromFile;
			 ArrayFromFile = NotConvertedFile.split(" ");
			 //Convert those strings into integers store them in the highscoe array
			 int SizeOfArray = ArrayFromFile.length;
			 int[] HighscoreList = new int [SizeOfArray];
			 int k = 0;
			 for(int i = 0; i < SizeOfArray ; i++){
					HighscoreList[i] = Integer.parseInt(ArrayFromFile[k]);
					k++;
				}
			 //Finding the minimum value in that list of high scores
			 int min = Integer.MAX_VALUE;
			 for(int i = 0; i < SizeOfArray; i++){
			 	if(HighscoreList[i] < min){
			 		min = HighscoreList[i];
			 	}
			 }
			 x = min;
		 }
		 else{
		 	x = 0;
		 } 
		 int j = 0;
		 if(score < x){
		 	try{
		 		//Writing the new highscore in text file
				FileWriter Lefthand = new FileWriter("Highscore.txt",true);
				BufferedWriter Righthand = new BufferedWriter(Lefthand);
				Lefthand.write(" " + String.valueOf(score));
				Lefthand.close();
			 }
			 catch(IOException e){
			 	System.out.println(e);
			 }
			 Highscore = true;
		 }
		 return Highscore;
	}
	public static void Save(boolean DefaultColors,int[][] DefaultArray,int score) throws IOException
	{
		FileWriter LeftHand = new FileWriter("Save.txt");
		BufferedWriter bw = new BufferedWriter(LeftHand);
		String Array= String.valueOf(DefaultColors) + " " + String.valueOf(DefaultArray.length) + " " + String.valueOf(score) + " ";
		for(int i = 0; i < DefaultArray.length; i++){
			for(int j = 0; j < DefaultArray.length; j++){
				Array = Array + String.valueOf(DefaultArray[i][j]) + " ";
			}
		}
		bw.write(Array);
		bw.close();
	}
	public static int[][] Load(boolean DefaultColors,int[][] DefaultArray,int score) throws IOException
	{	
		String NotConvertedFile = "";
		String[] ArrayFromFile; 
		File file = new File ("Save.txt");
		FileReader fileScan = new FileReader(file);
		BufferedReader br = new BufferedReader(fileScan);
		for(String NewLine = ""; NewLine != null; NewLine = br.readLine()){
			NotConvertedFile = NotConvertedFile + NewLine;
		}
		br.close();
		ArrayFromFile = NotConvertedFile.split(" "); 
		int k = 3;
		boolean x = (Boolean.parseBoolean(ArrayFromFile[0]));
		DefaultColors = x;
		int SizeOfGrid = Integer.parseInt(ArrayFromFile[1]);
		int y = Integer.parseInt(ArrayFromFile[1]);
		score = y;
		DefaultArray = new int [SizeOfGrid][SizeOfGrid];
		for(int i = 0; i < SizeOfGrid ; i++){
			for(int j = 0; j < SizeOfGrid; j++){
				DefaultArray[i][j] = Integer.parseInt(ArrayFromFile[k]);
				k++;
			}
		}
		return DefaultArray;	
	}
	public static int[][] DrawMySmallGrid(int[][] DefaultArray,boolean DefaultColors,int score)
	{
		StdDraw.resetMouseInput();
		int SizeOfGrid = DefaultArray.length;
		StdDraw.clear((new Color((int)(254*Math.random())+1,(int)(254*Math.random())+1,(int)(254*Math.random())+1)));
		int x = 512;
		int y = 512;
		if(SizeOfGrid == 15){
			x = 512;
			y = 512;
		}
		else if(SizeOfGrid == 20){
			x = 640;
			y = 640;
		}
		else if(SizeOfGrid == 30){
			x = 768;
			y = 768;
		}
		StdDraw.setCanvasSize(x,y);
		StdDraw.clear(new Color((int)(254*Math.random())+1,(int)(254*Math.random())+1,(int)(254*Math.random())+1));
		DefaultArray = new int[SizeOfGrid][SizeOfGrid];
		DefaultArray = DrawMyGrid(DefaultColors,false,DefaultArray);
		DrawMyFeatures(SizeOfGrid,DefaultColors);
		score = 0;
		Score(SizeOfGrid, score);
		return DefaultArray;
	}
	public static boolean Restart(int z,boolean HasWon,boolean Highscore)
	{
		StdDraw.clear();
		if(Highscore){
			StdDraw.clear((new Color((int)(254*Math.random())+1,(int)(254*Math.random())+1,(int)(254*Math.random())+1)));
			StdDraw.setXscale(-2,2);
			StdDraw.setYscale(-2,2);
			StdAudio.play("Clap.wav");
			SquareS.Square(7,-1,0,0,1);
		}
		else if(HasWon && !Highscore){
			StdDraw.clear((new Color((int)(254*Math.random())+1,(int)(254*Math.random())+1,(int)(254*Math.random())+1)));
			StdDraw.setXscale(-2,2);
			StdDraw.setYscale(-2,2);
			StdAudio.play("JustWin.wav");
			SquareS.Square(7,-1,0,0,1);
		}
		StdDraw.setCanvasSize(300,300);
		StdDraw.setYscale(-5-0.5,5+0.5);
		StdDraw.setXscale(-5-0.5,5+0.5);
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.filledRectangle(0,0,5,4);
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.setPenRadius();
		StdDraw.filledRectangle(0,4.8,6,0.9);
		StdDraw.setPenColor();
		StdDraw.rectangle(0,4.8,6,0.9);
		StdDraw.setPenColor(StdDraw.BLUE);
		Font f = new Font ("sansserif",Font.BOLD,15);
		StdDraw.setFont(f);
		if(HasWon && Highscore){
			StdDraw.resetMouseInput();
			StdDraw.text(0,5.3,"SALUTE!");
			StdDraw.text(0,4.3,"You have the current highscore" );
		}
		else if (HasWon){
			StdDraw.text(0,5.3,"Well done!");
		}
		else{
			StdDraw.text(0,5.3,"These things happen, next time hey");
		}
		StdDraw.resetMouseInput();
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(0,3.3,"Want to exit or play again?");
		StdDraw.setFont();
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledSquare(-2.25,0,2);
		StdDraw.setPenColor();
		StdDraw.text(-2.25,0,"Quit");
		StdDraw.square(-2.25,0,2);
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledSquare(2.25,0,2);
		StdDraw.setPenColor();
		StdDraw.text(2.25,0,"Restart");
		StdDraw.square(2.25,0,2);
		boolean StartOver;
		while(true){
			double x = StdDraw.mouseReleaseX();
			double y = StdDraw.mouseReleaseY();
			boolean Restart = StdDraw.mouseReleased() && (x >=0.25) && (x <= 4.25) && (y >= -2) && (y <= 2);
			boolean Quit = StdDraw.mouseReleased() && (x >= -4.25 && x <= -0.25) && (y >= -2 && y <= 2);
			if(Restart){ 
				StartOver = true;
				break;
			}
			if(Quit){
				StartOver = false;
				break;
			}	
		}
		return StartOver;
	}
	public static void main(String args[]) throws IOException {
	}
}
