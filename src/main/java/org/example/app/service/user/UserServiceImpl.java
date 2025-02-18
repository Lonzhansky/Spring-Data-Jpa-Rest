package org.example.app.service.user;

import org.example.app.dto.user.UserDtoRequest;
import org.example.app.entity.user.User;
import org.example.app.entity.user.UserMapper;
import org.example.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Qualifier("userServiceImpl")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(UserDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        User user = mapper.dtoCreateToEntity(request);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<List<User>> getAll() {
        Iterable<User> iterable = userRepository.findAll();
        // Конвертуємо Iterable в List,
        // оскільки interface CrudRepository<T, ID>
        // має саме метод Iterable<T> findAll();
        List<User> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .toList();
        // Запаковуємо List в Optional та повертаємо
        return Optional.of(list);
    }

    @Override
    @Transactional
    public User getById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        return userRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public User updateById(Long id, UserDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided!");
        }
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User userToUpdate =
                    mapper.dtoUpdateToEntity(id, request,
                            optional.get());
            userRepository.save(userToUpdate);
        }
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public User findFirstByOrderByIdDesc() {
        return userRepository.findFirstByOrderByIdDesc()
                .orElse(null);
    }

    @Override
    @Transactional
    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName)
                .orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public List<User> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName)
                .orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public List<User> findByEmailContaining(String emailPart) {
        return userRepository.findByEmailContaining(emailPart);
    }
}
