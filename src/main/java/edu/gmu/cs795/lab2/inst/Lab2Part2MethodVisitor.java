package edu.gmu.cs795.lab2.inst;

import edu.columbia.cs.psl.phosphor.instrumenter.TaintAdapter;
import edu.columbia.cs.psl.phosphor.instrumenter.analyzer.NeverNullArgAnalyzerAdapter;
import edu.columbia.cs.psl.phosphor.org.objectweb.asm.MethodVisitor;
import edu.columbia.cs.psl.phosphor.org.objectweb.asm.Opcodes;
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
		if(methodName.startsWith("getTaintedIntArray"))
		{
			if(opcode == ARETURN)
			{
				super.visitLdcInsn("Some label");
				super.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/columbia/cs/psl/phosphor/runtime/MultiTainter", "taintedIntArray", "([ILjava/lang/Object;)[I", false);
			}
		}
		else if(methodName.startsWith("getTaintedInt"))
		{
			if(opcode == IRETURN)
			{
				super.visitLdcInsn("Some label");
				super.visitMethodInsn(Opcodes.INVOKESTATIC, "edu/columbia/cs/psl/phosphor/runtime/MultiTainter", "taintedInt", "(ILjava/lang/Object;)I", false);
			}
		}
		super.visitInsn(opcode);
	}
}
