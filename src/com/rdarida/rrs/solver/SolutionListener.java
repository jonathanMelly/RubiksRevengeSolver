package com.rdarida.rrs.solver;

import java.util.ArrayList;

import com.rdarida.rrs.cube.Twist;

public interface SolutionListener {
	public void setSolution(ArrayList<Twist> solution);
}