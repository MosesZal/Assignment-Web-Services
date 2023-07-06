package moses.zal.Individual.Assignment.Entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private int userID;

  @NotBlank(message = "\"Username\" can't be blank!")
  @Column (name = "username")
  private String username;

  @NotBlank(message = "\"First name\" can't be blank!")
  @Column (name = "first_name")
  private String firstName;

  @NotBlank(message = "\"Last name\" can't be blank!")
  @Column (name = "last_name")
  private String lastName;

  @NotBlank(message = "\"Email\" can't be blank!")
  @Column (name = "email")
  private String email;

  @NotBlank(message = "\"Phone number\" can't be blank!")
  @Column (name = "phone_number")
  private String phoneNumber;

  @NotBlank(message = "\"Member type\" can't be blank!")
  @Column (name = "member_type")
  private String memberType;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
  @JoinColumn(name = "address_id")
  @Valid
  private Address address;

  public User() {
  }

  public User(String username, String firstName, String lastName, String email, String phoneNumber, String memberType, Address address) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.memberType = memberType;
    this.address = address;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String userName) {
    this.username = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getMemberType() {
    return memberType;
  }

  public void setMemberType(String memberType) {
    this.memberType = memberType;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
