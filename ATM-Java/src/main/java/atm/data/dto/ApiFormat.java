package atm.data.dto;

import atm.data.entity.AccountBank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiFormat {

    private boolean success;

    private Response response;

    @Getter
    @Builder
    static public class Response {

        private String message;

        private String cardId;

        private AccountBank accountBank;

        private Long currentMoney;
    }

    public ApiFormat(boolean success, Response response) {
        this.success = success;
        this.response = response;
    }
}
