package edu.gmu.cs795.lab2.part1;

import static org.junit.Assert.*;


import org.junit.Test;

import edu.columbia.cs.psl.phosphor.runtime.MultiTainter;

public class SimpleUsageIT {
	
	@Test
	public void testTaintedPrimitive() throws Exception {
		int i = MultiTainter.taintedInt(5, "Mwahaha ");
		int j = i + 5;
		int k = 10;
		if(j > 0)
			k = 14;
		System.out.println("The taint on variable j is: " + MultiTainter.getTaint(j));
		System.out.println("The taint on variable k is: " + MultiTainter.getTaint(k));
	}
}
