package com.rdarida.rrs.solver.phases;

import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Edge;
import com.rdarida.rrs.databases.BackEdgeDatabase;
import com.rdarida.rrs.databases.Database;

public class Phase3c extends Phase3b {
	public Phase3c() {
		super();
		
		databases = new Database[] {
			BackEdgeDatabase.getInstance()
		};
	}
	
	@Override
	protected boolean isGoal(Cube cube) {
		if (Edge.BR0.indexOf(cube) != Edge.BR1.indexOf(cube)) return false;
		else if (Edge.BL0.indexOf(cube) != Edge.BL1.indexOf(cube)) return false;
		else return super.isGoal(cube);
	}
}