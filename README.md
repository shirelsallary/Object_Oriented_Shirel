# Arkanoid Game Engine - A Professional Object-Oriented Implementation

> A feature-rich Arkanoid/Breakout game implemented in Java, demonstrating advanced Object-Oriented Programming principles and professional software architecture patterns.

## 🎮 Project Overview

This project is a complete implementation of the classic Arkanoid game, built progressively across 4 assignments that demonstrate the evolution from basic geometric algorithms to a full-featured game engine with proper separation of concerns, design patterns, and professional architecture.

The game features:
- **Dynamic Level System** with 5 progressively challenging levels
- **Real-time Physics Engine** with collision detection and response
- **Score Tracking & Persistence** across levels
- **Professional Animation Loop** running at 60 FPS
- **Interactive UI** with level indicators and game state transitions

---

## ✨ Key Features

### Game Mechanics
- **Multi-ball Physics**: 1-3 balls per level with realistic velocity and collision mechanics
- **Paddle Control**: Responsive keyboard-controlled paddle with variable sizes
- **Block Destruction**: Hit-event-driven block removal with immediate feedback
- **Collision Detection**: Advanced trajectory-based collision system preventing ball penetration
- **Score System**: Dynamic scoring (5 points per block, 100 bonus for level completion)

### Visual Design
- **5 Unique Levels**:
  1. **Direct Hit**: Single block, precision challenge (1 ball)
  2. **Wide Easy**: 15 blocks in a row with sun background (3 balls)
  3. **Green 3**: Triangular block pattern with building background (2 balls)
  4. **Final Four**: 60 blocks in a grid, vertical movement challenge (3 balls)
  5. **Original Level**: Dense 72-block layout (3 balls)

- **Professional Decorations**: Background Sprites (sun, building) that enhance theme without affecting gameplay
- **State-aware UI**: Real-time level and score display

### User Experience
- **Countdown Animation**: 3-second pre-level countdown before play begins
- **Level Completion Screens**: Pause between levels for player readiness
- **End-game Screens**: Game Over and Victory screens with final scores
- **Sticky Key Prevention**: Smart key press handling to avoid accidental input multiple frames

---

## 🏗️ Architecture & Design Patterns

This project is a masterclass in applying professional software design patterns, making it an ideal portfolio piece for demonstrating technical competence.

### 1. **Observer Pattern** ⭐

**Implementation**: Hit event system for blocks and balls

```
Block (Subject) 
  ├── HitListener interface (Observer)
  ├── BlockRemover (Concrete Observer)
  ├── BallRemover (Concrete Observer)
  └── ScoreTrackingListener (Concrete Observer)
```

**Key Benefits:**
- Decouples block logic from removal/scoring logic
- Listeners are notified dynamically when blocks are hit
- Enables simultaneous block removal and score updates without tight coupling
- Safe iteration using `ConcurrentModificationException` prevention (copy-on-iterate pattern)

**Code Highlight:**
```java
// Block notifies all listeners safely
private void notifyHit(Ball hitter) {
    List<HitListener> listeners = new ArrayList<>(this.hitListeners);
    for (HitListener hl : listeners) {
        hl.hitEvent(this, hitter);
    }
}
```

### 2. **Decorator Pattern** ⭐

**Implementation**: `KeyPressStoppableAnimation` wrapper

```
Animation (Component)
  └── KeyPressStoppableAnimation (Concrete Decorator)
        ├── Wraps any Animation
        ├── Adds keyboard responsiveness
        └── Prevents sticky-key issues
```

**Key Benefits:**
- Wraps any animation with key-press functionality
- Solves the "sticky key" problem by tracking key state at animation start
- Enables code reuse (pause screens, level complete screens, end screens all use same decorator)
- Follows Open/Closed Principle (open for extension, closed for modification)

**Usage Example:**
```java
Animation pauseScreen = new PauseScreen(keyboard);
Animation stoppable = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, pauseScreen);
animationRunner.run(stoppable);
```

### 3. **Composite Pattern & Sprite System**

**Implementation**: Game object management via collections

```
Sprite Collection (Composite)
  ├── Sprites (Leaf): Ball, Paddle, ScoreIndicator, Background
  └── Composite operations: drawAllOn(), notifyAllTimePassed()

GameEnvironment (Composite)
  ├── Collidables: Blocks, Walls, Paddle
  └── Queries: getClosestCollision()
```

**Key Benefits:**
- Treats individual sprites and collections uniformly
- Enables batch operations (draw all, update all) efficiently
- Easy to add/remove game objects dynamically
- Clear separation between visual and collision concerns

### 4. **Animation Engine & Strategy Pattern**

**Implementation**: Pluggable animation system

```
AnimationRunner (Engine)
  ├── Animation interface (Strategy)
  ├── GameLevel (Concrete Strategy)
  ├── CountdownAnimation (Concrete Strategy)
  ├── PauseScreen (Concrete Strategy)
  ├── GameOverScreen (Concrete Strategy)
  ├── LevelCompleteScreen (Concrete Strategy)
  └── YouWinScreen (Concrete Strategy)
```

**Key Benefits:**
- Separation of Animation rendering from game logic
- Any Animation can run through the same 60 FPS loop
- Easy to add new screen types or animations
- Game loop logic is centralized and reusable

**Architecture:**
```java
public interface Animation {
    void doOneFrame(DrawSurface d);
    boolean shouldStop();
}

public class AnimationRunner {
    public void run(Animation animation) {
        while (!animation.shouldStop()) {
            // Fixed 60 FPS timing
            animation.doOneFrame(drawSurface);
        }
    }
}
```

### 5. **Adapter/Adapter-like Interface Pattern**

**Implementation**: GameInterface and LevelInformation

- `GameInterface`: Abstracts game for listeners and sprites
- `LevelInformation`: Defines contract for level configuration
- Enables flexible level design and easy addition of new levels

### 🔄 Data Flow Architecture

```
GameFlow (Orchestrator)
    ├── Creates GameLevel for each LevelInformation
    ├── Runs CountdownAnimation
    ├── Runs GameLevel (Animation)
    └── Handles Level Transitions
        ├── LevelCompleteScreen (Win path)
        ├── GameOverScreen (Lose path)
        └── YouWinScreen (All levels complete)
```

---

## 📚 Development Roadmap

The project evolved across 4 assignments, each adding layers of complexity:

### **Assignment 1: Geometry Foundation**
- Implemented geometric primitives: `Point`, `Line`, `Velocity`, `Rectangle`
- Developed collision detection algorithms
- **Concepts**: Basic OOP, geometric math, vectors

### **Assignment 2: Collision & Movement System**
- Created `Ball` with physics simulation
- Implemented `Collidable` interface for obstacle types
- Built `GameEnvironment` for trajectory-based collision detection
- **Concepts**: Polymorphism, interface design, trajectory algorithms

### **Assignment 3: Interactive Game**
- Implemented `Block`, `Paddle`, interactive gameplay
- Created event listeners for game mechanics
- Built core game loop and animation framework
- **Concepts**: Observer Pattern, game loops, real-time rendering

### **Assignment 4: Multi-level Engine & Animations** (Current)
- Refactored to `Animation` interface and `AnimationRunner`
- Implemented 5 levels with `LevelInformation` interface
- Added professional UI with screen transitions
- Applied Decorator Pattern for key-press handling
- **Concepts**: Advanced design patterns, architecture patterns, separation of concerns

---

## 🖼️ Visual Preview

> **Screenshot/GIF Placeholder**: Insert gameplay screenshot showing:
> - Game board with paddle, balls, blocks
> - Score indicator at top
> - Level layout variation
>
> Example location: `/assets/gameplay.gif`

---

## 🛠️ Technologies Used

| Technology | Purpose |
|-----------|---------|
| **Java** | Core language (OOP, Collections, Interfaces) |
| **biuoop** | Graphics rendering library (DrawSurface, GUI, Keyboard input) |
| **Object-Oriented Programming** | Interfaces, inheritance, polymorphism, abstraction |
| **Design Patterns** | Observer, Decorator, Composite, Strategy, Adapter |
| **Git** | Version control (implicit from development process) |

---

## 📋 Project Structure

```
Object_Oriented_Shirel/
├── src/
│   ├── Core Geometry/
│   │   ├── Point.java
│   │   ├── Line.java
│   │   ├── Rectangle.java
│   │   └── Velocity.java
│   │
│   ├── Game Objects/
│   │   ├── Ball.java
│   │   ├── Block.java
│   │   ├── Paddle.java
│   │   └── *.java
│   │
│   ├── Collision System/
│   │   ├── Collidable.java (Interface)
│   │   ├── CollisionInfo.java
│   │   ├── GameEnvironment.java
│   │   └── Sprite.java (Interface)
│   │
│   ├── Event System (Observer Pattern)/
│   │   ├── HitListener.java (Interface)
│   │   ├── HitNotifier.java (Interface)
│   │   ├── BlockRemover.java
│   │   ├── BallRemover.java
│   │   ├── ScoreTrackingListener.java
│   │   └── Counter.java
│   │
│   ├── Animation Engine/
│   │   ├── Animation.java (Interface)
│   │   ├── AnimationRunner.java
│   │   ├── GameLevel.java (implements Animation)
│   │   ├── KeyPressStoppableAnimation.java (Decorator)
│   │   └── *.java (Screen animations)
│   │
│   ├── Level System/
│   │   ├── LevelInformation.java (Interface)
│   │   ├── DirectHit.java
│   │   ├── WideEasy.java
│   │   ├── Green3.java
│   │   ├── FinalFour.java
│   │   ├── OriginalLevel.java
│   │   └── *.java (Background decorations)
│   │
│   └── Main/
│       ├── Ass4Game.java (Entry point for Assignment 4)
│       └── GameFlow.java (Game orchestrator)
│
├── bin/ (Compiled .class files)
├── libs/ (biuoop-1.4.jar)
└── README.md (this file)
```

---

## 🚀 How to Run

### Prerequisites
- **Java 8+** installed
- `biuoop-1.4.jar` library (included in `/libs` directory)

### Compilation

```bash
cd Object_Oriented_Shirel
javac -cp libs/biuoop-1.4.jar src/*.java -d bin
```

### Execution

**Run the full 5-level game:**
```bash
java -cp "bin;libs/biuoop-1.4.jar" Ass4Game
```

**Run individual level (example - Direct Hit):**
```bash
java -cp "bin;libs/biuoop-1.4.jar" GameLevel
# (Note: Modify GameLevel.java to accept command-line arguments for level selection)
```

### Gameplay Controls

| Key | Action |
|-----|--------|
| **LEFT ARROW** | Move paddle left |
| **RIGHT ARROW** | Move paddle right |
| **SPACE** | Advance screens (pause, level complete, game over) |

### Game Rules

- **Objective**: Destroy all blocks on each level without losing all balls
- **Lives**: 3 balls per level (if all are lost, Game Over)
- **Scoring**: +5 points per block destroyed, +100 bonus for completing a level
- **Victory**: Complete all 5 levels to win

---

## 📊 Key Metrics

| Metric | Value |
|--------|-------|
| **Total Classes** | 30+ |
| **Interfaces** | 8 |
| **Design Patterns Applied** | 5+ |
| **Lines of Code** | ~3000+ |
| **Assignment Iterations** | 4 |

---

## 🎓 Learning Outcomes

This project demonstrates:

✅ **Solid OOP Principles**
- Encapsulation: Data hiding within classes
- Inheritance: Interface-based hierarchies
- Polymorphism: Different behavior through common interfaces
- Abstraction: Complex systems hidden behind simple interfaces

✅ **Professional Design Patterns**
- Observer Pattern for loose coupling
- Decorator Pattern for dynamic behavior extension
- Composite Pattern for hierarchical object management
- Strategy Pattern for interchangeable algorithms

✅ **Clean Code Practices**
- Clear naming conventions
- Single Responsibility Principle
- DRY (Don't Repeat Yourself)
- Immutability where appropriate

✅ **Real-time Systems**
- Fixed 60 FPS game loop
- Collision detection optimization
- Safe concurrent collection modifications

---

## 🔧 Future Enhancements

Potential improvements to showcase advanced skills:

- **Power-ups System**: Temporary effects (expanded paddle, slower balls, multi-ball)
- **Persistence**: Save/load game state
- **Difficulty Scaling**: Dynamic AI for ball placement
- **Sound System**: Audio cues for collisions and events
- **Mobile Support**: Touch controls for mobile devices
- **Unit Tests**: JUnit test suite for collision detection
- **Configuration System**: XML/JSON level definitions

---

## 📝 Code Quality & Best Practices

This codebase adheres to:

- **Java Naming Conventions**: `camelCase` for variables, `PascalCase` for classes
- **Clean Architecture**: Clear separation between UI, game logic, and data
- **Documentation**: Javadoc comments on public methods and classes
- **Error Prevention**: Safe collection operations, null checks
- **Scalability**: Easy to add new levels, animations, or game objects

---

## 👨‍💼 Professional Context

**Ideal For Showcasing To:**
- Companies valuing clean code and architectural patterns
- Roles emphasizing software design
- Teams building game engines or interactive systems
- Organizations valuing educational background and fundamentals

**Highlights for Recruiters:**
1. Demonstrates understanding of industry-standard design patterns
2. Shows progression from simple to complex (4-assignment arc)
3. Clean, maintainable, well-organized codebase
4. Practical application of OOP principles
5. Real-time system implementation skills

---

## 📖 Additional Resources

- **Design Patterns Reference**: Gang of Four book, refactoring.guru
- **Java OOP Guide**: Oracle Java Tutorials
- **Game Development**: Game Engine Architecture concepts
- **Real-time Systems**: Frame-based animation principles

---

## 📄 License & Attribution

**Author**: Shirel Sallary  
**Course**: Object-Oriented Programming (Academic Project)  
**Library**: biuoop - Graphics library by Jelle/TechEd team

---

## 📧 Contact & Questions

For questions about the codebase, design decisions, or architectural patterns used:
> [Your Email Here] | [Your LinkedIn] | [Your GitHub]

---

**⭐ If you find this project useful, please consider giving it a star! It helps showcase quality work to other developers and recruiters.**

