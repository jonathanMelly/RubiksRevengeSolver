package com.rdarida.rrs.solver.phases;

import java.util.ArrayList;

import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Twist;

public interface IPhase {
	public ArrayList<Twist> search(Cube cube, int maxDepth);
}