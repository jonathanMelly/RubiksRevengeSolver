package com.rdarida.rrs.solver.phases;

import com.rdarida.rrs.cube.Corner;
import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Dedge;
import com.rdarida.rrs.cube.Edge;

public class Phase4b extends Phase4a {
	protected float calculateDistance(Cube cube) {
		float result = super.calculateDistance(cube);
		result += Edge.UR1.indexOf(cube) == Edge.UR0.indexOf(cube) ? 0 : 2;
		result += Edge.UF0.indexOf(cube) == Edge.UF1.indexOf(cube) ? 0 : 1;
		
		if (result == 0) {
			result += Corner.getParity(cube) == Dedge.getParity(cube) ? 0 : 1;
		}
		
		return result;
	}
}