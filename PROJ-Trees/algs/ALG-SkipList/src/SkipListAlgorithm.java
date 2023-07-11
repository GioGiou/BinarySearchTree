/**
 * https://www.baeldung.com/cs/skip-lists#bd-how-to-insert-into-a-skip-list
 * @author ...
 */

import java.util.*;
public class SkipListAlgorithm extends TreesAbsAlgorithm {

  	private NodeSkipList[] head;
	int stNodes;
	static int maxRep;
	int i=0;
	int max=0;

  protected void  init() {
		this.stNodes =0;
		maxRep = (int) (Math.log(1000) / Math.log(2));
		head = new NodeSkipList[maxRep];
  }
  
  protected void insert(Integer searchKey) {
	
    int kovanec = met();
	
	NodeSkipList novi = new NodeSkipList(searchKey, kovanec);
	if(head[0] == null){
		if(kovanec>max){
			max = kovanec;
		}
		for(i =0;i<kovanec;i++){
			head[i] = novi;
		}
		
		stNodes++;
		
		return;
	}
	else{
		if(kovanec>max){
			max = kovanec;
		}
		for(int i = kovanec-1;i>=0;i--){
			NodeSkipList trenutni = head[i];
			if(trenutni == null){
				head[i]=novi;
			}
			else{
				NodeSkipList naslednik = trenutni.rep[i];
				while(naslednik != null && searchKey.intValue() > naslednik.head.intValue() ){
					trenutni = naslednik;
					naslednik = trenutni.rep[i];
				}
				trenutni.rep[i] = novi;
				novi.rep[i]= naslednik;
			}
		}
	}
	//System.out.println(searchKey.intValue()+ " "+stNodes);
	stNodes++;
	return;
  }

  protected Integer find(Integer elt) {
	if(this.head[0] == null){
		return Integer.MIN_VALUE;
	}
	NodeSkipList trenutni = head[max-1];
	for(int i = max -1; i>=0;i--){
		if(trenutni != null){
			trenutni = head[i];
		}
		while(trenutni.rep[i] != null && elt.intValue() > trenutni.rep[i].head.intValue()){
			trenutni =  trenutni.rep[i];
		}
	}
	if(trenutni.rep[0] != null &&  trenutni.rep[0].head.intValue() == elt.intValue()){
		return elt;
	}
	return Integer.MIN_VALUE;
	}
  
  protected boolean delete(Integer key) {
	if(head[0] == null){
		return false;
	}
	NodeSkipList trenutni = null;
	NodeSkipList naslednik = null;
	NodeSkipList popravi[] = new NodeSkipList[max];
    for(int i = max -1; i>=0;i--){
		trenutni = head[i];
		naslednik = trenutni.rep[i];
		while(naslednik != null && naslednik.head.intValue()>key.intValue()){
			trenutni = naslednik;
			naslednik = trenutni.rep[i];
		}
		if(naslednik != null){
			if(naslednik.head.intValue()==key.intValue()){
				popravi[i]=trenutni;
			}
		}
	}
	if(naslednik == null){
		return false;
	}
	if(naslednik.head.intValue()==key.intValue()){
		for(int i = 0; i<max;i++){
			if(popravi[i]!=null){
				popravi[i].rep[i] =naslednik.rep[i];
			}
		}
		while(max>1 && head[max-1] == null){
				max--;
			
		}
	}
	stNodes--;
	return true;
  }
  
  protected int size() {
    return stNodes;
  }
  
  
  protected String getElements() {
	
    if (head == null)
      return "Empty";
    else
      return print(head[0]);
  }

  protected String print(NodeSkipList node){
	if (node== null)
      return null;
    else {
		String ret = String.valueOf(node.head.intValue());
		if (node.rep[0] != null) {
			ret = ret + ";" + print(node.rep[0]);
		}
		return ret;
	}
	//
  }

  public static int met() {
		Random r = new Random();
		int st = 1;
		while (r.nextBoolean() && st < maxRep) {
			st++;
		}
		return st;
	}

}