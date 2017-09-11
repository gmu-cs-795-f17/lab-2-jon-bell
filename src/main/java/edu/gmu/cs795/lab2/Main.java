package edu.gmu.cs795.lab2;

import edu.columbia.cs.psl.phosphor.Configuration;
import edu.columbia.cs.psl.phosphor.Instrumenter;
import edu.gmu.cs795.lab2.inst.Lab2Part2MethodVisitor;

public class Main {

	public static void main(String[] args) {
		String[] _args = new String[args.length+2];
		System.arraycopy(args, 0, _args, 0, args.length);
		_args[args.length] = "-multiTaint";
		_args[args.length+1] = "-lightControlTrack";

		Instrumenter.main(_args);
	}
}
