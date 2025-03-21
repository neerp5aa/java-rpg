# Java RPG Game Test Cases

This document describes all the test cases for the Java RPG game, including unit tests, integration tests, and system tests.

## Unit Tests

### CharacterTest

Tests for the Character class that handles player and NPC movement and interactions.

| Test Case | Inputs | Expected Outputs | Description |
|-----------|--------|------------------|-------------|
| testInitialPosition | Character(5, 5, 0, DOWN, 0, mockMap) | x=5, y=5, px=160, py=160 | Verifies character is initialized at the correct position |
| testDirection | setDirection(LEFT/RIGHT/UP/DOWN) | direction=LEFT/RIGHT/UP/DOWN | Verifies character direction can be set and retrieved correctly |
| testMovement | setDirection(DOWN), setMoving(true), multiple move() calls | x=5, y=6, movementComplete=true | Verifies character moves correctly in the specified direction |
| testCollisionWithWall | Character at (2,1), setDirection(DOWN), setMoving(true), multiple move() calls | x=2, y=1, isMoving()=false | Verifies character cannot move through walls |
| testInventory | addToInventory("Sword"), addToInventory("Shield"), removeFromInventory("Sword"), clearInventory() | hasItem("Sword")=false, hasItem("Shield")=true, inventory.isEmpty()=true after clearInventory | Verifies inventory system works correctly |

## Integration Tests

### MapIntegrationTest

Tests for the interaction between Map and Character classes.

| Test Case | Inputs | Expected Outputs | Description |
|-----------|--------|------------------|-------------|
| testCharacterMapInteraction | map.addCharacter(hero), map.addCharacter(npc), map.removeCharacter(npc) | characters.size()=2 then 1, map.isHit(7,7)=true then false | Verifies adding/removing characters to/from map works correctly |
| testCharacterMovement | hero.setDirection(RIGHT), hero.setMoving(true), multiple hero.move() calls | hero.getX()=6, hero.getY()=5, map.checkCharacter(6,5)=hero | Verifies character movement is correctly reflected in the map |
| testEventInteraction | map.addEvent(testEvent), map.removeEvent(eventToRemove) | map.checkEvent(x,y)!=null then null | Verifies adding/removing events to/from map works correctly |

## System Tests

### SystemTest

End-to-end tests for the game system.

| Test Case | Inputs | Expected Outputs | Description |
|-----------|--------|------------------|-------------|
| testGameInitialization | Create new RPG() | maps.length=4, hero!=null, messageWindow!=null | Verifies game initializes correctly with all required components |
| testHeroMovement | rightKey.press(), multiple gameUpdate() calls | hero.getX()=initialX+1, hero.getY()=initialY | Verifies hero movement through keyboard input works correctly |
| testMapTransition | Position hero at door, spaceKey.press(), multiple gameUpdate() calls | (Simulation only) | Simulates map transition when hero interacts with a door |
| testInventorySystem | addToInventory("Legendary Key") | getInventory().isEmpty()=false, hasItem("Legendary Key")=true | Verifies inventory system works in the complete game context |

## Test Setup

1. The tests require JUnit 4 library to run
2. Run TestRunner.java to execute all tests and generate a log file
3. Log files are stored in test/logs directory with timestamp

## Test Input Coverage

- **Character Movement**: Testing movement in all four directions
- **Collision Detection**: Testing collision with walls and other characters
- **Inventory System**: Testing adding, checking, and removing items
- **Event Interaction**: Testing interaction with map events
- **Keyboard Input**: Testing game response to keyboard input

## Expected Output Logs

The test logs will show:
- Total number of tests run
- Number of successful tests
- Number of failed tests
- Detailed failure messages for any failed tests
- Execution time for tests 