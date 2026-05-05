# ☕ Cafe Management System

REST API для управления кафе на Java Spring Boot.

## 🏗️ Архитектура

```
Controller → Service → Repository → Database (H2)
```

### Сущности (5 штук):
- **FoodCategory** — категория еды
- **Food** — блюдо/напиток
- **Person** — клиент
- **Order** — заказ (с orderNumber)
- **OrderItem** — позиция в заказе

## 🚀 Запуск

### Требования:
- Java 17+
- Maven 3.6+

### Команды:
```bash
# Сборка
mvn clean install

# Запуск
mvn spring-boot:run
```

Приложение запустится на: **http://localhost:8080**

H2 Console (база данных): **http://localhost:8080/h2-console**
- JDBC URL: `jdbc:h2:mem:cafedb`
- Username: `sa`
- Password: (пусто)

---

## 📋 API Endpoints

### 📂 Категории `/api/categories`
| Метод | URL | Описание |
|-------|-----|----------|
| GET | `/api/categories` | Все категории |
| GET | `/api/categories/{id}` | Категория по ID |
| POST | `/api/categories` | Создать категорию |
| PUT | `/api/categories/{id}` | Обновить категорию |
| DELETE | `/api/categories/{id}` | Удалить категорию |

### 🍔 Еда `/api/foods`
| Метод | URL | Описание |
|-------|-----|----------|
| GET | `/api/foods` | Все блюда |
| GET | `/api/foods/available` | Доступные блюда |
| GET | `/api/foods/category/{id}` | Блюда по категории |
| GET | `/api/foods/{id}` | Блюдо по ID |
| POST | `/api/foods` | Создать блюдо |
| PUT | `/api/foods/{id}` | Обновить блюдо |
| DELETE | `/api/foods/{id}` | Удалить блюдо |

### 👤 Клиенты `/api/persons`
| Метод | URL | Описание |
|-------|-----|----------|
| GET | `/api/persons` | Все клиенты |
| GET | `/api/persons/{id}` | Клиент по ID |
| POST | `/api/persons` | Создать клиента |
| PUT | `/api/persons/{id}` | Обновить клиента |
| DELETE | `/api/persons/{id}` | Удалить клиента |

### 🧾 Заказы `/api/orders`
| Метод | URL | Описание |
|-------|-----|----------|
| GET | `/api/orders` | Все заказы |
| GET | `/api/orders/{id}` | Заказ по ID |
| GET | `/api/orders/number/{orderNumber}` | Заказ по номеру (ORD-...) |
| GET | `/api/orders/person/{personId}` | Заказы клиента |
| GET | `/api/orders/status/{status}` | Заказы по статусу |
| POST | `/api/orders` | Создать заказ |
| PUT | `/api/orders/{id}` | Изменить заказ |
| PATCH | `/api/orders/{id}/cancel` | Отменить заказ |
| PATCH | `/api/orders/{id}/status?status=READY` | Изменить статус |
| DELETE | `/api/orders/{id}` | Удалить заказ |

### Статусы заказа:
`PENDING` → `CONFIRMED` → `PREPARING` → `READY` → `DELIVERED` / `CANCELLED`

---

## 🧪 Примеры запросов (curl)

```bash
# Создать клиента
curl -X POST http://localhost:8080/api/persons \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@test.com","phone":"+7700123456"}'

# Создать заказ
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"personId":1,"notes":"No sugar","items":[{"foodId":1,"quantity":2},{"foodId":7,"quantity":1}]}'

# Отменить заказ
curl -X PATCH http://localhost:8080/api/orders/1/cancel

# Изменить статус
curl -X PATCH "http://localhost:8080/api/orders/1/status?status=CONFIRMED"

# Найти заказ по номеру
curl http://localhost:8080/api/orders/number/ORD-1234567890
```

---

## 📦 Структура проекта

```
src/main/java/com/cafe/app/
├── CafeApplication.java        # Точка входа
├── DataSeeder.java             # Начальные данные
├── model/                      # Сущности БД
│   ├── FoodCategory.java
│   ├── Food.java
│   ├── Person.java
│   ├── Order.java
│   ├── OrderItem.java
│   └── OrderStatus.java
├── dto/                        # Data Transfer Objects
│   ├── FoodCategoryDTO.java
│   ├── FoodDTO.java
│   ├── PersonDTO.java
│   ├── OrderDTO.java
│   └── OrderItemDTO.java
├── repository/                 # Слой данных (JPA)
│   ├── FoodCategoryRepository.java
│   ├── FoodRepository.java
│   ├── PersonRepository.java
│   └── OrderRepository.java
├── service/                    # Бизнес-логика
│   ├── FoodCategoryService.java
│   ├── FoodService.java
│   ├── PersonService.java
│   └── OrderService.java
├── controller/                 # REST контроллеры
│   ├── FoodCategoryController.java
│   ├── FoodController.java
│   ├── PersonController.java
│   └── OrderController.java
└── exception/                  # Обработка ошибок
    ├── ResourceNotFoundException.java
    ├── BadRequestException.java
    └── GlobalExceptionHandler.java
```

## 👥 Авторы
- [Имя студента 1]
- [Имя студента 2]
- [Имя студента 3]
