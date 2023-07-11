import java.io.Serializable;
/**
 *
 * @author ...
 */
public class Drevo implements Serializable{

  private static final long serialVersionUID = 1L;

  public Integer st;
	public Drevo Levo, Desno;
  
    public Drevo(Integer st) {
    	this.st = st;
      this.Levo = null;
      this.Desno = null;
    }
    public Drevo() {
    	this.st = null;
      this.Levo = null;
      this.Desno = null;
    }
}