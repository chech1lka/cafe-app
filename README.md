# ☕ Cafe Management System

> REST API для управления кафе — Java Spring Boot

**Команда:** Rustem · Dias · Ilkhan

---

## О проекте

Веб-приложение для управления кафе. Позволяет просматривать меню, создавать заказы, изменять и отменять их. Всё хранится в реляционной базе данных H2. Взаимодействие — через JSON REST API.

---

## Технологии

| Технология | Версия | Назначение |
|---|---|---|
| Java | 22 | Язык программирования |
| Spring Boot | 3.2.0 | Основной фреймворк |
| Spring Data JPA | 3.2.0 | ORM, работа с базой данных |
| Hibernate | 6.x | Реализация JPA, генерация SQL |
| H2 Database | 2.x | In-memory реляционная БД |
| Lombok | 1.18.x | Уменьшение boilerplate кода |
| Maven | 3.8+ | Сборка и управление зависимостями |

---

## Архитектура

Проект построен на стандартной 3-слойной архитектуре Spring Boot:

```
HTTP Request → Controller → Service → Repository → Database (H2)
```

| Слой | Назначение |
|---|---|
| **Controller** | Принимает HTTP запросы, возвращает JSON. Логики нет. |
| **Service** | Вся бизнес-логика: проверки, подсчёты, правила. |
| **Repository** | Только чтение и запись в БД через JPA. |
| **Model** | JPA Entity — описывает таблицы в БД. |
| **DTO** | Объекты для передачи данных через API. |

---

## Сущности (5 таблиц)

- **FoodCategory** — категория еды (Hot Drinks, Main Dishes и т.д.)
- **Food** — блюдо или напиток с ценой и статусом доступности
- **Person** — клиент с именем, email и телефоном
- **Order** — заказ с уникальным `orderNumber` (ORD-...), статусом и итоговой ценой
- **OrderItem** — позиция в заказе (связь Order ↔ Food с количеством и ценой)

### Статусы заказа

```
PENDING → CONFIRMED → PREPARING → READY → DELIVERED
                                        ↘ CANCELLED
```

---

## Запуск

**Требования:** Java 17+, Maven 3.6+ (или IntelliJ IDEA)

```bash
mvn clean install
mvn spring-boot:run
```

| URL | Описание |
|---|---|
| `http://localhost:8080` | Базовый URL API |
| `http://localhost:8080/h2-console` | Визуальная консоль БД |

**H2 Console:** JDBC URL: `jdbc:h2:mem:cafedb` · Username: `sa` · Password: *(пусто)*

При запуске автоматически создаются тестовые данные: 5 категорий, 10 блюд, 3 клиента, 1 заказ.

---

## API Endpoints

### Категории `/api/categories`

| Метод | URL | Описание |
|---|---|---|
| GET | `/api/categories` | Все категории |
| GET | `/api/categories/{id}` | Категория по ID |
| POST | `/api/categories` | Создать категорию |
| PUT | `/api/categories/{id}` | Обновить категорию |
| DELETE | `/api/categories/{id}` | Удалить категорию |

### Еда `/api/foods`

| Метод | URL | Описание |
|---|---|---|
| GET | `/api/foods` | Все блюда |
| GET | `/api/foods/available` | Только доступные |
| GET | `/api/foods/category/{id}` | Блюда по категории |
| GET | `/api/foods/{id}` | Блюдо по ID |
| POST | `/api/foods` | Создать блюдо |
| PUT | `/api/foods/{id}` | Обновить блюдо |
| DELETE | `/api/foods/{id}` | Удалить блюдо |

### Клиенты `/api/persons`

| Метод | URL | Описание |
|---|---|---|
| GET | `/api/persons` | Все клиенты |
| GET | `/api/persons/{id}` | Клиент по ID |
| POST | `/api/persons` | Создать клиента |
| PUT | `/api/persons/{id}` | Обновить клиента |
| DELETE | `/api/persons/{id}` | Удалить клиента |

### Заказы `/api/orders`

| Метод | URL | Описание |
|---|---|---|
| GET | `/api/orders` | Все заказы |
| GET | `/api/orders/{id}` | Заказ по ID |
| GET | `/api/orders/number/{orderNumber}` | Заказ по номеру ORD-... |
| GET | `/api/orders/person/{personId}` | Заказы клиента |
| GET | `/api/orders/status/{status}` | Заказы по статусу |
| POST | `/api/orders` | Создать заказ |
| PUT | `/api/orders/{id}` | Изменить позиции заказа |
| PATCH | `/api/orders/{id}/cancel` | Отменить заказ |
| PATCH | `/api/orders/{id}/status?status=X` | Изменить статус |
| DELETE | `/api/orders/{id}` | Удалить заказ |

---

## Примеры запросов

**Создать заказ:**
```json
POST /api/orders
{
  "personId": 1,
  "notes": "No sugar",
  "items": [
    { "foodId": 2, "quantity": 2 },
    { "foodId": 8, "quantity": 1 }
  ]
}
```

**Отменить заказ:**
```
PATCH /api/orders/1/cancel
```

**Изменить статус:**
```
PATCH /api/orders/1/status?status=CONFIRMED
```

**Найти по номеру:**
```
GET /api/orders/number/ORD-1777808906927
```

---

## Структура проекта

```
src/main/java/com/cafe/app/
├── CafeApplication.java       # Точка входа
├── DataSeeder.java            # Тестовые данные при запуске
├── model/                     # JPA сущности (таблицы БД)
├── dto/                       # Data Transfer Objects
├── repository/                # Слой данных (Spring Data JPA)
├── service/                   # Бизнес-логика
├── controller/                # REST контроллеры
└── exception/                 # Глобальная обработка ошибок
```

---

## Авторы

| Имя | GitHub | Вклад |
|---|---|---|
| Rustem | [@Chech1lka](https://github.com/Chech1lka) | Model + Repository layer |
| Dias | [@dias-github](https://github.com) | Service layer |
| Ilkhan | [@ilkhan-github](https://github.com) | Controller + DTO + Exception handling |
