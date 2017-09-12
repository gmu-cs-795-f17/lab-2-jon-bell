package edu.gmu.cs795.lab2.part2;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import edu.columbia.cs.psl.phosphor.runtime.MultiTainter;
import edu.columbia.cs.psl.phosphor.runtime.Taint;

public class DynamicTaintingIT {

	@Test
	public void testDynamicTainting() throws Exception {
		int i = getTaintedInt();
		int[] ar = getTaintedIntArray();
		assertNotNull(MultiTainter.getTaint(i));
		assertNotNull(MultiTainter.getTaint(ar[0]));
	}
	
	public static void checkTaint(Taint in)
	{
		if(in == null)
			System.err.println("Uh oh, tag is null!");
	}
	private static void assertEquals(int actual, int expected)
	{
		checkTaint(MultiTainter.getTaint(expected));
		//Whatever assert does usually
	}
	public int getTaintedInt()
	{
		return 100;
	}
	
	public int[] getTaintedIntArray()
	{
//		return MultiTainter.taintedIntArray(new int[10], "lbl");
		return new int[]{0,1,2,3,4,5,6,7,8,9}; 
	}
}
