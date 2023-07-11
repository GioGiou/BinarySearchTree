import si.fri.algotest.execute.AbstractAlgorithm;
import java.util.*;

/**
 *
 * @author td
 */
public abstract class TreesAbsAlgorithm extends AbstractAlgorithm {
 
  @Override
  public TreesTestCase getCurrentTestCase() {
    return (TreesTestCase) super.getCurrentTestCase(); 
  }

  protected abstract void    init     ();
  protected abstract void    insert   (Integer elt);
  protected abstract Integer find     (Integer elt);
  protected abstract boolean delete   (Integer elt);
  protected abstract int     size     ();
  

  // prints all elements in in-order sequence; elements are delimited by semicolon
  protected abstract String getElements();

  private int getTimerIndex(String operacija) {
    switch(operacija) {
      case "insert":
        return 1;
      case "find":
        return 2;
      case "delete":
        return 3;       
      default:
        return -1;
    }
  }

  @Override
  public void run() {    
    ArrayList<String> operations = getCurrentTestCase().getInput().operations;
    
    // generator naključnih števil
    Random rnd = new Random();
    // naključna števila bodo iz intervala [0...MAX_RND)
    final int MAX_RND = 100000;

    init();
    
    String returnValue = "";
    int currentOpTimer;

    for (String op : operations) {
      String[] opParts = op.trim().split(" ");
      if (opParts.length < 2) continue;
      
      int steviloOperacij = Integer.parseInt(opParts[0]);      
      String operacija    = opParts[1]; 
      int operand         = opParts.length > 2 ? Integer.parseInt(opParts[2]) : 0;
      int j = 0;
      if(operacija == "deleteN"){
        j =  steviloOperacij -1;
      }
      for(int i=0; i<steviloOperacij; i++) {
        switch (opParts[1]) {
          case "insert":
            currentOpTimer = getTimerIndex("insert");
            timer.resume(currentOpTimer);
              insert(operand);
            timer.stop(currentOpTimer);
            
            returnValue = "true";
            break;

          case "insertN":
            currentOpTimer = getTimerIndex("insert");
            timer.resume(currentOpTimer);
              insert(j);
            timer.stop(currentOpTimer);
            j++;
            returnValue = "true";
            break;

          case "find":
            currentOpTimer = getTimerIndex("find");
            timer.resume(currentOpTimer);
            Integer result = find(operand);
            timer.stop(currentOpTimer); 
            
            returnValue = result == null ? "null" : result.toString();
            break;

          case "delete":
            currentOpTimer = getTimerIndex("delete");
            timer.resume(currentOpTimer);            
              boolean deleted = delete(operand);
            timer.stop(currentOpTimer);
            
            returnValue = deleted ? "true" : "false";
            break;

            
          case "insertRND":
            int nextEltI = rnd.nextInt(MAX_RND);
            currentOpTimer = getTimerIndex("insert");
            timer.resume(currentOpTimer);            
              insert(nextEltI);
            timer.stop(currentOpTimer);
            
            returnValue = "true";
            break;

          case "deleteRND":
            int nextEltD = rnd.nextInt(MAX_RND);
            currentOpTimer = getTimerIndex("delete");
            timer.resume(currentOpTimer);            
              boolean deletedRND = delete(nextEltD);
            timer.stop(currentOpTimer);
            
            returnValue = deletedRND  ? "true" : "false";
            break;

          case "findRND":
            int nextEltF = rnd.nextInt(MAX_RND);
            currentOpTimer = getTimerIndex("find");
            timer.resume(currentOpTimer);            
              int findRND = find(nextEltF);
            timer.stop(currentOpTimer);
            
            returnValue = findRND==nextEltF  ? "true" : "false";
            break;

          case "deleteN":
            currentOpTimer = getTimerIndex("delete");
            timer.resume(currentOpTimer);            
              boolean deletedN = delete(j);
            timer.stop(currentOpTimer);
            j--;
            returnValue = deletedN  ? "true" : "false";
            break;

          case "findN":
            
            int nextEltFN  = j;
            currentOpTimer = getTimerIndex("find");
            timer.resume(currentOpTimer);            
              int findN = find(j).intValue();
            timer.stop(currentOpTimer);
            j++;
            returnValue = findN==nextEltFN  ? "true" : "false";
            break;
            
          case "nop": 
            returnValue = "true";
            break;
            
          case "seed":
            rnd = new Random(operand);
            break;
            
          case "elements":
            returnValue = getElements();
            break;
          case "size":
            returnValue = Integer.toString(size());
            break;

            
          default:
            returnValue = "";
        } 
      }
    }
    algorithmOutput = new TreesOutput(returnValue);
  }

}