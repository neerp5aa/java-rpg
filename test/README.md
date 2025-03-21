# Java RPG Game Test Suite

This directory contains the test suite for the Java RPG game, including unit tests, integration tests, and system tests.

## Setup

To run the tests, you need to have JUnit 4 installed. You can download it from https://junit.org/junit4/ or add it to your classpath using your preferred dependency management tool.

### Compiling the Tests

```bash
# From the project root directory
javac -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar test/*.java
```

### Running the Tests

```bash
# From the project root directory
java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar test.TestRunner
```

## Test Cases

The test suite includes:

1. **Unit Tests** (CharacterTest.java)
   - Tests for individual components like Character class

2. **Integration Tests** (MapIntegrationTest.java)
   - Tests for interactions between components like Map and Character

3. **System Tests** (SystemTest.java)
   - End-to-end tests for the entire game system

See `TEST_CASES.md` for a detailed description of all test cases.

## Test Logs

Test logs are stored in the `logs` directory. Each test run creates a new log file with a timestamp.

Sample log output:
```
=== Java RPG Test Suite ===
Started: Thu May 16 14:30:22 PDT 2024

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
Finished: Thu May 16 14:30:24 PDT 2024
```

## Dependencies

- JUnit 4.13.2
- Hamcrest Core 1.3

## Mock Objects

The test suite includes some mock objects to facilitate testing:
- `MockEvent.java`: A simple mock implementation of the Event class

## Notes

- The system tests use reflection to access private members of the game classes
- Some test cases may need to be adjusted based on changes to the game code 