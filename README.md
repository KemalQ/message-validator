# 📦 Message Validator (Reusable Snippet)

Lightweight, transport-independent DTO validation utility based on **Jakarta Bean Validation**.

Designed to be copied into any Java / Spring project (REST, MQ, Kafka, microservices).  
No dependency on RabbitMQ, Spring Boot, or any infrastructure layer.

---

## 🎯 Purpose

Quickly validate DTO objects manually using a reusable validator.

Use cases:
- Validate incoming REST requests
- Validate RabbitMQ / Kafka messages
- Validate service-layer DTOs
- Validate before saving to DB
- Validate before publishing events

---

## 📁 Structure
```xml
validation/
├── ValidatableMessage.java
├── MessageValidator.java
└── example/
    └── ExampleUserEventDTO.java
```

---

# 🚀 Quick Integration

## 1️⃣ Add Dependencies

### Maven

```xml
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
</dependency>

<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
</dependency>
```

## 2️⃣ Make DTO implement ValidatableMessage

```Java
public class OrderDTO implements ValidatableMessage {

    @NotBlank
    private String orderId;

    @NotNull
    private BigDecimal amount;

    // getters/setters
}
```

## 3️⃣ Use the Validator

### Plain Java

```Java
MessageValidator validator = new MessageValidator();
validator.validate(orderDTO);
```

## Spring Boot

```Java
@Component
public class OrderService {

    private final MessageValidator validator;

    public OrderService(Validator springValidator) {
        this.validator = new MessageValidator(springValidator);
    }

    public void process(OrderDTO dto) {
        validator.validate(dto);
    }
}
```

# ❗ Validation Failure

If validation fails, an **IllegalArgumentException** is thrown.

Example:

```xml
Invalid OrderDTO: orderId: must not be blank, amount: must not be null
```

# 📌 How To Reuse (Future Me Checklist)

>1. Copy **validation** folder

>2. Add validation dependencies

>3. Make DTO implement **ValidatableMessage**

>4. Instantiate **MessageValidator**

>5. Call **validate(dto)**

Done.

# 🛠 Design Principles

-No infrastructure dependency

-Interface-based contract

-Manual validation control

-Reusable across services

-Works with and without Spring