package com.rdarida.rrs;

import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.databases.BackEdgeDatabase;
import com.rdarida.rrs.databases.Database;
import com.rdarida.rrs.databases.FrontEdgeDatabase;
import com.rdarida.rrs.databases.RLCenterDatabase;
import com.rdarida.rrs.databases.UDFBCenterDatabase;
import com.rdarida.rrs.solver.SolutionListener;
import com.rdarida.rrs.solver.Solver;
import com.rdarida.rrs.solver.Validator;
import com.rdarida.rrs.view.Gui;

public class RubiksRevengeSolver {
	private Database[] databases;
	private Cube cube;
	private Validator validator;
	private Solver solver;
	
	public RubiksRevengeSolver() {
		databases = new Database[] {
			RLCenterDatabase.getInstance(),
			UDFBCenterDatabase.getInstance(),
			BackEdgeDatabase.getInstance(),
			FrontEdgeDatabase.getInstance()
		};
		
		cube = new Cube();
		validator = new Validator();
		solver = new Solver();
	}
	
	public void stopSolving() {
		Solver.Stop = true;
	}
	
	public void solveCube(SolutionListener solutionListener) {
		Solver.Stop = false;
		solver.solve(cube.clone(), solutionListener);
		new Thread(solver).start();
	}
	
	public byte[] getColoring() {
		return cube.getColors();
	}
	
	public void resetCube(byte[] colors) throws RRSException {
		Cube newCube = new Cube(colors);
		validator.validate(newCube);
		cube = newCube;
	}
	
	public void scrambleCube() throws RRSException {
		cube.scramble();
		validator.validate(cube);
	}
	
	public int getPercentage() {
		double percent = 1;
		
		for (Database database : databases) {
			percent *= database.percentage();
		}
		
		return (int) (percent * 100.0);
	}
	
	public int getSolverProgress() {
		return solver.getProgress();
	}
	
	public void startGui() {
		Gui gui = new Gui(this);
		gui.start();
	}
	
	public void loadDatabases() {
		for (Database database : databases) {
			if (!database.load()) {
				database.generate();
			}
		}
	}
	
	public void runTests(int numTests) {
		int minTwists = Integer.MAX_VALUE;
		int maxTwists = 0;
		int sumTwists = 0;
		double sumRuntime = 0f;
		
		for (int t = 0; t < numTests; t++) {
			int numOfTwists = 40;
			System.out.println("Test: " + (t + 1) + ", twists: " + numOfTwists);
			
			Cube cube = new Cube();
			cube.scramble(numOfTwists);
			cube.clear();
			
			double runtime = System.currentTimeMillis();
			solver.solve(cube);
			
			runtime = System.currentTimeMillis() - runtime;
			runtime /= 1000f;			
			sumRuntime += runtime;
			
			int size = solver.getSolution().size();
			
			if (size < minTwists) minTwists = size;
			else if (maxTwists < size) maxTwists = size;
			
			sumTwists += size;
			
			System.out.println("Runtime: " + runtime + " seconds.");
			System.out.println("Number of twists: " + size);
			System.out.println();
		}
		
		System.out.println("AVG(twists): " + (sumTwists / numTests));
		System.out.println("MIN(twists): " + minTwists);
		System.out.println("MAX(twists): " + maxTwists);
		
		System.out.println("AVG(runtime): " + (sumRuntime / numTests) + "s");
	}
	
	public static void main(String[] args) {
		RubiksRevengeSolver rrs = new RubiksRevengeSolver();
		rrs.loadDatabases();
		
		if (0 < args.length) {
			rrs.runTests(Integer.parseInt(args[0]));
		}
		else rrs.startGui();
	}
}