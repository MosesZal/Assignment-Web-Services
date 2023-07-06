package moses.zal.Individual.Assignment.Repositories;

import moses.zal.Individual.Assignment.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Integer> {
  Boolean existsAddressByCountryAndCityAndStreetAndPostalCode (String country, String city, String street, String postalCode);
  Address findAddressByCountryAndCityAndStreetAndPostalCode (String country, String city, String street, String postalCode);
}
