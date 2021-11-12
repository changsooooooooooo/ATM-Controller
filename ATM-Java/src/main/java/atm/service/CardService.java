package atm.service;

import atm.data.entity.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public Optional<Card> isCorrectPinNumber(String cardId, Long pinNumber) {
        return cardRepository.findCardByCardIdAndPinNumber(cardId, pinNumber);
    }
}
