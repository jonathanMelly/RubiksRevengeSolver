package com.rdarida.rrs.solver.phases;

import java.util.ArrayList;
import java.util.LinkedList;

import com.rdarida.rrs.cube.Center;
import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Twist;
import com.rdarida.rrs.databases.Database;
import com.rdarida.rrs.databases.RLCenterDatabase;

public class Phase1 implements IPhase {
	protected static final Center[] RL = new Center[] {
		Center.R0, Center.R1, Center.R2, Center.R3,
		Center.L0, Center.L1, Center.L2, Center.L3
	};
	
	protected Twist[] generators;
	protected Database[] databases;
	
	public Phase1() {
		generators = Twist.values();
		
		databases = new Database[] {
			RLCenterDatabase.getInstance()
		};
	}
	
	public ArrayList<Twist> search(Cube startCube, int maxDepth) {
		LinkedList<Cube> cubes = new LinkedList<Cube>();
		
		for (int depth = 0; depth <= maxDepth; depth++) {
			cubes.addLast(startCube);
			
			while (!cubes.isEmpty()) {
				Cube cube = cubes.removeLast();
				
				if (cube.numTwists() == depth) {
					if (isGoal(cube)) return cube.getTwists();
					else continue;
				}
				
				Twist lastTwist = cube.lastTwist();
				
				for (Twist twist : generators) {
					if (twist.shouldAvoid(lastTwist)) continue;
					
					Cube clone = cube.clone();
					clone.twist(twist);
					float distance = getDistance(clone);
					
					if (depth < distance + clone.numTwists()) continue;
					
					cubes.addLast(clone);
				}
			}
		}
		
		return null;
	}
	
	protected boolean isGoal(Cube cube) {
		byte[] colors = cube.getColors();
		
		for (Center center : RL) {
			byte color = colors[center.x];
			
			if (color != Cube.O && color != Cube.R) return false;
		}
		
		return true;
	}
	
	protected float getDistance(Cube cube) {
		float max = 0;
		
		for (int i = 0; i < databases.length; i++) {
			float value = databases[i].getDistance(cube);
			
			if (max < value) max = value;
		}
		
		return max;
	}
}