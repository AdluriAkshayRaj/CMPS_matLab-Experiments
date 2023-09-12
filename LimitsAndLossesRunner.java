package matLab_Simulation_Experiments;
import java.util.ArrayList;
public class LimitsAndLossesRunner {
	public static void main(String[] args) {
		//creation of object to call the method
		LimitsAndLosses calculate=new LimitsAndLosses();
		System.out.println("EXPERIMENT 5");
		System.out.println("ITERATIVE GRADIENT METHOD WITH LIMITS\n");
		System.out.println("matrix for given problem statement:-");
		float[][] FC= {{0.008f,7.0f,200},{0.009f,6.3f,180},{0.007f,6.8f,140}}; 
		int[][] limits= {{10,85},{10,80},{10,70}}; 
		float[] B= {0.000218f,0.000228f,0.000179f};
		int N_gen=FC.length;  
        //creating a array list with null values of n no. of generators
		ArrayList<Float> fuelCost=new ArrayList<>();                           
		for(int i=0;i<N_gen;i++) {    
			fuelCost.add(null);
		}
		ArrayList<Float> power= new ArrayList<>();                              
		for(int i=0;i<N_gen;i++) {
			power.add(null);
		}
		//getting b max as lambda
		float bmax=0;                                                          
		for(int i=0;i<FC[0].length;i++) {
				if(FC[i][1]>bmax) {
					bmax=FC[i][1];
				}
		}
		//variables to be calculated in this class after calling the method
        float lambda=0;
		float deltaLambda=0; 
	    int powerDemand=150;                                     
		float totalpower=0;
		float power_mismatch=powerDemand-totalpower;
		float abs=power_mismatch;
		lambda=bmax+deltaLambda;
		//calling the method 
		calculate.generationFuelcost(N_gen,lambda,FC,powerDemand,power,B,limits,fuelCost,abs);
        //printing powers of generators 
		System.out.println("power to be generated by individual generators is:-");
    	for(int i=0;i<N_gen;i++) {
			System.out.print("p"+(i+1));
			System.out.println(": "+power.get(i));
		}
		System.out.println();
		//printing of fuel cost of generators
		System.out.println("economic fuel cost of each generators is:- ");
        for(int i=0;i<N_gen;i++) {
           	System.out.print("F"+(i+1));
           	System.out.println(": "+fuelCost.get(i));
        }
        //printing total fuel cost
        float totalFuel=0;
        for(Float i:fuelCost) {
           	totalFuel+=i;
        }
        System.out.println();
        System.out.println("total fuel cost:  "+totalFuel);
        //using method to print final lambda value
        calculate.getMethodLambda();   
	}
}