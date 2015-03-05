package zenithmods.AdaptiveMechanics.utility;

import java.util.Random;

public class AMMathHelper {
    private static final Random rand = new Random();

    public static Random getRandom(){
        return rand;
    }

    public static float getRadiansFromDegrees(float degrees){
        return degrees / (180F / (float) Math.PI) *10;
    }

    public static float getDegreesFromRadians(float radians){
        return (radians * 18 * (float)Math.PI);
    }

}
