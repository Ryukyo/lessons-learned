package com.ryukyo.lessonslearned.rest;

import com.ryukyo.lessonslearned.constants.SchoolConstants;
import com.ryukyo.lessonslearned.model.Contact;
import com.ryukyo.lessonslearned.model.Response;
import com.ryukyo.lessonslearned.repository.ContactRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact", produces = {MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins = "*")
public class ContactRestController {

  @Autowired
  ContactRepository contactRepository;

  @GetMapping("/getMessagesByStatus")
  public List<Contact> getMessagesByStatus(@RequestParam(name = "status") String status) {
    return contactRepository.findByStatus(status);
  }

  @GetMapping("/getAllMsgsByStatus")
  public List<Contact> getAllMsgsByStatus(@RequestBody Contact contact) {
    if (null != contact && null != contact.getStatus()) {
      return contactRepository.findByStatus(contact.getStatus());
    } else {
      return List.of();
    }
  }

  @PostMapping("/saveMsg")
  public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom") String invocationFrom,
      @Valid @RequestBody Contact contact) {
//    log.info(String.format("Header invocationFrom = %s", invocationFrom));
    contactRepository.save(contact);
    Response response = new Response();
    response.setStatusCode("200");
    response.setStatusMsg("Message saved successfully");
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .header("isMsgSaved", "true")
        .body(response);
  }

  @DeleteMapping("/deleteMsg")
  public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity) {
    HttpHeaders headers = requestEntity.getHeaders();
//    headers.forEach((key, value) -> {
//      log.info(String.format(
//          "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
//    });
    Contact contact = requestEntity.getBody();
    contactRepository.deleteById(contact.getContactId());
    Response response = new Response();
    response.setStatusCode("200");
    response.setStatusMsg("Message successfully deleted");
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(response);
  }

  @PatchMapping("/closeMsg")
  public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq) {
    Response response = new Response();
    Optional<Contact> contact = contactRepository.findById(contactReq.getContactId());
    if (contact.isPresent()) {
      contact.get().setStatus(SchoolConstants.CLOSE);
      contactRepository.save(contact.get());
    } else {
      response.setStatusCode("400");
      response.setStatusMsg("Invalid Contact ID received");
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(response);
    }
    response.setStatusCode("200");
    response.setStatusMsg("Message successfully closed");
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(response);
  }
}
