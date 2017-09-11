package edu.gmu.cs795.lab2;

import java.lang.instrument.Instrumentation;

import edu.columbia.cs.psl.phosphor.Configuration;
import edu.gmu.cs795.lab2.inst.Lab2Part2MethodVisitor;




public class PreMain {
	public static void premain(String args, Instrumentation inst) {
		Configuration.IMPLICIT_LIGHT_TRACKING = true;
		Configuration.init();
		Configuration.extensionMethodVisitor = Lab2Part2MethodVisitor.class;
		edu.columbia.cs.psl.phosphor.PreMain.DEBUG = true;
		edu.columbia.cs.psl.phosphor.PreMain.premain(args, inst);
	}
}
