package com.rdarida.rrs.solver;

import java.util.ArrayList;
import java.util.Iterator;

import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Twist;
import com.rdarida.rrs.solver.phases.IPhase;
import com.rdarida.rrs.solver.phases.Phase1;
import com.rdarida.rrs.solver.phases.Phase2a;
import com.rdarida.rrs.solver.phases.Phase2b;
import com.rdarida.rrs.solver.phases.Phase3a;
import com.rdarida.rrs.solver.phases.Phase3b;
import com.rdarida.rrs.solver.phases.Phase3c;
import com.rdarida.rrs.solver.phases.Phase4a;
import com.rdarida.rrs.solver.phases.Phase4b;
import com.rdarida.rrs.solver.phases.Phase4c;
import com.rdarida.rrs.solver.phases.Phase4d;
import com.rdarida.rrs.solver.phases.Phase5;
import com.rdarida.rrs.solver.phases.Phase6;
import com.rdarida.rrs.solver.phases.Phase7;
import com.rdarida.rrs.solver.phases.Phase8;

public class Solver implements Runnable {
	private static final int MAX_DEPTH = 100;
	public static boolean Stop = false;
	private ArrayList<Twist> solution;
	private IPhase[] phases;
	private Cube cube;
	private SolutionListener solutionListener;
	private double progress;
	
	public ArrayList<Twist> getSolution() {
		return solution;
	}
	
	public Solver() {
		solution = new ArrayList<Twist>();
		
		phases = new IPhase[] {
			new Phase1(),
			new Phase2a(),
			new Phase2b(),
			new Phase3a(), new Phase3b(), new Phase3c(),
			new Phase4a(), new Phase4b(), new Phase4c(), new Phase4d(),
			new Phase5(),
			new Phase6(),
			new Phase7(),
			new Phase8()
		};
	}
	
	public void solve(Cube cube) {
		solve(cube, null);
		run();
	}
	
	public void solve(Cube cube, SolutionListener solutionListener) {
		this.cube = cube;
		this.solutionListener = solutionListener;
	}
	
	@Override
	public void run() {
		Stop = false;
		solution.clear();
		progress = 0;
		
		for (int i = 0; i < phases.length; i++) {
			IPhase phase = phases[i];
			
			for (int depth = 0; depth <= MAX_DEPTH && !Stop; depth++) {
				ArrayList<Twist> path = phase.search(cube, depth);
				
				if (path == null) continue;
				
				Iterator<Twist> iterator = path.iterator();
				
				while (iterator.hasNext()) {
					Twist twist = iterator.next();
					cube.turn(twist);
					solution.add(twist);
				}
				
				System.out.print(i + "(" + path.size() + ") ");
				break;
			}
			
			progress++;
		}
		
		System.out.println();
		
		solution = postProcess(solution);
		
		if (solutionListener != null) {
			solutionListener.setSolution(solution);
		}
	}
	
	private ArrayList<Twist> postProcess(ArrayList<Twist> solution) {
		ArrayList<Twist> result = new ArrayList<Twist>();
		
		while (!solution.isEmpty()) {
			Twist next = solution.remove(0);
			
			if (result.isEmpty()) {
				result.add(next);
				continue;
			}
			
			Twist last = result.get(result.size() - 1);
			int lMod = last.ordinal() % Twist.NUM_UNIQUE_TWIST;
			int nMod = next.ordinal() % Twist.NUM_UNIQUE_TWIST;
			
			if (lMod == nMod) {
				result.remove(result.size() - 1);
				Twist swap = Twist.swap(last, next);
				
				if (swap != null) result.add(swap);
			}
			else result.add(next);
		}
		
		return result;
	}
	
	public int getProgress() {
		return (int) (progress / phases.length * 100.0);
	}
}