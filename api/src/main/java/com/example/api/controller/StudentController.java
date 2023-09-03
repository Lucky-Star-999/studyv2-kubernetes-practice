package com.example.api.controller;

import com.example.api.dao.StudentDao;
import com.example.api.properties.ApiProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final RestTemplate restTemplate;

    private final ApiProperties apiProperties;

    @Autowired
    public StudentController(RestTemplate restTemplate, ApiProperties apiProperties) {
        this.restTemplate = restTemplate;
        this.apiProperties = apiProperties;
    }

    // Create (POST) a new student
    @PostMapping("")
    public ResponseEntity<StudentDao> createStudent(@RequestBody StudentDao studentDao) throws JsonProcessingException {
        // Define the request URL
        String apiUrl = apiProperties.getQueryServicePath();

        // Convert object into JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Create headers with content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create a request entity with your JSON payload and headers
        String requestBody = objectMapper.writeValueAsString(studentDao);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the POST request
        ResponseEntity<StudentDao> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, StudentDao.class);

        return responseEntity;
    }

    // Read (GET) all students
    @GetMapping("")
    public ResponseEntity<List<StudentDao>> getAllStudents() {
        ResponseEntity<List<StudentDao>> responseEntity = restTemplate.exchange(
                apiProperties.getQueryServicePath(),
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDao>>() {
                }
        );
        return responseEntity;
    }

    // Read (GET) a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDao> getStudentById(@PathVariable Long id) {
        // Make a GET request to the other microservice
        ResponseEntity<StudentDao> responseEntity =
                restTemplate.getForEntity(apiProperties.getQueryServicePath() + "/" + id, StudentDao.class);
        return responseEntity;
    }

    // Update (PUT) a student by ID
    @PutMapping("/{id}")
    public ResponseEntity<StudentDao> updateStudent(@PathVariable Long id, @RequestBody StudentDao studentDao)
            throws JsonProcessingException {
        // Define the request URL
        String apiUrl = apiProperties.getQueryServicePath() + "/" + id;

        // Create headers with content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create a request entity with your JSON payload and headers
        HttpEntity<StudentDao> requestEntity = new HttpEntity<>(studentDao, headers);

        // Make the PUT request
        ResponseEntity<StudentDao> responseEntity = restTemplate.exchange(
                apiUrl,
                org.springframework.http.HttpMethod.PUT,
                requestEntity,
                StudentDao.class
        );

        return responseEntity;
    }

    // Delete (DELETE) a student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        // Define the request URL
        String apiUrl = apiProperties.getQueryServicePath() + "/" + id;

        // Make the DELETE request
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                apiUrl,
                org.springframework.http.HttpMethod.DELETE,
                null,
                Void.class
        );

        return responseEntity;
    }
}
