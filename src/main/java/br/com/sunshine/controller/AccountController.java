package br.com.sunshine.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.sunshine.detail.AccountDetail;
import br.com.sunshine.dto.AccountDTO;
import br.com.sunshine.jwt.TokenService;
import br.com.sunshine.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import br.com.sunshine.model.Account;
import br.com.sunshine.repository.AccountRepository;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountService service;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password) {
		var authToken = new UsernamePasswordAuthenticationToken(email, password);
		Authentication auth = authenticationManager.authenticate(authToken);
		var response = ((AccountDetail) auth.getPrincipal()).getAccount();

		if(response.isPresent()){
			return ResponseEntity.ok(tokenService.generateToken(response.get()));
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@PostMapping("/create")
	public ResponseEntity<AccountDTO> save(@RequestBody @Valid Account account) {
		return ResponseEntity.ok(service.save(account));
	}

	@PutMapping("/update")
	public ResponseEntity<AccountDTO> update(@RequestBody @Valid AccountDTO account) {
		return ResponseEntity.ok(service.update(account));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id){
		service.deleteAccount(id);
		return ResponseEntity.ok("Successful");
	}

	@GetMapping("/byToken")
	public ResponseEntity<AccountDTO> getByToken(@RequestParam("token") String token){
		var email = tokenService.getSubject(token);
		return ResponseEntity.ok(service.getByEmail(email));
	}

	@GetMapping("/")
	public ResponseEntity<List<Account>> getAll(){
		return ResponseEntity.ok(service.getAll());
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationException(BindException ex) {
		Map<String, String> errorsMap = new HashMap<>();
		List<FieldError> errors = ex.getFieldErrors();

		for (FieldError error : errors) {
			String field = error.getField();
			String message = error.getDefaultMessage();
			errorsMap.put(field, message);
		}

		return errorsMap;
	}
}
