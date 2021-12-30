import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Guardian {
    private int ID;
    private BillingAccount billingAccount;
    private List<AppUser> appUsers = new ArrayList<>();

    public Guardian(int ID, BillingAccount billingAccount) {
        this.ID = ID;
        this.billingAccount = billingAccount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(List<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    public BillingAccount getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(BillingAccount billingAccount) {
        this.billingAccount = billingAccount;
    }

    public static float measureChildHeight(){
        java.lang.System.out.println("Please measure your child's height with the machine\n Press enter to measure");
        try {
            java.lang.System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        float measured = MeasureMachine.getHeight();
        java.lang.System.out.println("Measured Height: " + measured); return measured;
    }
    public static float measureChildWeight() {
        java.lang.System.out.println("Please measure your child's height with the machine\n Press enter to measure");
        try{java.lang.System.in.read();}catch (IOException e) {
            e.printStackTrace();
        }
        float measured = MeasureMachine.getWeight();
        java.lang.System.out.println("Measured Weight: " + measured); return measured;
    }
}
