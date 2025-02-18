package org.example.app.dto.user;

import org.springframework.http.HttpStatus;

public record UserDtoDeleteResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message) {

    public static final String SUCCESS_MESSAGE = "User with id %s has been deleted successfully.";
    public static final String FAILURE_MESSAGE = "User with id %s has not been found!";

    public static UserDtoDeleteResponse of(Long id, boolean isUserFound) {
        return isUserFound ?
                new UserDtoDeleteResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        true, SUCCESS_MESSAGE.formatted(id)) :
                new UserDtoDeleteResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        false, FAILURE_MESSAGE.formatted(id));
    }
}
