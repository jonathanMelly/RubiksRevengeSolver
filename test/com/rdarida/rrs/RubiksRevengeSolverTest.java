package com.rdarida.rrs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.rdarida.rrs.cube.CenterTest;
import com.rdarida.rrs.cube.CornerTest;
import com.rdarida.rrs.cube.CubeTest;
import com.rdarida.rrs.cube.EdgeTest;
import com.rdarida.rrs.cube.TwistTest;
import com.rdarida.rrs.solver.SolverTest;
import com.rdarida.rrs.solver.ValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({
	TwistTest.class,
	CornerTest.class,
	EdgeTest.class,
	CenterTest.class,
	CubeTest.class,
	ValidatorTest.class,
	SolverTest.class
})
public class RubiksRevengeSolverTest {

}