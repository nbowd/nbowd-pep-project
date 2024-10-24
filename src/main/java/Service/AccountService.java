package Service;

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

    public Account loginAccount(Account account) {
        Account storedAccount = this.accountDAO.getAccountByUsername(account.getUsername());
        // Checks if account is valid
        if (storedAccount == null) {
            return null;
        }
        // Checks for matching password
        if (!storedAccount.getPassword().equals(account.getPassword())) {
            return null;
        }

        return storedAccount;
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
