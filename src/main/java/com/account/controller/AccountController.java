package com.account.controller;

import com.account.domain.Account;
import com.account.repository.AccountRepository;
import com.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	private AccountRepository accountRepository;

	@RequestMapping(
			method = RequestMethod.POST,
			headers="Accept=application/json",
			produces="application/json"
	)
	public Account save(@RequestBody Account a) {
		return accountService.save(a);
	}

	/*@Secured({"ROLE_USER", "ROLE_ADMIN"})*/
	@RequestMapping(
			method = RequestMethod.GET,
			headers="Accept=application/json",
			produces="application/json"
	)

	public Account getCurrentAccount(Principal principal) {
		Assert.notNull(principal);
		return accountRepository.findByEmail(principal.getName());
	}

	@RequestMapping(
			value="/authenticate",
			method = RequestMethod.POST,
			headers="Accept=application/json",
			produces="application/json"
	)
	public void authenticate(@RequestBody Account a) {
		accountService.authenticate(a);
	}


}
