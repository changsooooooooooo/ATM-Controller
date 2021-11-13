package atm.controller;

import atm.data.dto.PostRequestBody;
import atm.data.entity.AccountBank;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ATMControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Controller Method getAccount Failed By PW Exception")
    void getAccountFailureTest1() throws Exception {

        PostRequestBody postRequestBody = PostRequestBody.builder()
                .accountBank(AccountBank.KB)
                .pinNumber(1236L)
                .money(0L)
                .build();

        String requestBody = new ObjectMapper().writeValueAsString(postRequestBody);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/show")
                .queryParam("id", "card_1")
                .content(requestBody)
                .contentType("application/json")
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.response.message", is("Not Correct PassWord")))
                .andDo(print());

    }

    @Test
    @DisplayName("Controller Method getAccount Failed By Cannot Find Account")
    void getAccountFailureTest2() throws Exception {

        PostRequestBody postRequestBody = PostRequestBody.builder()
                .accountBank(AccountBank.KB)
                .pinNumber(1234L)
                .money(0L)
                .build();

        String requestBody = new ObjectMapper().writeValueAsString(postRequestBody);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/show")
                        .queryParam("id", "card_1")
                        .content(requestBody)
                        .contentType("application/json")
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.response.message", is("No Matching Account For RequestBody")))
                .andDo(print());

    }

    @Test
    @DisplayName("Controller Method getAccount Success")
    void getAccountSuccessTest() throws Exception {

        PostRequestBody postRequestBody = PostRequestBody.builder()
                .accountBank(AccountBank.SHINHAN)
                .pinNumber(1234L)
                .money(0L)
                .build();

        String requestBody = new ObjectMapper().writeValueAsString(postRequestBody);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/show")
                        .queryParam("id", "card_1")
                        .content(requestBody)
                        .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.currentMoney", is(100)))
                .andDo(print());

    }

    @Test
    @Transactional
    @DisplayName("Controller Method doDeposit Success")
    void doDepositTest() throws Exception {

        PostRequestBody postRequestBody = PostRequestBody.builder()
                .accountBank(AccountBank.SHINHAN)
                .pinNumber(1234L)
                .money(10L)
                .build();

        String requestBody = new ObjectMapper().writeValueAsString(postRequestBody);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/deposit")
                        .queryParam("id", "card_1")
                        .content(requestBody)
                        .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.currentMoney", is(110)))
                .andDo(print());

    }

    @Test
    @DisplayName("Controller Method doWithdraw Failure")
    void doWithdrawFailureTest() throws Exception {

        PostRequestBody postRequestBody = PostRequestBody.builder()
                .accountBank(AccountBank.SHINHAN)
                .pinNumber(1234L)
                .money(110L)
                .build();

        String requestBody = new ObjectMapper().writeValueAsString(postRequestBody);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/withdraw")
                        .queryParam("id", "card_1")
                        .content(requestBody)
                        .contentType("application/json")
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.response.message", is("Cannot Withdraw Money")))
                .andDo(print());

    }

    @Test
    @Transactional
    @DisplayName("Controller Method doWithdraw Success")
    void doWithdrawSuccessTest() throws Exception {

        PostRequestBody postRequestBody = PostRequestBody.builder()
                .accountBank(AccountBank.SHINHAN)
                .pinNumber(1234L)
                .money(75L)
                .build();

        String requestBody = new ObjectMapper().writeValueAsString(postRequestBody);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/withdraw")
                        .queryParam("id", "card_1")
                        .content(requestBody)
                        .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.currentMoney", is(25)))
                .andDo(print());

    }
}
