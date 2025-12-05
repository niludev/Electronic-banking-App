package models;

public class PayaBatchItem {
    private String toCardNumber;
    private Long amount;

    public PayaBatchItem(String toCardNumber, Long amount) {
        this.toCardNumber = toCardNumber;
        this.amount = amount;
    }

    public String getToCardNumber() {
        return toCardNumber;
    }

    public Long getAmount() {
        return amount;
    }
}
