package challenge.api.service;

import challenge.api.dto.AddressDto;
import challenge.api.dto.UserDto;
import challenge.api.exception.AlreadyUserException;
import challenge.api.exception.RecordNotFoundException;
import challenge.api.integration.AddressViaCep;
import challenge.api.model.User;
import challenge.api.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

	@Mock
    private ZipCodeService zipCodeService;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldCreateuser() {
        UserDto dto = new UserDto();
		buildUser(dto);

		userService.createUser(dto);

        verify(userRepository, times(1)).findByDocumentOrEmail(dto.getDocument(), dto.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

	@Test(expected = AlreadyUserException.class)
    public void givenAnAlreadyRegisteredUserThrowException() {
        UserDto dto = new UserDto();
		buildUser(dto);

		doReturn(Optional.of(new User()))
				.when(userRepository).findByDocumentOrEmail(eq(dto.getDocument()), eq(dto.getEmail()));

        userService.createUser(dto);

		verify(userRepository, times(1)).findByDocumentOrEmail(dto.getDocument(), dto.getEmail());
		verify(userRepository, times(0)).save(any(User.class));
    }

    @Test(expected = RecordNotFoundException.class)
    public void givenAUserIdNotFoundThrowException() {
    	userService.findUser(1L);

		verify(userRepository, times(1)).findById(any());
	}

	@Test
	public void shouldRegisterAnAddress() {

		AddressDto addressDto = new AddressDto();
		addressDto.setZipCode("24745893");

		when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
		when(zipCodeService.findZipeCode(addressDto.getZipCode())).thenReturn(new AddressViaCep());

		userService.registerAddress(1L, addressDto);

		verify(userRepository, times(1)).save(any(User.class));
		verify(userRepository, times(1)).findById(any());
		verify(zipCodeService, times(1)).findZipeCode(any());
	}

	private void buildUser(UserDto dto) {
		dto.setName("Ususario 1");
		dto.setDocument("16516763720");
		dto.setEmail("usuario@gmail.com");
		dto.setBirthDate(LocalDate.now());
	}

}
