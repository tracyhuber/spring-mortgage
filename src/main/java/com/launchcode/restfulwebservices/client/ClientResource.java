package com.launchcode.restfulwebservices.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


import com.launchcode.restfulwebservices.client.Client;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ClientResource {
	
	@Autowired
	private ClientHardcodedService clientService;
	
	@GetMapping("/users/{username}/clients")
	public List<Client> getAllClients(@PathVariable String username) {
		return clientService.findAll();
	}

	@GetMapping("/users/{username}/clients/{id}")
	public Client getClient(@PathVariable String username, @PathVariable long id) {
		return clientService.findById(id);
	}

	
	@DeleteMapping("/users/{username}/clients/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable String username, @PathVariable long id) {
		Client client = clientService.deleteById(id);
		if(client!=null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
}
