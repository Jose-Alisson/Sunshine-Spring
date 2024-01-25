package br.com.sunshine.services;

import br.com.sunshine.dto.AccountDTO;
import br.com.sunshine.model.Account;
import br.com.sunshine.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private final ModelMapper mapper;

    @Autowired
    private AccountRepository repository;

    AccountService() {
        mapper = new ModelMapper();
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AccountDTO save(Account account) {
        account.setPassword(getPasswordEncoder().encode(account.getPassword()));
        return mapper.map(repository.save(account), AccountDTO.class);
    }

    public AccountDTO update(AccountDTO accountDTO) {
        Optional<Account> account_ = repository.findById(accountDTO.getId());

        if (account_.isPresent()) {
            Account account = Account.builder().
                    id(account_.get().getId()).
                    email(accountDTO.getEmail()).
                    password(account_.get().getPassword()).
                    phone(accountDTO.getPhone()).
                    name(accountDTO.getName()).
                    addresses(account_.get().getAddresses()).
                    photoUrl(accountDTO.getPhotoUrl()).build();

            return mapper.map(repository.save(account), AccountDTO.class);
        }
        return null;
    }

    public void deleteAccount(String id) {
        repository.deleteById(id);
    }

    public AccountDTO getById(String id) {
        Optional<Account> account_ = repository.findById(id);
        return account_.map(account -> mapper.map(account, AccountDTO.class)).orElse(null);
    }

    public AccountDTO getByEmail(String email){
        return repository.findByEmail(email).map(account -> mapper.map(account, AccountDTO.class)).orElse(null);
    }

    public List<Account> getAll(){
        return repository.findAll();
    }
}
