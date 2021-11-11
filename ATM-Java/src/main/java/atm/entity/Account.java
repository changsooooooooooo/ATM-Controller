package atm.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "account_bank")
    private AccountBank accountBank;

    @Column(name="balance")
    private Long balance;

    public void updateBalance (Long money) {
        balance += money;
        card.updateAccount(this);
    }
}
