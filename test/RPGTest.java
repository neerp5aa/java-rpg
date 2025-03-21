import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Simplified test class for RPG game.
 * This includes unit tests, integration tests, and system tests in one file.
 */
public class RPGTest {
    // Common test constants
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private static final int CS = 32; // Character size
    
    // Test results counter
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    private static StringBuilder logOutput = new StringBuilder();

    /**
     * Main method to run all tests
     */
    public static void main(String[] args) {
        try {
            logOutput.append("=== Java RPG Test Suite ===\n");
            logOutput.append("Started: " + new Date() + "\n\n");
            
            // Run unit tests
            logOutput.append("=== Unit Tests ===\n");
            runUnitTests();
            
            // Run integration tests
            logOutput.append("\n=== Integration Tests ===\n");
            runIntegrationTests();
            
            // Run system tests
            logOutput.append("\n=== System Tests ===\n");
            runSystemTests();
            
            // Report summary
            logOutput.append("\n=== Test Summary ===\n");
            logOutput.append("Total tests: " + (testsPassed + testsFailed) + "\n");
            logOutput.append("Passed: " + testsPassed + "\n");
            logOutput.append("Failed: " + testsFailed + "\n");
            logOutput.append("Success rate: " + 
                            (int)(100 * testsPassed / (float)(testsPassed + testsFailed)) + "%\n");
            logOutput.append("Finished: " + new Date() + "\n");
            
            // Write log to file
            PrintWriter writer = new PrintWriter(new FileWriter("logs/test_log.txt"));
            writer.println(logOutput.toString());
            writer.close();
            
            // Show results in console
            System.out.println(logOutput.toString());
            
        } catch (Exception e) {
            System.err.println("Error running tests: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Run all unit tests
     */
    private static void runUnitTests() {
        // Character movement tests
        testCharacterMovement();
        
        // Character collision tests
        testCharacterCollision();
        
        // Inventory tests
        testInventorySystem();
    }
    
    /**
     * Run all integration tests
     */
    private static void runIntegrationTests() {
        // Map-Character interaction
        testMapCharacterInteraction();
        
        // Event interaction
        testEventInteraction();
    }
    
    /**
     * Run system tests
     */
    private static void runSystemTests() {
        // Game initialization
        testGameInitialization();
        
        // Full game flow
        testGameFlow();
    }
    
    // ---- Unit Tests ----
    
    /**
     * Test character movement in all four directions
     */
    private static void testCharacterMovement() {
        logOutput.append("Testing character movement...\n");
        try {
            MockMap map = new MockMap(10, 10);
            MockCharacter character = new MockCharacter(5, 5, DOWN, map);
            
            // Test moving right
            character.setDirection(RIGHT);
            character.setMoving(true);
            
            // Simulate multiple move calls
            boolean movementComplete = false;
            for (int i = 0; i < 10 && !movementComplete; i++) {
                movementComplete = character.move();
            }
            
            assertEquals(6, character.getX(), "Character should move right");
            assertEquals(5, character.getY(), "Y position should not change");
            
            // Test moving down
            character.setDirection(DOWN);
            character.setMoving(true);
            
            movementComplete = false;
            for (int i = 0; i < 10 && !movementComplete; i++) {
                movementComplete = character.move();
            }
            
            assertEquals(6, character.getX(), "X position should not change");
            assertEquals(6, character.getY(), "Character should move down");
            
            testsPassed++;
            logOutput.append("PASSED\n");
        } catch (AssertionError e) {
            testsFailed++;
            logOutput.append("FAILED: " + e.getMessage() + "\n");
        }
    }
    
    /**
     * Test character collision with walls
     */
    private static void testCharacterCollision() {
        logOutput.append("Testing character collision...\n");
        try {
            MockMap map = new MockMap(10, 10);
            map.setWall(2, 2);
            
            MockCharacter character = new MockCharacter(2, 1, DOWN, map);
            
            // Try to move into wall
            character.setDirection(DOWN);
            character.setMoving(true);
            
            boolean movementComplete = false;
            for (int i = 0; i < 10 && !movementComplete; i++) {
                movementComplete = character.move();
            }
            
            assertEquals(2, character.getX(), "X position should not change");
            assertEquals(1, character.getY(), "Character should not move into wall");
            
            testsPassed++;
            logOutput.append("PASSED\n");
        } catch (AssertionError e) {
            testsFailed++;
            logOutput.append("FAILED: " + e.getMessage() + "\n");
        }
    }
    
    /**
     * Test inventory system
     */
    private static void testInventorySystem() {
        logOutput.append("Testing inventory system...\n");
        try {
            MockMap map = new MockMap(10, 10);
            MockCharacter character = new MockCharacter(5, 5, DOWN, map);
            
            assertTrue(character.getInventory().isEmpty(), "Inventory should start empty");
            
            character.addToInventory("Sword");
            character.addToInventory("Shield");
            
            assertEquals(2, character.getInventory().size(), "Inventory should have 2 items");
            assertTrue(character.hasItem("Sword"), "Character should have a sword");
            
            character.removeFromInventory("Sword");
            assertFalse(character.hasItem("Sword"), "Sword should be removed");
            assertTrue(character.hasItem("Shield"), "Shield should remain");
            
            testsPassed++;
            logOutput.append("PASSED\n");
        } catch (AssertionError e) {
            testsFailed++;
            logOutput.append("FAILED: " + e.getMessage() + "\n");
        }
    }
    
    // ---- Integration Tests ----
    
    /**
     * Test map-character interaction
     */
    private static void testMapCharacterInteraction() {
        logOutput.append("Testing map-character interaction...\n");
        try {
            MockMap map = new MockMap(10, 10);
            MockCharacter hero = new MockCharacter(5, 5, DOWN, map);
            MockCharacter npc = new MockCharacter(7, 7, DOWN, map);
            
            map.addCharacter(hero);
            map.addCharacter(npc);
            
            assertEquals(2, map.getCharacters().size(), "Map should have 2 characters");
            assertTrue(map.isHit(7, 7), "NPC position should be occupied");
            
            map.removeCharacter(npc);
            assertEquals(1, map.getCharacters().size(), "Map should have 1 character left");
            assertFalse(map.isHit(7, 7), "NPC position should be free");
            
            testsPassed++;
            logOutput.append("PASSED\n");
        } catch (AssertionError e) {
            testsFailed++;
            logOutput.append("FAILED: " + e.getMessage() + "\n");
        }
    }
    
    /**
     * Test interaction with events
     */
    private static void testEventInteraction() {
        logOutput.append("Testing event interaction...\n");
        try {
            MockMap map = new MockMap(10, 10);
            MockCharacter hero = new MockCharacter(5, 5, DOWN, map);
            map.addCharacter(hero);
            
            MockEvent treasureEvent = new MockEvent(3, 3, "Legendary Key");
            map.addEvent(treasureEvent);
            
            MockEvent event = map.checkEvent(3, 3);
            assertNotNull(event, "Event should be found at position");
            assertEquals("Legendary Key", event.getTreasure(), "Event should contain the key");
            
            map.removeEvent(treasureEvent);
            assertNull(map.checkEvent(3, 3), "Event should be removed");
            
            testsPassed++;
            logOutput.append("PASSED\n");
        } catch (AssertionError e) {
            testsFailed++;
            logOutput.append("FAILED: " + e.getMessage() + "\n");
        }
    }
    
    // ---- System Tests ----
    
    /**
     * Test game initialization
     */
    private static void testGameInitialization() {
        logOutput.append("Testing game initialization...\n");
        try {
            // Create a simplified game state
            MockMap map = new MockMap(20, 20);
            MockCharacter hero = new MockCharacter(5, 5, DOWN, map);
            map.addCharacter(hero);
            
            assertNotNull(map, "Map should be initialized");
            assertNotNull(hero, "Hero should be initialized");
            assertEquals(5, hero.getX(), "Hero X position should be 5");
            assertEquals(5, hero.getY(), "Hero Y position should be 5");
            
            testsPassed++;
            logOutput.append("PASSED\n");
        } catch (AssertionError e) {
            testsFailed++;
            logOutput.append("FAILED: " + e.getMessage() + "\n");
        }
    }
    
    /**
     * Test a complete game flow
     */
    private static void testGameFlow() {
        logOutput.append("Testing game flow...\n");
        try {
            // Create a simplified game state
            MockMap map = new MockMap(20, 20);
            MockCharacter hero = new MockCharacter(5, 5, DOWN, map);
            map.addCharacter(hero);
            
            // Add a treasure event
            MockEvent treasureEvent = new MockEvent(6, 5, "Legendary Key");
            map.addEvent(treasureEvent);
            
            // Add a door event
            MockEvent doorEvent = new MockEvent(10, 5, "Door");
            map.addEvent(doorEvent);
            
            // Move hero to get treasure
            hero.setDirection(RIGHT);
            hero.setMoving(true);
            
            // Simulate movement
            boolean movementComplete = false;
            for (int i = 0; i < 10 && !movementComplete; i++) {
                movementComplete = hero.move();
            }
            
            // Check if hero reached the treasure
            assertEquals(6, hero.getX(), "Hero should reach treasure");
            
            // Get treasure and check inventory
            MockEvent event = map.checkEvent(6, 5);
            hero.addToInventory(event.getTreasure());
            map.removeEvent(treasureEvent);
            
            assertTrue(hero.hasItem("Legendary Key"), "Hero should have key in inventory");
            
            testsPassed++;
            logOutput.append("PASSED\n");
        } catch (AssertionError e) {
            testsFailed++;
            logOutput.append("FAILED: " + e.getMessage() + "\n");
        }
    }
    
    // ---- Mock Classes ----
    
    /**
     * Simple mock of Character class for testing
     */
    static class MockCharacter {
        private int x, y;
        private int direction;
        private boolean isMoving;
        private MockMap map;
        private ArrayList<String> inventory = new ArrayList<>();
        
        public MockCharacter(int x, int y, int direction, MockMap map) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.map = map;
        }
        
        public boolean move() {
            if (!isMoving) return false;
            
            int nextX = x;
            int nextY = y;
            
            // Calculate next position based on direction
            switch (direction) {
                case LEFT:
                    nextX--;
                    break;
                case RIGHT:
                    nextX++;
                    break;
                case UP:
                    nextY--;
                    break;
                case DOWN:
                    nextY++;
                    break;
            }
            
            // Check for collision
            if (!map.isHit(nextX, nextY)) {
                x = nextX;
                y = nextY;
                isMoving = false;
                return true;
            } else {
                isMoving = false;
                return false;
            }
        }
        
        public int getX() { return x; }
        public int getY() { return y; }
        public int getDirection() { return direction; }
        public void setDirection(int direction) { this.direction = direction; }
        public boolean isMoving() { return isMoving; }
        public void setMoving(boolean moving) { this.isMoving = moving; }
        
        public ArrayList<String> getInventory() { return inventory; }
        public void addToInventory(String item) { inventory.add(item); }
        public void removeFromInventory(String item) { inventory.remove(item); }
        public boolean hasItem(String item) { return inventory.contains(item); }
        public void clearInventory() { inventory.clear(); }
    }
    
    /**
     * Simple mock of Map class for testing
     */
    static class MockMap {
        private int[][] map;
        private int rows;
        private int cols;
        private ArrayList<MockCharacter> characters = new ArrayList<>();
        private ArrayList<MockEvent> events = new ArrayList<>();
        
        public MockMap(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.map = new int[rows][cols];
        }
        
        public void setWall(int x, int y) {
            map[y][x] = 1;
        }
        
        public boolean isHit(int x, int y) {
            // Check map boundaries
            if (x < 0 || y < 0 || x >= cols || y >= rows) {
                return true;
            }
            
            // Check for walls
            if (map[y][x] == 1) {
                return true;
            }
            
            // Check for characters
            for (MockCharacter character : characters) {
                if (character.getX() == x && character.getY() == y) {
                    return true;
                }
            }
            
            return false;
        }
        
        public void addCharacter(MockCharacter character) {
            characters.add(character);
        }
        
        public void removeCharacter(MockCharacter character) {
            characters.remove(character);
        }
        
        public ArrayList<MockCharacter> getCharacters() {
            return characters;
        }
        
        public void addEvent(MockEvent event) {
            events.add(event);
        }
        
        public void removeEvent(MockEvent event) {
            events.remove(event);
        }
        
        public MockEvent checkEvent(int x, int y) {
            for (MockEvent event : events) {
                if (event.getX() == x && event.getY() == y) {
                    return event;
                }
            }
            return null;
        }
    }
    
    /**
     * Simple mock of Event class for testing
     */
    static class MockEvent {
        private int x, y;
        private String treasure;
        
        public MockEvent(int x, int y, String treasure) {
            this.x = x;
            this.y = y;
            this.treasure = treasure;
        }
        
        public int getX() { return x; }
        public int getY() { return y; }
        public String getTreasure() { return treasure; }
    }
    
    // ---- Helper Assertion Methods ----
    
    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + ": expected " + expected + ", but got " + actual);
        }
    }
    
    private static void assertEquals(Object expected, Object actual, String message) {
        if (expected == null && actual == null) return;
        if (expected == null || !expected.equals(actual)) {
            throw new AssertionError(message + ": expected " + expected + ", but got " + actual);
        }
    }
    
    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
    
    private static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }
    
    private static void assertNull(Object obj, String message) {
        if (obj != null) {
            throw new AssertionError(message + ": expected null, but got " + obj);
        }
    }
    
    private static void assertNotNull(Object obj, String message) {
        if (obj == null) {
            throw new AssertionError(message + ": expected non-null");
        }
    }
} 