/**
 * https://algorithmtutor.com/Data-Structures/Tree/Red-Black-Trees/
 * @author ...
 */
public class RBAlgorithm extends TreesAbsAlgorithm {

  public Vozlisce root;
  public int n;
  protected void init() {
    this.root= null;
    this.n =0;
  }
  
  protected void insert(Integer elt) {
    Vozlisce noviElement = new Vozlisce(elt);

    Vozlisce trenutno = this.root;
    Vozlisce stars = null;
    while(trenutno != null){
      stars = trenutno;
      if(elt.intValue()<trenutno.st.intValue()){
        trenutno = trenutno.Levo;
      }
      else{
        trenutno = trenutno.Desno;
      }
    }
    noviElement.Stars=stars;
    if(stars== null){
      this.root= noviElement;
    }
    else if(stars.st.intValue()>elt.intValue()){
      stars.Levo=noviElement;
    }
    else{
      stars.Desno=noviElement;
    }

    if(noviElement.Stars==null){
      noviElement.barva=0;
      return;
    }
    if(noviElement.Stars.Stars==null){
      return;
    }
    popraviDrevoIns(noviElement);

  }

  protected void popraviDrevoIns(Vozlisce vozlisce){
    Vozlisce t;
    while(vozlisce.Stars.barva == 1){
      
      if(vozlisce.Stars==vozlisce.Stars.Stars.Desno){
        t = vozlisce.Stars.Stars.Levo;
        if(t==null){
          if(vozlisce== vozlisce.Stars.Levo){
            vozlisce = vozlisce.Stars;
            obrniDesno(vozlisce);
          }
          vozlisce.Stars.barva =0;
          vozlisce.Stars.Stars.barva =1;
          obrniLevo(vozlisce.Stars.Stars);
        }
        else if(t.barva == 1){          
          t.barva= 0;
          vozlisce.Stars.barva= 0;
          vozlisce.Stars.Stars.barva= 1;
          vozlisce =  vozlisce.Stars.Stars;
        }
        else{
          if(vozlisce== vozlisce.Stars.Levo){
            vozlisce = vozlisce.Stars;
            obrniDesno(vozlisce);
          }
          vozlisce.Stars.barva =0;
          vozlisce.Stars.Stars.barva =1;
          obrniLevo(vozlisce.Stars.Stars);
        }
      }
      else{
        t = vozlisce.Stars.Stars.Desno;
        if(t==null){
          if(vozlisce== vozlisce.Stars.Desno){
            vozlisce = vozlisce.Stars;
            obrniLevo(vozlisce);
          }
          vozlisce.Stars.barva =0;
          vozlisce.Stars.Stars.barva =1;
          obrniDesno(vozlisce.Stars.Stars);
        }
        else if(t.barva == 1){
          t.barva= 0;
          vozlisce.Stars.barva= 0;
          vozlisce.Stars.Stars.barva= 1;
          vozlisce =  vozlisce.Stars.Stars;
        }
        else{
          if(vozlisce== vozlisce.Stars.Desno){
            vozlisce = vozlisce.Stars;
            obrniLevo(vozlisce);
          }
          vozlisce.Stars.barva =0;
          vozlisce.Stars.Stars.barva =1;
          obrniDesno(vozlisce.Stars.Stars);
        }
      }
      if( vozlisce == this.root){
        break;
      }
    }
    root.barva=0;
  }
  
  protected void obrniDesno(Vozlisce voz){
    Vozlisce tmp = voz.Levo;
    voz.Levo = tmp.Desno;
    if(tmp.Desno != null){
      tmp.Desno.Stars= voz;
    
    }
    tmp.Stars=voz.Stars;
    if(voz.Stars== null){
      this.root= tmp;
    }
    else if(voz == voz.Stars.Desno){
      voz.Stars.Desno = tmp;
    }
    else{
      voz.Stars.Levo = tmp;
    }
    tmp.Desno = voz;
    voz.Stars = tmp;
  }

  protected void obrniLevo(Vozlisce voz){
    Vozlisce tmp = voz.Desno;
    voz.Desno = tmp.Levo;
    if(tmp.Levo != null){
      tmp.Levo.Stars= voz;
    
    }
    tmp.Stars=voz.Stars;
    if(voz.Stars== null){
      this.root= tmp;
    }
    else if(voz == voz.Stars.Levo){
      voz.Stars.Levo = tmp;
    }
    else{
      voz.Stars.Desno = tmp;
    }
    tmp.Levo = voz;
    voz.Stars = tmp;
  }

  protected boolean delete(Integer key) {
    if(this.root == null){return false;}
    return delete(this.root, key);
  }
  protected boolean delete(Vozlisce voz, Integer key) {
    Vozlisce izbris = null;
    Vozlisce x = null;
    Vozlisce y = null;

    while(voz !=null){
      if(null == voz.st){
        return false;
      }
      if(key.intValue()== voz.st.intValue()){
        izbris = voz;
        break;
      }
      if(key.intValue()<= voz.st.intValue()){
        voz = voz.Levo;
      }
      else{
        voz = voz.Desno;
      }
    }
    if(izbris == null){
      return false;
    }

    y = izbris;
    
    int originalanBarva = y.barva;
    if(izbris.Levo == null){
      x = izbris.Desno;
      if(x == null){
        x = new Vozlisce();
        x.Stars =  izbris;
      }
      presadi(izbris,izbris.Desno);
    }
    else if(izbris.Desno == null){
      x = izbris.Levo;
      if(x == null){
        x = new Vozlisce();
        x.Stars =  izbris;
      }
      presadi(izbris,izbris.Levo);
    }
    else{
      y = min(izbris.Desno);
      originalanBarva = y.barva;
      x = y.Desno;
      if(x == null){
        x = new Vozlisce();
        x.Stars = y;
      }
      if(y.Stars == izbris){
        x.Stars = y;
      }
      else{
        presadi(y,y.Desno);
        y.Desno=izbris.Desno;
        y.Desno.Stars=y;

      }
      presadi(izbris,y);
      y.Levo=izbris.Levo;
      y.Levo.Stars=y;
      y.barva=izbris.barva;
    }
    if(originalanBarva==0){
      popraviDrevoDel(x);
    }

    return true;
  }

  protected void popraviDrevoDel(Vozlisce vozlisce){
    Vozlisce t;
    if(vozlisce == null){
      vozlisce = new Vozlisce();
    }
    while(vozlisce != this.root && vozlisce.barva==0){
      if(vozlisce==vozlisce.Stars.Levo){
        t=vozlisce.Stars.Desno;
        if(t == null){
          t = new Vozlisce();
          t.Stars = vozlisce.Stars;
        }
        if(t.barva==1){
          t.barva=0;
          vozlisce.Stars.barva=1;
          obrniLevo(vozlisce.Stars);
        }
        if(t.st == null){break;}
        if(t.Levo == null){
          t.Levo  = new Vozlisce();
          t.Levo.Stars = t;
        }
        if(t.Desno == null){
          t.Desno  = new Vozlisce();
          t.Desno.Stars = t;
        }
        if(t.Levo.barva==0 && t.Desno.barva==0){
          t.barva=1;
          vozlisce = vozlisce.Stars;
        }
        else{
          if(t.Desno.barva==0){
            t.Levo.barva=0;
            t.barva=1;
            obrniDesno(t);
            t=vozlisce.Stars.Desno;
          }
          t.barva=vozlisce.Stars.barva;
          vozlisce.Stars.barva=0;
          t.Desno.barva=0;
          obrniLevo(vozlisce.Stars);
          vozlisce =this.root;
        }
      }
      else{
        t=vozlisce.Stars.Levo;
        if(t == null){
          t = new Vozlisce();
          t.Stars = vozlisce.Stars;
          break;
        }
        if(t.Levo == null){
          t.Levo  = new Vozlisce();
          t.Levo.Stars = t;
          t.barva=0;
        }
        if(t.Desno == null){
          t.Desno  = new Vozlisce();
          t.Desno.Stars = t;
          t.barva=0;
        }
        if(t.barva==1){
          t.barva=0;
          vozlisce.Stars.barva=1;
          obrniDesno(vozlisce.Stars);
          t=vozlisce.Stars.Levo;
        }
        if(t.Levo == null){
          t.Levo  = new Vozlisce();
          t.Levo.Stars = t;
          t.barva=0;
        }
        if(t.Desno == null){
          t.Desno  = new Vozlisce();
          t.Desno.Stars = t;
          t.barva=0;
        }
        if(t.Levo.barva==0 && t.Desno.barva==0){
            t.barva=1;
            vozlisce = vozlisce.Stars;
        }
        else{
            if(t.Levo.barva==0){
              t.Desno.barva=0;
              t.barva=1;
              obrniLevo(t);
              if(vozlisce.Stars.Levo== null){
                t = new Vozlisce();
                t.Stars = vozlisce.Stars;
              }
              else{
                t=vozlisce.Stars.Levo;
              }
              
            }
            t.barva=vozlisce.Stars.barva;
            vozlisce.Stars.barva=0;
            if(t.Levo == null){
              t.Levo  = new Vozlisce();
              t.Levo.Stars = t;
              }
              if(t.Desno == null){
              t.Desno  = new Vozlisce();
              t.Desno.Stars = t;
              }
            t.Levo.barva=0;
            obrniDesno(vozlisce.Stars);
            vozlisce =this.root;
          }
        }
      }
    
    vozlisce.barva=0;
  }

  protected Vozlisce min(Vozlisce voz) {
    Vozlisce tmp = voz;
    while(tmp.Levo != null){
      tmp=tmp.Levo;
    }
    return tmp;
  }

  protected void presadi(Vozlisce voz1, Vozlisce voz2) {
    if(voz2 == null){
      voz2 = new Vozlisce();
    }
    if(voz1.Stars == null){
      this.root = voz2;
      voz2.Stars = null;
      return;
    }
    else if(voz1 == voz1.Stars.Levo){
      voz1.Stars.Levo = voz2;
    }
    else{
      voz1.Stars.Desno = voz2;
    }
    
    voz2.Stars = voz1.Stars;
    
  }

  protected Integer find(Integer elt) {
    if (root == null) {
	    return null;
	  } else {
      return root.find(elt);
	  }
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