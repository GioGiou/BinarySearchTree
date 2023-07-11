/**
 * https://arxiv.org/pdf/1806.06726.pdf
 * @author ...
 */
import java.util.*;

public class ZipAlgorithm extends TreesAbsAlgorithm {

  public static Random rnd;
  public Vozlisce root;
  static int maxRep;
  public int n;
  protected void init() {
    maxRep = (int) (Math.log(10000) / Math.log(2));
    this.root= null;
    this.n =0;
    rnd = new Random(10);
  }
  
  protected void insert(Integer elt) {
    Vozlisce x = new Vozlisce(elt,ZipAlgorithm.met());
    int rank = x.rank;
    int stevilo = x.st.intValue();
    Vozlisce trenutno = this.root;
    Vozlisce predhodnje = null;
    while(trenutno != null && (rank<trenutno.rank || (rank==trenutno.rank && stevilo>trenutno.st.intValue()))){
      predhodnje = trenutno;
      if(stevilo>trenutno.st.intValue()){
        trenutno = trenutno.Desno;
      }
      else{
        trenutno = trenutno.Levo;
      }
    }
    if(trenutno==this.root){
      this.root = x;
    }
    else if(stevilo < predhodnje.st.intValue()){
      predhodnje.Levo = x;
    }
    if(trenutno==null){return;}
    else if(trenutno.st.intValue() > stevilo){
      x.Desno = trenutno;
    }
    else{
      x.Levo = trenutno;
    }
    predhodnje = x;
    Vozlisce fix = null;
    while(trenutno != null){
      
      fix = predhodnje;
      if(trenutno.st.intValue() < stevilo){
        do{
          predhodnje = trenutno;
          trenutno = trenutno.Desno;
        }
        while(trenutno != null &&  trenutno.st.intValue() > stevilo);
      }
      else{
        do{
          predhodnje = trenutno;
          trenutno = trenutno.Levo;
        }while(trenutno != null && trenutno.st.intValue() < stevilo);
      }
      if(fix.st.intValue() > stevilo || (fix == x && predhodnje.st.intValue()>stevilo)){
        fix.Levo = trenutno;
      }
      else{
        fix.Desno = trenutno;
      }
    }

    n++;

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
  Vozlisce findRec(Vozlisce root, Integer elt){
    if(root == null){return root;}
    if(root.st==elt){return root;}
    if(root.st<elt){return findRec(root.Desno, elt);}
    else{return findRec(root.Levo, elt);}
  }
  
  protected boolean delete(Integer key) {
    Vozlisce trenutno = this.root;
    Vozlisce predhodnje = null;

    if(trenutno == null){
      return false;
    }
    
    while(trenutno.st.intValue() != key.intValue()){
      //System.out.print(trenutno.st.intValue() +","+ key.intValue()+"; ");
      predhodnje = trenutno;
      if(key.intValue() < trenutno.st.intValue()){
        trenutno = trenutno.Levo;
      }
      else{
        trenutno = trenutno.Desno;
      }

      if(trenutno == null){
        return false;
      }
    }
    Vozlisce levo = trenutno.Levo;
    Vozlisce desno = trenutno.Desno;
    if(levo == null){
      trenutno = desno;
    }
    else if(desno == null){
      trenutno = levo;
    }
    else if(levo.rank >= desno.rank){
      trenutno = levo;
    }
    else{
      trenutno = desno;
    }
    if (this.root.st.intValue()==key.intValue()){
      this.root=trenutno;
    }
    else if(key.intValue()< predhodnje.st.intValue()){
      predhodnje.Levo=trenutno;
    }
    else{
      predhodnje.Desno=trenutno;
    }
    while(desno != null && levo != null){
      if(levo.rank>=desno.rank){
        do{
          predhodnje = levo;
          levo = levo.Desno;
        }
        while(levo != null && levo.rank<desno.rank);
        predhodnje.Desno = desno;
      }
      else{
        do{
          predhodnje = desno;
          desno = desno.Levo;
        }
        while(desno != null && levo.rank>=desno.rank);
        predhodnje.Levo = levo;
      }
    }
    this.n--;
    return true;
  }
  
  Integer min(Vozlisce root){
    int min=root.st;
    while(root.Levo != null){
      min=root.Levo.st;
      root=root.Levo;
    }
    return min;
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

  public static int met() {
		int st = 1;
		while (rnd.nextBoolean() && st < maxRep) {
			st++;
		}
		return st;
	}

}