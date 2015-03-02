package zenithmods.AdaptiveMechanics.utility;

public class AMMathHelper {

    public static float getRadiansFromDegrees(float degrees){
        return degrees / (180F / (float) Math.PI) *10;
    }

    public static float getDegreesFromRadians(float radians){
        return (radians * 18 * (float)Math.PI);
    }

}
