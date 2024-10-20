package Service;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }
    
    // For testing/mocking
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message) {

        if (accountDAO.getAccountByAccountID(message.getPosted_by()) == null) {
            return null;
        }
        
        if (message.getMessage_text().equals("")) {
            return null;
        }

        if (message.getMessage_text().length() > 254) {
            return null;
        }
        
        return this.messageDAO.insertMessage(message);
    }
}
