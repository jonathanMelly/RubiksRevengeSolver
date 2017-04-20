package com.rdarida.rrs.solver.phases;

import com.rdarida.rrs.cube.Corner;
import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Dedge;
import com.rdarida.rrs.cube.Twist;

public class Phase7 extends Phase5 {
	public Phase7() {
		super();
		
		generators = new Twist[] {
			Twist.U,                      Twist.D,
			Twist.U2, Twist.R2, Twist.F2, Twist.D2, Twist.L2, Twist.B2,
			Twist.U_,                     Twist.D_
		};
	}
	
	@Override
	protected long index(Cube cube) {
		long result = 0;
		int index = 0;
		
		int[] dedgeConfiguration = Dedge.getConfiguration(cube);
		
		for (int dedge : dedgeConfiguration) {
			result += ((dedge / 4) << index);
			index += 2;
		}
		
		int[] cornerConfiguration = Corner.getConfiguration(cube);
		
		for (int corner : cornerConfiguration) {
			result += ((corner / 2) << index);
			index += 2;
		}
		
		int parity = Dedge.getParity(dedgeConfiguration) == 0 ? 1 : 0;
		result += (parity << index);
		return result;
	}
}