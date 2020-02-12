package com.launchcode.restfulwebservices.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class ClientHardcodedService {
	
	private static List<Client> clients = new ArrayList<>();
	private static int idCounter = 0;
	
	static {
		clients.add(new Client(++idCounter, "Tracy", "Purchase", new Date(), false ));
		clients.add(new Client(++idCounter, "Micky", "Prospect", new Date(), false ));
		clients.add(new Client(++idCounter, "Zeplin", "Refinance", new Date(), false ));
		
	}
	
	public List<Client> findAll() {
		return clients;
	}
	
	public Client deleteById(long id) {
		Client client = findById(id);
		
		if(client==null) return null;
		
		if(clients.remove(client)) {
			return client;
		}
		
		return null;
	}
	
	public Client findById(long id) {
		for(Client client:clients) {
			if(client.getId() == id) {
				return client;
			}
		}
		
		return null;
	}
	
	

}
