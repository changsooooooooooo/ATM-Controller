package atm.data.dto;

import atm.data.entity.AccountBank;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class PostRequestBody {

    @NotEmpty
    @Size(min = 1)
    private Long pinNumber;

    @NotEmpty
    @Size(min = 1)
    private AccountBank accountBank;

    private Long money;

    public PostRequestBody () {

    }
}
