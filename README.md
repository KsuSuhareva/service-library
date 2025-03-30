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

