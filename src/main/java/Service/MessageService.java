package Service;

import java.util.List;

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

    public List<Message> getAllMessages() {
        return this.messageDAO.getAllMessages();
    }

    public Message getMessageByID(int message_id) {
        return this.messageDAO.getMessageByID(message_id);
    }

    public List<Message> getMessagesByAccount(int account_id) {
        return this.messageDAO.getMessagesByAccount(account_id);
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

    public Message deleteMessageByID(int message_id) {
        return this.messageDAO.deleteMessageByID(message_id);
    }

    public Message updateMessageByID(int message_id, String message_text) {
        if (messageDAO.getMessageByID(message_id) == null) {
            return null;
        }

        if (message_text.equals("")) {
            return null;
        }

        if (message_text.length() > 255) {
            return null;
        }
        
        return this.messageDAO.updateMessageByID(message_id, message_text);
    }
}
