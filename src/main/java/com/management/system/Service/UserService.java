package com.management.system.Service;

import com.management.system.DAO.UserRepository;
import com.management.system.Entity.DTO.Credentials;
import com.management.system.Entity.DTO.EventUserMappingDTO;
import com.management.system.Entity.DTO.UserDTO;
import com.management.system.Entity.DTO.UserDetailsDTO;
import com.management.system.Entity.Event;
import com.management.system.Entity.User;
import com.management.system.Utility.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventService eventService;

    @Autowired
    private DTOConverter dtoConverter;
    @Autowired
    private CityService cityService;

    public User persistUser(User user){
        return userRepository.save(user);
    }

    public UserDTO registerUser(UserDTO userDTO) throws Exception {
       User user = dtoConverter.userDtoToEntitySave(userDTO);
       user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
       user.setCity(cityService.getCityByName(userDTO.getCity()));

       if(validateUsername(user.getUsername())) {
           userRepository.save(user);
       }else{
           throw new Exception("Username already exist!");
       }
       return userDTO;
    }

    public boolean validateUsername(String username){
        if(userRepository.getUserByUsername(username) == null){
            return true;
        }else{
            return false;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserDetailsDTO> getBulkUserDetails(){
        List<User> users = getAllUsers();
        List<UserDetailsDTO> userDetails = new ArrayList<>();

        for(User user : users){
            UserDetailsDTO userDto = dtoConverter.convertUserToUserDetailsDTO(user);
            userDto.setCity(cityService.getCityNameById(user.getCity().getCityId()));

            userDetails.add(userDto);
        }

        return userDetails;
    }


    public User getUserById(long id){
        return userRepository.findById(id).get();
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }


    public Set<Event> getUserEvents(long id){
        return getUserById(id).getEvents();
    }


    public void deleteUser(User user) {
        userRepository.delete(user);
    }


    public void deleteById(Long id){
        userRepository.deleteById(id);
    }


    public boolean validateAge(User user){
        return user.getAge() >=18 ? true : false;
    }

    public void appendEventToUserList(EventUserMappingDTO eventUserMappingDTO){
        User user = getUserById(eventUserMappingDTO.getUserId());
        Event event = eventService.getEventById(eventUserMappingDTO.getEventId());

        user.addEvent(event);
        persistUser(user);
    }


    public List<String> getAllEmailsFromSubscription(){
        return userRepository.getAllEmailsFromSubscription();
    }

    public UserDetailsDTO getLoggedUser(String username){
        User user = getUserByUsername(username);
        UserDetailsDTO userDetailsDTO = dtoConverter.convertUserToUserDetailsDTO(user);
        userDetailsDTO.setCity(cityService.getCityNameById(user.getCity().getCityId()));

        return userDetailsDTO;

    }
}

