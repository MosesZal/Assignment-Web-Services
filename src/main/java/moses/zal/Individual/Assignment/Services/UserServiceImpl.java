package moses.zal.Individual.Assignment.Services;

import moses.zal.Individual.Assignment.Entities.Address;
import moses.zal.Individual.Assignment.Entities.User;
import moses.zal.Individual.Assignment.Exceptions.ResourceNotFoundException;
import moses.zal.Individual.Assignment.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
  private UserRepository userRepository;
  private AddressService addressService;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, AddressService addressService) {
    this.userRepository = userRepository;
    this.addressService = addressService;
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public ResponseEntity<String> userCreationLogic(User newUser, BindingResult bindingResult) {
    newUser.setUserID(0);
    Address addressBeingSearched = addressService.findAddressByID(newUser.getAddress().getAddressID());
    if (newUser.getAddress().getAddressID() != 0 && addressBeingSearched == null)
      throw new ResourceNotFoundException("address", "ID", newUser.getAddress().getAddressID());
    else if (existsUserByUsernameOrEmailOrPhoneNumber(newUser))
      return new ResponseEntity<>("Process failed! There is already an existing user with the same \"user name\", "
              + "\"email\", and/or \"phone number\"!", HttpStatus.CONFLICT);
    else if (addressBeingSearched != null) {
      newUser.setAddress(addressBeingSearched);
      userRepository.save(newUser);
      return new ResponseEntity<>( "Address found!\n" +
              "User created and assigned successfully to the specified address!", HttpStatus.CREATED);
    }
    else if (bindingResult.hasErrors())
      return ResponseEntity.badRequest().body(userNotBlankMessagesAppender(bindingResult));
    else if (addressService.existsAddressByCountryAndCityAndStreetAndPostalCode(newUser.getAddress())) {
      newUser.setAddress(addressService.findAddressByCountryAndCityAndStreetAndPostalCode(newUser.getAddress()));
      userRepository.save(newUser);
      return new ResponseEntity<>("Address already exists!\n" +
              "User created and assigned successfully to that address!", HttpStatus.CREATED);
    }
    else {
      userRepository.save(newUser);
      return new ResponseEntity<>("User and address created successfully!", HttpStatus.CREATED);
    }
  }

  @Override
  public Boolean existsUserByUsernameOrEmailOrPhoneNumber(User user) {
    return userRepository.existsUserByUsernameOrEmailOrPhoneNumber(user.getUsername(), user.getEmail(),
            user.getPhoneNumber());
  }

  @Override
  public User userRetrieval(int userID) {
    return userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("user", "ID", userID));
  }

  @Override
  public String userNotBlankMessagesAppender(BindingResult bindingResult) {
    StringBuilder errors = new StringBuilder("Process failed due to the following error(s):\n");
    for (FieldError fieldError : bindingResult.getFieldErrors())
      errors.append("* ").append(fieldError.getDefaultMessage()).append("\n");
    return errors.toString();
  }

  /** Not used anymore because I found a better solution. Still exist as a reference to go back to when encountering a similar
      problem :) **/
  @Override
  public String userCreationErrors (BindingResult bindingResult, User newUser) {
    StringBuilder errors = new StringBuilder("Process failed due to the following error(s):\n");
    if (newUser.getAddress().getAddressID() != 0) {
      for (FieldError fieldError : bindingResult.getFieldErrors()) {
        if (fieldError.getField().startsWith("address."))
          bindingResult.rejectValue(fieldError.getField(), "NotBlank");
        else
          errors.append("* ").append(fieldError.getDefaultMessage()).append("\n");
      }
      errors.append("► Address could not be found!!!");
    }
    else
      for (FieldError fieldError : bindingResult.getFieldErrors())
        errors.append("* ").append(fieldError.getDefaultMessage()).append("\n");
    return errors.toString();
  }
}
