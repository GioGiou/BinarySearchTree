import java.io.Serializable;
public class Vozlisce implements Serializable{
    public Integer st;
	public Vozlisce Levo, Desno;
    public int rank;

    public Vozlisce(Integer st, int rank) {
        this.st = st;
        this.Levo = null;
        this.Desno = null;
        this.rank = rank;
    }
    public Vozlisce() {
        this.st = null;
        this.Levo = null;
        this.Desno = null;
        this.rank = 0;
    }
    
}