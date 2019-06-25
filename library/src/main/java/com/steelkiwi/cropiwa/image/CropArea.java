package com.steelkiwi.cropiwa.image;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import java.io.IOException;

/**
 * @author yarolegovich
 * 25.02.2017.
 */
public class CropArea {

    public static CropArea create(RectF coordinateSystem, RectF imageRect, RectF cropRect) {
        return new CropArea(
            moveRectToCoordinateSystem(coordinateSystem, imageRect),
            moveRectToCoordinateSystem(coordinateSystem, cropRect));
    }

    private static Rect moveRectToCoordinateSystem(RectF system, RectF rect) {
        float originX = system.left, originY = system.top;
        return new Rect(
            Math.round(rect.left - originX), Math.round(rect.top - originY),
            Math.round(rect.right - originX), Math.round(rect.bottom - originY));
    }

    private final Rect imageRect;
    private final Rect cropRect;

    private CropArea(Rect imageRect, Rect cropRect) {
        this.imageRect = imageRect;
        this.cropRect = cropRect;
    }

    Bitmap applyCropTo(Bitmap bitmap) throws IOException {
        if (imageRect.top > cropRect.top
            || imageRect.left > cropRect.left
            || imageRect.right < cropRect.right
            || imageRect.bottom < cropRect.bottom) {
            throw new IOException("Coordinates must not negative");
        } else {
            int left = findRealCoordinate(bitmap.getWidth(), cropRect.left, imageRect.width());
            int top = findRealCoordinate(bitmap.getHeight(), cropRect.top, imageRect.height());
            int right = findRealCoordinate(bitmap.getWidth(), cropRect.width(), imageRect.width());
            int bottom = findRealCoordinate(bitmap.getHeight(), cropRect.height(), imageRect.height());
            Bitmap immutableCropped = Bitmap.createBitmap(bitmap, left, top, right, bottom);
            return immutableCropped.copy(immutableCropped.getConfig(), true);
        }
    }

    private int findRealCoordinate(int imageRealSize, int cropCoordinate, float cropImageSize) {
        return Math.round((imageRealSize * cropCoordinate) / cropImageSize);
    }

}
