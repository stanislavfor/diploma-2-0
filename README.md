## 1. Сборка проекта

Открыть консоль в корне проекта (где находится `pom.xml`) и выполнить:

```
./mvnw clean package
```

или, если Maven установлен глобально:

```
mvn clean package
```

После этого в папке `target/` появится `.jar`-файл, например:

```
target/diploma-2-0-0.0.1-SNAPSHOT.jar
```



## 2. Запуск приложения

Чтобы запустить приложение, выполнить команду:

```bash
java -jar target/diploma-2-0-0.0.1-SNAPSHOT.jar
```

Если менялось имя JAR-файла или `artifactId`, нужно скорректировать имя файла для команды запуска.


## SQL-запросы для создания базы данных PostgreSQL

Создание базы данных и таблиц в PostgreSQL:

```
-- Создать базу данных
CREATE
DATABASE images_db;


-- Создать таблицу users
CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL    
);

-- Создать таблицу item
CREATE TABLE item
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    link        VARCHAR(200)
);

```

Добавление значений в Таблицу 'users'

```
INSERT INTO users (login, password)
VALUES ('admin', '$2a$12$LbAPCsHn8ZN5MUDqDmIX7e9n1YlDkCxEt0lW3Q2WuW0M1vteo8jvG'), -- пароль зашифрован
       ('user', '$2a$12$.dlnBAYq6sOUumn3jtG.AepxdSwGxJ8xA2iAPoCHSH61Vjl.JbIfq') -- пароль зашифрован


```

#### Страница авторизации

Это страница, на которой пользователи могут ввести свои логин и пароль для входа в систему.<br>
URL: http://localhost:8084/my-app

## Главная страница (index.html):

### URL: http://localhost:8084/images

<br><br>