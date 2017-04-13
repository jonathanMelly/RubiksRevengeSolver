package com.rdarida.rrs.solver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rdarida.rrs.RRSException;
import com.rdarida.rrs.cube.Cube;
import com.rdarida.rrs.cube.Twist;

public class ValidatorTest {
	private static Validator validator;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		validator = new Validator();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		validator = null;
	}

	@Test
	public void testValidateColoring() {
		try {
			Cube cube = SolverTest.fromString(
				  "rowy yrgr oyyw goyy"
				+ "gggo oyro gbgy bybb"
				+ "ywgb bywy rbbb rwgw"
				+ "rrbr broy wowo grro"
				+ "gbyw rorg ygwr yowo"
				+ "wowo rgww bWbg wgbb" //o -> W
			);
			
			validator.validate(cube);
			fail("Should throw a coloring validation exeption");
		}
		catch (RRSException exception) {
			if (exception.getMessage() == RRSException.INVALID_COLORING) {
				assertTrue("Validate coloring", true);
			}
			else fail(exception.getMessage());
		}
	}
	
	@Test
	public void testValidateCenters() {
		try {
			Cube cube = SolverTest.fromString(
				  "Rgoy yYry bwwo wgwg" //Y <-> R
				+ "ogrr gyrr ogyr boyr"
				+ "bwbr bbbo yrwy bbbw"
				+ "wwor bbbb royr owog"
				+ "ggwg wgwr ggog oywy"
				+ "yyyb gygr boow orow"
			);
			
			validator.validate(cube);
			fail("Should throw a center validation exception");
		}
		catch (RRSException exception) {
			if (exception.getMessage() == RRSException.INVALID_CENTERS) {
				assertTrue("Validate centers", true);
			}
			else fail(exception.getMessage());
		}
	}
	
	@Test
	public void testValidateEdges() {
		try {
			Cube cube = SolverTest.fromString(
				  "wbor ybyw yyow ybbb"
				+ "roob yooo ybbo ywwo"
				+ "rwyw byyY bwor bwoo" //R -> Y
				+ "rggg Bggw brrG wrro"
				+ "ygwg owgg Rbwb yyyg" //Y -> R
				+ "wogg rwrg rgrg orrb"
			);
			
			validator.validate(cube);
			fail("Should throw an edge validation exception");
		}
		catch (RRSException exception) {
			if (exception.getMessage() == RRSException.INVALID_EDGES) {
				assertTrue("Validate edges", true);
			}
			else fail(exception.getMessage());
		}
	}
	
	@Test
	public void testValidateCorners() {
		try {
			Cube cube = SolverTest.fromString(
				  "byro bbww bbrr Rwrr"
				+ "wrrY ygwg obrr owoo" //W -> Y
				+ "Gbgg ogrr goyy gggr"
				+ "ywog woyb ryyb wgwB"
				+ "wyoo obob owog Wbgb" //Y -> W
				+ "yywb yggw owrb yyyR"
			);
			
			validator.validate(cube);
			fail("Should throw a corner validation exception");
		}
		catch (RRSException exception) {
			if (exception.getMessage() == RRSException.INVALID_CORNERS) {
				assertTrue("Validate corners", true);
			}
			else fail(exception.getMessage());
		}
	}
	
	@Test
	public void testValidateEdgeParity() {
		try {
			Cube cube = SolverTest.fromString(
				  "wwww wwww wwww wBww" // w -> B
				+ "rrrr rrrr rrrr rrrr"
				+ "bWbb bbbb bbbb bbbb" // b -> W
				+ "oooo oooo oooo oooo"
				+ "gggg gggg gggg gggg"
				+ "yyyy yyyy yyyy yyyy"
			);
			
			cube.scramble();
			
			validator.validate(cube);
			fail("Should throw an edge parity validation exception");
		}
		catch (RRSException exception) {
			if (exception.getMessage() == RRSException.INVALID_EDGE_PARITY) {
				assertTrue("Validate edge parity", true);
			}
			else fail(exception.getMessage());
		}
	}
	
	@Test
	public void testValidateCornerParity() {
		try {
			Cube cube = SolverTest.fromString(
				  "gyrw rwyw gwry ggrO"//B -> O
				+ "rwyr rbro yoyg rbog"
				+ "wwwB bgbg rgww ywwr"//Y -> B
				+ "Yroo wggg oryo woob"//O -> Y
				+ "gbby rwby goog wyyy"
				+ "obbb gbrb ryoy bobo"
			);
			
			validator.validate(cube);
			fail("Should throw a corner parity validation exception");
		}
		catch (RRSException exception) {
			if (exception.getMessage() == RRSException.INVALID_CORNER_PARITY) {
				assertTrue("Validate corner parity", true);
			}
			else fail(exception.getMessage());
		}
	}
	
	@Test
	public void testInfiniteLoop() {
		try {
			Cube cube = new Cube();
			twist(
				cube,
				Twist.B2,
				Twist.R2,
				Twist.U,
				Twist.F2,
				Twist.U_,
				Twist.D2,
				Twist.D2,
				Twist.B2,
				Twist.U_,
				Twist.D2,
				Twist.L2,
				Twist.B2,
				Twist.U_,
				Twist.D2,
				Twist.U,
				Twist.D2,
				Twist.D2,
				Twist.R2,
				Twist.L2,
				Twist.B2,
				Twist.U_,
				Twist.D,
				Twist.D,
				Twist.B2,
				Twist.L2
			);
			
			validator.validate(cube);
			assertTrue("There is no infinite loop.", true);
		}
		catch (RRSException exception) {
			fail(exception.getMessage());
		}
	}
	
	private void twist(Cube cube, Twist... twists) {
		for (int i = 0; i < twists.length; i++) {
			cube.twist(twists[i]);
		}
	}
	
	/*
	 * Tests are generated by the wca official scrambler.
	 * https://www.worldcubeassociation.org/regulations/history/files/scrambles/scramble_cube.htm?size=4&num=20&len=20&col=yrgwob&subbutton=Scramble%21
	 */
	@Test
	public void testRandomTwists() throws RRSException {
		String[] tests = {
			"L' U' L2 l' B' F' L d R2 b2 f2 L2 R2 F' r' R' D2 B b' U2",
			"b' D' b d' u' r' B2 b' f' u2 l d' F2 u2 L r D2 d u F2",
			"d2 l2 r D' l2 b2 F2 L d r' R2 U l' r' D2 d' b' F' u r'",
			"f d2 l' r' R2 D' U L2 l2 B' b L' b2 d r2 D' B R' D' U",
			"B' f D' U' l' D b f2 d2 L' B' f' L2 r' b2 R D' F' L2 b2",
			"L2 R2 u' F' l2 d u' L2 l B2 L B D' R2 B2 f2 u2 F L' l2",
			"u' R' f' d R2 D' b2 l' D U l2 r D2 u B' d' U2 L2 R2 B'",
			"u2 U' b2 R u' l u L l' d2 f D' R b' D f r2 b2 d f'",
			"d' u' R B2 r R d2 F r' f u U r f' d' U2 l2 D' L l'",
			"B2 F d b2 D F' L' r2 F2 R2 B' F' u2 B b' d2 u F' l b",
			"B2 D' L2 u2 L' b2 D2 U' B2 F L' R' b F' r' b d' u l r2",
			"f' D' l R2 D2 r' B2 F U2 l F2 R2 u L2 l' U' B D2 l D2",
			"R D2 d2 l2 f' L r R2 F2 R2 D' u' L b' L' d' L U2 b' r'",
			"D' u2 U' R U F2 l' f2 F d2 f2 l2 U2 l2 b L' l d2 F l2",
			"B2 F U2 f d B2 D' U' r' f' F' U' f d2 r B D2 L2 B F2",
			"B f R2 b D U f R2 D' u2 r' B L' u L R2 u l2 d B2",
			"L' l d2 U' L U r' B2 U' R f2 r B' L2 l' r' b' R' B u'",
			"U F2 l' F L F r2 U2 L2 D' u r' R U F2 l2 r2 B' F u'",
			"d l2 f u' U2 F' D' B b R' b' F d' u2 U' l b' L2 d2 f2",
			"L2 l' R' U B b2 l' r2 F2 R u' U2 f R' d F r2 f2 F D2"
		};
		
		for (int i = 0, l = tests.length; i < l; i++) {
			Cube cube = new Cube();
			twist(cube, tests[i]);
			validator.validate(cube);
		}
	}
	
	private void twist(Cube cube, String input) {
		String[] twists = input.replaceAll("'", "_").split(" ");
		
		for (int i = 0, l = twists.length; i < l; i++) {
			cube.twist(Twist.valueOf(twists[i]));
		}
	}
}