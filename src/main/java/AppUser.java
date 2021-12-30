public class AppUser {
    static int IdCounter = 0;
    private final Visitor visitor;
    private int ID;
    private String password;
    private Guardian guardian;
    private ETicket eTicket;

    public AppUser(String password, Guardian guardian, ETicket eTicket, Visitor visitor) {
        this.ID = ++IdCounter;
        this.password = password;
        this.guardian = guardian;
        this.eTicket = eTicket;
        this.visitor = visitor;
    }

    public Visitor getVisitor() {
        return visitor;
    }    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public ETicket getETicket() {
        return eTicket;
    }

    public void setETicket(ETicket eTicket) {
        this.eTicket = eTicket;
    }
}
