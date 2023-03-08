package pset3;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InvokeInstruction;
import org.apache.bcel.generic.MethodGen;

public class GraphGenerator {
	public CFG createCFG(String className) throws ClassNotFoundException {
		CFG cfg = new CFG();
		JavaClass jc = Repository.lookupClass(className);
		ClassGen cg = new ClassGen(jc);
		ConstantPoolGen cpg = cg.getConstantPool();
		for (Method m: cg.getMethods()) {
			cfg.addNode(-1, m, jc); //create the exit node
			MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
			InstructionList il = mg.getInstructionList();
			InstructionHandle[] handles = il.getInstructionHandles();
			for (InstructionHandle ih: handles) {
				int position = ih.getPosition();
				cfg.addNode(position, m, jc);
				Instruction inst = ih.getInstruction();
				String name = inst.getName();
				InstructionHandle nextInstHandle = ih.getNext();
				if(name.contains("return")){ 
					cfg.addEdge(position, -1, m, jc);
				}else if(name.contains("load")||name.contains("invokespecial")){
					cfg.addEdge(position, nextInstHandle.getPosition(), m, jc);
				}else if(name.contains("if")){
					BranchInstruction bi = (BranchInstruction) inst;
					cfg.addEdge(position, bi.getTarget().getPosition(), m, jc);
					cfg.addEdge(position, nextInstHandle.getPosition(), m, jc);
				}
			} 
		}
		return cfg; 
	}

	public CFG createCFGWithMethodInvocation(String className) throws ClassNotFoundException {
		CFG cfg = new CFG();
		JavaClass javaClass = Repository.lookupClass(className);
		ClassGen classGen= new ClassGen(javaClass);
		ConstantPoolGen cpg = classGen.getConstantPool();
		for (Method method: classGen.getMethods()) {
			cfg.addNode(-1, method, javaClass); //create the exit node
			MethodGen methodGen = new MethodGen(method, classGen.getClassName(), cpg);
			InstructionList instrList = methodGen.getInstructionList();
			InstructionHandle[] handles = instrList.getInstructionHandles();
			for (InstructionHandle instrHandle: handles) {
				int position = instrHandle.getPosition();
				cfg.addNode(position, method, javaClass);
				Instruction instr = instrHandle.getInstruction();
				System.out.print(instrHandle.toString() + " ** ");
				System.out.println(instr.toString() + " * "+ method);
				String name = instr.getName();
				InstructionHandle nextInstHandle = instrHandle.getNext();
				if(name.contains("return")){ 
					cfg.addEdge(-1, method, javaClass, -1, method, javaClass);
				}else if(name.contains("load") || name.contains("invokespecial")){
					cfg.addEdge(position, nextInstHandle.getPosition(), method, javaClass);
				}else if(name.contains("if")){
					BranchInstruction brInstr = (BranchInstruction) instr;
					cfg.addEdge(position, brInstr.getTarget().getPosition(), method, javaClass);
					cfg.addEdge(position, nextInstHandle.getPosition(), method, javaClass);
				}else if(name.toLowerCase().contains("invokestatic")){
					InvokeInstruction invInstr = (InvokeInstruction) instr;
					JavaClass nextJc = Repository.lookupClass(invInstr.getClassName(cpg));
					Method nextMethod = classGen.containsMethod(invInstr.getName(cpg), invInstr.getSignature(cpg));
					cfg.addEdge(position, method, javaClass, 0, nextMethod, nextJc); //outgoing edge
					cfg.addEdge(-1, nextMethod, nextJc, nextInstHandle.getPosition(), method, javaClass); //incoming edge
				}
			} 
		}
		return cfg; 
	}
	public static void main(String[] a) throws ClassNotFoundException {
		GraphGenerator gg = new GraphGenerator();
		gg.createCFG("pset3.C"); // example invocation of createCFG
		gg.createCFGWithMethodInvocation("pset3.D"); // example invocation of createCFGWithMethodInovcation
	} 
}