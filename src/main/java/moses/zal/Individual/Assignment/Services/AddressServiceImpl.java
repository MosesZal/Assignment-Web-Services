package moses.zal.Individual.Assignment.Services;

import moses.zal.Individual.Assignment.Entities.Address;
import moses.zal.Individual.Assignment.Repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{
  private AddressRepository addressRepository;

  @Autowired
  public AddressServiceImpl(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @Override
  public Address findAddressByID(int ID) {
    Address address = null;
    Optional<Address> possibleAddress = addressRepository.findById(ID);
    if (possibleAddress.isPresent())
      address = possibleAddress.get();
    return address;
  }

  @Override
  public Boolean existsAddressByCountryAndCityAndStreetAndPostalCode(Address address) {
    return addressRepository.existsAddressByCountryAndCityAndStreetAndPostalCode(address.getCountry(), address.getCity(),
            address.getStreet(), address.getPostalCode());
  }

  @Override
  public Address findAddressByCountryAndCityAndStreetAndPostalCode(Address address) {
    return addressRepository.findAddressByCountryAndCityAndStreetAndPostalCode(address.getCountry(), address.getCity(),
            address.getStreet(), address.getPostalCode());
  }
}
