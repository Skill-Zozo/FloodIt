public class Solver
{
	static int[] Button = new int [6];
	static boolean[][] BooleanArray;
	static int[][] DefaultArray;
	public static boolean[][] Reset (boolean[][] BooleanArray)
	{
		for(int i = 0; i < BooleanArray.length; i++){
			for(int j = 0; j < BooleanArray.length; j++){
				BooleanArray[i][j] = false;
			}
		}
		return BooleanArray;
	} 
	public static void PrintArray(int[][] DefaultArray)
	{
		for(int i = 0; i < DefaultArray.length; i++){
			for(int j = 0; j < DefaultArray.length; j++){
				System.out.print(DefaultArray[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void floodfill(int x,int y,int oldColor,boolean[][] BooleanArray,int[][] DefaultArray,int[] Button)
	{
		if( x < 0 || y < 0){
			return;
		}
		if(x >= DefaultArray.length || y >= DefaultArray.length){
			return;
		}
		if(!BooleanArray[x][y]){
			BooleanArray[x][y] = true;
			for(int i = 0; i < Button.length; i++){
				if(DefaultArray[x][y] != oldColor && DefaultArray[x][y] == i){
					Button[i]++;
				}
				else if(DefaultArray[x][y] == oldColor){
					floodfill(x+1,y,oldColor,BooleanArray, DefaultArray,Button);
					floodfill(x,y+1,oldColor,BooleanArray, DefaultArray,Button);
					floodfill(x-1,y,oldColor,BooleanArray, DefaultArray,Button);
					floodfill(x,y-1,oldColor,BooleanArray, DefaultArray,Button);
				}
			} 
		}
		else{
			return;
		}
		if(x != DefaultArray.length-1 && DefaultArray[x][y] == DefaultArray[x+1][y]){
			floodfill(x+1,y,oldColor,BooleanArray, DefaultArray,Button);
		}
		if(y != DefaultArray.length-1 && DefaultArray[x][y] == DefaultArray[x][y+1]){
			floodfill(x,y+1,oldColor,BooleanArray, DefaultArray,Button);
		}
		if(y != 0 && DefaultArray[x][y] == DefaultArray[x][y-1]){
			floodfill(x,y-1,oldColor,BooleanArray, DefaultArray,Button);
		}
		if(x != 0 && DefaultArray[x][y] == DefaultArray[x-1][y]){
			floodfill(x-1,y,oldColor,BooleanArray, DefaultArray,Button);
		}
	}
	public static void Floodfill(int x, int y, int oldColor, int newColor,int[][] DefaultArray)
	{	
		if( x < 0 || y < 0){
			return;
		}
		if(x >= DefaultArray.length || y >= DefaultArray.length){
			return;
		}
		if(DefaultArray[x][y] == oldColor && DefaultArray[x][y] != newColor){
			DefaultArray[x][y] = newColor;	
		}
		else{
			return;
		}
		Floodfill(x+1,y,oldColor,newColor,DefaultArray);
		Floodfill(x,y+1,oldColor,newColor,DefaultArray);
		Floodfill(x-1,y,oldColor,newColor,DefaultArray);
		Floodfill(x,y-1,oldColor,newColor,DefaultArray);
	}
	public static void main(String[] args)
	{
		DefaultArray = new int [Integer.parseInt(args[0])][Integer.parseInt(args[0])];
		BooleanArray = new boolean[Integer.parseInt(args[0])][Integer.parseInt(args[0])];
		for(int i = 0; i < DefaultArray.length; i++){
			for(int j = 0; j < DefaultArray.length; j++){
				DefaultArray[i][j] = StdIn.readInt();
			}
		}
		int score = 0;
		int oldColor;
		int newColor;
		int max = Integer.MIN_VALUE;
		int j = 0;
		while(score < 2*DefaultArray.length){
			j = 0;
			max = Integer.MIN_VALUE;
			oldColor = DefaultArray[0][0];
			BooleanArray = Reset(BooleanArray);
			Button = new int [6];
			floodfill(0,0,oldColor,BooleanArray, DefaultArray,Button);
			System.out.println("Button Array");
			for(int i = 0; i < Button.length; i++){
				System.out.print(Button[i] + " ");
			}
			System.out.println();
			for(int i = 0; i < Button.length; i++){
				if(Button[i] > max){
					max = Button[i];
					j = i;
				}
			}
			System.out.println("Next Best Color: " + j);
			PrintArray(DefaultArray);
			newColor = j;
			Floodfill(0,0,oldColor,newColor,DefaultArray);
			score++;
		}
	}
}
