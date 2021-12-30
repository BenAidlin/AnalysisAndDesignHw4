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
    public static Scanner input = new Scanner(java.lang.System.in);

    /**
     * Initialize the park with 3 devices "Mamba Ride", "Giant Wheel", "Carrousel"
     */
    // Methods
    public static void init() {
        Device mambaRide = new ExtremeDevice("Mamba Ride",
                12,-1, (float) 1.4, 2);
        Device giantWheel = new Device("Giant Wheel",
                -1, -1, -1, 0);
        Device carrousel = new Device("Carrousel",
                8, -1, -1, 20000);
        eParkDevices.add(mambaRide); eParkDevices.add(giantWheel); eParkDevices.add(carrousel);
        systemObjects.add(mambaRide); systemObjects.add(giantWheel); systemObjects.add(carrousel);
    }

    /**
     * Interact with user as long as he doesn't exit
     */
    public static void Ui(){
        while (!System.exit) {
            String input = getInput("Welcome to ePark!\n" +
                    "1.\tRegister child\n" +
                    "2.\tManage ticket (Add/Remove ride)\n" +
                    "3.\tExit park\n" +
                    "4.\tExit", new String[]{"1","2","3","4"});
            if(input!=null) // valid input
                System.handleInput(Integer.parseInt(input));
        }
    }

    /**
     * @param messageBeforeInput string printed before inputting. Null meaning don't print anything.
     * @param mustBe string array containing all valid inputs user should insert. Null meaning no restrains on user input.
     * @return a string the user inputted. If the string was invalid returns null.
     */
    public static String getInput(String messageBeforeInput, String[] mustBe){
        if(messageBeforeInput!=null) java.lang.System.out.println(messageBeforeInput);
        String in = input.next();
        if(mustBe==null) return in;
        String mustBeAsString = "";
        for (String str:mustBe) {
            if(str.equals(in)) return in;
            mustBeAsString += ", " +str;
        }
        java.lang.System.out.println("Invalid input, input must be in " + mustBeAsString.substring(1) +"\nYou inserted " + in);
        return null;
    }

    /**
     * @param userChoice int representing the user choice of action.
     * This method only proceeds to call the relevant method according to user's choice.
     */
    public static void handleInput(int userChoice){
        //System.PrintObjects(); // TODO: Remove this!!!

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

    /**
     * @param guardianId
     * @return a registered guardian that holds that ID, or null if such guardian doesn't exist
     */
    private static Guardian findGuardian(String guardianId){
        Guardian guardian = null;
        for (Guardian g:
                guardianList) {
            try {
                if (g.getID() == Integer.parseInt(guardianId)) guardian = g;
            }
            catch (Exception e) {java.lang.System.out.println("Guardian's ID must be a number"); }
        }
        return guardian;
    }

    /**
     * request appUser to remove and remove it. If the appUser is the guardians only one, the guardian will be removed as well.
     */
    private static void ExitPark() {

        Guardian guardian = System.findGuardian(System.getInput("Please insert guardian ID", null));
        if(guardian == null){
            java.lang.System.out.println("No such guardian in the system."); return;
        }
        String appUserToDelete = System.getInput("Please insert appUser to remove", null);
        String sure = System.getInput("Are you sure? App user will be removed from the system..." +
                "\n Press Y if you are sure, and N to cancel.", new String[]{"Y", "N"});
        if(sure==null) return;
        if(sure.equals("N")){
            java.lang.System.out.println("We knew you wouldn't leave!"); return;
        }
        // remove appusers, entries, eticket, visitor
        for (AppUser au: guardian.getAppUsers()) {
            if(String.valueOf(au.getID()).equals(appUserToDelete)) {
                for (Entry e : au.getETicket().getEntryList()) {
                    systemObjects.remove(e);
                }
                systemObjects.remove(au.getETicket());
                systemObjects.remove(au.getVisitor());
                systemObjects.remove(au);
                guardian.getAppUsers().remove(au);
                if(guardian.getAppUsers().size()==0) {
                    java.lang.System.out.println("Last app user.. removing guardian as well..");
                    systemObjects.remove(guardian.getBillingAccount());
                    systemObjects.remove(guardian);
                    guardianList.remove(guardian);
                }
                java.lang.System.out.println("Successfully removed.. your left balance is " + guardian.getBillingAccount().getLimitLeft() +
                        "\n See ya next time!");
                return;
            }
        }
        java.lang.System.out.println("No such App user.. try again");

    }

    /**
     * @param appUser app user to remove rides from.
     */
    private static void RemoveRide(AppUser appUser) {
        while(true) {
            java.lang.System.out.println("Which device would you like to remove?");
            java.lang.System.out.println("please choose the device by inserting the device number, or E to exit");
            for (int i = 0; i < appUser.getETicket().getEntryList().size(); i++)
                java.lang.System.out.println(i + ") " + appUser.getETicket().getEntryList().get(i).getDevice().getName());
            String choice = getInput(null,null);
            if(choice.equals("E"))break;
            try{
                int iChoice = Integer.parseInt(choice);
                Entry chosenEntry = appUser.getETicket().getEntryList().get(iChoice);
                Device chosenDevice = appUser.getETicket().getEntryList().get(iChoice).getDevice();
                int iEntries = Integer.parseInt(System.getInput("please choose the amount of entries you want to remove",null));
                if(iEntries > chosenEntry.getEntriesLeft()) java.lang.System.out.println("You don't have that much..");
                else if(iEntries < 0) java.lang.System.out.println("Please.. be serious..");
                else {
                    Entry toRem = appUser.getETicket().RemoveEntries(chosenDevice, iEntries);
                    appUser.getGuardian().getBillingAccount().setLimitLeft(appUser.getGuardian().getBillingAccount().getLimitLeft()+chosenDevice.getCost()*iEntries);
                    if(toRem!=null) systemObjects.remove(toRem);
                    java.lang.System.out.println("Successfully removed!");

                }
            }
            catch (Exception e){
                java.lang.System.out.println("Invalid input, try again"); continue;
            }
        }
    }

    /**
     * @param appUser to add rides to.
     */
    private static void AddRide(AppUser appUser) {
        Visitor visitor = appUser.getVisitor();
        List<Device> allowedDevices = new ArrayList<>();
        for (Device device: eParkDevices) {
            if(device.getMinAge()<visitor.getAge() && device.getMinHeight()<visitor.getHeight() && device.getMinWeight()< visitor.getWeight())
                allowedDevices.add(device);
        }
        while(true) {
            java.lang.System.out.println("Allowed devices for your child:");
            for (int i = 0; i < allowedDevices.size(); i++)
                java.lang.System.out.println(i + ") " + allowedDevices.get(i).getName() + ", costs: " + allowedDevices.get(i).getCost());
            String choice = System.getInput("please choose the device by inserting the device number, or E to exit",null);
            if(choice.equals("E")) break;
            try{
                int iChoice = Integer.parseInt(choice);
                Device chosenDevice = allowedDevices.get(iChoice);
                if(!chosenDevice.GivePermission()) {java.lang.System.out.println("Not allowed");continue;};
                int iEntries = Integer.parseInt(System.getInput("How many entries would you like to add?", null));
                if(chosenDevice.getCost()*iEntries>appUser.getGuardian().getBillingAccount().getLimitLeft()){
                    java.lang.System.out.println("Too much for your limit...");
                    continue;
                }
                appUser.getGuardian().getBillingAccount().setLimitLeft(appUser.getGuardian().getBillingAccount().getLimitLeft()-chosenDevice.getCost()*iEntries);
                Entry entryIfAdded = appUser.getETicket().AddEntries(chosenDevice, iEntries);
                if(entryIfAdded!=null) systemObjects.add(entryIfAdded);
                java.lang.System.out.println("Successfully added! Have fun!");
            }
            catch (Exception e){
                java.lang.System.out.println("Invalid input... Try again"); continue;
            }
        }

    }

    /**
     * either add or remove entries from an appUser selected by the guardian, after he was verified.
     */
    private static void ManageTicket() {
        Guardian guardian = System.findGuardian(System.getInput("Please insert guardian ID", null));
        if(guardian == null){
            java.lang.System.out.println("No such guardian in the system. Please register through option 1");
            return;
        }
        int userID = -1;
        try{userID =Integer.parseInt(System.getInput("Please insert your app user id(you got it on registry)", null));}
        catch (Exception e) {java.lang.System.out.println("Invalid username"); return;}
        String password = System.getInput("Please insert your password(you selected on registry)", null);
        boolean found = false;
        for (AppUser appUser:
             guardian.getAppUsers()) {
            if(appUser.getID() == userID && appUser.getPassword().equals(password)){
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

    /**
     * insert a new visitor in the system through the guardian.
     */
    private static void RegisterChild() {
        Guardian activeGuardian = findGuardianOrCreate();
        if(activeGuardian==null) return;
        java.lang.System.out.println("Please fill the form: \n");
        String password = System.getInput("Choose password:", null);
        String age = System.getInput("Enter visitor age:", null);

        Visitor visitor = new Visitor(Integer.parseInt(age),
                Guardian.measureChildWeight(), Guardian.measureChildHeight(), activeGuardian);

        ETicket eTicket = new ETicket("Park", "2020/12/12", false);
        AppUser appUser = new AppUser(password, activeGuardian,
                eTicket, visitor);
        java.lang.System.out.println("Important!!! Your app user id is "+appUser.getID());
        systemObjects.add(visitor);systemObjects.add(appUser);systemObjects.add(eTicket);
        activeGuardian.getAppUsers().add(appUser);
    }

    /**
     * @return an existing guardian if he is in the system, or creates a new one and returns it.
     */
    private static Guardian findGuardianOrCreate(){
        String guardianId = System.getInput("Please insert guardian ID",null);
        Guardian guardian =System.findGuardian(guardianId);
        if(guardian!=null) return guardian;
        return CreateGuardian(guardianId);
    }

    /**
     * @param guardianId an id the created guardian will have
     * @return the created guardian object.
     */
    private static Guardian CreateGuardian(String guardianId){
        String creditCardNumber = System.getInput("Welcome!\n" +
                "Please insert billing account number", null);
        String expirationDate = System.getInput("Please insert expiration date",null);
        String billingPassword = System.getInput("Please insert billing password", null);
        int limit = -1;
        try{limit = Integer.parseInt(System.getInput("Please insert billing limit", null));}
        catch (Exception e){java.lang.System.out.println("Invalid input.. Please try again"); return null;}
        java.lang.System.out.println("Waiting for credit card approval");
        if (!CreditCardCompany.getApproval())java.lang.System.out.println("Not approved, sorry...");;
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