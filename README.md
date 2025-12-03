# OCP Exercise - Discount Calculator System

A Java-based demonstration project showcasing SOLID principles, specifically the Open/Closed Principle (OCP), through the implementation of Strategy and Factory design patterns in a discount calculation system.

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Design Patterns](#design-patterns)
  - [Strategy Pattern](#strategy-pattern)
  - [Factory Pattern](#factory-pattern)
- [SOLID Principles](#solid-principles)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage Examples](#usage-examples)
- [Technologies](#technologies)
- [Author](#author)

## Overview

This project implements a flexible discount calculation system that applies various types of discounts to customer orders. The system is designed to be easily extensible, allowing new discount types to be added without modifying existing code, demonstrating the Open/Closed Principle in action.

The application supports multiple discount strategies:
- **Coupon Discount (CUPOM)**: 10% discount
- **VIP Discount (VIP)**: 15% discount
- **Seasonal Discount (SAZONAL)**: 5% discount
- **Birthday Discount (ANIVERSARIO)**: 20% discount
- **No Discount (SEM_DESCONTO)**: Applied when no discount type is specified

## Architecture

The system follows a clean, layered architecture organized into three main components:

```
┌─────────────────────────────────────────┐
│           Main Application              │
│      (Entry Point & Orchestration)      │
└──────────────┬──────────────────────────┘
               │
               ↓
┌─────────────────────────────────────────┐
│      CalculadoraDeDesconto              │
│    (Discount Calculation Service)       │
└──────────────┬──────────────────────────┘
               │
               ↓
┌─────────────────────────────────────────┐
│         DescontoFactory                 │
│    (Factory for Discount Strategies)    │
└──────────────┬──────────────────────────┘
               │
               ↓
┌─────────────────────────────────────────┐
│         Desconto Interface              │
│       (Strategy Interface)              │
└──────────────┬──────────────────────────┘
               │
      ┌────────┴────────┬─────────────┬──────────┐
      ↓                 ↓             ↓          ↓
┌──────────┐   ┌──────────┐   ┌──────────┐   ┌──────────┐
│  Cupom   │   │   VIP    │   │ Sazonal  │   │Aniversario│
│ Desconto │   │ Desconto │   │ Desconto │   │ Desconto  │
└──────────┘   └──────────┘   └──────────┘   └──────────┘
```

### Core Components

1. **Model Layer** (`pablo.tzeliks.model`)
   - `Pedido`: Represents an order with all its attributes (ID, value, discount type, items, payment status, customer email)
   - `TipoDesconto`: Enum defining available discount types

2. **Service Layer** (`pablo.tzeliks.service`)
   - `CalculadoraDeDesconto`: Orchestrates the discount calculation process

3. **Discount Strategy Layer** (`pablo.tzeliks.service.discount`)
   - `Desconto`: Strategy interface defining the contract for all discount calculations
   - Concrete implementations: `CupomDesconto`, `VipDesconto`, `SazonalDesconto`, `AniversarioDesconto`, `SemDesconto`
   - `DescontoFactory`: Factory class responsible for creating appropriate discount strategy instances

## Design Patterns

### Strategy Pattern

The **Strategy Pattern** allows the system to define a family of algorithms (discount calculations), encapsulate each one, and make them interchangeable. The strategy pattern lets the algorithm vary independently from clients that use it.

**Implementation:**

```java
// Strategy Interface
public interface Desconto {
    double calcular(double valorBruto);
}

// Concrete Strategies
public class CupomDesconto implements Desconto {
    @Override
    public double calcular(double valorBruto) {
        return valorBruto * 0.9; // 10% discount
    }
}

public class VipDesconto implements Desconto {
    @Override
    public double calcular(double valorBruto) {
        return valorBruto * 0.85; // 15% discount
    }
}
```

**Benefits:**
- Each discount calculation is isolated in its own class
- Easy to add new discount types without modifying existing code
- Eliminates conditional statements for different discount calculations
- Promotes code reusability and maintainability

### Factory Pattern

The **Factory Pattern** provides an interface for creating objects without specifying their exact classes. The `DescontoFactory` class encapsulates the logic for instantiating the appropriate discount strategy based on the discount type.

**Implementation:**

```java
public class DescontoFactory {
    public static Desconto criarDesconto(TipoDesconto tipoDesconto) {
        if (tipoDesconto == null) {
            return new SemDesconto();
        }
        
        return switch (tipoDesconto) {
            case CUPOM -> new CupomDesconto();
            case VIP -> new VipDesconto();
            case SAZONAL -> new SazonalDesconto();
            case ANIVERSARIO -> new AniversarioDesconto();
            default -> throw new IllegalArgumentException("Invalid discount type: " + tipoDesconto);
        };
    }
}
```

**Benefits:**
- Centralizes object creation logic
- Decouples client code from concrete discount implementations
- Makes it easy to introduce new discount types
- Provides a single point of control for strategy instantiation

## SOLID Principles

This project demonstrates adherence to SOLID principles, particularly emphasizing the Open/Closed Principle:

### Single Responsibility Principle (SRP)
Each class has a single, well-defined responsibility:
- `Pedido`: Represents order data
- `CalculadoraDeDesconto`: Orchestrates discount calculation
- `DescontoFactory`: Creates discount strategy instances
- Each discount class: Implements a specific discount calculation algorithm

### Open/Closed Principle (OCP)
**The core principle this project demonstrates.** The system is:
- **Open for extension**: New discount types can be added by creating new classes implementing the `Desconto` interface
- **Closed for modification**: Existing discount calculation classes don't need to be modified when adding new discount types

**Example**: To add a "Student Discount" (25% off):
1. Create `EstudanteDesconto` implementing `Desconto`
2. Add `ESTUDANTE` to the `TipoDesconto` enum
3. Update the factory's switch statement

**No existing discount classes need modification!**

### Liskov Substitution Principle (LSP)
All discount strategy implementations can be used interchangeably through the `Desconto` interface. Any code expecting a `Desconto` object will work correctly with any concrete implementation.

### Interface Segregation Principle (ISP)
The `Desconto` interface is minimal and focused, containing only the essential `calcular()` method. Clients depend only on the methods they use.

### Dependency Inversion Principle (DIP)
High-level modules (`CalculadoraDeDesconto`) depend on abstractions (`Desconto` interface) rather than concrete implementations. This makes the system flexible and easier to test.

## Project Structure

```
ocp-exercise/
├── src/
│   └── main/
│       └── java/
│           └── pablo/
│               └── tzeliks/
│                   ├── Main.java
│                   ├── model/
│                   │   ├── Pedido.java
│                   │   └── TipoDesconto.java
│                   └── service/
│                       ├── CalculadoraDeDesconto.java
│                       └── discount/
│                           ├── Desconto.java
│                           ├── DescontoFactory.java
│                           ├── CupomDesconto.java
│                           ├── VipDesconto.java
│                           ├── SazonalDesconto.java
│                           ├── AniversarioDesconto.java
│                           └── SemDesconto.java
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 22 or higher
- Maven 3.6+

### Installation

1. Clone the repository:
```bash
git clone https://github.com/PabloTzeliks/ocp-exercise.git
cd ocp-exercise
```

2. Build the project:
```bash
mvn clean compile
```

3. Run the application:
```bash
mvn exec:java -Dexec.mainClass="pablo.tzeliks.Main"
```

## Usage Examples

### Example 1: Order with Birthday Discount

```java
CalculadoraDeDesconto calculadora = new CalculadoraDeDesconto();

Pedido pedido = new Pedido();
pedido.setId("001");
pedido.setValorBruto(1000.0);
pedido.setDesconto(TipoDesconto.ANIVERSARIO);
pedido.setQuantidadeItens(1);
pedido.setPago(true);
pedido.setEmailCliente("cliente@gmail.com");

calculadora.CalcularDesconto(pedido);
// Result: valorLimpo = 800.0 (20% discount applied)
```

### Example 2: Order without Discount

```java
Pedido pedido2 = new Pedido();
pedido2.setId("002");
pedido2.setValorBruto(1000.0);
pedido2.setQuantidadeItens(1);
pedido2.setPago(false);
pedido2.setEmailCliente("cliente@gmail.com");
// desconto not set (null)

calculadora.CalcularDesconto(pedido2);
// Result: valorLimpo = 1000.0 (no discount applied)
```

### Adding a New Discount Type

To add a new discount type (e.g., Student Discount with 25% off):

1. Create the strategy class:
```java
public class EstudanteDesconto implements Desconto {
    @Override
    public double calcular(double valorBruto) {
        return valorBruto * 0.75; // 25% discount
    }
}
```

2. Add to enum:
```java
public enum TipoDesconto {
    // ... existing types
    ESTUDANTE("Estudante");
}
```

3. Update factory:
```java
case ESTUDANTE -> new EstudanteDesconto();
```

## Technologies

- **Java 22**: Core programming language
- **Maven**: Build automation and dependency management
- **Lombok**: Reduces boilerplate code with annotations (@Data)

## Author

**Pablo Tzeliks**
- GitHub: [@PabloTzeliks](https://github.com/PabloTzeliks)

### Acknowledgments

The initial purpose and foundation of this activity was created by my teacher, [Lucas](https://github.com/engineer-lucas), as an educational exercise to demonstrate SOLID principles and design patterns in action.

---

*This project serves as a practical demonstration of software engineering best practices, emphasizing clean code, maintainability, and extensibility through proven design patterns and principles.*
