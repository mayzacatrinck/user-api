package challenge.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.api.dto.AddressDto;
import challenge.api.dto.UserDto;
import challenge.api.exception.AlreadyUserException;
import challenge.api.exception.RecordNotFoundException;
import challenge.api.integration.AddressViaCep;
import challenge.api.model.Address;
import challenge.api.model.User;
import challenge.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ZipCodeService zipCodeService;

	public User createUser(UserDto userDto) {
		validateSingleField(userDto);
		User user = userDto.toUser();
		return userRepository.save(user);
	}

	private void validateSingleField(UserDto userDto) {
		userRepository.findByDocumentOrEmail(userDto.getDocument(), userDto.getEmail())
				.ifPresent(user -> { 
					throw new AlreadyUserException("Document or email already registered."); });
	}

	public User findUser(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("User not found"));
	}

	public User registerAddress(Long idUser, AddressDto addressDto) {
		User user = findUser(idUser);
		AddressViaCep zipCode = zipCodeService.findZipeCode(addressDto.getZipCode());
		Address address = buidAddress(user, addressDto, zipCode);
		user.addAddress(address);	
		return userRepository.save(user);
	}
	
	private Address buidAddress(User user, AddressDto addressDto, AddressViaCep zipCode) {
		Address address = new Address();
		address.setNumber(addressDto.getNumber());
		address.setComplement(addressDto.getComplement());
		address.setZipCode(addressDto.getZipCode());
		address.setNeighborhood(zipCode.getNeighborhood());
		address.setCity(zipCode.getCity());
		address.setStreet(zipCode.getStreet());
		address.setState(zipCode.getState());
		address.setUser(user);
		return address;
	}

}
