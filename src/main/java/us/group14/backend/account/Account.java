package us.group14.backend.account;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import us.group14.backend.transaction.Transaction;
import us.group14.backend.user.User;

import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private Double balance;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "Transaction")
    private Set<Transaction> transactions;

    public Account() {
        this.balance = 0d;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void withdraw(double amount) {
        this.balance = this.balance - amount;
        System.out.println("WITHDRAW " + this.balance);
   }

    public void deposit(double amount) {
        this.balance = this.balance + amount;
        System.out.println("DEPOSIT " + this.balance);
    }

    public void complete(Transaction transaction) {
        switch (transaction.getType()) {
            case DEPOSIT -> {
                this.deposit(transaction.getAmount());
            }
            case WITHDRAW -> {
                this.withdraw(transaction.getAmount());
            }
            case TRANSFER -> {
                if (transaction.getSender().equals(this)) {
                    this.withdraw(transaction.getAmount());
                } else if (transaction.getRecipient().equals(this)) {
                    this.deposit(transaction.getAmount());
                }
            }
        }

        this.addTransaction(transaction);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", user=" + user.getId() +
                ", transactions=" + transactions +
                '}';
    }
}
