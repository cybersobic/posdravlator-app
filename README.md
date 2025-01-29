# Приложение "Поздравлятор" - ведение списка дней рождения

<img src="https://cdn1.iconfinder.com/data/icons/business-vivid-vol-2/256/Schedule-Plan-1024.png" width="400" />

## Функциональность приложения:
- Отображение списка сегодняшних ДР
- Отображение списка ближайших ДР
- Отображение списка будущих ДР
- Добавление записей в список ДР
- Редактирование записей в списке ДР
- Удаление записей из списка ДР

## Приложение использует следующие технологии:
- JDK 21
- Spring Boot 3.4.1
- Maven
- PostgreSQL
- MongoDB
- HTML/CSS
- JavaScript
- Docker & docker-compose

## Конфигурация MongoDB:
!!! Необходимо обязательно перейти в файл `application.properties` и выбрать конфигурацию запуска
### Локальный запуск:
```
spring.data.mongodb.uri=mongodb://localhost:27017/posdravlator
```
### Запуск в Docker:
```
spring.data.mongodb.uri=mongodb://mongo:27017/posdravlator
```

## Запуск приложения:
В проекте присутствует **Makefile**, который позволяет быстро запустить приложение через Docker.

!!! Запуск приложения через Docker требует порты для PosgreSQL (5432) и MongoDB (27017), поэтому завершите эти процессы локально если они запущены.