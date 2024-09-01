package br.com.fiap.traffic_management_spring_boot.controller;

import br.com.fiap.traffic_management_spring_boot.dto.account.AccountCreateDto;
import br.com.fiap.traffic_management_spring_boot.dto.account.AccountViewDto;
import br.com.fiap.traffic_management_spring_boot.dto.traffic_light.TrafficLightCreateDto;
import br.com.fiap.traffic_management_spring_boot.dto.traffic_light.TrafficLightUpdateDto;
import br.com.fiap.traffic_management_spring_boot.dto.traffic_light.TrafficLightViewDto;
import br.com.fiap.traffic_management_spring_boot.model.account.Account;
import br.com.fiap.traffic_management_spring_boot.service.AccountService;
import br.com.fiap.traffic_management_spring_boot.service.TrafficLightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/account/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<AccountViewDto> findOneByEmail(@PathVariable String email){
        return this.accountService.findOneByEmail(email);
    }
}
