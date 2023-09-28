package uoft.csc207.reincarnation.level3;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.util.Map;

/**
 * This is a graph factory that give away bitmap in appropriate size.
 */

class GraphFactory {
    /**
     * the height and width of the screen.
     */
    private int height;
    private int width;
    /**
     * a collection of bitmap of all graphs we need.
     */
    private Map<String, Bitmap> bitmaps;

    /**
     * Initialize a new graph factory.
     *
     * @param bitmaps      The collection of all bitmaps.
     * @param screenHeight The height of the screen.
     * @param screenWidth  The width of the screen.
     */
    GraphFactory(Map<String, Bitmap> bitmaps, int screenHeight, int screenWidth) {
        this.height = screenHeight;
        this.width = screenWidth;
        this.bitmaps = bitmaps;
    }

    /**
     * Get a bitmap with appropriate size of given item.
     *
     * @param item Given a name of item to get the bitmap
     * @return the bitmap of given item in appropriate size.
     */
    Bitmap createBitmap(String item) {
        switch (item) {
            case "Role": {
                Bitmap graph = bitmaps.get("Role");
                float newHeight = height / 6;
                float newWidth = newHeight / 4;
                Bitmap newGraph = getNewBitmap(graph, newWidth, newHeight);
                return newGraph;
            }
            case "Guardleft": {
                Bitmap graph = bitmaps.get("Guardleft");
                float newWidth = width / 20;
                float newHeight = (float) (newWidth / 3.5 * 2.5);
                return getNewBitmap(graph, newWidth, newHeight);
            }
            case "Guardright": {
                Bitmap graph = bitmaps.get("Guardright");
                float newWidth = width / 20;
                float newHeight = (float) (newWidth / 3.5 * 2.5);
                return getNewBitmap(graph, newWidth, newHeight);
            }
            case "Cloud": {
                Bitmap graph = bitmaps.get("Cloud");
                float newHeight = height / 10;
                float newWidth = (float) (newHeight / 2.5 * 6);
                return getNewBitmap(graph, newWidth, newHeight);
            }
            case "Darkcloud": {
                Bitmap graph = bitmaps.get("Darkcloud");
                float newHeight = height / 10;
                float newWidth = (float) (newHeight / 2.5 * 6);
                return getNewBitmap(graph, newWidth, newHeight);
            }
            case "Goldencloud": {
                Bitmap graph = bitmaps.get("Goldencloud");
                float newHeight = height / 10;
                float newWidth = (float) (newHeight / 2.5 * 6);
                return getNewBitmap(graph, newWidth, newHeight);
            }
            case "Background": {
                Bitmap graph = bitmaps.get("Background");
                float newWidth = width;
                float newHeight = height;
                return getNewBitmap(graph, newWidth, newHeight);
            }
        }
        return null;
    }

    /**
     * helper of createBitmap to get bitmap of given size.
     *
     * @param graph     Given a name of item to get the bitmap.
     * @param newWidth  the width of the new bitmap.
     * @param newHeight the height of the new bitmap.
     * @return the bitmap of given item in appropriate size.
     */

    private Bitmap getNewBitmap(Bitmap graph, float newWidth, float newHeight) {
        int oldHeight = graph.getHeight();
        int oldWidth = graph.getWidth();
        float scaleWidth = (newWidth) / oldWidth;
        float scaleHeight = (newHeight) / oldHeight;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(graph, 0, 0, oldWidth, oldHeight, matrix,
                true);
    }
}
