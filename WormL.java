
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;
import java.io.PrintWriter;

class WormL {
public static void main (String args[]) {
	int omega = 100000;
	int scanRate = 2;
	int simulRun = 3;
	int simulTime = 1400;
	int [][] infNode = new int[simulRun][simulTime ];


	for(int x=0;x<simulRun;x++){
	System.out.println(" Start Executing  Simulation"+x);
	int infectComputer = 0;

	Boolean [] status = new Boolean [omega];
	int k = omega/1000;
	for(int i=0; i<k;i++){
	   for(int j=0; j<10; j++){
		status[i*1000 + j] = false;
	   }
	}

	int idxSeed = ThreadLocalRandom.current().nextInt(0, k) * 1000
			+ThreadLocalRandom.current().nextInt(0,10);
	status[idxSeed] = true;
	infectComputer++;
	System.out.println("Initial infected Comp set at IP" +  idxSeed); 


	int IP;
	for(int t=0; t<simulTime; t++){
	    for(int j=0; j<infectComputer; j++){
		for(int i=0; i<scanRate;i++){
			double randomNo = ThreadLocalRandom.current().nextDouble(0, 1);
                        int temp =idxSeed;
                        int current_index=temp;
                        
                        if(randomNo<0.6)
                        {
                            IP=ThreadLocalRandom.current().nextInt(current_index-10,current_index+10);
                        }
                        else
                        {
                            IP = ThreadLocalRandom.current().nextInt(0,100000);
                        }
                        
			if(status[IP] !=null && !status[IP]){
			   status[IP] = true;
			infectComputer++;
			}
		}
	    }
	infNode[x][t] = infectComputer;
	}
	}

	PrintWriter writer = null;
	try{
	   writer = new PrintWriter("outputL.txt", "UTF-8");
	}catch(IOException e) {
	    e.printStackTrace();
	}
	for (int i=0; i<simulTime;i++){
		String out ="";
		for(int j=0; j<simulRun;j++){
			if(out.equals(""))
			   out += infNode[j][i];
			else
			   out += " " + infNode[j][i];
		}
		System.out.println(out);
		try{
			writer.println(out);
		}catch (Exception e) {
		  e.printStackTrace();
		}
		}

		try{
			writer.close();
		}catch(Exception e) {
			e.printStackTrace();

		}
	    }
	} 