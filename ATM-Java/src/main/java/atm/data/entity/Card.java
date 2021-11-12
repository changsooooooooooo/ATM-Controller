package atm.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "card_id")
    private String cardId;

    @Column(name = "pin_number")
    private Long pinNumber;

    @OneToMany(mappedBy = "card", targetEntity = Account.class)
    private final Set<Account> accountSet = new LinkedHashSet<>();

    public void updateAccount(Account account) {
        for(Account acc : accountSet) {
            if(acc.getId() == account.getId()){
                accountSet.remove(acc);
                accountSet.add(account);
                break;
            }
        }
    }
}
