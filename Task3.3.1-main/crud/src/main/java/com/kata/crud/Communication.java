package com.kata.crud;

import com.kata.crud.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {
    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Person> getAllPersons() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        ResponseEntity<List<Person>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        headers.set("Cookie", responseEntity.getHeaders().get("Set-Cookie")
                .stream()
                .collect(Collectors.joining(";")));

        Person person = new Person();
        person.setId(3L);
        person.setName("James");
        person.setLastName("Brown");
        person.setAge(24);

        savePerson(new HttpEntity<>(person, headers));

        person.setName("Thomas");
        person.setLastName("Shelby");

        updateUser(new HttpEntity<>(person, headers));

        deletePerson(new HttpEntity<>(person, headers));

        return responseEntity.getBody();
    }

    public void savePerson(HttpEntity<Person> entity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println("USER SAVE: " + responseEntity.getBody());
    }

    public void updateUser(HttpEntity<Person> entity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println("USER UPDATE: " + responseEntity.getBody());
    }

    public void deletePerson(HttpEntity<Person> entity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/3", HttpMethod.DELETE, entity, String.class);
        System.out.println("USER DELETE: " + responseEntity.getBody());
    }
}
