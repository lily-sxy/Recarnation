package uoft.csc207.reincarnation.maze.drawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import uoft.csc207.reincarnation.maze.dimension.Maze2;
import uoft.csc207.reincarnation.maze.dimension.Point2;
import uoft.csc207.reincarnation.maze.entity.Entity2;

public class SquareDrawer implements IDrawer {
    private Maze2 maze;

    private int width;
    private int height;
    private float blockSize;
    private int numHeight;
    private int numWidth;

    private Bitmap bitmap;

    public SquareDrawer(Maze2 maze_in, int width_in, int height_in, float block_size_in, int sc_width, int sc_height) {
        maze = maze_in;
        width = width_in;
        height = height_in;
        blockSize = block_size_in;

        this.updateScreen(sc_width, sc_height);

        bitmap = Bitmap.createBitmap(
                (int) (width * blockSize), (int) (height * blockSize),
                Bitmap.Config.ARGB_8888
        );

        this.generateBitmap();
    }

    private void generateBitmap() {
        int way = Color.WHITE;
        int wall = Color.BLACK;
        int exit = Color.BLUE;
        int temp_clr = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (maze.getPoint(new Point2(j, i, true))) {
                    case WALL:
                        temp_clr = wall;
                        break;
                    case WALKWAY:
                        temp_clr = way;
                        break;
                    case EXIT:
                        temp_clr = exit;
                        break;
                }
                for (double k = i * blockSize; k < (i + 1) * blockSize; k++)
                    for (double l = j * blockSize; l < (j + 1) * blockSize; l++) {
                        bitmap.setPixel((int) l, (int) (k), temp_clr);
                    }
            }
        }
    }

    public void draw(Canvas canvas) {
        Entity2 player = maze.getPlayer();
        double centre_x = player.getX();
        double centre_y = player.getY();
        float left = (float) ((int) centre_x - centre_x);
        float top = (float) ((int) centre_y - centre_y);


        int min_y = (int) (centre_y - 0.5) - numHeight;
        if (centre_y < 0.5)
            min_y = -1 - numHeight;
        int max_y = (int) centre_y + numHeight + 2;
        if (top > -0.5) {
            top--;
        } else {
            max_y++;
        }
        if (min_y < 0) {
            top -= min_y;
            min_y = 0;
        }
        if (max_y > height)
            max_y = height;

        int min_x = (int) (centre_x - 0.5) - numWidth;
        if (centre_x < 0.5)
            min_x = -1 - numWidth;
        int max_x = (int) centre_x + numWidth + 2;
        if (left > -0.5) {
            left--;
        } else {
            max_x++;
        }
        if (min_x < 0) {
            left -= min_x;
            min_x = 0;
        }
        if (max_x > width)
            max_x = width;

        left *= blockSize;
        top *= blockSize;

        this.draw(canvas, left - min_x * blockSize, top - min_y * blockSize);

        List<Entity2> entities = maze.getEntities();

        for (Entity2 entity : entities) {
            // First test for which block entity is in.
            Point2 loc = entity.getLocation();
            if (loc.getX() >= min_x && loc.getX() < max_x &&
                    loc.getY() >= min_y && loc.getY() < max_y) {
                entity.draw(
                        canvas,
                        left + ((float) (loc.getX() - min_x)) * blockSize,
                        top + ((float) (loc.getY() - min_y)) * blockSize,
                        blockSize);
            }
        }
    }

    public void draw(Canvas canvas, float left, float top) {
        canvas.drawBitmap(bitmap, left, top, new Paint());
    }

    public void setSize(int block_size_in) {
    }

    public void updateScreen(int sc_width, int sc_height) {
        //blockSize = (float) (sc_height / 2.0 / (numHeight + 1));
        numHeight = (int) (sc_height / blockSize / 2 - 0.5);
        numWidth = (int) (sc_width / blockSize / 2 - 0.5);
    }
}
