package moses.zal.Individual.Assignment.Services;

import moses.zal.Individual.Assignment.Entities.Address;

public interface AddressService {
  Address findAddressByID(int ID);
  Boolean existsAddressByCountryAndCityAndStreetAndPostalCode (Address address);
  Address findAddressByCountryAndCityAndStreetAndPostalCode (Address address);
}
