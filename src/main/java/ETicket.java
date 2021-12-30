import java.util.ArrayList;
import java.util.List;

public class ETicket {
    private String location;
    private String expirationTime;
    private boolean extremeAllowed;
    private List<Entry> entryList = new ArrayList<>();
    public ETicket(String location, String expirationTime, boolean extremeAllowed) {
        this.location = location;
        this.expirationTime = expirationTime;
        this.extremeAllowed = extremeAllowed;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isExtremeAllowed() {
        return extremeAllowed;
    }

    public void setExtremeAllowed(boolean extremeAllowed) {
        this.extremeAllowed = extremeAllowed;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public void DisplayEticket() {
        if (entryList.isEmpty()){
            java.lang.System.out.println("eTicket is empty"); return;
        }
        for (Entry entry:
             entryList) {
            java.lang.System.out.println("Device: "+entry.getDevice().getName()+", Entries: " + entry.getEntriesLeft());
        }
    }
}
