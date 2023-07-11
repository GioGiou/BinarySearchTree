import si.fri.algotest.execute.AbstractOutput;
import si.fri.algotest.execute.AbstractTestCase;

/**
 * 
 * @author td
 */
public class TreesOutput extends AbstractOutput {

  public String answer;
  
  public TreesOutput(String ans) {    
    this.answer = ans;
  }
  
  
  @Override
  public String toString() {    
    return this.answer;
  }
  
  
  @Override
  protected Object getIndicatorValue(AbstractTestCase testCase, AbstractOutput algorithmOutput, String indicatorName) {
    TreesTestCase TreesTestCase        = (TreesTestCase) testCase;
    TreesOutput   TreesAlgorithmOutput = (TreesOutput) algorithmOutput;

    String eAns = TreesTestCase.getExpectedOutput().answer;
    String ans  = TreesAlgorithmOutput.answer;


    switch (indicatorName) {
      // TODO: for each indicator defined in the atrd file provide a "case" to determnine its value
      //case "indicator_name" :
      //  using the given test case TreesTestCase (which includes the input and the expected output)
      //    and the given TreesAlgorithmOutput (the actual output of the algorithm) calculate indicator_value
      //  return indicator_value;

      case "Check":        
        return (answer==null || answer.isEmpty() || eAns.equals(ans)) 
          ? "OK" : "NOK - " + String.format("got '%s', expected '%s'", ans, eAns);
    }
    
    return "?";
  }
}