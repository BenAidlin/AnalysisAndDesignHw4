import java.time.LocalDateTime;

public class BillingAccount {
    private int creditCardNumber;
    private String expirationDate;
    private String password;
    private float limit;
    public BillingAccount(int creditCardNumber, String expirationDate, String password, float limit) {
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.password = password;
        this.limit = limit;
    }
}
