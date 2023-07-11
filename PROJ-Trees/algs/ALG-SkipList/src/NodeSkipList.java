import java.io.Serializable;

public class NodeSkipList implements Serializable{

	Integer head;
	NodeSkipList[] rep;

	public NodeSkipList(Integer head, int n) {
		rep = new NodeSkipList[n];
		this.head = head;
	}

}
