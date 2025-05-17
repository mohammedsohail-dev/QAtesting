
# 🤖 Moving-Robot Simulator

[![Java](https://img.shields.io/badge/Java-1.8-blue)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Build-blue)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/Testing-JUnit4%20%26%205-brightgreen)](https://junit.org/)
[![Coverage](https://img.shields.io/badge/Test%20Coverage-JaCoCo-orange)](https://www.eclemma.org/jacoco/)

This Java-based Robot Simulator lets you control a robot in a 2D room using simple text commands. It supports movement, pen up/down drawing, grid printing, and boundary validation. Tests and coverage are integrated using **JUnit** and **JaCoCo**.

---

## 📁 Project Structure

Moving-Robot-main/
├── src/
│ ├── main/java/sourceCode/
│ │ └── Robot.java # Core logic for robot movement
│ └── test/java/test/
│ ├── RobotTest.java # JUnit tests for robot logic
│ └── QATest.java # Additional tests for edge cases
├── target/ # Compiled classes and reports
├── pom.xml # Maven configuration
└── README.md


---

## 🔧 Features

- `I n` – Initialize the grid (e.g., `I 5` for 5x5)
- `M n` – Move the robot forward `n` steps
- `L` / `R` – Turn left or right
- `U` / `D` – Pen up or pen down
- `P` – Print the room grid and robot's path
- `C` – Print robot's current status (position, direction, pen)

Includes boundary checks, invalid command handling, and a clear room printout.

---

## 🚀 Getting Started

### 📋 Prerequisites
- Java 8+
- Maven 3+

### 📦 Install and Run

```
git clone https://github.com/mohammedsohail-dev/QAtesting.git
cd Moving-Robot-main
mvn clean compile
```
   

🧪 Testing & Coverage
✅ Run Unit Tests
```
mvn test
```

Uses JUnit 4 (for RobotTest.java) and JUnit 5 (QATest.java).

JUnit Vintage engine bridges the two.

📊 Generate JaCoCo Coverage Report
```
mvn clean test
mvn package
```
Then open the report:

target/site/jacoco/index.html

View detailed method and line-level coverage.
💡 Sample Commands
```
I 5
D
M 3
R
M 2
C
P
```
Expected output:

    Robot moves 3 steps forward with pen down.

    Turns right and moves 2 steps.

    Room grid printed with * marking the path.
