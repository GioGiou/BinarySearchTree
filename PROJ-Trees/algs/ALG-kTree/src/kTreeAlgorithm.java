import java.io.Serializable;

/**
 * 2-3 tree 
 * https://github.com/SValentyn/2-3-tree
 * @author ...
 */
public class kTreeAlgorithm extends TreesAbsAlgorithm {
  public Vozlisce root;
  public int n;
  private boolean flag; 

  public class Vozlisce implements Serializable{
    Integer kljucLevi, kljucDesni;
    Vozlisce Levo, Srednje, Desno;
    int stopnja;

    Vozlisce(){
      kljucLevi=null;
      kljucDesni = null;
      Levo=null;
      Srednje=null;
      Desno=null;
    }
    public boolean jeVranotezen(){
      boolean rez = false;
      if(this.Levo == null && this.Srednje == null && this.Desno == null){
        rez = true;
      }
      else if(this.Levo.kljucLevi != null && this.Srednje.kljucLevi != null){
        if(this.kljucDesni != null){
          if(this.Desno == null){
            rez = false;
          }
          else if(this.Desno.kljucLevi != null){
            rez = true;
          }
        }
        else{
          rez = true;
        }
      }
      return rez;
    }
    public void vranotezi(){
      while(!this.jeVranotezen()){
        if(this.Levo.kljucLevi ==null){
          this.Levo.kljucLevi = this.kljucLevi;
          this.kljucLevi = this.Srednje.kljucLevi;
          if(this.Srednje.kljucDesni != null){
            this.Srednje.kljucLevi =this.Srednje.kljucDesni;
            this.Srednje.kljucDesni = null;
          }
          else{
            this.Srednje.kljucLevi =null;
          }
        }
        else if(this.Srednje.kljucLevi ==null){
          if(this.kljucDesni == null){
            if(this.Levo.kljucLevi !=null&& this.Levo.kljucDesni ==null&& this.Srednje.kljucLevi ==null){
              this.kljucDesni =this.kljucLevi;
              this.kljucLevi = this.Levo.kljucLevi;
              this.Levo = null;
              this.Srednje = null;
              this.Desno = null;

            }
            else{
              this.Srednje.kljucLevi = this.kljucLevi;
              if(this.Levo.kljucDesni == null){
                this.kljucLevi=this.Levo.kljucLevi;
                this.Levo = null;
              }
              else{
                this.kljucLevi=this.Levo.kljucDesni;
                this.Levo.kljucDesni=null;
              }

              if(this.Levo.kljucLevi ==null&& this.Srednje.kljucLevi ==null){
                this.Levo = null;
                this.Srednje = null;
                this.Desno = null;
              }
            }
          }
          else{
            this.Srednje.kljucLevi =this.kljucDesni;
            if(this.Desno != null){
            this.kljucDesni=this.Desno.kljucLevi;
            if(this.Desno.kljucDesni != null){
              this.Desno.kljucLevi = this.Desno.kljucDesni;
              this.Desno.kljucDesni = null;
            }
            else{
              this.Desno.kljucLevi =null;
            }
          }
          }
        }
        else if(this.kljucDesni != null && this.Desno == null){
          if(this.Srednje.kljucDesni != null){
            this.Desno.kljucLevi = this.kljucDesni;
            this.kljucDesni = this.Srednje.kljucDesni;
            this.Srednje.kljucDesni = null;
          }
          else{
            this.Srednje.kljucDesni = this.kljucDesni;
            this.kljucDesni = null;
          }
        }
        else if(this.kljucDesni != null && this.Desno.kljucLevi == null){
          if(this.Srednje.kljucDesni != null){
            this.Desno.kljucLevi = this.kljucDesni;
            this.kljucDesni = this.Srednje.kljucDesni;
            this.Srednje.kljucDesni = null;
          }
          else{
            this.Srednje.kljucDesni = this.kljucDesni;
            this.kljucDesni = null;
          }
        }
      }
    }
    public Integer min(){
      Integer ret;
      if(this.Levo == null && this.Srednje == null && this.Desno == null){
        ret = this.kljucLevi;
        this.kljucLevi = null;
        if(this.kljucDesni!= null){
          this.kljucLevi = this.kljucDesni;
          this.kljucDesni = null;
        }
      }
      else{
        ret = this.Levo.min();
      }
      if(!this.jeVranotezen()){
        vranotezi();
      }
      return ret;
    }
    public Integer max(){
      Integer ret;
      if(this.Levo == null && this.Srednje == null && this.Desno == null){
        if(this.kljucDesni!= null){
          ret = this.kljucDesni;
          this.kljucDesni = null;
        }
        else{
          ret = this.kljucLevi;
          this.kljucLevi = null;
        }
      }
      else{
        if(this.kljucDesni!= null){
          ret = this.Desno.max();
        }
        else{
          ret = this.Srednje.max();
        }
      }
      if(!this.jeVranotezen()){
        vranotezi();
      }
      return ret;
    }
  }
  

  protected void init() {
    this.root= null;
    this.n =0;
    
  }
  
  protected void insert(Integer elt) {
    
    flag = false;

    if(root == null || root.kljucLevi==null){
      flag= true;
      if(root== null){
        root = new Vozlisce();
      }
      root.kljucLevi=elt;
    }
    else{
      Vozlisce novo = insertRec(root,elt);
      if(novo != null){
        root = novo;
      }
    }
    if(flag){
      this.n++;
    }
  }

  protected Vozlisce insertRec(Vozlisce n, Integer elt){
    Vozlisce stars=null;
    
    if(n.Levo != null && n.Srednje != null && n.Desno !=null){
      
      Vozlisce novoVozlisce;
      if(n.kljucLevi.intValue() == elt.intValue() || (n.kljucDesni != null && n.kljucDesni.intValue() == elt.intValue())){}
      else if(n.kljucLevi.intValue() > elt.intValue()){
       
        novoVozlisce = insertRec(n.Levo, elt);
        if(novoVozlisce != null){
          if(n.kljucDesni == null){
            n.kljucDesni=n.kljucLevi;
            n.kljucLevi = novoVozlisce.kljucLevi;
            n.Desno = n.Srednje;
            n.Srednje = novoVozlisce.Srednje;
            n.Levo = novoVozlisce.Levo;
          }
          else{
            Vozlisce desno = new Vozlisce();
            desno.kljucLevi = n.kljucDesni;
            desno.Levo = n.Srednje;
            desno.Desno = n.Desno;
            stars = new Vozlisce();
            stars.kljucLevi = n.kljucLevi;
            stars.Levo = novoVozlisce;
            stars.Srednje = desno;
          }
        }
        
      }
      else if(n.kljucDesni == null || (n.kljucDesni != null && n.kljucDesni.intValue() > elt.intValue())){
        
        novoVozlisce = insertRec(n.Srednje, elt);
        if(novoVozlisce != null){
          if(n.kljucDesni == null){
            n.kljucDesni = novoVozlisce.kljucLevi;
            n.Desno = novoVozlisce.Srednje;
            n.Srednje = novoVozlisce.Levo;
          }
          else{
            Vozlisce levo = new Vozlisce();
            levo.kljucLevi = n.kljucLevi;
            levo.Levo = n.Levo;
            levo.Srednje = novoVozlisce.Levo;
            Vozlisce srednje = new Vozlisce();
            srednje.kljucLevi = n.kljucDesni;
            srednje.Levo = novoVozlisce.Srednje;
            srednje.Srednje = n.Desno;
            stars = new Vozlisce();
            stars.kljucLevi = novoVozlisce.kljucLevi;
            stars.Levo = levo;
            stars.Srednje = srednje;

          }
        }
      }
      else if(n.kljucDesni != null && n.kljucDesni.intValue() < elt.intValue()){
        
        novoVozlisce = insertRec(n.Desno, elt);
        if(novoVozlisce != null){
            Vozlisce kopija = new Vozlisce();
            kopija.kljucLevi = n.kljucLevi;
            kopija.Levo = n.Levo;
            kopija.Srednje = n.Srednje;
            stars = new Vozlisce();
            stars.kljucLevi = n.kljucDesni;
            stars.Levo = kopija;
            stars.Desno = novoVozlisce;
        }
      }
    }
    else{
      
      flag = true;
      if(n.kljucLevi.intValue() == elt.intValue() || (n.kljucDesni != null && n.kljucDesni.intValue() == elt.intValue())){
        flag = false;
      }
      else if(n.kljucDesni == null){
        if(n.kljucLevi.intValue() > elt.intValue()){
          n.kljucDesni = n.kljucLevi;
          n.kljucLevi = elt;
        }
        else if(n.kljucLevi.intValue() < elt.intValue()){
          n.kljucDesni = elt;
        }
      }
      else{
        stars = split(n, elt);
      }
    }
  
    return stars;
  }

  Vozlisce split(Vozlisce n, Integer elt){
    Vozlisce stars = null;
    if(n.kljucLevi.intValue()> elt.intValue()){
      Vozlisce levo = new  Vozlisce();
      levo.kljucLevi = elt;
      Vozlisce desno = new  Vozlisce();
      desno.kljucLevi = n.kljucDesni;
      stars = new Vozlisce();
      stars.kljucLevi = n.kljucLevi;
      stars.Levo = levo;
      stars. Srednje= desno;
    }
    else if(n.kljucDesni.intValue()<elt.intValue()){
      Vozlisce levo = new  Vozlisce();
      levo.kljucLevi = n.kljucLevi;
      if(n.kljucLevi.intValue() > elt.intValue()){
        Vozlisce desno = new  Vozlisce();
        desno.kljucLevi = n.kljucDesni;
        stars = new Vozlisce();
        stars.kljucLevi = elt;
        stars.Levo = levo;
        stars. Srednje= desno;
      }
      else{
        Vozlisce desno = new  Vozlisce();
        desno.kljucLevi = elt;
        stars = new Vozlisce();
        stars.kljucLevi = n.kljucDesni;
        stars.Levo = levo;
        stars. Srednje= desno;

      }
    }
    return stars;
  }

  
  protected Integer find(Integer elt) {
      Vozlisce a = findRec(this.root, elt);
      if(a == null){
        return Integer.MIN_VALUE;
      }
      return elt;
	  
  }
  Vozlisce findRec(Vozlisce n, Integer elt){
    if(n == null){return n;}
    if(elt.intValue() < n.kljucLevi.intValue()) {
      return findRec(n.Levo,elt);
    }
    else if(elt.intValue() == n.kljucLevi.intValue()){
      return n;
    }
    else if(n.kljucDesni == null){
      return findRec(n.Srednje,elt);
    }
    else if(elt.intValue() < n.kljucDesni.intValue()) {
      return findRec(n.Srednje,elt);
    }
    else if(elt.intValue() == n.kljucDesni.intValue()){
      return n;
    }
    return findRec(n.Desno,elt);
  }
  
  protected boolean delete(Integer key) {
    this.n--;

    boolean zbrisano = deleteRec(this.root, key);
    //this.root = deleteRec(this.root, key);
    if(this.root == null){}
    else if(this.root.kljucLevi==null){
      this.root=null;
    }
    if(!zbrisano){
      this.n++;
    }
    return zbrisano;
    
  }
  boolean deleteRec(Vozlisce n, Integer elt){
    boolean jeZbrisan = true;
    if(n==null){
      return false;
    }
    else{
      if(!(n.kljucLevi.intValue()==elt.intValue())){
        if(n.kljucDesni==null || n.kljucDesni.intValue()>elt.intValue()){
          if( n.kljucLevi.intValue()>elt.intValue()){
            jeZbrisan = deleteRec(n.Srednje,elt);
          }
          else{
            jeZbrisan = deleteRec(n.Levo,elt);
          }
        }
        else{
          if(!(n.kljucDesni.intValue()==elt.intValue())){
            jeZbrisan = deleteRec(n.Desno,elt);
          }
          else{
            if(n.Desno == null && n.Levo == null&& n.Srednje== null){
              n.kljucDesni = null;
            }
            else{
              Integer temp = n.Desno.min();
              n.kljucDesni=temp;
            }
          }
        }
      }
      else{
        if(n.Desno == null && n.Levo== null && n.Srednje== null){
          if(n.kljucDesni != null){
            n.kljucLevi = n.kljucDesni;
            n.kljucDesni = null;
          }
          else{
            n.kljucLevi = null;
            return true;
          }
        }
        else{
          Integer temp = n.Levo.max();
          n.kljucLevi=temp;
        }
      }
    }

    if(!n.jeVranotezen()){
      n.vranotezi();
    }
    else if(!(n.Desno == null && n.Levo== null && n.Srednje== null)){
      boolean jeVran = false;
      while(!jeVran){
        if(n.Desno == null){
          if((n.Levo.Desno == null && n.Levo.Levo== null && n.Levo.Srednje== null) && !(n.Srednje.Desno == null && n.Srednje.Levo== null && n.Srednje.Srednje== null)){
            Integer rep = n.Srednje.min();
            Integer temp = n.kljucLevi;
            n.kljucLevi = rep;
            insert(temp);
          }
          else if(!(n.Levo.Desno == null && n.Levo.Levo== null && n.Levo.Srednje== null) && (n.Srednje.Desno == null && n.Srednje.Levo == null && n.Srednje.Srednje == null)){
            if(n.kljucDesni== null){
              Integer rep = n.Levo.max();
              Integer temp = n.kljucLevi;
              n.kljucLevi = rep;
              insert(temp);
            }
            
          }
        }

        if(n.Desno != null){
          if(!(n.Desno.Desno == null && n.Desno.Levo== null && n.Desno.Srednje== null)&& (n.Srednje.Desno == null && n.Srednje.Levo== null && n.Srednje.Srednje== null)){
            n.Desno.vranotezi();
          }
          if(!(n.Desno.Desno == null && n.Desno.Levo == null && n.Desno.Srednje == null)&& (n.Srednje.Desno == null && n.Srednje.Levo == null && n.Srednje.Srednje== null)){
            Integer rep = n.Desno.min();
            Integer temp = n.kljucDesni;
            n.kljucDesni = rep;
            insert(temp);
          }else{
            jeVran = true;
          }
        }
        if(n.jeVranotezen()){
          jeVran = true;
        }
      }
    }
    return jeZbrisan;
  }
  //Vozlisce min(Vozlisce root){
  //  Integer min=root.st;
  //  Vozlisce tmp = root;
  //  while(tmp.Levo != null){
  //    min=root.Levo.st;
  //    tmp=tmp.Levo;
  //  }
  //  return tmp;
  //}

  protected int size() {
    return this.n;
  }
  
   
  protected String getElements() {
    if (root == null)
      return "Empty";
    else
      return print(this.root);
  }
  protected String print(Vozlisce voz) {
    if (voz== null)
      return null;
    else {
      String ret = String.valueOf(voz.kljucLevi.intValue());
      String levo = print(voz.Levo);
      if(levo != null){ ret = levo+";"+ret;}
      String srednje = print(voz.Srednje);
      if(srednje != null){ret=ret+";"+srednje;}
      if(voz.kljucDesni!= null){
      ret = String.valueOf(voz.kljucDesni.intValue());
      String desno = print(voz.Desno);
      if(desno != null){ret=ret+";"+desno;}}
      return ret;}
  }
}
