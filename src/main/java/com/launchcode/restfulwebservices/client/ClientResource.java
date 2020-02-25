package com.launchcode.restfulwebservices.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
	
	@PutMapping("/users/{username}/clients/{id}")
	public ResponseEntity<Client> updateClient(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Client client) {
		
		Client clientUpdated = clientService.save(client);
		
		return new ResponseEntity<Client>(client, HttpStatus.OK);
		
	}
	
	@PostMapping("/users/{username}/clients")
	public ResponseEntity<Void> updateClient(
			@PathVariable String username, @RequestBody Client client) {
		
		Client createdClient = clientService.save(client);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(createdClient.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
}
