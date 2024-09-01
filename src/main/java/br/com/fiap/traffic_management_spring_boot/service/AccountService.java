package br.com.fiap.traffic_management_spring_boot.service;

import br.com.fiap.traffic_management_spring_boot.dto.account.AccountCreateDto;
import br.com.fiap.traffic_management_spring_boot.dto.account.AccountUpdateDto;
import br.com.fiap.traffic_management_spring_boot.dto.account.AccountViewDto;
import br.com.fiap.traffic_management_spring_boot.exception.NotFoundException;
import br.com.fiap.traffic_management_spring_boot.model.account.Account;
import br.com.fiap.traffic_management_spring_boot.model.account.Role;
import br.com.fiap.traffic_management_spring_boot.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public AccountViewDto insertOne(AccountCreateDto accountCreateDto){
        Account account = new Account();
        BeanUtils.copyProperties(accountCreateDto, account);
        account.setCreatedAt(new Date());
        String password = new BCryptPasswordEncoder().encode(accountCreateDto.password());
        account.setPassword(password);
        account.setRole(Role.valueOf(accountCreateDto.role().toUpperCase()));
        Account entity = this.accountRepository.save(account);
        return new AccountViewDto(entity);
    }

    public Optional<AccountViewDto> findOneById(Long id){
        Optional<Account> account = this.accountRepository.findById(id);
        return account.map(AccountViewDto::new);
    }

    public Optional<AccountViewDto> findOneByEmail(String email){
        Optional<Account> account = this.accountRepository.findByEmail(email);
        return account.map(AccountViewDto::new);
    }

    public Page<AccountViewDto> findAll(Pageable pagination){
        Page<Account> accountList = this.accountRepository.findAll(pagination);
        return accountList.map(AccountViewDto::new);
    }

    public Page<AccountViewDto> findManyByCreatedAtPeriod(Date init, Date end, Pageable pagination){
        Page<Account> accountList = this.accountRepository.findManyByCreatedAtPeriod(init, end, pagination);
        return accountList.map(AccountViewDto::new);
    }

    public void deleteOneById(Long id){
        Optional<Account> accountOptional = this.accountRepository.findById(id);
        if(accountOptional.isPresent()){
            this.accountRepository.deleteById(id);
        }else{
            throw new NotFoundException("Account doesn't exists");
        }
    }

    public AccountViewDto updateOne(AccountUpdateDto accountUpdateDto){
        Optional<Account> accountOptional = this.accountRepository.findById(accountUpdateDto.id());
        if(accountOptional.isPresent()){
            Account account = accountOptional.get();
            BeanUtils.copyProperties(accountUpdateDto, account);
            account.setUpdatedAt(new Date());
            Account entity = this.accountRepository.save(account);
            return new AccountViewDto(entity);
        }else{
            throw new NotFoundException("Account doesn't exists");
        }
    }
}
