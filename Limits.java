package matLab_Simulation_Experiments;
import java.util.ArrayList;
public class Limits {
	public static void main(String[] args) {
		System.out.println("EXPERIMENT 3");
		System.out.println("ITERATIVE GRADIENT METHOD WITH LIMITS\n");
		float lambda=0;
		float deltaLambda=0; 
		int powerDemand=850;                                     
		float p=0; 
		float totalpower=0;
		System.out.println("matrix for given problem statement:-");
		float[][] FC= {{0.001562f,7.92f,561},{0.00197f,7.85f,310},{0.00482f,7.97f,78}}; 
		float[][] limits= {{150,600},{100,400},{50,200}}; 
		int N_gen=FC.length;                                                   
		ArrayList<Float> fuelCost=new ArrayList<>();                           
		for(int i=0;i<N_gen;i++) {    
			fuelCost.add(null);
		}
		ArrayList<Float> power= new ArrayList<>();                              
		for(int i=0;i<N_gen;i++) {
			power.add(null);
		}
		float bmax=0;                                                          
		for(int i=0;i<FC[0].length;i++) {
				if(FC[i][1]>bmax) {
					bmax=FC[i][1];
				}
		}
		System.out.println();
		float power_mismatch=powerDemand-totalpower;
		float abs=power_mismatch;
	    //WHILE LOOP 
		lambda=bmax+deltaLambda;
		while(abs>0.001) {                                         
        for(int i=0;i<FC[0].length;i++) {                                     
        		p=(lambda-FC[i][1])/(2*FC[i][0]); 
        		if(p<limits[i][0]) {
        			power.set(i,limits[i][0]);
        		}else if(p>limits[i][1]) {
        			power.set(i,limits[i][1]);
        		}else {
        		power.set(i,p);
        		}
        }
		totalpower=0;                                                    
		for(Float i:power) {
			totalpower=totalpower+i;
		}
		power_mismatch=powerDemand-(totalpower);
		float sum=0;                                                           
		for(int i=0;i<N_gen;i++) {
			sum += (1/(2*FC[i][0]));	
		}	
		deltaLambda= power_mismatch/(sum);                                     
		for(int i=0;i<FC.length;i++) {                                        
			fuelCost.set(i,FC[i][0]*power.get(i)*power.get(i)+FC[i][1]*power.get(i)+FC[i][2]);
		}
		lambda+=deltaLambda;
        abs=Math.abs(power_mismatch);
        //END OF WHILE LOOP
		}                  
        System.out.println("power to be generated by individual generators is:-");
    	for(int i=0;i<N_gen;i++) {
			System.out.print("p"+(i+1));
			System.out.println(": "+power.get(i));
		}
		System.out.println();
		System.out.println("economic fuel cost of each generators is:- ");
        for(int i=0;i<N_gen;i++) {
           	System.out.print("F"+(i+1));
           	System.out.println(": "+fuelCost.get(i));
        }
        float totalFuel=0;
        for(Float i:fuelCost) {
           	totalFuel+=i;
        }
        System.out.println();
        System.out.println("total fuel cost is: "+totalFuel);
        System.out.println("incremental cost: "+lambda);
	}
}