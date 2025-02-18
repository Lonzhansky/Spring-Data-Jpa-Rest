

ТЕХСТЕК

JDK
https://www.oracle.com/java/technologies/downloads/
(якщо встановлена, то Apache Tomcat має підтягнути встановлену)

Apache Tomcat
https://tomcat.apache.org/
(якщо Apache Tomcat вже встановлено, то можна скористатися встановленим)

Spring Framework
https://spring.io/

Spring Data JPA
https://spring.io/projects/spring-data-jpa

PostgreSQL
https://www.postgresql.org/

Hibernate 6
https://hibernate.org/

Jackson
https://github.com/FasterXML/jackson

Lombok
https://projectlombok.org/

Apache Maven WAR Plugin
https://maven.apache.org/plugins/maven-war-plugin/index.html

------------------------

JAVA ПРОЕКТ

(1) Налаштовуємо БД ( INFO/SQLs.sql ).
(2) Створюємо Maven-проект.
(3) Додаємо залежності ( pom.xml ).
(4) Формуємо та відповідно структуруємо
необхідний контент ( src/main ).

------------------------

РОЗГОРТАННЯ (ДЕПЛОЙ) ПРОЕКТУ

Запустимо Apache Tomcat.
Створемо секцію Git Bash.
В IDE внизу

Terminal >  <іконка-випадаючого-списку>  > Git Bash

В секції буде шлях до директорії поточного проекту

<your-base-path>/<your-project-name>

Переходимо до директорії, де розташовані файли запуску
та припинення роботи Apache Tomcat

$ cd <your-base-path>/apache-tomcat-<version>/bin

Виконуємо команду

$ ./startup.bat

УВАГА.
Запуск Apache Tomcat: startup.bat - для Windows,
startup.sh - для MacOS/Linux.
Припинення Apache Tomcat: shutdown.bat - для Windows,
shutdown.sh - для MacOS/Linux.

Окремо з'явиться інформаційне вікно,
де відображається інформація про роботу
Apache Tomcat та програми.

Можемо згорнути його.

В IDE відкриваємо бокову праворуч вкладку Maven.
Через меню вкладки відкриваємо вікно команд,
де послідовно знаходимо та подвійним кліком
запускаємо відповідні Maven-команди

mvn clean

mvn install

mvn war:war

В директорії проекту target , знаходимо файл
Your-Project-Name-1.0-SNAPSHOT.war та копіюємо його
у відповідну директорію Apache Tomcat, в файловій системі
комп'ютера

<your-base-path>/apache-tomcat-<version>/webapps

Через декілька секунд, в цій директорії, має з'явитися
папка Your-Project-Name-1.0-SNAPSHOT.
Apache Tomcat розархівував архівний файл проекту.

Тестуємо функціонал програми.

------------------------

РЕСУРСИ

https://spring.io/projects/spring-data-jpa
https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
https://www.baeldung.com/spring-data-repositories
https://www.baeldung.com/spring-data-jpa-update-or-insert
https://www.baeldung.com/spring-data-jpa-last-record
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
https://www.baeldung.com/spring-response-entity
https://www.baeldung.com/hibernate-identifiers
https://www.baeldung.com/spring-autowire
https://www.baeldung.com/spring-qualifier-annotation
