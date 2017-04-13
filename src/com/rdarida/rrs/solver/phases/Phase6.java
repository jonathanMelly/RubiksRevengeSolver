package com.rdarida.rrs.solver.phases;

import com.rdarida.rrs.cube.Corner;
import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Dedge;
import com.rdarida.rrs.cube.Twist;

public class Phase6 extends Phase5 {
	public Phase6() {
		super();
		
		generators = new Twist[] {
			Twist.U,            Twist.F,  Twist.D,            Twist.B,
			Twist.U2, Twist.R2, Twist.F2, Twist.D2, Twist.L2, Twist.B2,
			Twist.U_,           Twist.F_, Twist.D_,           Twist.B_
		};
	}
	
	@Override
	protected long index(Cube cube) {
		long result = 0;
		int index = 0;
		
		int[] cornerOrientation = Corner.getOrientation(cube);
		
		for (int corner : cornerOrientation) {
			result += (corner << index);
			index += 2;
		}
		
		int[] dedgeConfiguration = Dedge.getConfiguration(cube);
		
		for (int i = 0; i < Dedge.NUM_DEDGES; i++) {
			int dedge = dedgeConfiguration[i];
			result += ((dedge / 8) << index);
			index++;
		}
		
		return result;
	}
}