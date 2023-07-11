import si.fri.algotest.entities.Variables;
import si.fri.algotest.execute.AbstractTestCase;

/**
 *
 * @author ...
 */
public class TreesTestCase extends AbstractTestCase {

  @Override
  public TreesInput getInput() {
    return (TreesInput) super.getInput(); 
  } 

  @Override
  public TreesOutput getExpectedOutput() {
    return (TreesOutput) super.getExpectedOutput();
  }
  
  
  @Override
  /**
   * TYPE0 test case generator. 
   * Method creates a test case based on the given test case parameters.
  **/
  public TreesTestCase testCaseGenerator(Variables generatingParameters) {  
    // a path to the testset folder 
    String path       = generatingParameters.getVariable(TESTS_PATH,    "").getStringValue();  

          
    String ops = generatingParameters.getVariable("OPS", "").getStringValue();              
    String ans = generatingParameters.getVariable("ANS", "").getStringValue();              
       
    
    // create a test case and set ...
    TreesTestCase TreesTestCase = new TreesTestCase();    

    // ... the input                
    TreesTestCase.setInput(new TreesInput(ops));    

    // ... the testcase parameters (for the type0 generator the set of InputParameters equals to the set of GeneratingParameters)
    Variables inputParameters = new Variables(generatingParameters);
    TreesTestCase.getInput().setParameters(inputParameters);
    
    // ... and the expected output
    TreesTestCase.setExpectedOutput(new TreesOutput(ans));
    
    return TreesTestCase;
  }
}