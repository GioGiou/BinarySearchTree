import java.io.Serializable;
public class Vozlisce implements Serializable{
    public Integer st;
	public Vozlisce Levo, Desno,Stars;
    public int barva; // 1 = rdeče, 0 = črno


    public Vozlisce(Integer st) {
        this.st = st;
        this.Levo = null;
        this.Desno = null;
        this.barva = 0;
    }
    public Vozlisce() {
        this.st = null;
        this.Levo = null;
        this.Desno = null;
        this.barva = 0;
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