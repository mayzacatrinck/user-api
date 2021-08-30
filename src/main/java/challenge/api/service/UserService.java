package challenge.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.api.dto.AddressRequest;
import challenge.api.dto.UserRequest;
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
	
	public User createUser(UserRequest userRequest) {
		validateSingleField(userRequest);
		User user = userRequest.toUser();
		return userRepository.save(user);
	}

	private void validateSingleField(UserRequest userRequest) {
		userRepository.findByDocumentOrEmail(userRequest.getDocument(), userRequest.getEmail())
				.ifPresent(user -> { 
					throw new AlreadyUserException("Document or email already registered."); });
	}

	public User findUser(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("User not found"));
	}

	public User registerAddress(Long idUser, AddressRequest addressRequest) {
		User user = findUser(idUser);
		AddressViaCep zipCode = zipCodeService.findZipeCode(addressRequest.getZipCode());
		Address address = buidAddress(user, addressRequest, zipCode);
		user.addAddress(address);	
		return userRepository.save(user);
	}
	
	private Address buidAddress(User user, AddressRequest addressRequest, AddressViaCep zipCode) {
		return new Address(addressRequest.getNumber(), addressRequest.getComplement(), addressRequest.getZipCode(),
				zipCode.getNeighborhood(), zipCode.getCity(), zipCode.getStreet(), zipCode.getState(), user);
	}

}
