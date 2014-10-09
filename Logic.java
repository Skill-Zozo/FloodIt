public class Logic
{
	public static boolean Checklist(int[][] GridMatrix,int i, int j,int[] ChecklistI,int[] ChecklistJ)
	{	
		boolean Ok = false;
		for(int m = 0; m < GridMatrix.length*GridMatrix.length; m++){
			if(ChecklistI[m] == i && ChecklistJ[m] == j){
				Ok = true;
			}
		}
		return Ok;
	}
	public static int CheckDown(int[][] GridMatrix,int i,int j,int Button,boolean Check,int[] ChecklistI, int[] ChecklistJ,int k)
	{
		if(i < GridMatrix.length){
			int Next = i+1;
			Check = Checklist(GridMatrix,Next,j,ChecklistI, ChecklistJ);
			if(Check == false){
				for(int s = i+1; s < GridMatrix.length; s++){
					boolean HaveTheyBeenAdded = Checklist(GridMatrix,s,j,ChecklistI,ChecklistJ);
					if(HaveTheyBeenAdded == false){	
						k++;
						ChecklistI[k] = s;
						ChecklistJ[k] = j;
					}
					if((GridMatrix[0][0]==GridMatrix[s][j])&&(s + j!=0)){
						k = CheckLeft(GridMatrix,s,j,Button,Check,ChecklistI,ChecklistJ,k);
						k = CheckRight(GridMatrix,s,j,Button,Check,ChecklistI,ChecklistJ,k);
						GridMatrix[s][j] = Button;
					}
					else{
						break;
					}
				}
			}
		}
		return k;	
	}
	public static int CheckLeft(int[][] GridMatrix,int i, int j,int Button,boolean Check,int[] ChecklistI,int[] ChecklistJ,int k)
	{
		if(j < GridMatrix.length){
			int Next = j+1;
			Check = Checklist(GridMatrix,i,Next,ChecklistI, ChecklistJ);
			if(Check == false){	
				for(int t = j+1; t < GridMatrix.length; t++){
					boolean HaveTheyBeenAdded = Checklist(GridMatrix,i,t,ChecklistI,ChecklistJ);
					if(HaveTheyBeenAdded == false){	
						k++;
						ChecklistI[k] = i;
						ChecklistJ[k] = t;
					}
					if((GridMatrix[0][0] == GridMatrix[i][t])&& (i + t !=0)){
						k = CheckDown(GridMatrix,i,t,Button,Check,ChecklistI,ChecklistJ,k);
						k = CheckUp(GridMatrix,i,t,Button,Check,ChecklistI,ChecklistJ,k);
						GridMatrix[i][t] = Button;
					}
					else{
						break;
					}
				}
			}
		}
		return k;
	}
	public static int CheckUp(int[][] GridMatrix,int i, int j, int Button,boolean Check,int[] ChecklistI,int[] ChecklistJ, int k)
	{
		if(i > 0){
			int Next = i-1;
			Check = Checklist(GridMatrix,Next,j,ChecklistI, ChecklistJ);
			if(Check == false){
				for(int u = i-1; u>-1; u=u-1){
					boolean HaveTheyBeenAdded = Checklist(GridMatrix,u,j,ChecklistI,ChecklistJ);
					if(HaveTheyBeenAdded == false){	
						k++;
						ChecklistI[k] = u;
						ChecklistJ[k] = j;
					}
					if((GridMatrix[0][0] == GridMatrix[u][j])&&(u+j!=0)){
						k = CheckLeft(GridMatrix,u,j,Button,Check,ChecklistI,ChecklistJ,k);
						k = CheckRight(GridMatrix,u,j,Button,Check,ChecklistI,ChecklistJ,k);
						GridMatrix[u][j] = Button;
					}
					else{
						break;
					}
				}
			}
		}
		return k;
	}
	public static int CheckRight(int[][] GridMatrix, int i, int j, int Button,boolean Check,int[] ChecklistI,int[] ChecklistJ,int k)
	{
		if(j > 0){
			int Next = j-1;
			Check = Checklist(GridMatrix,i,Next,ChecklistI,ChecklistJ);
			if(Check == false){	
				for(int v = j-1; v>-1; v=v-1){
					boolean HaveTheyBeenAdded = Checklist(GridMatrix,i,v,ChecklistI,ChecklistJ);
					if(HaveTheyBeenAdded == false){	
						k++;
						ChecklistI[k] = i;
						ChecklistJ[k] = v;
					}
					if((GridMatrix[0][0] == GridMatrix[i][v])&&(i+v!=0)){
						k = CheckUp(GridMatrix,i,v,Button,Check,ChecklistI,ChecklistJ,k);
						k = CheckDown(GridMatrix,i,v,Button,Check,ChecklistI,ChecklistJ,k);
						GridMatrix[i][v] = Button;
					}
					else{
						break;
					}
				}
			}
		}
		return k;
	}
	public static void Algorithm(int[][] GridMatrix,int Button)
	{
		boolean Check = false;
		int [] ChecklistI = new int [GridMatrix.length*GridMatrix.length];
		int [] ChecklistJ = new int [GridMatrix.length*GridMatrix.length];
		int i = 0;
		int j = 0;
		int k = 0;
		ChecklistI[k] = i;
		ChecklistJ[k] = j;
		k = CheckDown(GridMatrix,i,j,Button,Check,ChecklistI,ChecklistJ,k);
		k = CheckLeft(GridMatrix,i,j,Button,Check,ChecklistI,ChecklistJ,k);
		k = CheckUp(GridMatrix,i,j,Button,Check,ChecklistI,ChecklistJ,k);
		k = CheckRight(GridMatrix,i,j,Button,Check,ChecklistI,ChecklistJ,k);
		GridMatrix[0][0] = Button;
	}					
	public static void Logic(int[][] GridMatrix,int Button)
	{
		//first off we have to import the grid matrix
		int SOG = GridMatrix.length;
		Algorithm(GridMatrix,Button);
	}
}

	
		 
				
		
