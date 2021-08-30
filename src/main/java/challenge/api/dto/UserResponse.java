package challenge.api.dto;

import challenge.api.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {

    private String name;
    private String email;
    private String document;
    private LocalDate birthDate;
    private List<AddressResponse> addresses;

    public UserResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.document = user.getDocument();
        this.birthDate = user.getBirthDate();
        this.addresses = user.getAddresses().stream().map(AddressResponse::new).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<AddressResponse> getAddresses() {
        return addresses;
    }
}
