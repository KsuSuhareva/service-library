Library Service — это сервис для управления книгами и абонементами. 
Позволяет:
- получать информацию об абонементах и взятых книгах;
- загружать данные из журнала "О выданных книгах" в json-формате (данные изначально кладутся в очередь и в асинхронном режиме пачками достаются из очереди и сохраняются в базу данных);
- каждый день в 9 00 отправлять уведомления о просроченных книгах, которые были взяты более 20 дней назад.

---

## 🚀 Стек технологий

- Java 21 (Corretto)
- Spring Boot (Data JPA, Kafka, Mail)
- PostgreSQL / H2
- Liquibase
- Apache Kafka
- Lombok
- Docker, Docker Compose
- Mapstruct

---

## ⚙️ Установка и запуск

```bash
git clone https://github.com/KsuSuhareva/service-library.git
cd library-service

выполнить команду из корневого каталага docker-compose up
Запусить в постмане

- получать информацию об абонементах и взятых книгах
GET http://localhost:8080/subscriptions?username=Suhareva Ksu

-  загружать данные из журнала "О выданных книгах" 
POST  http://localhost:8080/accountingbooks
{
    "data": [
        {
            "username": "ivan_petrov",
            "userFullName": "Ivan Petrov",
            "userActive": true,
            "bookName": "War and Peace",
            "bookAuthor": "Leo Tolstoy"
        },
        {
            "username": "maria_ivanova",
            "userFullName": "Maria Ivanova",
            "userActive": false,
            "bookName": "1984",
            "bookAuthor": "George Orwell"
        },
        {
            "username": "sergey_smirnov",
            "userFullName": "Sergey Smirnov",
            "userActive": true,
            "bookName": "Crime and Punishment",
            "bookAuthor": "Fyodor Dostoevsky"
        }
    ]
}



