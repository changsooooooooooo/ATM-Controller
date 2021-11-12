package atm.data.entity;

import atm.Exception.BalanceIsLittleThanMoney;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@ToString(exclude = {"card"})
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @NotNull
    @Column(name = "account_bank")
    private AccountBank accountBank;

    @NotNull
    @Column(name="balance")
    private Long balance;

    public void updateBalance (Long money) throws Exception {
        if(money<0 && balance + money < 0) {
            throw new BalanceIsLittleThanMoney("Cannot Withdraw Money");
        }
        balance += money;
        card.updateAccount(this);
    }
}
