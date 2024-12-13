import processing.core.PApplet;
import processing.core.PVector;

import static java.lang.Math.pow;
import static parameters.Parameters.*;

public class Mass {
    private static PApplet pApplet;
    private final PVector position;
    private final float weight;

    public Mass() {
        position = new PVector(pApplet.random(-MASS_MARGIN_FACTOR, 1 + MASS_MARGIN_FACTOR) * WIDTH
                        * (float) pow(pApplet.random(MASS_OFFSET_MAXIMUM_FACTOR), MASS_OFFSET_EXPONENT),
                pApplet.random(-MASS_MARGIN_FACTOR, 1 + MASS_MARGIN_FACTOR) * HEIGHT
                        * (float) pow(pApplet.random(MASS_OFFSET_MAXIMUM_FACTOR), MASS_OFFSET_EXPONENT));
        weight = pApplet.random(MASS_MINIMUM_WEIGHT, MASS_MAXIMUM_WEIGHT);
    }

    public static void setPApplet(PApplet pApplet) {
        Mass.pApplet = pApplet;
    }

    public PVector getPosition() {
        return position;
    }

    public float getWeight() {
        return weight;
    }
}
