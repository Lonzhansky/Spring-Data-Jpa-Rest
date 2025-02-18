package org.example.app.controller;

import org.example.app.dto.user.*;
import org.example.app.entity.user.User;
import org.example.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

// @RestController - комбінація @Controller і @ResponseBody,
// що означає, що замість рендерингу сторінок він просто відповідає
// даними, які ми йому надали.
// Це природно для REST API, повертати інформацію після
// потрапляння в кінцеву точку API.
// @GetMapping, @DeleteMapping, @PostMapping
// зазначають типи HTTP-запитів, які оброблюють методи.
// Це похідні варіанти анотації @RequestMapping з методом
// RequestMethod.METHOD, встановленим для відповідних типів.
// @RequestMapping зіставляє REST-запити з контролером або
// методами оброблювача.
@RestController
@RequestMapping("api/v1/users")
public class UserController {

//    @Autowired
//    @Qualifier("userServiceImpl")
//    private UserService userService;

//    @Autowired
//    public void setUserService(@Qualifier("userServiceImpl") UserService userService) {
//        this.userService = userService;
//    }

    private final UserService userService;

    public UserController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDtoCreateResponse> createUser(
            @RequestBody UserDtoRequest request) {
        User user = userService.create(request);
        // ternary operator usage
        return (user != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoCreateResponse.of(true,
                                user)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoCreateResponse.of(false,
                                null));
    }

    @GetMapping
    public ResponseEntity<UserDtoListResponse> getAllUsers() {
        Optional<List<User>> optional = userService.getAll();
        if (optional.isPresent()) {
            List<User> list = optional.get();
            return (!list.isEmpty()) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(UserDtoListResponse.of(false,
                                    list)) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(UserDtoListResponse.of(true,
                                    Collections.emptyList()));
        } else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(UserDtoListResponse.of(true,
                            Collections.emptyList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoGetByIdResponse> getUserById(
            @PathVariable("id") Long id) {
        User user = userService.getById(id);
        return (user != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoGetByIdResponse.of(id, true,
                                user)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoGetByIdResponse.of(id, false,
                                null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDtoUpdateResponse> updateUserById(
            @PathVariable("id") Long id,
            @RequestBody UserDtoRequest request) {
        return (userService.getById(id) != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoUpdateResponse.of(id, true,
                                userService.updateById(id, request))) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoUpdateResponse.of(id, false,
                                null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDtoDeleteResponse> deleteUserById(
            @PathVariable(value = "id") Long id) {
        return (userService.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoDeleteResponse.of(id, true)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoDeleteResponse.of(id, false));
    }

    @GetMapping("/last-entity")
    public ResponseEntity<UserDtoGetLastEntityResponse> getLastEntity() {
        User user = userService.findFirstByOrderByIdDesc();
        return user != null ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoGetLastEntityResponse.of(true,
                                user)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoGetLastEntityResponse.of(false,
                                null));
    }

    @GetMapping("/first-name/{firstName}")
    public ResponseEntity<UserDtoListResponse> getByFirstName(
            @PathVariable(value = "firstName") String firstName) {
        List<User> list = userService.findByFirstName(firstName);
        return (!list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoListResponse.of(false,
                                list)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoListResponse.of(true,
                                Collections.emptyList()));
    }

    @GetMapping("/last-name/{lastName}")
    public ResponseEntity<UserDtoListResponse> getByLastName(
            @PathVariable(value = "lastName") String lastName) {
        List<User> list = userService.findByLastName(lastName);
        return (!list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoListResponse.of(false,
                                list)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoListResponse.of(true,
                                Collections.emptyList()));
    }
}
