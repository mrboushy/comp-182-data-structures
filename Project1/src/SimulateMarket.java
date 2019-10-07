/**
 * This class read the file and create the model to calculate the historical sp500 and 
 * 	then runs 2 simulations using methods:
 *  	normalSP500();
 * 	    sampledSP500();
 * @author Bishoy A Abdelmalik
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SimulateMarket {
	private final String FILE_NAME="SP500-1950-2018-weekly.txt";
	private final int WEEKS=3601;
	private final double INVEST=100;
	private final int TRIALS=10000;
	private final double AVERAGE=0.0016;
	private final double STDEV =0.0206;
	private Random aRandomNumber;
	private Sample model;
	
    public static void main(String[] arg) {
    	new SimulateMarket();// create anonymous simulate market object 
    }

    /**
     * constructor create a Sample instance named “model”, it report the statistics of model, and 
     * call methods:
     * 		historicalSP500()
     *     	normalSP500();
     * 	    sampledSP500();
     */
    public SimulateMarket() {
    	
    	model = new Sample("SP500", makeDistribution(FILE_NAME));// call makeDistribution method to read the file and return ArrayList to path to the object   
    	System.out.println("Historical SP500 statistics:");
    	System.out.print(model.toString());
    	System.out.println();
    	System.out.print("Historical SP500 $100 becomes $"+String.format("%.2f", historicalSP500()));
    	System.out.println();
    	
    	System.out.println();
    	normalSP500();
    	
    	System.out.println();
    	sampledSP500();
    	
    }

    /**
     * Method historicalSP500() calculate the return of investing $100 over the WEEKS number of weeks using the data stored in the Sample model. 
     * 
     * For each week (where week = 0; week < WEEKS; week++) the value of money for the previous week is increased by the weekly percent return. 
     */
    private double historicalSP500() {
    	
    	double money=INVEST;
    	for(int week=0; week<WEEKS; week++) {
    		money=money+model.get(week)*money;//model.get(week);  to get the percent weekly return
    	}
    	return money;
    }
    
    /**
     * Method normalSP500() run the simulation using Gaussian  method of simulation 
     * 
     * calculate the percent of return using  AVERAGE + aRandomNumber.nextGaussian() * STDEV
     * 
     */
    public void normalSP500() {
    	Sample equity = new Sample("Normal SP500"); 
    	aRandomNumber=new Random();

    	double money = INVEST;
    	for (int i=0; i<TRIALS;i++) {
    		money=INVEST;
    		for(int week=0; week<WEEKS; week++) {
				money=money+(AVERAGE + aRandomNumber.nextGaussian() * STDEV)*money ;

        	}
    		equity.add(money);
    	}
    	System.out.println("Normal SP500 statistics:");
    	System.out.print(equity.toString());
    	System.out.println();
    	System.out.print("Normal SP500 $100 becomes $"+String.format("%.2f", equity.getAverage())+" (average), and $"
    			+String.format("%.2f", equity.getMedian())+" (median)");

    	System.out.println();
    }
    /**
     * Method sampledSP500() run the simulation using sampled percentages from the Sample model that we created before  
     */
    public void sampledSP500() {
    	Sample equity = new Sample("Sampled SP500"); 

    	double money = INVEST;
    	for (int i=0; i<TRIALS;i++) {
    		money=INVEST;
    		for(int week=0; week<WEEKS; week++) {
				money=money+model.getRandom()*money;
        	}
    		equity.add(money);
    	}
    	System.out.println("Sampled  SP500 statistics:");
    	System.out.print(equity.toString());
    	System.out.println();
    	System.out.print("Sampled SP500 $100 becomes $"+String.format("%.2f", equity.getAverage())+" (average), and $"
    			+String.format("%.2f", equity.getMedian())+" (median)");
    	System.out.println();

    }
   

    /**
     * The method makeDistribution reads the data from the file (SP500-1950-2018-weekly.txt) and stores it in a local ArrayList<Double>
     */
    private ArrayList<Double> makeDistribution(String fileName) {
    	ArrayList<Double> data=new ArrayList<Double>();
    	String s;
    	Scanner sc;
    	try {
			sc=new Scanner(new File(FILE_NAME));// load the file in the scanner 
			sc.nextLine();// skip first line 
			while(sc.hasNext()) {//while it has values 
				/*
				 * read and extract the value without the date 
				 */
				s=sc.nextLine();
				s=s.substring(s.indexOf('	')+1, s.length());
				data.add(Double.parseDouble(s));				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();	
		}
        return data;
    }

}