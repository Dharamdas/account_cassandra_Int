package com.test.dao;

import com.test.model.AccountDTO;
import com.test.model.AccountResponseDTO;

public interface AccountDAO {

    AccountResponseDTO getProfile(int guestId);
    AccountResponseDTO createAccount(AccountDTO accountDTO,int ttl);

}
