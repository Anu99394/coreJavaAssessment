public class Planet {
    private String name;
    private double orbitalPeriod; // Time taken for a full revolution in LTU
    private double currentPosition; // Current position in degrees

    public Planet(String name, double orbitalPeriod) {
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
        this.currentPosition = 0.0;
    }

    public void updatePosition(double timeElapsed) {
        currentPosition = (currentPosition + (360 / orbitalPeriod) * timeElapsed) % 360;
    }

    public double getCurrentPosition() {
        return currentPosition;
    }

    public boolean isAlignedWith(Planet other) {
        double difference = Math.abs(this.currentPosition - other.getCurrentPosition());
        return difference <= 10 || difference >= 350;
    }
}
