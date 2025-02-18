package org.example.app.repository;

import org.example.app.entity.product.Product;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Репозиторії, зокрема, призначені визначення логіки для шару збереження.
// JpaRepository приймає клас сутності, а також тип даних ID,
// який він повинен використовувати для запиту.
@Qualifier("productRepository")
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    // Значення частин назви методу findByPrice(Double price):
    // - find повідомляє Spring Data JPA створити запит на вибірку.
    // - Price вказує, що потрібно отримати запис(и) із набору
    // результатів за властивістю price сутності Product,
    // що має збігатися зі значенням параметру price цього методу.
    // Результат: маємо отримати колекцію відповідних записів.
    Optional<List<Product>> findByPrice(Double price);
}
