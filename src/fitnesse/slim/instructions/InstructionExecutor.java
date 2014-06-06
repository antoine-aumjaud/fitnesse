package fitnesse.slim.instructions;

import java.util.List;

import fitnesse.slim.SlimException;

public interface InstructionExecutor {
  void addPath(String path) throws SlimException;

  void create(String instanceName, String className, Object... constructorArgs) throws SlimException;

  Object callAndAssign(String symbolName, String instanceName, String methodsName, Object... arguments) throws SlimException;

  Object callAndAssign(List<String> symbolNames, String instanceName, String methodsName, Object... arguments) throws SlimException;

  Object call(String instanceName, String methodName, Object... arguments) throws SlimException;
}
