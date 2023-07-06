package moses.zal.Individual.Assignment.Repositories;

import moses.zal.Individual.Assignment.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {
  Boolean existsUserByUsernameOrEmailOrPhoneNumber (String userName, String email, String phoneNumber);
  User findUserByUsername (String userName);
  Boolean existsUserByUsername (String userName);
}
