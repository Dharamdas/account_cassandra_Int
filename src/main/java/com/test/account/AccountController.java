package com.test.account;

import com.test.base.BaseController;
import com.test.model.AccountDTO;
import com.test.model.AccountResponseDTO;
import com.test.service.AccountService;
import com.test.util.AsyncDeclarationsTask;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController extends BaseController{

    @Autowired
    AccountService accountService;
    @Autowired
    AsyncDeclarationsTask declarationsTask;

    @RequestMapping(value = "/get/profile",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Guest Profile Info", notes = "Input Pram is guestId as Query String Format")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = "Successfull Retrival of Guest Profile",response = AccountResponseDTO.class),
            @ApiResponse(code=401,message = "Unauthrized Access"),
            @ApiResponse(code=400,message = "Malformed Request"),
            @ApiResponse(code=500,message = "Internal Error")
    })
    @ResponseBody
    public ResponseEntity getProfile(@RequestParam("guestid") int guestid){
      log.info("fetching profile info......for profile id {}",guestid);

      AccountResponseDTO accountResponseDTO = accountService.getProfile(guestid);


      return new ResponseEntity<>(accountResponseDTO,HttpStatus.FOUND);
    }

    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Guest Profile Info", notes = "Input Pram is as Query String Format")
    @ApiResponses(value = {
            @ApiResponse(code=201,message = "Successfull Creation of Account",response = AccountResponseDTO.class),
            @ApiResponse(code=401,message = "Unauthrized Access"),
            @ApiResponse(code=400,message = "Malformed Request"),
            @ApiResponse(code=500,message = "Internal Error")
    })
    @ResponseBody
    public ResponseEntity createAccount(@RequestBody AccountDTO accountDTO){
        log.info("Creating account with ...... id {}",accountDTO.getGuestid());
      //  AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        System.out.println("Start Calling Aync-1 Call");
        declarationsTask.task1(50);


       AccountResponseDTO accountResponseDTO =  accountService.createAccount(accountDTO);
        System.out.println("Start Calling Aync-2 Call");
        declarationsTask.task2(25);
        return new ResponseEntity<>(accountResponseDTO,HttpStatus.FOUND);
    }


    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Guest Profile Info", notes = "Input is all the attribute along with updated Attribute")
    @ApiResponses(value = {
            @ApiResponse(code=201,message = "Successfull Updation of Account",response = AccountResponseDTO.class),
            @ApiResponse(code=401,message = "Unauthrized Access"),
            @ApiResponse(code=400,message = "Malformed Request"),
            @ApiResponse(code=500,message = "Internal Error")
    })
    @ResponseBody
    public ResponseEntity updateProfile(@RequestBody AccountDTO accountDTO){
         AccountResponseDTO accountResponseDTO =    accountService.updateProfile(accountDTO);
       return new ResponseEntity<>(accountResponseDTO,HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/delete",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Guest Profile Info", notes = "Input Pram is guestId as Query String Format")
    @ApiResponses(value = {
            @ApiResponse(code=201,message = "Successfull Deletion of Account"),
            @ApiResponse(code=401,message = "Unauthrized Access"),
            @ApiResponse(code=400,message = "Malformed Request"),
            @ApiResponse(code=500,message = "Internal Error")
    })
    @ResponseBody
    public ResponseEntity deleteProfile(@RequestParam("guestid") int guestid){
        log.info("Deleting Account for guestId {}",guestid);
        accountService.deleteProfile(guestid);
        log.info("Deleted Account for GuestId {}",guestid);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
