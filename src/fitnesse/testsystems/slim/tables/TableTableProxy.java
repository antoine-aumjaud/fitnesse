package fitnesse.testsystems.slim.tables;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

class TableTableProxy0 { //TOAA
  private List<List<String>> out;
  private Object instance;

  public void init(String className) throws Exception {
    instance = Class.forName(className).newInstance();
  }

  @SuppressWarnings("unchecked")
  public List<List<String>> doTable(List<List<String>> data) throws Exception {
    out = (List<List<String>>) instance.getClass().getMethod("doTable").invoke(instance, data);
    return out;
  }

  public String getValue(int row, int col) {
    return out != null ? out.get(row).get(col) : null;
  }
}

public class TableTableProxy implements InvocationHandler {
  private Object target;
  private List<List<String>> out;

  public TableTableProxy(Object target) {
    this.target = target;
  }

  public String getValue(int row, int col) {
    return out != null ? out.get(row).get(col) : null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object invoke(Object proxy, Method method, Object[] args)
      throws Throwable {
    Object ret = method.invoke(target, args);

    if (method.getName().equals("doTable"))
      out = (List<List<String>>) ret;

    return ret;
  }
}
