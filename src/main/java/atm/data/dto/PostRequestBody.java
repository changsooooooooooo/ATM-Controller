package atm.data.dto;

import atm.data.entity.AccountBank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
