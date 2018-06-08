
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;
import java.io.PrintWriter;

class WormR {
public static void main (String args[]) {
	int omega = 100000;
	int scanRate = 2;
	int simulN = 3;
	int simulTime = 800;
	int [][] infNode = new int[simulN][simulTime ];


	for(int x=0;x<simulN;x++){
	System.out.println("Start Executing  Simulation"+x);
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
			IP = ThreadLocalRandom.current().nextInt(0,100000);
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
	   writer = new PrintWriter("outputR.txt", "UTF-8");
	}catch(IOException e) {
	    e.printStackTrace();
	}
	for (int i=0; i<simulTime;i++){
		String out ="";
		for(int j=0; j<simulN;j++){
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