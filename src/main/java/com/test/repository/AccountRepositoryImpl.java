package com.test.repository;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.test.config.cassandra.BasicCurdOperations;
import com.test.model.AccountDTO;
import com.test.model.AccountResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class AccountRepositoryImpl extends BasicCurdOperations<AccountDTO> implements AccountRepository {

    @Autowired
    AccountRepositoryImpl(MappingManager mappingManager)
    {
        super(mappingManager);
    }
    public AccountDTO getProfile(int guestId){

        AccountDTO accountDTO= curd.get(guestId);

        return accountDTO;
    }

    public AccountResponseDTO createAccount(AccountDTO accountDTO,int ttl){

        curd.save(accountDTO, Mapper.Option.ttl(ttl));
        log.info("Record has been saved..........");
        return null;
    }
    public AccountDTO updateProfile(AccountDTO accountDTO){
       curd.save(accountDTO);
        return curd.get(accountDTO.getGuestid());
    }
    public void deleteProfile(int guestid){
            curd.delete(guestid);
            log.info("Account Successfully Deleted with {}",guestid);
    }

}
