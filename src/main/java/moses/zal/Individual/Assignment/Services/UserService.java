package moses.zal.Individual.Assignment.Services;

import moses.zal.Individual.Assignment.Entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {
  List<User> getAllUsers();
  ResponseEntity <String> userCreationLogic(User newUser, BindingResult bindingResult);
  Boolean existsUserByUsernameOrEmailOrPhoneNumber (User user);
  User userRetrieval (int userID);
  String userNotBlankMessagesAppender(BindingResult bindingResult);
  public String userCreationErrors(BindingResult bindingResult, User newUser);
}
