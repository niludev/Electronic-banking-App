package config;

import repository.CardRepository;
import repository.TransactionRepository;
import repository.UserRepository;
import service.CardService;
import service.TransactionService;
import service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ApplicationContext {

    private static ApplicationContext instance;
    private Connection connection;


    // Repositories
    private UserRepository userRepository;
    private CardRepository cardRepository;
    private TransactionRepository transactionRepository;

    // Services
    private UserService userService;
    private CardService cardService;
    private TransactionService transactionService;

    private ApplicationContext() {}

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        ApplicationProperties.DATABASE_URL,
                        ApplicationProperties.DATABASE_USER,
                        ApplicationProperties.DATABASE_PASSWORD
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }

    // ---------- Repositories ----------

//    lazy load:

    public UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public CardRepository getCardRepository() {
        if (cardRepository == null) {
            cardRepository = new CardRepository();
        }
        return cardRepository;
    }

    public TransactionRepository getTransactionRepository() {
        if (transactionRepository == null) {
            transactionRepository = new TransactionRepository();
        }
        return transactionRepository;
    }

    // ---------- Services ----------

    public UserService getUserService() {
        if(userService == null) {
            userService = new UserService(getUserRepository());
        }
        return userService;
    }

    public CardService getCardService() {
        if (cardService == null) {
            cardService = new CardService(getCardRepository());
        }
        return cardService;
    }

    public TransactionService getTransactionService() {
        if (transactionService == null) {
            transactionService = new TransactionService(getTransactionRepository(), getCardRepository());
        }
        return transactionService;
    }
}
