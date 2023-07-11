/**
 * https://www.javatpoint.com/avl-tree-program-in-java
 * 
 * @author ...
 */
public class AVLAlgorithm extends TreesAbsAlgorithm {

  public Vozlisce root;
  public int n;
  protected void init() {
    this.root= null;
    this.n =0;
  }
  
  protected void insert(Integer elt) {
    //System.out.println(elt);
    root=insertRec(root,elt);
  }
  Vozlisce insertRec(Vozlisce root, Integer elt){
    if (root==null){
      root=new Vozlisce(elt);
      return root;
    }
    else if (elt.intValue()< root.st.intValue()){
      root.Levo = insertRec(root.Levo, elt);
      //
      if(getH(root.Desno)-getH(root.Levo) == 2){
        if(elt.intValue()> root.Desno.st.intValue()){
          root = rotateLevo(root);
        }
        else{
          root = rotateDvojnoLevo(root);
        }
      }
    }
    //
    else if (elt.intValue()> root.st.intValue()){
      root.Desno = insertRec(root.Desno, elt);
      if(getH(root.Levo)-getH(root.Desno) == 2){
        if(elt.intValue()> root.Desno.st.intValue()){
          root = rotateDesno(root);
        }
        else{
          root = rotateDvojnoDesno(root);
        }
      }
    }
    
    root.visina = Math.max(getH(root.Levo),getH(root.Desno));
    return root;

  }
  int getBalance(Vozlisce N) {
    if (N == null)
        return 0;

    return getH(N.Levo) - getH(N.Desno);
  }
  int getH(Vozlisce root){
    if (root==null){
      return -1;
    }
    return root.visina;
  }

  Vozlisce rotateLevo(Vozlisce root){

    Vozlisce tmp = root.Levo;
    root.Levo = tmp.Desno;
    tmp.Desno = root;
    root.visina = Math.max(getH(root.Levo),getH(root.Desno));
    tmp.visina = Math.max(getH(tmp.Levo),getH(tmp.Desno)) + 1;
    return tmp;
  }

  Vozlisce rotateDesno(Vozlisce root){

    Vozlisce tmp = root.Desno;
    root.Desno = tmp.Levo;
    tmp.Levo = root;
    root.visina = Math.max(getH(root.Levo),getH(root.Desno));
    tmp.visina = Math.max(getH(tmp.Levo),getH(tmp.Desno)) + 1;
    return tmp;
  }

  Vozlisce rotateDvojnoDesno(Vozlisce root){
    root.Desno = rotateLevo(root.Desno);
    return rotateDesno(root);
  }

  Vozlisce rotateDvojnoLevo(Vozlisce root){
    root.Levo = rotateDesno(root.Levo);
    return rotateLevo(root);
  }

  protected Integer find(Integer elt) {
    if(this.root == null){
      return Integer.MIN_VALUE;
    }
    
    Vozlisce tren = this.root;
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
    this.root = deleteRec(this.root, key);
    return true;
    
  }
  Vozlisce deleteRec(Vozlisce root, Integer elt){
    if (root == null){
      return root;
    }
    else if(elt.intValue()< root.st.intValue()){
      root.Levo = deleteRec(root.Levo, elt);
    }
    else if(elt.intValue()> root.st.intValue()){
      root.Desno = deleteRec(root.Desno, elt);
    }
    else{
      if(root.Levo== null && root.Desno == null){
        root=null;
      }
      else if(root.Levo== null ){
        root=root.Desno;
      }
      else if(root.Desno== null ){
        root=root.Levo;
      }
      else{
        Vozlisce tmp = min(root.Desno);
        root.st = tmp.st;
        root.Desno = deleteRec(root.Desno,tmp.st);

      }
    }
    if(root == null) { return root;}
    root.visina = Math.max(getH(root.Levo),getH(root.Desno));
    int vtezenost = getBalance(root);
    if(vtezenost>1 &&getBalance(root.Levo)>=0){
      return rotateDesno(root);
    }
    else if (vtezenost>1 &&getBalance(root.Levo)<0){
      root.Levo = rotateLevo(root.Levo);
      return rotateDesno(root);
    }
    else if(vtezenost<-1 &&getBalance(root.Desno)<=0){
      return rotateLevo(root);
    }
    else if (vtezenost<-1 &&getBalance(root.Desno)>0){
      root.Desno = rotateDesno(root.Desno);
      return rotateLevo(root);
    }
    return root;
  }
  Vozlisce min(Vozlisce root){
    Integer min=root.st;
    Vozlisce tmp = root;
    while(tmp.Levo != null){
      min=root.Levo.st;
      tmp=tmp.Levo;
    }
    return tmp;
  }

  protected int size() {
    return getSize(this.root);
  }
  
  int getSize(Vozlisce root){
    if(root == null){return 0;}
    int x =1;
    x = x+getSize(root.Levo);
    x = x + getSize(root.Desno);
    return x;
  }
  
  protected String getElements() {
    if (root == null)
      return "Empty";
    else
      return print(root);
  }
  protected String print(Vozlisce voz) {
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