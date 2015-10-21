package samples.datastructures;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Miguel Reyes
 *         Date: 10/20/15
 *         Time: 1:57 PM
 */
public class AccountManager {

    private Map<String, Integer> accountTotals = new HashMap<>();
    private int retirementFund;

    public int getBalance(String accountName) {
        Integer total = accountTotals.get(accountName);
        if(total == null) {
            total = 0;
        }
        return total;
    }

    public void setBalance(String accountName, int amount) {
        accountTotals.put(accountName, amount);

    }

    public static void main(String[] args) {
        AccountManager am = new AccountManager();
        am.setBalance("1234", 2000);
        int balance = am.getBalance("1234");
        System.out.println("balance = " + balance);
    }

}
