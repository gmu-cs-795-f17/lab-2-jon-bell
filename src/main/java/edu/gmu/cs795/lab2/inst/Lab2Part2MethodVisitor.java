package edu.gmu.cs795.lab2.inst;

import edu.columbia.cs.psl.phosphor.instrumenter.TaintAdapter;
import edu.columbia.cs.psl.phosphor.instrumenter.analyzer.NeverNullArgAnalyzerAdapter;
import edu.columbia.cs.psl.phosphor.org.objectweb.asm.MethodVisitor;
import edu.columbia.cs.psl.phosphor.org.objectweb.asm.Type;
import edu.gmu.cs795.lab2.runtime.TaintRuntime;

public class Lab2Part2MethodVisitor extends TaintAdapter{

	private String methodName;
	private String className;
	private Type returnType;
	public Lab2Part2MethodVisitor(int access, String className, String name, String desc, String signature, String[] exceptions, MethodVisitor mv, NeverNullArgAnalyzerAdapter analyzer, String classSource, String classDebug) {
		super(access, className, name, desc, signature, exceptions, mv, analyzer, classSource, classDebug);
		this.methodName = name;
		this.className = className;
		this.returnType = Type.getReturnType(desc);
	}


	@Override
	public void visitInsn(int opcode) {
		super.visitInsn(opcode);
	}
}
