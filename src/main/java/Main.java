import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.init(); // Initialize the system before presenting the UI.
        System.Ui();
    }
}

class System {
    // Fields
    public static List<Object> systemObjects = new ArrayList<>();
    public static boolean exit = false;
    public static List<Device> eParkDevices = new ArrayList<>();
    public static List<Guardian> guardianList = new ArrayList<>();

    // Methods
    public static void init() {
        Device mambaRide = new ExtremeDevice("Mamba Ride",
                12,-1, (float) 1.4, 0);
        Device giantWheel = new Device("Giant Wheel",
                -1, -1, -1, 0);
        Device carrousel = new Device("Carrousel",
                8, -1, -1, 0);
        eParkDevices.add(mambaRide); eParkDevices.add(giantWheel); eParkDevices.add(carrousel);
        systemObjects.add(mambaRide); systemObjects.add(giantWheel); systemObjects.add(carrousel);
    }
    public static void Ui(){
        while (!System.exit) {
            java.lang.System.out.println("Welcome to ePark!\n" +
                    "1.\tRegister child\n" +
                    "2.\tManage ticket (Add/Remove ride)\n" +
                    "3.\tExit park\n" +
                    "4.\tExit");
            System.handleInput(System.getInput());
        }
    }
    public static int getInput(){
        Scanner input = new Scanner(java.lang.System.in);
        int userChoice = -1;
        try {
            userChoice = input.nextInt();
        }
        catch (InputMismatchException e){
            java.lang.System.out.println("Please enter a number");
        }
        return userChoice;
    }
    public static void handleInput(int userChoice){
        System.PrintObjects(); // TODO: Remove this!!!

        switch(userChoice){
            case 1:
                RegisterChild();
                break;
            case 2:
                ManageTicket();
                break;
            case 3:
                ExitPark();
                break;
            case 4:
                System.exit = true;
                break;
        }
    }

    private static void ExitPark() {
    }

    private static void RemoveRide(AppUser appUser) {
    }

    private static void AddRide(AppUser appUser) {
        java.lang.System.out.println("");
    }

    private static void ManageTicket() {
        java.lang.System.out.println("Please insert guardian ID");
        Scanner input = new Scanner(java.lang.System.in);
        String guardianId = input.next();
        Guardian guardian = null;
        for (Guardian g:
                guardianList) {
            if(g.getID() == Integer.parseInt(guardianId)) guardian = g;
        }
        if(guardian == null){
            java.lang.System.out.println("No such guardian in the system. Please register through option 1");
            return;
        }
        java.lang.System.out.println("Please insert your app user id(you got it on registry)");
        String userID = input.next();
        java.lang.System.out.println("Please insert your password(you selected on registry)");
        String password = input.next();
        boolean found = false;
        for (AppUser appUser:
             guardian.getAppUsers()) {
            if(appUser.getID() == Integer.parseInt(userID) && appUser.getPassword().equals(password)){
                found = true;
                java.lang.System.out.println("Your app user's e-Ticket:");
                appUser.getETicket().DisplayEticket();
                java.lang.System.out.println("would you like to add or remove? 1=add, 2=remove");
                String addOrRemove = input.next();
                if(addOrRemove.equals("1")) AddRide(appUser);
                else if(addOrRemove.equals("2")) RemoveRide(appUser);
                else java.lang.System.out.println("wrong input, try again");
            }
        if(!found) java.lang.System.out.println("Wrong username or password. Please retry.");
        }
    }

    private static void RegisterChild() {
        Guardian activeGuardian = findGuardianOrCreate();
        java.lang.System.out.println("Please fill the form: \n");
        java.lang.System.out.println("Choose password:");
        Scanner input = new Scanner(java.lang.System.in);
        String password = input.next();
        java.lang.System.out.println("Enter visitor age:");
        String age = input.next();

        Visitor visitor = new Visitor(Integer.parseInt(age),
                Guardian.measureChildWeight(), Guardian.measureChildHeight(), activeGuardian);

        ETicket eTicket = new ETicket("Park", "2020/12/12", false);
        AppUser appUser = new AppUser(password, activeGuardian,
                eTicket, visitor);
        java.lang.System.out.println("Your app user id is "+appUser.getID());
        systemObjects.add(visitor);systemObjects.add(appUser);systemObjects.add(eTicket);
        activeGuardian.getAppUsers().add(appUser);
    }
    private static Guardian findGuardianOrCreate(){
        java.lang.System.out.println("Please insert guardian ID");
        Scanner input = new Scanner(java.lang.System.in);
        String guardianId = input.next();

        for (Guardian g:
                guardianList) {
            if(g.getID() == Integer.parseInt(guardianId)) return g;
        }

        return CreateGuardian(guardianId);
    }
    private static Guardian CreateGuardian(String guardianId){
        java.lang.System.out.println("Welcome!\n" +
                "Please insert billing account number");
        Scanner input = new Scanner(java.lang.System.in);
        String creditCardNumber = input.next();
        java.lang.System.out.println("Please insert expiration date");
        String expirationDate = input.next();
        java.lang.System.out.println("Please insert billing password");
        String billingPassword = input.next();
        java.lang.System.out.println("Please insert billing limit");
        int limit = Integer.parseInt(input.next());
        java.lang.System.out.println("Waiting for credit card approval");
        if (!CreditCardCompany.getApproval())java.lang.System.out.println("Waiting for credit card approval");;
        BillingAccount billingAccount = new BillingAccount(Integer.parseInt(creditCardNumber), expirationDate, billingPassword, limit);
        Guardian guardian = new Guardian(Integer.parseInt(guardianId),
                billingAccount);
        guardianList.add(guardian);
        systemObjects.add(guardian);
        systemObjects.add(billingAccount);
        return guardian;

    }
    private static void PrintObjects(){
        for (Object o:
                System.systemObjects) {
            java.lang.System.out.println(o);
        }
    }
}

class CreditCardCompany{
    public static boolean getApproval(){
        java.lang.System.out.println("approved");
        return true;
    }
}