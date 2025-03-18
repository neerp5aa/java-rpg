public class DoorEvent extends Event {
    private String requiredKey;
    
    public DoorEvent(int x, int y) {
        super(x, y, 18, true);
        this.requiredKey = "KEY";
    }
    
    public DoorEvent(int x, int y, String requiredKey) {
        super(x, y, 18, true);
        this.requiredKey = requiredKey;
    }
    
    public String getRequiredKey() {
        return requiredKey;
    }

    public String toString() {
        return "DOOR:" + super.toString() + ":" + requiredKey;
    }
}
