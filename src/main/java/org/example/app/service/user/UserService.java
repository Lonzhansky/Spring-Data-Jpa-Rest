package org.example.app.service.user;

import org.example.app.dto.user.UserDtoRequest;
import org.example.app.entity.user.User;
import org.example.app.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends BaseService<User, UserDtoRequest> {
    User create(UserDtoRequest request);
    Optional<List<User>> getAll();
    User getById(Long id);
    User updateById(Long id, UserDtoRequest request);
    boolean deleteById(Long id);
    User findFirstByOrderByIdDesc();
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
}
