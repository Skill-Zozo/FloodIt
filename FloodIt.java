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
//			Name: BANELE MATSEBULA			O:100%		Class: FloodIt(THE GAME ITSELF... Beaware of the variables required as input) 
public class FloodIt
{
	public static void main(String[] args) throws IOException
	{
		boolean UserGrid; //This is a boolean used by the DrawMyGrid method of which uses to check if this grid should be random or not 
		int SizeOfGrid;
		int[][] DefaultArray = new int [15][15];
		int score = 0;
		boolean DefaultColors = false;
		//The first thing the game asks is weher or not you want to save of which you return a boolean
		System.out.println("Do you want to continue from the last saved point,return true for an affirmative answer");
		boolean Load = StdIn.readBoolean();
		StdDraw.clear(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
		if(Load){
			//We load from that file certain information like the score of that previous game
			DefaultArray = GUI.Load(DefaultColors,DefaultArray,score);
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
			DefaultColors = Boolean.parseBoolean(ArrayFromFile[0]);
			SizeOfGrid = Integer.parseInt(ArrayFromFile[1]);
			score = Integer.parseInt(ArrayFromFile[2]);
			//Adjusting canvas size
			if(SizeOfGrid <= 17){
				StdDraw.setCanvasSize();
			}
			else if(SizeOfGrid >= 25){
				StdDraw.setCanvasSize(768,768);
			}
			else{
				StdDraw.setCanvasSize(640,640);
			}
			StdDraw.clear(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
			DefaultArray = GUI.DrawMyGrid(DefaultColors,true,DefaultArray);
			GUI.DrawMyFeatures(SizeOfGrid,DefaultColors);
			UserGrid = true;
		}
		else{
			//If the user doesn't want to load from a saved ile then we'll ask if he/she will like to define the grid or not
			System.out.println("Do you want a random grid or your own grid with specific colors?\nPlease return true if you want your own or false if you want a random one.\nPlease also take note the smallest posiible grid size is a 13 by 13 grid.");
			StdDraw.clear(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
			//Get it, USERGRID
			UserGrid = StdIn.readBoolean();
			if(UserGrid){
				int x = StdIn.readInt();
				int y = StdIn.readInt();
				DefaultArray = new int[x][y]; 
				for(int i = 0; i < x; i++){
					for(int j = 0; j < x; j++){
						DefaultArray[i][j] = StdIn.readInt()-1;
					}
					StdIn.readLine();
				}
				SizeOfGrid = x;
				UserGrid = true;
			}
			else{	
				SizeOfGrid = 15;
				DefaultColors = true;
				DefaultArray = new int[SizeOfGrid][SizeOfGrid];
			}
			score = 0;
			DefaultColors = true;
			DefaultArray = GUI.DrawMyGrid(DefaultColors,UserGrid,DefaultArray);
			GUI.DrawMyFeatures(SizeOfGrid,DefaultColors);
			UserGrid = false;
		}
		StdDraw.setPenColor();
		GUI.Score(SizeOfGrid, score);
		double gridSquareRadius = 0.25;
		boolean GameFinished = false;
		StdDraw.resetMouseInput();
		// Mouse events
		while (!GameFinished) {
			SizeOfGrid = DefaultArray.length;
			double MouseLocation_X = StdDraw.mouseX();
			double MouseLocation_Y = StdDraw.mouseY();
			double LastMouseLocation_X = StdDraw.mouseReleaseX();
			double LastMouseLocation_Y = StdDraw.mouseReleaseY();
			boolean MouseRelease = StdDraw.mouseReleased();
			double r;
			boolean S = false;
			boolean M = false;
			boolean L = false;
			//I calculte the radius from(x,y) of the last click to the center of the circular butons
			int centerX;
			for(double i = SizeOfGrid/2; i < SizeOfGrid/2 + 3 ; i += 1){
				double a = Math.pow((i-LastMouseLocation_X),2);
				double b = Math.pow((SizeOfGrid + 1.5 - LastMouseLocation_Y),2);
				r = Math.sqrt(a + b);
				centerX = (int)(LastMouseLocation_X + Math.sqrt(Math.pow(r,2)-b));
				boolean isWithin = r <= 0.4;
				if(isWithin){
					S = centerX <= SizeOfGrid/2 + 0.5 && centerX >= SizeOfGrid/2 - 0.5 && MouseRelease;
					M = centerX <= SizeOfGrid/2 + 1.5 && centerX >= SizeOfGrid/2 + 0.5 && MouseRelease;
					L = centerX <= SizeOfGrid/2 + 2.5 && centerX >= SizeOfGrid/2 + 1.5 && MouseRelease;
				}
			} 
			//Small grid	
			if (S) {
				StdDraw.resetMouseInput();
				UserGrid = false;
				SizeOfGrid = 15;
				StdDraw.clear();
				StdDraw.setCanvasSize();
				StdDraw.clear(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
				DefaultArray = new int[SizeOfGrid][SizeOfGrid];
				DefaultArray = GUI.DrawMyGrid(DefaultColors,UserGrid,DefaultArray);
				GUI.DrawMyFeatures(SizeOfGrid,DefaultColors);
				score = 0;
				GUI.Score(SizeOfGrid, score);
				S = false; 
			}
			//Medium grid
			if (M) {
				StdDraw.resetMouseInput();
				SizeOfGrid = 20;
				UserGrid = false;
				StdDraw.clear();
				StdDraw.setCanvasSize(640, 640);
				StdDraw.clear(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
				DefaultArray = new int[SizeOfGrid][SizeOfGrid];
				DefaultArray = GUI.DrawMyGrid(DefaultColors,UserGrid,DefaultArray);
				GUI.DrawMyFeatures(SizeOfGrid,DefaultColors);
				score = 0;
				GUI.Score(SizeOfGrid, score);
				M = false;
			}
			// 30 by 30
			if (L) {
				StdDraw.resetMouseInput();
				UserGrid = false;
				SizeOfGrid = 30;
				StdDraw.clear();
				StdDraw.setCanvasSize(768, 768);
				StdDraw.clear(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
				DefaultArray = new int[SizeOfGrid][SizeOfGrid];
				DefaultArray = GUI.DrawMyGrid(DefaultColors,UserGrid,DefaultArray);
				GUI.DrawMyFeatures(SizeOfGrid,DefaultColors);
				score = 0;
				GUI.Score(SizeOfGrid, score);
				L = false;
			}
			// Altenate colours
			boolean MouseRangeForAlternateColors = (MouseRelease)
					&& ((MouseLocation_X >= SizeOfGrid - 1.75) && (MouseLocation_X <= SizeOfGrid - 0.25)
					&& (MouseLocation_Y >= (SizeOfGrid + 0.75)) && (MouseLocation_Y <= (SizeOfGrid + 1.5 + gridSquareRadius)))
					&& ((LastMouseLocation_X >= SizeOfGrid - 1.75) && (LastMouseLocation_X <= SizeOfGrid - 0.25)
					&& (LastMouseLocation_Y >= (SizeOfGrid + 0.75)) && (LastMouseLocation_Y <= (SizeOfGrid + 1.5 + gridSquareRadius)));
			if (MouseRangeForAlternateColors) {
				StdDraw.resetMouseInput();
				DefaultColors = false;
				UserGrid = false;
				DefaultArray = new int[SizeOfGrid][SizeOfGrid];
				DefaultArray = GUI.DrawMyGrid(DefaultColors,UserGrid,DefaultArray);
				score = 0;
				GUI.Score(SizeOfGrid, score);
			}
			// Default colors
			boolean MouseRangeForDefaultColors = (MouseRelease)
					&& (MouseLocation_X >= SizeOfGrid - 3.75)
					&& (MouseLocation_X <= SizeOfGrid - 2.25)
					&& (MouseLocation_Y >= SizeOfGrid + 0.75)
					&& (MouseLocation_Y <= SizeOfGrid + 1.75)
					&& (LastMouseLocation_X >= SizeOfGrid - 3.75)
					&& (LastMouseLocation_X <= SizeOfGrid - 2.25)
					&& (LastMouseLocation_Y >= SizeOfGrid + 0.75)
					&& (LastMouseLocation_Y <= SizeOfGrid + 1.75);
			//Save
			boolean Save = (MouseRelease) && (LastMouseLocation_X >= 0.0) && (LastMouseLocation_X <= SizeOfGrid) && (LastMouseLocation_Y >= -4.75) && (LastMouseLocation_Y <= -3.25);
			if(Save){
				StdDraw.resetMouseInput();
				GUI.Save(DefaultColors,DefaultArray,score);
			}
			//Default Colors 
			if (MouseRangeForDefaultColors) {
				StdDraw.resetMouseInput();
				DefaultColors = true;
				int[][] ArrayForDefaultColors = new int[SizeOfGrid][SizeOfGrid];
				UserGrid = false;
				DefaultArray = GUI.DrawMyGrid(DefaultColors,UserGrid,DefaultArray);
				score = 0;
				GUI.Score(SizeOfGrid, score);
			}
			//Copy of the array before it is flooded inorder to compare it to the floode grid so as to determine whether the game has finished or not 
			int[][] CopyOfArray = new int[DefaultArray.length][DefaultArray.length];
			for (int i = 0; i < DefaultArray.length; i++) {
				for (int j = 0; j < DefaultArray.length; j++) {
					CopyOfArray[i][j] = DefaultArray[i][j];
				}
			}
			//Color Buttons
			double y = -2.0; 
			boolean ButtonRange0 = false;
			boolean ButtonRange1 = false;
			boolean ButtonRange2 = false;
			boolean ButtonRange3 = false;
			boolean ButtonRange4 = false;
			boolean ButtonRange5 = false;
			boolean ButtonSolver = LastMouseLocation_X >= SizeOfGrid - 2 && LastMouseLocation_X <= SizeOfGrid && LastMouseLocation_Y >= -2.7 && LastMouseLocation_Y <= -0.7; 
			for(int i = 2; i < 13; i = i + 2){
				double a = Math.pow((i-LastMouseLocation_X),2);
				double b = Math.pow((y - LastMouseLocation_Y),2);
				r = Math.sqrt(a + b);
				centerX = (int)(LastMouseLocation_X + Math.sqrt(Math.pow(r,2)-b));
				boolean isWithin = r <= 0.75;
				if(isWithin){
					ButtonRange0 = centerX <= 2.5 && centerX >= 1.5;
					ButtonRange1 = centerX <= 4.5 && centerX >= 3.5;
					ButtonRange2 = centerX <= 6.5 && centerX >= 5.5;
					ButtonRange3 = centerX <= 8.5 && centerX >= 7.5;
					ButtonRange4 = centerX <= 10.5 && centerX >= 9.5;
					ButtonRange5 = centerX <= 12.5 && centerX >= 11.5;
				}
		
			}
			boolean HaveAnyOfTheButtonsBeenPressed = ButtonRange0
					|| ButtonRange1 || ButtonRange2 || ButtonRange3
					|| ButtonRange4 || ButtonRange5 || ButtonSolver;
			int Button = 99;
			if (MouseRelease && HaveAnyOfTheButtonsBeenPressed) {
				StdDraw.resetMouseInput();
				if (ButtonRange0) {
					Button = 0;
				} else if (ButtonRange1) {
					Button = 1;
				} else if (ButtonRange2) {
					Button = 2;
				} else if (ButtonRange3) {
					Button = 3;
				} else if (ButtonRange4) {
					Button = 4;
				} else if (ButtonRange5) {
					Button = 5;
				}
				else if (ButtonSolver) {
					int Move = 0;
					int[] Button2 = new int [6];
					int max = Integer.MIN_VALUE;
					int oldColor = DefaultArray[0][0];
					boolean[][] BooleanArray = new boolean [SizeOfGrid][SizeOfGrid];
					BooleanArray = Solver.Reset(BooleanArray);
					Solver.floodfill(0,0,oldColor,BooleanArray, DefaultArray,Button2);
					for(int i = 0; i < Button2.length; i++){
						if(Button2[i] > max){
							max = Button2[i];
							Move = i;
						}
					}
					Button = Move;
				}
				Logic.Algorithm(DefaultArray, Button);
				int k = 0;
				int[] ChangeListI = new int[DefaultArray.length
						* DefaultArray.length];
				int[] ChangeListJ = new int[DefaultArray.length
						* DefaultArray.length];
				int[] ChangedTo = new int[DefaultArray.length
						* DefaultArray.length];
				for (int i = 0; i < DefaultArray.length; i++) {
					for (int j = 0; j < DefaultArray.length; j++) {
						if (DefaultArray[i][j] != CopyOfArray[i][j]) {
							ChangeListI[k] = i;
							ChangeListJ[k] = j;
							ChangedTo[k] = Button;
							k = k + 1;
						}
					}
				}
				score++;
				GUI.Score(DefaultArray.length, score);
				GUI.FloodMyGrid(DefaultArray,ChangeListI, ChangeListJ, Button,DefaultColors, k);
			}
			boolean HasWon = true;
			for (int i = 0; i < DefaultArray.length; i++) {
				for (int j = 0; j < DefaultArray.length; j++) {
					if (DefaultArray[0][0] != DefaultArray[i][j]) {
						HasWon = false;
						break;
					}
				}
				if(!HasWon){
					break;
				}
			}
			boolean Restart;
			if (HasWon){
				 boolean Highscore = GUI.HighScore(DefaultArray,score);
				 Restart = GUI.Restart(DefaultArray.length,HasWon,Highscore);
				 if(!Restart){
				 	GameFinished = true;
				 }
				 else if (Restart){
				 	DefaultArray = GUI.DrawMySmallGrid(DefaultArray,DefaultColors,score);
				 }
				 score = 0;
			}
			if (score == 2 * DefaultArray.length && !HasWon) {
				Restart = GUI.Restart(DefaultArray.length,HasWon,false);
				if(!Restart){
				 	GameFinished = true;
				}
				else if(Restart){
					DefaultArray = GUI.DrawMySmallGrid(DefaultArray,DefaultColors,score);
				}
				score = 0;
			}
		}
			System.exit(0);
	}
}
