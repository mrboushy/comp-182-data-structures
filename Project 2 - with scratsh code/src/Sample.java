
/**
 * This takes in the sample data and the name and it calculates stats that can be used to understand the data 
 * @author Bishoy A Abdelmalik
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Sample {
	private String name;
	private ArrayList<Double> data;
	private double min;
	private double max;
	private double average;
	private double median;
	private double standardDeviation;
	private boolean changed;

	/**
	 * constructor takes String name and ArrayList<Double> data initialize the
	 * variables and call computeStats() to calculate stats
	 */
	public Sample(String name, ArrayList<Double> data) {
		this.name = name;
		this.data = data;
		changed = true;// if this is true when we output the class will compute stats again
		this.computeStats();
	}

	/**
	 * constructor takes String name only and create an empty ArrayList<Double>
	 */
	public Sample(String name) {
		this.name = name;
		this.data = new ArrayList<Double>();
		changed = true;
	}

	/**
	 * add data to the arraylist
	 */
	public void add(double value) {
		this.data.add(value);
		changed = true;
	}

	/**
	 * The method get(i) should return the value in data at index i
	 */
	public double get(int week) {
		return data.get(week);
	}

	public double getRandom() {
		Random aRandomNumber = new Random();
		return this.get(aRandomNumber.nextInt(data.size()));
	}

	/**
	 * The method computeStats() should calculate the following statistics for the
	 * values in data: min, max, average, median (midpoint), standard deviation (a
	 * measure of variability or risk)
	 */
	private void computeStats() {
		ArrayList<Double> list = new ArrayList<Double>(data);// copy the array using a copy constructor so we can sort it
		Collections.sort(list);// sort the array
		/*
		 * calculate average
		 */
		average = 0;
		for (double d : data) {
			average += d;
		}
		average /= list.size();
		/* * * * * * * */
		/*some testing code
		 * for (double d : list) {
			System.out.println(d);
		}*/
		/* calculate min */
		min = list.get(0);
		/* calculate max */
		max = list.get(list.size() - 1);
		/* calculate median */
		median = (list.size() % 2 == 0) ? ((list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2)// if
																											// list.size()
																											// even
				: list.get(list.size() / 2);// if list.size() not even
		/* * * * * * * */

		/* calculate standardDeviation */
		standardDeviation = 0;
		for (double d : list) {
			standardDeviation += Math.pow(d - average, 2);
		}

		standardDeviation /= list.size();
		standardDeviation = Math.sqrt(standardDeviation);
		/* * * * * * * */
		changed = false;// make it false so it knows that stats has been calculated
	}

	/**
	 * It will generate the report of the descriptive statistics using the method
	 */
	public String toString() {
		return String.format("%s: size = %d, min = %1.4f, average = %1.4f, median = %1.4f, sd = %1.4f, max = %1.4f",
				getName(), getSize(), getMin(), getAverage(), getMedian(), getStandardDeviation(), getMax());
	}

	/* get name */
	public String getName() {
		return this.name;
	}

	/* get size */
	public int getSize() {
		return this.data.size();
	}

	/*
	 * get each stat separately
	 */
	public double getStandardDeviation() {
		if (changed) {
			this.computeStats();
		}
		return this.standardDeviation;
	}

	public double getMin() {
		if (changed) {
			this.computeStats();
		}
		return this.min;
	}

	public double getMax() {
		if (changed) {
			this.computeStats();
		}
		return this.max;
	}

	public double getAverage() {
		if (changed) {
			this.computeStats();
		}
		return this.average;
	}

	public double getMedian() {
		if (changed) {
			this.computeStats();
		}
		return this.median;
	}

}
