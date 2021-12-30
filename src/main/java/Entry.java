public class Entry {
    private Device device;
    private ETicket eTicket;
    private int entriesLeft;

    public Entry(Device device, ETicket eTicket, int entriesLeft) {
        this.device = device;
        this.eTicket = eTicket;
        this.entriesLeft = entriesLeft;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public ETicket geteTicket() {
        return eTicket;
    }

    public void seteTicket(ETicket eTicket) {
        this.eTicket = eTicket;
    }

    public int getEntriesLeft() {
        return entriesLeft;
    }

    public void setEntriesLeft(int entriesLeft) {
        this.entriesLeft = entriesLeft;
    }
}
