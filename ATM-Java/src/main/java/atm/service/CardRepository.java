package atm.service;

import atm.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findCardByCardIdAndPinNumber(String cardId, Long pinNumber);
}
