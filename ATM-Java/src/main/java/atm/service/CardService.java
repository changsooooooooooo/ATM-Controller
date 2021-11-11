package atm.service;

import atm.entity.Card;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    private CardRepository cardRepository;

    public Optional<Card> isCorrectPinNumber(String cardId, long pinNumber) {
        return cardRepository.findCardByCardIdAndPinNumber(cardId, pinNumber);
    }
}
