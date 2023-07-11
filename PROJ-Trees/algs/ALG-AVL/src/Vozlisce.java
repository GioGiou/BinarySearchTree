import java.io.Serializable;
public class Vozlisce implements Serializable{
    public Integer st;
	public Vozlisce Levo, Desno;
    public int visina;

    public Vozlisce(Integer st) {
        this.st = st;
        this.Levo = null;
        this.Desno = null;
        this.visina=0;
    }
    public Vozlisce() {
        this.st = null;
        this.Levo = null;
        this.Desno = null;
        this.visina=0;
    }
    protected Integer find(Integer elt) {
        if (st.intValue() == elt.intValue()) {
			return this.st;
		} else if (st.intValue() > elt.intValue()) {
			if (Levo == null) {
				return null;
			} else {
				return Levo.find(elt);
			}
		} else if (st.intValue() < elt.intValue()) {
			if (Desno == null) {
				return null;
			} else {
				return Desno.find(elt);
			}
		}
		return null;
    }
}