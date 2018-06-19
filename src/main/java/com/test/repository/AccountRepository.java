package com.test.repository;

import com.test.model.AccountDTO;
import com.test.model.AccountResponseDTO;

public interface AccountRepository {
    AccountDTO getProfile(int guestId);
    AccountResponseDTO createAccount(AccountDTO accountDTO,int ttl);
    AccountDTO updateProfile(AccountDTO accountDTO);
    void deleteProfile(int guestid);

}
