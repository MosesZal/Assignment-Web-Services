package moses.zal.Individual.Assignment.Controllers;

import moses.zal.Individual.Assignment.Entities.User;
import moses.zal.Individual.Assignment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {
  private UserService userService;

  @Autowired
  public AdminRestController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/newuser")
  public ResponseEntity<String> userCreation (@Valid @RequestBody User newUSer, BindingResult bindingResult) {
    return userService.userCreationLogic(newUSer, bindingResult);
  }

  @GetMapping("/users/{ID}")
  public User userRetrieval (@PathVariable int ID) {
    return userService.userRetrieval(ID);
  }
}
