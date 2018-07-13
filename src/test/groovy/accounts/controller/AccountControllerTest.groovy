package accounts.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.test.account.AccountController
import com.test.model.AccountDTO
import com.test.service.AccountService
import com.test.util.AsyncDeclarationsTask
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Subject

class AccountControllerTest extends Specification{

    public final static String ACCOUNTS="/account/create";

    @Subject
    AccountController accountController;

    AccountService accountService =Mock();
    AsyncDeclarationsTask declarationsTask=Mock();
    MockHttpServletRequest mockHttpServletRequest = Mock();
    AccountDTO accountDTO;
    MockMvc mockMvc;
    ObjectMapper mapper;

    def setup(){

        accountController = new AccountController(
                accountService: accountService,
                declarationsTask: declarationsTask)
        accountDTO = new AccountDTO(
                guestid: "2323",
                firstName: "Dharam",
                lastName: "Kushwaha",
                email: "dharam@gmail.com",
                mobile: "8888888888",
                passsword: "testing123"
        )

        mapper = new ObjectMapper()
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build()
    }
    def "Should Respond back with 201 for successful account creation"(){

        when:"User calls the Create end point with valid user details"
        MvcResult result = mockMvc.perform(post(ACCOUNTS)
                .content(mapper.writeValueAsString(accountDTO))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn()

        then: "A new guest Account is created"
        1 * accountService.createAccount(accountDTO)
    }
}
