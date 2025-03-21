# Java RPG Game - Testing Documentation

This document outlines the testing strategy and procedures for the Java RPG game.

## Test Structure

The test suite consists of three levels of testing:

1. **Unit Tests**: Testing individual components in isolation
2. **Integration Tests**: Testing interactions between components
3. **System Tests**: End-to-end testing of the entire game

## Running Tests

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Internet connection (first run only, to download test dependencies)

### Execution

Run the test script from the project root directory:

```bash
./test/run_tests.sh
```

This script will:
1. Download required dependencies (JUnit and Hamcrest)
2. Compile all test files
3. Run the tests and generate log files

### Test Output

Test results are stored in log files located in `test/logs/` directory. Each test run creates a new log file with a timestamp.

## Test Cases

### Unit Tests

Unit tests focus on verifying individual components work correctly in isolation:

- **Character**: Movement, collision detection, inventory management
- **Map**: Loading maps, map data access
- **Events**: Trigger events, event behavior

#### Sample Inputs & Outputs

**Character Movement Test**
- Input: Character at (5,5), direction set to DOWN, moving=true
- Expected Output: Character position moves to (5,6)

**Inventory Test**
- Input: Add "Sword" and "Shield" to inventory, remove "Sword"
- Expected Output: hasItem("Sword")=false, hasItem("Shield")=true

### Integration Tests

Integration tests verify that components work together correctly:

- **Character-Map Interaction**: Characters moving on the map, collision detection
- **Event-Map Interaction**: Events triggering on the map

#### Sample Inputs & Outputs

**Character-Map Collision Test**
- Input: Position character at (2,1), try to move down into a wall at (2,2)
- Expected Output: Character remains at (2,1), movement is blocked

**Event Detection Test**
- Input: Add treasure event at (3,3), check for event at that position
- Expected Output: map.checkEvent(3,3) returns the treasure event

### System Tests

System tests verify the entire game works as expected:

- **Game Initialization**: Game starts up correctly
- **Character Movement via Input**: Character moves correctly when keys are pressed
- **Map Transitions**: Moving between maps works correctly
- **Game Completion**: The win condition triggers correctly

#### Sample Inputs & Outputs

**Hero Movement Test**
- Input: Press right arrow key
- Expected Output: Hero moves one tile to the right

**Inventory System Test**
- Input: Hero collects "Legendary Key" item
- Expected Output: Hero's inventory contains the key

## Test Logs

The test logs provide detailed information about test execution:

```
=== Java RPG Test Suite ===
Started: [Timestamp]

=== Unit Tests ===
Running: CharacterTest
Tests run: 5, Failures: 0, Ignored: 0, Time: 245ms
SUCCESS

=== Integration Tests ===
Running: MapIntegrationTest
Tests run: 3, Failures: 0, Ignored: 0, Time: 368ms
SUCCESS

=== System Tests ===
Running: SystemTest
Tests run: 4, Failures: 0, Ignored: 0, Time: 523ms
SUCCESS

=== Test Suite Complete ===
Finished: [Timestamp]
```

If a test fails, the log will show details about the failure:

```
FAILURE: testMapTransition(SystemTest): expected:<1> but was:<0>
   at SystemTest.testMapTransition(SystemTest.java:125)
   [stack trace follows]
```

## Test Coverage

The test suite aims to cover:

- **Character Movement**: All four directions and collision detection
- **Map Navigation**: Moving between different maps
- **Event Interaction**: Doors, treasures, and character dialogue
- **Inventory System**: Adding, checking, and removing items
- **Game Loop**: Proper updating of game state

## Mock Objects

Since the game is designed as a visual application, some tests use mock objects:

- `MockEvent`: A simplified event implementation for testing
- Test maps and event files: Small maps for predictable testing

## Known Limitations

- System tests use reflection to access private fields, which may break if class structure changes
- Visual components are not fully testable without a display
- Sound playback cannot be validated automatically 