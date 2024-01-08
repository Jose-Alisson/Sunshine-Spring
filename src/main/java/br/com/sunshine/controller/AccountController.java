package br.com.sunshine.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sunshine.model.Account;
import br.com.sunshine.repository.AccountRepository;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountRepository repository;

	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@PostMapping("/save")
	public ResponseEntity<Account> save(@RequestBody Account account) {
		account.setPassword(getPasswordEncoder().encode(account.getPassword()));
		return ResponseEntity.ok(repository.save (account));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Account> update(@PathVariable("id") String id, @RequestBody Account account) {

		Optional<Account> accountOpt = repository.findById(id);

		if (accountOpt.isPresent()) {
			Account account_ = Account.builder().
					name(account.getName()).
					phone(account.getPhone()).
					password(account.getPassword()).
					photoUrl(account.getPhotoUrl()).
					build();
			
			return ResponseEntity.ok(repository.save(account_));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id){
		Optional<Account> accountOpt = repository.findById(id);
		
		if (accountOpt.isPresent()) {
			repository.delete(accountOpt.get());
			return ResponseEntity.ok("");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
