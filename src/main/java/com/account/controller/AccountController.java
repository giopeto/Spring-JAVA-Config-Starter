package com.account.controller;

import com.account.domain.Account;
import com.account.repository.AccountRepository;
import com.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

		System.out.println("\n\n\n\n\nGet Current Account \n principal: " + principal  );
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		System.out.println("\n\n\n\n\n ############ auth: " + auth  + "\n To string: " + auth.toString() + " NAME IS : " + auth.getName());
		User asd = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();


		System.out.println("\n\n\n\n\n\n\n Usera: " + asd.toString());



		//return accountRepository.findOneByEmail(auth.getName());
		Assert.notNull(principal);
		return accountRepository.findOneByEmail(principal.getName());
	}
	/*public Account getCurrentAccount(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		System.out.println("\n\n\n\n\nprincipal: " + principal  );

		Assert.notNull(principal);
		return accountRepository.findOneByEmail(principal.getName());
	}*/


	/*@RequestMapping(
			value="/authenticate",
			method = RequestMethod.POST,
			headers="Accept=application/json",
			produces="application/json"
	)
	public void authenticate(@RequestBody Account a) {
		accountService.authenticate(a);
	}*/

	@RequestMapping(value = "account/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	@Secured("ROLE_ADMIN")
	public Account account(@PathVariable("id") String id) {
		return accountRepository.findOne(id);
	}


	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public void logOut() {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		/*
		 * System.out.println("Auth: " + auth); System.out.println("Auth 2: " +
		 * auth.getCredentials()); System.out.println("Auth 3: " +
		 * auth.getPrincipal());
		 */
	}
}
