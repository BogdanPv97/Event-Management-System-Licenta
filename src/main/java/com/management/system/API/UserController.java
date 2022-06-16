package com.management.system.API;

import com.management.system.Entity.DTO.EventUserMappingDTO;
import com.management.system.Entity.DTO.UserDTO;
import com.management.system.Entity.DTO.UserDetailsDTO;
import com.management.system.Entity.Event;
import com.management.system.Entity.User;
import com.management.system.Service.*;
import com.management.system.Utility.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private BillService billService;
    @Autowired
    private DTOConverter dtoConverter;


    @Autowired
    private EventService eventService;

    @GetMapping

    public ResponseEntity<List<User>> getUsers(){
        try{
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    @GetMapping("/userDetails")

    public ResponseEntity<List<UserDetailsDTO>> getBulkUserDetails(){
        try{
            return new ResponseEntity<>(userService.getBulkUserDetails(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}")

    public ResponseEntity<User> getUserById(@PathVariable("userId") long id){
        try{
            return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getByUsername/{username}")

    public ResponseEntity<User> getUserByUsername(@PathVariable(value = "username") String username){
        try{
            User user= userService.getUserByUsername(username);
//            if(user.size() > 0) {
//                return new ResponseEntity<User>(user.get(0), HttpStatus.OK);
//            }
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch(UsernameNotFoundException e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getGoingEvents/{user_id}")

    public ResponseEntity<List<Event>> getGoingEventsForUserId(@PathVariable(value = "user_id") long userId){
        try{
            return new ResponseEntity<>(eventService.getGoingEventsForUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getInterestedEvents/{user_id}")

    public ResponseEntity<List<Event>> getInterestedEventsForUserId(@PathVariable(value = "user_id") long userId){
        try{
            return new ResponseEntity<>(eventService.getInterestedEventsForUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/appendEventToUser", consumes = "application/json")

    public ResponseEntity<String> appendEventToUserList(@RequestBody EventUserMappingDTO eventUserMappingDTO){
        try {
            userService.appendEventToUserList(eventUserMappingDTO);
            return new ResponseEntity<String>("Event added to user list!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/registerUser",consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        System.out.println("From controller" + user);
        try{
            return new ResponseEntity<UserDTO>(userService.registerUser(user), HttpStatus.CREATED);
        }catch(Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/updateUser/{userId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<User> updateUser(@RequestBody User user){
        try{
            return new ResponseEntity<User>(userService.persistUser(user), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "/partialUpdate/{userId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<User> partialUpdate(@PathVariable("userId") long id, @RequestBody @Valid UserDTO patchEmployee){
        User user = null;
        try {
            user = userService.getUserById(id);
        } catch(EmptyResultDataAccessException e){
            e.printStackTrace();
        }

        if(patchEmployee.getFirstName() != null) {
            user.setFirstName(patchEmployee.getFirstName());
        }
        if(patchEmployee.getLastName() != null) {
            user.setLastName(patchEmployee.getLastName());
        }
        if(patchEmployee.getEmail() != null) {
            user.setPassword(patchEmployee.getEmail());
        }
        if(patchEmployee.getAge() != 0) {
            user.setAge(patchEmployee.getAge());
        }
        if(patchEmployee.getUsername() != null) {
            user.setUsername(patchEmployee.getUsername());
        }
        if(patchEmployee.getPassword() != null) {
            user.setPassword(patchEmployee.getPassword());
        }

        try {
            return new ResponseEntity<>(userService.persistUser(user), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("deleteUser/{id}")

    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
