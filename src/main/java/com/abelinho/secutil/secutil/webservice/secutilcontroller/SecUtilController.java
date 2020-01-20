package com.abelinho.secutil.secutil.webservice.secutilcontroller;

//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abelinho.secutil.secutil.webservice.model.SecurityUtil;

@RestController
@RequestMapping(path="token" , produces= {"application/json","application/xml"})//http://localhost:8080/token
//@CrossOrigin(origins="*")
public class SecUtilController {

	@GetMapping(path="/getclientdetails")	
	public SecurityUtil getUserrDetails(@RequestParam(value="client_id") String clientId, @RequestParam(value="client_secret") String clientSecret, @RequestParam(value="client_ref") String clientRef, @RequestParam(value="trans_amount") String transAmount, @RequestParam(value="service_id") String servId ) {
		
		SecurityUtil tokenGen = new SecurityUtil();
		
		tokenGen.setClientId(clientId);
		
		tokenGen.setClientSecret(clientSecret);
		
		tokenGen.setClient_reference(clientRef);
		
		tokenGen.setTrans_amount(transAmount);
		
		tokenGen.setService_id(servId);
		
		String randToken=tokenGen.calculateAuthorizationSignature(clientRef, transAmount, servId, clientId, clientSecret);
		
		tokenGen.setRantok(randToken);
		
		 return tokenGen;	
		
	}
	
}
