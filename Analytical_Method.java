//EXPERIMENT 1
package matLab_Simulation_Experiments;
import java.util.ArrayList;
public class Analytical_Method {
	public static void main(String[] args) {
		System.out.println("EXPERIMENT 1");
		System.out.println("ANALYTICAL METHOD\n");
		//Reading the elements:
		System.out.println("matrix for given problem statement:-");
		float[][] FC= {{0.004f,5.3f,500},{0.006f,5.5f,400},{0.009f,5.8f,200}}; //create matrix
		
        //logic to print the matrix
		for(int i=0;i<FC[0].length;i++) {
			for(int j=0;j<FC.length;j++) {
			    System.out.print("  "+FC[i][j]);
			}		
			System.out.println();
		}
		//calculation of lambda formula is  (PD+summation(bi/2ai)/summation(1/2ai)
		int powerDemand=800;
	    //calculating numerator
		float numerator;                    
		float sum=0;                                     //sum=summation(bi/2ai)
		for(int i=0;i<FC.length;i++) {
			sum+=(FC[i][1])/(2*FC[i][0]);
		}
		numerator=powerDemand+sum;                      //numerator=PD+summation(bi/2ai)
		//calculating denominator
		float denominator=0;                            //denominator=summation(1/2ai)
		for(int i=0;i<FC.length;i++) {
			denominator+=(1/(2*FC[i][0]));
		}
		//calculation of lambda
		float lambda=numerator/denominator;             //lambda=(PD+summation(bi/2ai)/summation(1/2ai)
		System.out.println();
		System.out.println("incremental cost: "+lambda+" $/hour");
		//calculation of powers p1,p2,p3
		System.out.println();
		ArrayList<Float> powers=new ArrayList<>();      //power=(lambda-bi)/(2ai)
		for(int i=0;i<FC.length;i++) {
			powers.add((lambda-FC[i][1])/(2*FC[i][0]));
			System.out.print("p"+(i+1));
			System.out.println(": "+powers.get(i)+" Mega watts");
		}
		//calculation of fuel costs
		System.out.println();                          
		ArrayList<Float> fuelCost=new ArrayList<>();    //fuelCost=aipi(sq)+bipi+ci
		for(int i=0;i<FC.length;i++) {
			fuelCost.add(FC[i][0]*powers.get(i)*powers.get(i)+FC[i][1]*powers.get(i)+FC[i][2]);
			System.out.print("F"+(i+1));
			System.out.println(": "+fuelCost.get(i)+" $/hour");
		}
		//total fuel cost
		System.out.println();                           
		float totalFuelCost=0;                          //totalFuelCost=F1+F2+F3
		for(int i=0;i<FC.length;i++) {
			totalFuelCost+=fuelCost.get(i);
		}
		System.out.println("FT: "+totalFuelCost+" $/hour");
	}
}