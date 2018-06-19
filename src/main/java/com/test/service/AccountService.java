package com.test.service;

import com.test.model.AccountDTO;
import com.test.model.AccountResponseDTO;

public interface AccountService {

     AccountResponseDTO getProfile(int guestId);
     AccountResponseDTO createAccount(AccountDTO accountDTO);
     AccountResponseDTO updateProfile(AccountDTO accountDTO);
     void deleteProfile(int guestid);

}
