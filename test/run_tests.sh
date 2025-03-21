#!/bin/bash

# Determine if we're in the test directory or project root
if [ -f "RPGTest.java" ]; then
  # We're already in the test directory
  TEST_DIR="."
  LOG_PATH="."
else
  # We're in the project root
  TEST_DIR="test"
  LOG_PATH="test"
fi

# Create logs directory if it doesn't exist
mkdir -p "$TEST_DIR/logs"

# Compile the test class
if [ "$TEST_DIR" = "test" ]; then
  cd test
fi

javac RPGTest.java

# Run the tests
java RPGTest

echo "Test execution complete. Results are in $LOG_PATH/test_log.txt" 