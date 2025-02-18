package org.example.app.repository;

import org.example.app.entity.user.User;
import org.springframework.beans.factory.annotation.Qualifier;
// https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
// Interface JpaRepository<T,ID>, зокрема, розширює:
// - Interface CrudRepository<T,ID>
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
// Інтерфейс для загальних CRUD операцій для певного типу.
// - Interface PagingAndSortingRepository<T,ID>
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
// Фрагмент сховища для надання методів щодо отримання сутностей
// за допомогою абстракції розбиття на сторінки та сортування.
// У багатьох випадках поєднується з CrudRepository або подібним
// або з доданими вручну методами для забезпечення CRUD функціональності.
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// Репозиторії, зокрема, призначені визначення логіки для шару збереження.
// JpaRepository приймає клас сутності, а також тип даних ID,
// який він повинен використовувати для запиту.
@Qualifier("userRepository")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Значення частин назви методу findFirstByOrderByIdDesc():
    // - find повідомляє Spring Data JPA створити запит на вибірку.
    // - First вказує, що потрібно отримати перший запис із набору
    // результатів.
    // - OrderByIdDesc означає, що хочемо відсортувати записи
    // у зворотному порядку за властивістю id сутності User.
    //
    // Тут, Spring Data JPA інтелектуально оцінює назву методу.
    // Спочатку сортуються записи в порядку спадання за id.
    // Таким чином, останній запис розміщується на початку результату.
    // Потім, findFirst інтерпретується для повернення першого елемента
    // відсортованих записів.
    // Результат: маємо отримати останній запис таблиці.
    Optional<User> findFirstByOrderByIdDesc();

    // Значення частин назви методу findByFirstName(String firstName):
    // - find повідомляє Spring Data JPA створити запит на вибірку.
    // - FirstName вказує, що потрібно отримати запис(и) із набору
    // результатів за властивістю firstName сутності User,
    // що має збігатися зі значенням параметру firstName цього методу.
    // Результат: маємо отримати колекцію відповідних записів.
    Optional<List<User>> findByFirstName(String firstName);

    // Значення частин назви методу findByLastName(String lastName):
    // - find повідомляє Spring Data JPA створити запит на вибірку.
    // - LastName вказує, що потрібно отримати запис(и) із набору
    // результатів за властивістю lastName сутності User,
    // що має збігатися зі значенням параметру lastName цього методу.
    // Результат: маємо отримати колекцію відповідних записів.
    Optional<List<User>> findByLastName(String lastName);


    @Query("SELECT u FROM User u WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :emailPart, '%'))")
    List<User> findByEmailContaining(@Param("emailPart") String emailPart);
}
