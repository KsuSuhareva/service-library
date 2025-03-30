Library Service — это сервис для управления книгами и абонементами. 
Позволяет:
- получать информацию об абонементах и взятых книгах;
- загружать данные из журнала "О выданных книгах" в json-формате (данные изначально кладутся в очередь и в асинхронном режиме сохраняются в базу данных);
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

---

## ⚙️ Установка и запуск

```bash
git clone https://github.com/your-repo/library-service.git
cd library-service

выполнить команду из корневого каталага docker-compose up

---

##  Пример запросов
- получать информацию об абонементах и взятых книгах
http://localhost:8080/subscriptions?username=Suhareva Ksu
-  загружать данные из журнала "О выданных книгах" 
  http://localhost:8080/accountingbooks
[
 {
    "userLogin": "john_doe",
    "userFullName": "John Doe",
    "userEmail": "john.doe@example.com",
    "borrowAllowed": true,
    "bookTitle": "The Great Gatsby",
    "bookAuthor": "F. Scott Fitzgerald",
    "bookPublishedDate": "1925-04-10",
    "borrowedDate": "2024-03-01",
    "returnedDate": "2024-03-15"
  },
  {
    "userLogin": "jane_smith",
    "userFullName": "Jane Smith",
    "userEmail": "jane.smith@example.com",
    "borrowAllowed": false,
    "bookTitle": "1984",
    "bookAuthor": "George Orwell",
    "bookPublishedDate": "1949-06-08",
    "borrowedDate": "2024-02-15",
    "returnedDate": null
  }
]



