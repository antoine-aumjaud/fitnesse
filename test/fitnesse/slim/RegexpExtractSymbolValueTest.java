package fitnesse.slim;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fitnesse.testsystems.slim.tables.TableTable;
import static org.junit.Assert.*;
import static util.ListUtility.*;

public class RegexpExtractSymbolValueTest {

  @Test
  public void tableTableExtract() {
    List<List<?>> dataTableTable = new ArrayList<List<?>>();
    dataTableTable.add(list("pass:0", "pass: 1", "error:2", "fail:3", "fail:4str", "ignore:5", "without 6", "with space:7", null));
    RegexpExtractSymbolValue e = new RegexpExtractSymbolValue(dataTableTable, TableTable.EXTRACT_REGEXP);
    assertEquals("0", e.getValue(0, 0));
    assertEquals(" 1", e.getValue(0, 1));
    assertEquals("2", e.getValue(0, 2));
    assertEquals("3", e.getValue(0, 3));
    assertEquals("4str", e.getValue(0, 4));
    assertEquals("5", e.getValue(0, 5));
    assertEquals("without 6", e.getValue(0, 6));
    assertEquals("with space:7", e.getValue(0, 7));
    assertEquals("", e.getValue(0, 8));
    assertEquals("", e.getValue(0, 9));
    assertEquals("", e.getValue(0, 10));
    assertEquals("", e.getValue(1, 0));
    assertEquals("", e.getValue(2, 0));
  }
}