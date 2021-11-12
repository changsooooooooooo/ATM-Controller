package atm.data.dto;

import atm.data.entity.AccountBank;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;

@Getter
public class PostRequestBody {

    @NotNull
    private Long pinNumber;

    @NotNull
    private AccountBank accountBank;

    @Nullable
    private Long money;

    public PostRequestBody () {

    }
}
