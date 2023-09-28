package uoft.csc207.reincarnation.maze.drawer;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import uoft.csc207.reincarnation.maze.dimension.Maze2T;
import uoft.csc207.reincarnation.maze.dimension.Point2;
import uoft.csc207.reincarnation.maze.entity.Entity2;

public class TriangleDrawer implements IDrawer {
    private Maze2T maze;

    private int size;
    private double blockSize;
    private int screenHeight;
    private int screenWidth;

    private Bitmap bitmap;

    // This value is sqrt(3), use for calculation.
    private final static double r3 = 1.7320508075688772935274463415059;

    public TriangleDrawer(Maze2T maze_in, int size_in, double block_size_in, int sc_width, int sc_height) {
        maze = maze_in;
        size = size_in;
        blockSize = (block_size_in * r3);
        this.updateScreen(sc_width, sc_height);

        int temp_size = (int) (size * blockSize) + 1;
        bitmap = Bitmap.createBitmap(temp_size, temp_size, Bitmap.Config.ARGB_8888);

        this.generateBitmap();
    }

    private void generateBitmap() {
        double line_begin;
        double line_end;
        double head_line;
        double bottom_line;

        int way = Color.WHITE;
        int wall = Color.BLACK;
        int exit = Color.BLUE;
        int temp_clr = 0;

        for (int i = 0; i < size; i++) {
            head_line = i * blockSize * r3 / 2;
            bottom_line = head_line + blockSize * r3 / 2;
            for (int j = 0; j < i * 2 + 1; j += 2) {
                line_begin = blockSize * (size - i + j) / 2;
                line_end = line_begin + 1;
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
                for (double k = head_line; k < bottom_line; k++) {
                    for (double l = line_begin; l < line_end; l++) {
                        bitmap.setPixel((int) l, (int) k, temp_clr);
                    }
                    line_begin -= 1 / r3;
                    line_end += 1 / r3;
                }
            }

            for (int j = 1; j < i * 2 + 1; j += 2) {
                line_begin = blockSize * (size - i + j - 1) / 2;
                line_end = line_begin + blockSize;
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
                for (double k = head_line; k < bottom_line; k++) {
                    for (double l = line_begin; l < line_end; l++) {
                        bitmap.setPixel((int) l, (int) k, temp_clr);
                    }
                    line_begin += 1 / r3;
                    line_end -= 1 / r3;
                }
            }
        }
    }

    public void draw(Canvas canvas) {
        Entity2 player = maze.getPlayer();

        double centre_x = player.getX();
        double centre_y = player.getY();
        double bias_x = calBitMapX((int) centre_x, (int) centre_y) -
                ((int) centre_x - centre_x) * blockSize;
        double bias_y = calBitMapY((int) centre_y) -
                ((int) centre_y - centre_y) * blockSize;
        double left = screenWidth / 2.0 - bias_x;
        double top = screenHeight / 2.0 - bias_y;

        this.draw(canvas, (float) left, (float) top);

        List<Entity2> entities = maze.getEntities();

        for (Entity2 entity : entities) {
            Point2 loc = entity.getLocation();
            entity.draw(
                    canvas,
                    (float) left + (float) (calBitMapX(loc.getX(), loc.getY())),
                    (float) top + (float) (calBitMapY(loc.getY())),
                    (float) blockSize);
        }
    }

    private double calBitMapX(int x, int y) {
        return (size + x - y - 1) * blockSize / 2;
    }

    private double calBitMapY(int y) {
        return y * blockSize * r3 / 2;
    }

    public void draw(Canvas canvas, float left, float top) {
        canvas.drawBitmap(bitmap, left, top, new Paint());
    }

    public void setSize(int block_size_in) {
        blockSize = block_size_in;
    }

    public void updateScreen(int sc_width, int sc_height) {
        screenWidth = sc_width;
        screenHeight = sc_height;
    }
}
