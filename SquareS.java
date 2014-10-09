import java.awt.Color;

public class SquareS
{
	public static int Square(int n,int count,double x,double y,double r)
	{
		count += 1;
		if(count >= n){
			return 4;
		}
		Square(n,count,-x-r,y+r,r/2.2);
		Square(n,count,x+r,y+r,r/2.2);
		Square(n,count,-x-r,-y-r,r/2.2);
		Square(n,count,x+r,-y-r,r/2.2);
		StdDraw.setPenColor(new Color((int)(256*Math.random()),(int)(256*Math.random()),(int)(256*Math.random())));
		StdDraw.filledSquare(x,y,r);
		StdDraw.setPenColor();
		StdDraw.square(x,y,r);
		return 4;
	}
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		StdDraw.setYscale(-2,2);
		StdDraw.setXscale(-2,2);
		int count = -1;
		Square(n,count,0,0,1);
	}
}
