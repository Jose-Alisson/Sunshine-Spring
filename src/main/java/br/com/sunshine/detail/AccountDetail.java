package br.com.sunshine.detail;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.sunshine.model.Account;

public class AccountDetail implements  UserDetails {
	
	private Optional<Account> account;
	
	public AccountDetail(){}
	
	public AccountDetail(Optional<Account> account) {
		this.setAccount(account);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return account.orElse(new Account()).getPassword();
	}

	@Override
	public String getUsername() {
		return account.orElse(new Account()).getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Optional<Account> getAccount() {
		return account;
	}

	public void setAccount(Optional<Account> account) {
		this.account = account;
	}
}
