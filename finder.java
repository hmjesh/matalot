package Shana_B;

public class finder {
	public static void find(String[][] lost, String[][] have, int amount )
	{
		String checking ="";
		for(int i=1; i<lost.length; i++)//preparing a calculate table just like in the exel
		{
			double[][] calcul =new double[have.length][Integer.parseInt(lost[i][5])*3+1];//create calculation table
			for (int j=0; j<calcul.length; j++) // set the last column to 1, so it can be the result.
				calcul[j][calcul[0].length-1]=1;
			for (int j=0;j<calcul[0].length-1;j+=3)//insert the knowlage
			{
				if(lost[i][9+(j/3*4)]!=null)
				{
					calcul[0][j]= Integer.parseInt(lost[i][9+(j/3*4)]); //the lost signal
					checking=lost[i][7+(j/3*4)];//the lost mac, for comparing
					for (int u=1; u<calcul.length; u++) //the have signal			i=the row in lost table, j=the signal to calculate, column. u=row in the calculate table.
					{
						calcul[u][j]=-120;		//if there is no information about that mac
						calcul[u][j+1]=100;
						for(int h = 7; h<(Integer.parseInt(have[u][5])*4+6);h+=4) //if there is an information about that  mac
						{
							if(checking.equals(have[u][h]))
							{
								calcul[u][j]=Integer.parseInt(have[u][h+2]);
								calcul[u][j+1]=Math.abs(calcul[u][j]-calcul[0][j]);
								if(calcul[u][j+1]<3)
									calcul[u][j+1]=3;
							}
						}
						calcul[u][j+2]=10000/(Math.pow(calcul[u][j+1],0.4)*Math.pow(calcul[0][j],2));
						calcul[u][calcul[0].length-1]*=calcul[u][j+2];
					}
				}

			}	
			// here we have almost all the first row, we just have to calculate the last columns.
			double best[][]= new double[amount][4]; //calculate according to the 
			for (int j=0; j<best.length; j++) // zero the array
				best[j][0]=0;
			for(int j=1;j<calcul.length; j++)//check how is the biggest
			{
				int big=best.length-1;
				while(big!=-1&&calcul[j][calcul[0].length-1]>best[big][0] ) //enter to the biggest table
				{
					if(big==0||calcul[j][calcul[0].length-1]<=best[big-1][0])
					{
						if(best.length!=big+1)
						{
							best[big+1][0]=best[big][0];
							best[big+1][1]=best[big][1];
							best[big+1][2]=best[big][2];
							best[big+1][3]=best[big][3];
						}
						best[big][0]=calcul[j][calcul[0].length-1];
						best[big][1]=Double.parseDouble(have[j][2]);
						best[big][2]=Double.parseDouble(have[j][3]);
						best[big][3]=Double.parseDouble(have[j][4]);
					}
					big--;
				}
			}
			double sum[]={0,0,0,0};
			for(int j=0;j<amount;j++)//calculate the sum
			{
				sum[0]+=best[j][0];
				sum[1]+=best[j][1]*best[j][0];
				sum[2]+=best[j][2]*best[j][0];
				sum[3]+=best[j][3]*best[j][0];
			}
			//enter the missed location
			lost[i][2]=sum[1]/sum[0]+"";
			lost[i][3]=sum[2]/sum[0]+"";
			lost[i][4]=sum[3]/sum[0]+"";
		}
		print(lost);
	}
	private static void print(String[][] csv)
	{
		for (int k = 0; k < csv.length; k++) { for (int l = 0; l < csv[k].length; l++)
			System.out.print(csv[k][l] + " " + l + ","); System.out.println(); }
		System.out.println();
	}
}

