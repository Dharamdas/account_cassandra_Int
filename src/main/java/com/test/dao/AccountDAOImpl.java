package com.test.dao;

import com.test.model.AccountDTO;
import com.test.model.AccountResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class AccountDAOImpl implements AccountDAO{

    public  AccountResponseDTO getProfile(int guestId){
        return null;
    }
   public AccountResponseDTO createAccount(AccountDTO accountDTO, int ttl){
        return null;
    }
}
