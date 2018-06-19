package com.test.service;

import com.test.model.AccountDTO;
import com.test.model.AccountResponseDTO;
import com.test.repository.AccountRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepositoryImpl accountRepository;



    public AccountResponseDTO getProfile(int guestId){

        AccountDTO accountDTO =accountRepository.getProfile(guestId);
        return buildAccountResponseDTO(accountDTO);
    }
    public AccountResponseDTO createAccount(AccountDTO accountDTO){


        System.out.println("AccountServiceImpl  "+Thread.currentThread().getName()+" ---- ");

        return accountRepository.createAccount(accountDTO,60);
    }

    public AccountResponseDTO updateProfile(AccountDTO accountDTO){
                accountDTO.setCreateTimestamp(getCurrenttimestamp());
                log.info("updated Timestamp {}",accountDTO.getCreateTimestamp());
        return  buildAccountResponseDTO(accountRepository.updateProfile(accountDTO));
    }
    public void deleteProfile(int guestid){
        accountRepository.deleteProfile(guestid);
    }

    private Date getCurrenttimestamp(){
        return new Date();
    }

    private AccountResponseDTO buildAccountResponseDTO(AccountDTO accountDTO){
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setEmail(accountDTO.getEmail());
        accountResponseDTO.setFirstName(accountDTO.getFirstName());
        accountResponseDTO.setLastName(accountDTO.getLastName());
        accountResponseDTO.setGuestid(accountDTO.getGuestid());
        accountResponseDTO.setMobile(accountDTO.getMobile());
        return accountResponseDTO;
    }


}
