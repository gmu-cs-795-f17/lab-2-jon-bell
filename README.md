# CS 795 Program Analysis for Software Testing
## Lab 2: Phosphor

This lab will introduce you to some exercises using Phosphor and ASM for dynamic dataflow analysis.

### Part one: Experimenting with Phosphor API, in source code
While it is possible to write an ASM method visitor to automatically mark variables as taint sources or sinks, we'll get started using the Java API.

This project contains a simple little example that uses Phosphor to track the dataflow of an integer, configured to run as a test case (`edu.gmu.cs795.hw1.test.SimpleUsageIT`). You can run it by typing `mvn verify`.

NOTE: the first time that you run this project, it will generate a Phosphor-instrumented JRE. This might take a while, but will only need to be done once. It will keep reusing that instrumented JRE until you type `mvn clean`.

When the existing test runs, it should provide this output:
```
Running edu.gmu.cs795.lab2.part1.SimpleUsageIT
The taint on variable j is: Taint [lbl=This is some variable i   deps = []]
The taint on variable k is: Taint [lbl=null  deps = [This is some variable i  ]]
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.068 sec - in edu.gmu.cs795.lab2.part1.SimpleUsageIT
```

You should look at _how_ this works, by using `javap` to inspect the instrumented `SimpleUsageIT`. For this lab, Phosphor's debug mode is enabled (in `edu.gmu.cs795.lab2.PreMain`, we set `edu.columbia.cs.psl.phosphor.PreMain.DEBUG` to `true`). So, from the top level directory of the lab, you can run `javap -private -verbose debug/edu/gmu/cs795/lab2/part1/SimpleUsageIT.class`. Examine the bytecode for `testTaintedPrimitive` and compare it to the original (uninstrumented) class. Remember that in source code, you are calling the "simple" functions (e.g. "MultiTainter.taintedInt(int, Taint)"), but Phosphor is transforming this into the complicated, wrapped functions (e.g. "MultiTainter.taintedInt$$PHOSPHORTAGGED(Taint, int, Taint)").

Add a few more examples here, and try to see in action the different changes that Phosphor makes. Try adding a new method that returns a primitive type (or array of primitives). Compare the code that Phosphor generates (in the `debug` folder after you run it) to the original code. 

### Part two: Programmatically tainting things
Now, let's look into calling the Phosphor API by using bytecode rewriting. This project (and HW2) are configured so that you can write an ASM method visitor that operates _before_ Phosphor does any transformations (as opposed to after). This is generally the best way to interact with Phosphor, because it generally protects you from having to worry about what Phosphor does to transform the code.

Take a look at `edu.gmu.cs795.lab2.part2.DynamicTaintingIT`: Remove the `@Ignore` tag from the test. Your goal from this step is to implement `edu.gmu.cs795.lab2.inst.Lab2MethodVisitor` so that the return values of `edu.gmu.cs795.lab2.part2.DynamicTaintingIT.getTaintedInt()` and `edu.gmu.cs795.lab2.part2.DynamicTaintingIT.getTaintedIntArray()` are tainted (it doesn't matter what the tag is, it can be anything).

Hint: you want to insert this instrumentation in `visitInsn`, _just before_ the `RETURN` statement. There are several different `RETURN` statements depending on the type of value being returned. The ASM function [Type.getOpcode(int)](http://asm.ow2.org/asm50/javadoc/user/org/objectweb/asm/Type.html#getOpcode-int-) is handy for determining exactly which variant of `*LOAD, *STORE, *ALOAD, *ASTORE, *ADD, *SUB, *MUL, *DIV, *REM, *NEG, *SHL, *USHR, *AND, *XOR` _and_ `*RETURN` is correct for a given `Type`. So, for instance, given a `Type` object `t`, `t.getOpcode(Opcodes.IRETURN)` will return the correct opcode used to `RETURN` that type `t`.