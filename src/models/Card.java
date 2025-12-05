package models;

public class Card {

    private Long id;
    private String cardNumber;
    private String bankName;
    private Long balance;
    private Long userId;

    public Card () {

    }

    public Card(String cardNumber, String bankName, Long balance, Long userId) {
        this.cardNumber = cardNumber;
        this.bankName = bankName;
        this.balance = balance;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", card number='" + cardNumber + '\'' +
                ", bank name='" + bankName + '\'' +
                ", balance=" + balance +
                ", user id=" + userId +
                '}';
    }
}
