package edu.gmu.cs795.lab2.runtime;

import edu.columbia.cs.psl.phosphor.runtime.Taint;
import edu.columbia.cs.psl.phosphor.struct.TaintedIntWithObjTag;

public class TaintRuntime {

	public static TaintedIntWithObjTag ldc$$PHOSPHORTAGGED(Taint tag, int in, TaintedIntWithObjTag ret)
	{
		ret.taint = new Taint<String>("Some label");
		ret.val = in;
		return ret;
	}
}
