/**
 *
 * @author ...
 */
public class DrevoAlgorithm extends TreesAbsAlgorithm{

  private static final long serialVersionUID = 1L;
  private Drevo root;
  public int n;
  
 
  protected void init() {
    this.root= null;
    this.n=0;
  }
  
  protected void insert(Integer elt) {
    if (root == null) {
	    root = new Drevo(elt);
      this.n++;
	  } else {
      root = insertRec(this.root, elt);
      this.n++;
	  }

  }
  protected Drevo  insertRec(Drevo node, Integer key) {
    if (node == null) {
	    node = new Drevo(key);
      return node;
	  } 
    if(key.intValue()<node.st.intValue()){
      node.Levo = insertRec(node.Levo, key);
    }
    if(key.intValue()>node.st.intValue()){
      node.Desno = insertRec(node.Desno, key);
    }
    return node;
  }
  
  protected Integer find(Integer elt) {
    if(this.root == null){
      return Integer.MIN_VALUE;
    }
    
   Drevo tren = this.root;
    while (tren != null){
      if(tren.st.intValue() == elt.intValue()){
        return tren.st;
      }
      else if(tren.st.intValue() < elt.intValue()){
        tren = tren.Levo;
      }
      else if(tren.st.intValue() > elt.intValue()){
        tren = tren.Desno;
      }
    }
    //Vozlisce a = findRec(this.root, elt);
    //if (a == null) {
	  //  return null;
	  //} else {
    //  return elt;
	  //}
    return Integer.MIN_VALUE;
  }
  
  protected boolean delete(Integer key) {
    if (root == null) {
			return false;

		}
    this.root = deleteRec(this.root, key);
    this.n--;
    return true;
  }

  protected Drevo deleteRec(Drevo node, Integer key) {
    if (node == null) {return node;}
    if(node.st.intValue() <key.intValue()){
      node.Levo = deleteRec(node.Levo,key);
      return node;
    }
    if(node.st.intValue() >key.intValue()){
      node.Desno = deleteRec(node.Desno,key);
      return node;
    }

    if(node.Levo == null){
      
      return node.Desno;
    }
    if(node.Desno == null){
      
      return node.Levo;
    }
    Drevo stars = node;
    Drevo otrok = node.Desno;
    while(otrok.Levo != null){
      stars = otrok;
      otrok = otrok.Levo;
    }
    if(stars != node){
      stars.Levo = otrok.Desno;
    }
    else{
      stars.Desno = otrok.Desno;
    }
    node.st=otrok.st;
    return node;
  }
  
  protected int size() {
    return this.n;
  }
  
  
  protected String getElements() {
    if (root == null)
      return "Empty";
    else
      return print(root);
  }
  protected String print(Drevo voz) {
    if (voz== null)
      return null;
    else {
      String ret = String.valueOf(voz.st.intValue());
      String levo = print(voz.Levo);
      if(levo != null){ ret = levo+";"+ret;}
      String desno = print(voz.Desno);
      if(desno != null){ret=ret+";"+desno;}
      return ret;}
  }
}