import java.awt.Color;
import java.io.*;
import java.io.IOException;
//THIS CLASS IS SPECIFICALLY FOR THE SOLVER.... SIMPLY WATCH THE MAGIC HAPPEN

public class ControlFlowSolver
{
	public static int[][] DefaultArray = new int [15][15]; 
	public static void main(String args[]) throws IOException
	{
		StdDraw.clear(new Color((int)(254*Math.random())+1,(int)(254*Math.random())+1,(int)(254*Math.random())+1));
		int SizeOfGrid = 15;
		boolean DefaultColors = true;
		DefaultArray = new int[SizeOfGrid][SizeOfGrid];
		DefaultArray = GUI.DrawMyGrid(DefaultColors,false,DefaultArray);
		GUI.DrawMyFeatures(SizeOfGrid, DefaultColors);
		StdDraw.setPenColor();
		GUI.Score(SizeOfGrid, 0);
		double gridSquareRadius = 0.25;
		int score = 0;
		boolean GameFinished = false;
		while (!GameFinished){
			double MouseLocation_X = StdDraw.mouseX();
			double MouseLocation_Y = StdDraw.mouseY();
			double LastMouseLocation_X = StdDraw.mouseReleaseX();
			double LastMouseLocation_Y = StdDraw.mouseReleaseY();
			boolean MouseRelease = StdDraw.mouseReleased();
			double r;
			boolean S = false;
			boolean M = false;
			boolean L = false;
			double centerX;
			double centerX2;
			double y = -2.0; 
			boolean ButtonRange0 = false;
			boolean ButtonRange1 = false;
			boolean ButtonRange2 = false;
			boolean ButtonRange3 = false;
			boolean ButtonRange4 = false;
			boolean ButtonRange5 = false;
			int Button = 99;
			boolean HasWon = true;
			boolean Restart;
			//The difficulty buttons
			for(double i = SizeOfGrid/2; i < SizeOfGrid/2 + 3 ; i += 1){
				double a = Math.pow((i-LastMouseLocation_X),2);
				double b = Math.pow((SizeOfGrid + 1.5 - LastMouseLocation_Y),2);
				r = Math.sqrt(a + b);
				centerX = (LastMouseLocation_X + Math.sqrt(Math.pow(r,2)-b));
				boolean isWithin = r <= 0.4;
				if(isWithin && MouseRelease){
					S = centerX <= SizeOfGrid/2 + 0.5 && centerX >= SizeOfGrid/2 - 0.5;
					M = centerX <= SizeOfGrid/2 + 1.5 && centerX >= SizeOfGrid/2 + 0.5;
					L = centerX <= SizeOfGrid/2 + 2.5 && centerX >= SizeOfGrid/2 + 1.5;
					StdDraw.resetMouseInput();
				}
			} 
			//Responses to the difficulty buttons
			if (S) {
				StdDraw.resetMouseInput();
				SizeOfGrid = 15;
				StdDraw.clear();
				StdDraw.setCanvasSize();
				StdDraw.clear(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
				DefaultArray = new int[SizeOfGrid][SizeOfGrid];
				DefaultArray = GUI.DrawMyGrid(DefaultColors,false,DefaultArray);
				GUI.DrawMyFeatures(SizeOfGrid, DefaultColors);
				score = 0;
				GUI.Score(SizeOfGrid, score);
				S = false; 
			}
			if (M) {
				StdDraw.resetMouseInput();
				SizeOfGrid = 20;
				StdDraw.clear();
				StdDraw.setCanvasSize(640, 640);
				StdDraw.clear(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
				DefaultArray = new int[SizeOfGrid][SizeOfGrid];
				DefaultArray = GUI.DrawMyGrid(DefaultColors,false,DefaultArray);
				GUI.DrawMyFeatures(SizeOfGrid, DefaultColors);
				score = 0;
				GUI.Score(SizeOfGrid, score);
				M = false;
			}
			if (L) {
				StdDraw.resetMouseInput();
				SizeOfGrid = 30;
				StdDraw.clear();
				StdDraw.setCanvasSize(768, 768);
				StdDraw.clear(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
				DefaultArray = new int[SizeOfGrid][SizeOfGrid];
				DefaultArray = GUI.DrawMyGrid(DefaultColors,false,DefaultArray);
				GUI.DrawMyFeatures(SizeOfGrid, DefaultColors);
				score = 0;
				GUI.Score(SizeOfGrid, score);
				L = false;
			}
			//The game buttons
			for(int i = 2; i < 13; i = i + 2){
				double a = Math.pow((i-LastMouseLocation_X),2);
				double b = Math.pow((y - LastMouseLocation_Y),2);
				r = Math.sqrt(a + b);
				centerX = (LastMouseLocation_X - Math.sqrt(Math.pow(r,2)-b));
				centerX2 = (LastMouseLocation_X + Math.sqrt(Math.pow(r,2)-b));
				boolean isWithin = r <= 0.75;
				if(isWithin){
					ButtonRange0 = centerX == 2 || centerX2 == 2;
					ButtonRange1 = centerX == 4 || centerX2 == 4;
					ButtonRange2 = centerX == 6 || centerX2 == 6;
					ButtonRange3 = centerX == 8 || centerX2 == 8;
					ButtonRange4 = centerX == 10 || centerX2 == 10;
					ButtonRange5 = centerX == 12 || centerX2 == 12;
				}
			}
			boolean Save = (MouseRelease) && (LastMouseLocation_X >= 0.0) && (LastMouseLocation_X <= SizeOfGrid) && (LastMouseLocation_Y >= -4.75) && (LastMouseLocation_Y <= -3.25);
			if(Save){
				StdDraw.resetMouseInput();
				Save(DefaultColors,DefaultArray,score);
			}
			// Altenate colours
			boolean MouseRangeForAlternateColors = (MouseRelease)
					&& ((MouseLocation_X >= SizeOfGrid - 1.75) 
					&& (MouseLocation_X <= SizeOfGrid - 0.25)
					&& (MouseLocation_Y >= (SizeOfGrid + 0.75)) 
					&& (MouseLocation_Y <= (SizeOfGrid + 1.5 + gridSquareRadius)))
					&& ((LastMouseLocation_X >= SizeOfGrid - 1.75) 
					&& (LastMouseLocation_X <= SizeOfGrid - 0.25)
					&& (LastMouseLocation_Y >= (SizeOfGrid + 0.75)) 
					&& (LastMouseLocation_Y <= (SizeOfGrid + 1.5 + gridSquareRadius)));
			if (MouseRangeForAlternateColors) {
				StdDraw.resetMouseInput();
				DefaultColors = false;
				DefaultArray = new int[SizeOfGrid][SizeOfGrid];
				DefaultArray = GUI.DrawMyGrid(DefaultColors,false,DefaultArray);
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
			if (MouseRangeForDefaultColors) {
				StdDraw.resetMouseInput();
				DefaultColors = true;
				int[][] ArrayForDefaultColors = new int[SizeOfGrid][SizeOfGrid];
				DefaultArray = GUI.DrawMyGrid(DefaultColors,false,DefaultArray);
				score = 0;
				GUI.Score(SizeOfGrid, score);
				//System.out.println(SizeOfGrid);
			}
			//The flooding
			int[][] CopyOfArray = new int[DefaultArray.length][DefaultArray.length];
			for (int i = 0; i < DefaultArray.length; i++) {
				for (int j = 0; j < DefaultArray.length; j++) {
					CopyOfArray[i][j] = DefaultArray[i][j];
				}
			}
			boolean HaveAnyOfTheButtonsBeenPressed = ButtonRange0
					|| ButtonRange1 || ButtonRange2 || ButtonRange3
					|| ButtonRange4 || ButtonRange5;
			StdDraw.resetMouseInput();
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
			Logic.Algorithm(DefaultArray, Button);
			int k = 0;
			int[] ChangeListI = new int[DefaultArray.length * DefaultArray.length];
			int[] ChangeListJ = new int[DefaultArray.length * DefaultArray.length];
			int[] ChangedTo = new int[DefaultArray.length * DefaultArray.length];
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
			GUI.FloodMyGrid(DefaultArray, ChangeListI, ChangeListJ, Button, DefaultColors, k);
			for (int i = 0; i < DefaultArray.length; i++) {
				for (int j = 0; j < DefaultArray.length; j++) {
					if (DefaultArray[0][0] != DefaultArray[i][j]) {
						HasWon = false;
						break;
					}
				}
			}
			if (HasWon) {
				 boolean Highscore = GUI.HighScore(DefaultArray,score);
				 Restart = GUI.Restart(DefaultArray.length,HasWon,Highscore);
				 if(!Restart){
				 	break;
				 }
				 else if (Restart){
				 	DefaultArray = GUI.DrawMySmallGrid(DefaultArray,DefaultColors,score);
				 }
				 score = 0;
			}
			if (score == 2 * DefaultArray.length && !HasWon) {
				Restart = GUI.Restart(DefaultArray.length,HasWon,false);
				if(!Restart){
				 	break;
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
