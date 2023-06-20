package util;

import java.util.Random;

public class Randomizer {
    private static final Random random = new Random();

    public static int nextInt(int bound){
        return random.nextInt(bound);
    }
    public static String getRandomPhone() {
        return String.valueOf(random.nextLong(10000000, 99999999));
    }
}
