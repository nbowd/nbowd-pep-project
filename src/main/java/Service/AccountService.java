package Service;

import java.util.List;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    // For testing/mocking
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public List<Account> getAllAccounts() {
        return this.accountDAO.getAllAccounts();
    }

    public Account addAccount(Account account) {
        // Checks for duplicate usernames
        if (this.accountDAO.getAccountByUsername(account.getUsername()) != null) {
            return null;
        }
        // Checks for blank usernames
        if (account.getUsername().equals("")) {
            return null;
        }
        // Checks for password length at least 4 chars.
        if (account.getPassword().length() < 4) {
            return null;
        }

        return this.accountDAO.insertAccount(account);
    }
}
