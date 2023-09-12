package matLab_Simulation_Experiments;
import java.util.ArrayList;
public class LimitsAndLosses {
	float methodLambda=0;
	void generationFuelcost(int N_gen,float lambda,float FC[][], int PD, ArrayList<Float> power,float B[],int limits[][], ArrayList<Float> fuelCost,float abs){
		while(abs>0.001) {
		    for(int i=0;i<FC[0].length;i++) { 
	    	    float p=(lambda-FC[i][1])/(2*(FC[i][0]+lambda*B[i])); 
	    	    if(p<limits[i][0]) {
	    	    	power.set(i,(float) limits[i][0]);
	    	    }else if(p>limits[i][1]) {
	    	    	power.set(i,(float) limits[i][1]);
	   		    }else {
	    		    power.set(i,p);
	    	    }
	        }
		    float PL=B[0]*power.get(0)*power.get(0)+B[1]*power.get(1)*power.get(1)+B[2]*power.get(2)*power.get(2);
		    float totalpower=0;                                                    
		    for(float i:power) {
		    	totalpower=totalpower+i;
		    }
		    float deltaPD=PD+PL-totalpower;
		    float denominator=0;
		    for(int i=0;i<FC[0].length;i++) {
		    	denominator+=((FC[i][0]+FC[i][1]*B[i])/(2*(FC[i][0]+lambda*B[i])*(FC[i][0]+lambda*B[i])));
	    	}
		    float deltaLambda=deltaPD/denominator;                                     
		    for(int i=0;i<FC.length;i++) {                                        
			    fuelCost.set(i,FC[i][0]*power.get(i)*power.get(i)+FC[i][1]*power.get(i)+FC[i][2]);
		    }
		    lambda+=deltaLambda;
	        abs=Math.abs(deltaPD);
	        methodLambda=lambda;
		}
	}
	void getMethodLambda() {
		System.out.println("incrimental cost: "+methodLambda);
	}
}
