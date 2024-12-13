import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

import static parameters.Parameters.*;
import static save.SaveUtil.saveSketch;

public class InfiniteNorm extends PApplet {

    private List<Mass> masses;

    public static void main(String[] args) {
        PApplet.main(InfiniteNorm.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue());
        stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), STROKE_COLOR.alpha());
        noFill();
        frameRate(-1);

        Mass.setPApplet(this);

        masses = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_MASSES; i++) {
            masses.add(new Mass());
        }

    }

    @Override
    public void draw() {
        for (int k = 0; k < LINES_PER_FRAME; k++) {
            PVector point = new PVector(random(WIDTH), random(HEIGHT));
            for (int i = 0;
                 i < MAX_LINE_LENGTH && point.x > 0 && point.x < WIDTH && point.y > 0 && point.y < HEIGHT;
                 i++) {
                point(point.x, point.y);
                PVector speed = PVector.sub(point, new PVector(WIDTH / 2f, HEIGHT / 2f))
                        .setMag(ATTRACTION_FORCE);
                masses.forEach(mass -> {
                    PVector f = PVector.sub(point, mass.getPosition());
                    f.setMag(mass.getWeight() / (1 + sq(max(f.x, f.y))));
                    speed.add(f);
                });
                point.add(speed.setMag(SPEED_MAGNITUDE));
            }
        }
        if (frameCount >= NUMBER_OF_FRAMES) {
            noLoop();
            saveSketch(this);
        }
    }
}
