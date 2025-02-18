package org.example.app.entity.user;

import org.example.app.dto.user.UserDtoRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User dtoCreateToEntity(UserDtoRequest request) {
        User user = new User();

        Long id = request.id();
        if (id != null) user.setId(id);

        String firstName = request.firstName();
        if (firstName != null) {
            if (!firstName.isBlank())
                user.setFirstName(firstName);
        }

        String lastName = request.lastName();
        if (lastName != null) {
            if (!lastName.isBlank())
                user.setLastName(lastName);
        }

        String email = request.email();
        if (email != null) {
            if (!email.isBlank())
                user.setEmail(email);
        }

        return user;
    }

    public User dtoUpdateToEntity(Long id, UserDtoRequest request,
                                  User userToUpdate) {
        if (id != null) userToUpdate.setId(id);

        String firstName = request.firstName();
        if (firstName != null) {
            if (!firstName.isBlank())
                userToUpdate.setFirstName(firstName);
        }

        String lastName = request.lastName();
        if (lastName != null) {
            if (!lastName.isBlank())
                userToUpdate.setLastName(lastName);
        }

        String email = request.email();
        if (email != null) {
            if (!email.isBlank())
                userToUpdate.setEmail(email);
        }

        return userToUpdate;
    }
}
