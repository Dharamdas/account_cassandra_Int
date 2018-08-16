# account_cassandra_Int
to run this application install cassandra in local
use below commands:
### Cassandra install notes
cat /etc/issue
 echo "deb http://www.apache.org/dist/cassandra/debian 311x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list
 curl https://www.apache.org/dist/cassandra/KEYS | sudo apt-key add -
 sudo apt-get update
 sudo apt-get install cassandra  -y

## Provide below Credtional
  username: cassandra
  contactpoints: 127.0.0.1
  keyspace: test01
  password: cassandra
  
 Create key space with  test01
 use below command
 CREATE KEYSPACE test01 WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};

## Create Table

CREATE TABLE test01.account (
    guestid int PRIMARY KEY,
    createtimestamp timestamp,
    email text,
    firstname text,
    lastname text,
    mobile text,
    passsword text
)

## Execute Application

## Setup the app in Eclipse 
   Since Lambok is not fully supported by Eclipse need to patch eclipse to support fully follow below steps
   To Run in Eclipse:
   Steps:1 Down load Lambok Jar from https://projectlombok.org/downloads/lombok.jar
   Step:2 run the below command in CL
   java -jar lombok.jar
   Select Eclipse to install Lambok Support
   Step3: Restart eclipse and Machine
# Step:4 Clone project https://github.com/Dharamdas/account_cassandra_Int and import in Eclipse
# Step5: Refresh project (Project right click-- Gradle --> refresh)
# Step6: Project will display in Gradle Task (Build --> clean and Build)
# Step7: Run spring boot app (go to AccountApp.java - right clik and run as application)

## Test Case File


import com.test.account.AccountController;
import com.test.main.AccountApp;
import com.test.model.AccountDTO;
import com.test.model.AccountResponseDTO;
import com.test.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class, secure = false)
@ContextConfiguration(classes = AccountApp.class)
public class AccountControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService accountService;

    @Test
    public void createAccount()throws Exception{

        AccountDTO accountDTO = new AccountDTO().builder()
                .guestid(1010)
                .firstName("UnitTest")
                .lastName("Account")
                .email("account@gmail.com")
                .mobile("9988335566")
                .passsword("testing@123")
                .build();

        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setFirstName("Test");
        accountResponseDTO.setGuestid(1000);
        accountResponseDTO.setLastName("Kumar");
        accountResponseDTO.setEmail("kumar@gmail.com");

        Mockito.when(
                accountService.createAccount(Mockito.any(AccountDTO.class))).thenReturn(accountResponseDTO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8080/account/create")
                .accept(MediaType.APPLICATION_JSON).content(TestUtil.converObjectToJsonBytes(accountDTO))
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult responseResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = responseResult.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

}

## Test Util
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;

public class TestUtil {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    public static byte[] converObjectToJsonBytes(Object object) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    public static String createStringWithLenth(int length)
    {
        StringBuilder builder = new StringBuilder();
        for(int index=0;index<length;index++)
        {
            builder.append("a");
        }
        return builder.toString();
    }

}
