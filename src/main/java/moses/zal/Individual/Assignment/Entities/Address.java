package moses.zal.Individual.Assignment.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
  private int addressID;

  @NotBlank(message = "\"Country\" can't be blank!")
  @Column (name = "country")
  private String country;

  @NotBlank(message = "\"City\" can't be blank!")
  @Column (name = "city")
  private String city;

  @NotBlank(message = "\"Street\" can't be blank!")
  @Column (name = "street")
  private String street;

  @NotBlank(message = "\"Postal code\" can't be blank!")
  @Column (name = "postal_code")
  private String postalCode;

  public Address() {
  }

  public Address(String country, String city, String street, String postalCode) {
    this.country = country;
    this.city = city;
    this.street = street;
    this.postalCode = postalCode;
  }

  public int getAddressID() {
    return addressID;
  }

  public void setAddressID(int addressID) {
    this.addressID = addressID;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
}
