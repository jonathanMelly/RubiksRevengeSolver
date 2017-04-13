package com.rdarida.rrs.solver.phases;

import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Edge;
import com.rdarida.rrs.databases.Database;
import com.rdarida.rrs.databases.FrontEdgeDatabase;

public class Phase3b extends Phase3a {
	public Phase3b() {
		super();
		
		databases = new Database[] {
			FrontEdgeDatabase.getInstance()
		};
	}
	
	@Override
	protected boolean isGoal(Cube cube) {
		if (Edge.FL0.indexOf(cube) != Edge.FL1.indexOf(cube)) return false;
		else if (Edge.FR0.indexOf(cube) != Edge.FR1.indexOf(cube)) return false;
		else return super.isGoal(cube);
	}
}