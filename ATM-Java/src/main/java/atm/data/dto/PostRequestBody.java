package atm.data.dto;

import atm.data.entity.AccountBank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class PostRequestBody {

    @NotNull
    @JsonProperty(value = "pin_number")
    private Long pinNumber;

    @NotNull
    @JsonProperty(value = "account_bank")
    private AccountBank accountBank;

    @NotNull
    @Min(value = 0)
    private Long money;

    public PostRequestBody () {

    }
}
