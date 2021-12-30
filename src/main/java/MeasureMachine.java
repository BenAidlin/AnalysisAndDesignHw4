import java.util.Random;

public class MeasureMachine {
    public static Random random = new Random();

    public static float getWeight() {
        return random.nextFloat(40, 60);
    }

    public static float getHeight() {
        return random.nextFloat((float) 1.4, (float) 1.6);
    }
}
