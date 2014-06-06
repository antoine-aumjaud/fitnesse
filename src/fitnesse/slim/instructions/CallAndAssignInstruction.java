package fitnesse.slim.instructions;

import fitnesse.slim.NameTranslator;
import fitnesse.slim.SlimException;

import java.util.Arrays;
import java.util.List;

import static util.ListUtility.list;

public class CallAndAssignInstruction extends Instruction {
  public static final String INSTRUCTION = "callAndAssign";
  private List<String> symbolNames;
  private String instanceName;
  private String methodName;
  private Object[] args;

  public CallAndAssignInstruction(String id, String symbolName, String instanceName, String methodName) {
    this(id, symbolName, instanceName, methodName, new Object[]{});
  }

  public CallAndAssignInstruction(String id, String symbolName, String instanceName, String methodName, Object[] args) {
    this(id, list(symbolName), instanceName, methodName, args);
  }

  public CallAndAssignInstruction(String id, String symbolName, String instanceName, String methodName, Object[] args,
                                  NameTranslator methodNameTranslator) {
    this(id, symbolName, instanceName, methodNameTranslator.translate(methodName), args);
  }
  
  public CallAndAssignInstruction(String id, List<String> symbolNames,
      String instanceName, String methodName, Object[] args,
      NameTranslator methodNameTranslator) {
    this(id, symbolNames, instanceName, methodNameTranslator
        .translate(methodName), args);
  }

  public CallAndAssignInstruction(String id,  List<String> symbolNames, String instanceName, String methodName, Object[] args) {
    super(id);
    this.symbolNames = symbolNames;
    this.instanceName = instanceName;
    this.methodName = methodName;
    this.args = args;
  }

  @Override
  protected InstructionResult executeInternal(InstructionExecutor executor) throws SlimException {
    Object result = executor.callAndAssign(symbolNames, instanceName, methodName, args);
    return new InstructionResult(getId(), result);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer();
    sb.append("{id='").append(getId()).append('\'');
    sb.append(", instruction='").append(INSTRUCTION).append('\'');
    sb.append(", symbolNames='").append(symbolNames).append('\'');
    sb.append(", instanceName='").append(instanceName).append('\'');
    sb.append(", methodName='").append(methodName).append('\'');
    sb.append(", args=").append(args == null ? "null" : Arrays.asList(args).toString());
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    CallAndAssignInstruction that = (CallAndAssignInstruction) o;

    if (!Arrays.equals(args, that.args)) return false;
    if (!instanceName.equals(that.instanceName)) return false;
    if (!methodName.equals(that.methodName)) return false;
    return symbolNames.equals(that.symbolNames);

  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + symbolNames.hashCode();
    result = 31 * result + instanceName.hashCode();
    result = 31 * result + methodName.hashCode();
    result = 31 * result + Arrays.hashCode(args);
    return result;
  }
}
