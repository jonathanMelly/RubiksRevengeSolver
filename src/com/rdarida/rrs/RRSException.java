package com.rdarida.rrs;

public class RRSException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final String INVALID_COLORING      = "Invalid coloring!";
	public static final String INVALID_CENTERS       = "Invalid centers!";
	public static final String INVALID_EDGES         = "Invalid edges!";
	public static final String INVALID_CORNERS       = "Invalid corners!";
	public static final String INVALID_EDGE_PARITY   = "Invalid edge parity!";
	public static final String INVALID_CORNER_PARITY = "Invalid corner parity!";
	public static final String HAS_NO_SOLUTION       = "Has no solution!";
	
	public RRSException(String message) {
		super(message);
	}
}