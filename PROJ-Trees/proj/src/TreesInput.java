import si.fri.algotest.execute.AbstractInput;
import java.util.*;

/**
 * @author td
 */
public class TreesInput extends AbstractInput {

  private String ops;
  ArrayList<String> operations;

  
  public TreesInput(String ops) {
    this.ops        = ops;    
    this.operations = new ArrayList(Arrays.asList(ops.split(";")));
  }
      
  @Override
  public String toString() {
    return ops;
  }
}