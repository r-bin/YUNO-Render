package org.yuno.apps.heightmap.helper;

import android.graphics.Bitmap;
import android.graphics.Color;

public class BitmapUtils {
	// PUBLIC

	public static int[] convertHeightmap(Bitmap heightmap) {
		int[] pixels = convertToIntArray(heightmap);

		pixels = convertToGrayscale(pixels, heightmap.getWidth(),
				heightmap.getHeight());

		pixels = mirrorIntArrayY(pixels, heightmap.getWidth(),
				heightmap.getHeight());

		return pixels;
	}

	// PRIVATE

	public static int[] convertToIntArray(Bitmap bitmap) {
		int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];

		bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(),
				bitmap.getHeight());

		return pixels;
	}

	public static int[] convertToGrayscale(int[] pixels, int pixels_x_max,
			int pixels_y_max) {
		int xy_limit = pixels.length;

		for (int xy = 0; xy < xy_limit; xy++) {
			pixels[xy] = get8BitPixelValue(pixels[xy]);
		}

		return pixels;
	}

	public static int[] mirrorIntArrayXY(int[] pixels) {
		int xy_max = pixels.length - 1;
		int xy_limit = pixels.length / 2;

		for (int xy = 0, pixel_tmp; xy < xy_limit; xy++) {
			pixel_tmp = pixels[xy];

			pixels[xy] = pixels[xy_max - xy];
			pixels[xy_max - xy] = pixel_tmp;
		}

		return pixels;
	}

	public static int[] mirrorIntArrayX(int[] pixels, int pixels_x_max,
			int pixels_y_max) {
		int x_max = pixels_x_max - 1;
		int x_limit = pixels_x_max / 2;
		int y_limit = pixels_y_max;

		int xy_min; // = y * pixels_y_max;
		int xy_max; // = xy_min + x_max;
		int xy; // = xy_min + x;

		for (int y = 0, pixel_tmp; y < y_limit; y++) {
			xy_min = y * pixels_y_max;
			xy_max = xy_min + x_max;

			for (int x = 0; x < x_limit; x++) {
				xy = xy_min + x;

				pixel_tmp = pixels[xy];

				pixels[xy] = pixels[xy_max - x];
				pixels[xy_max - x] = pixel_tmp;
			}
		}

		return pixels;
	}

	public static int[] mirrorIntArrayY(int[] pixels, int pixels_x_max,
			int pixels_y_max) {
		int x_limit = pixels_x_max;
		int y_max = pixels_y_max - 1;
		int y_limit = pixels_y_max / 2;

		int xy_min; // = y * pixels_y_max;
		int xy; // = xy_min + x;

		int xy2_min; // = (y_max - y) * pixels_y_max;
		int xy2; // = xy2_min + x;

		for (int y = 0, pixel_tmp; y < y_limit; y++) {
			xy_min = y * pixels_y_max;

			xy2_min = (y_max - y) * pixels_y_max;

			for (int x = 0; x < x_limit; x++) {
				xy = xy_min + x;

				xy2 = xy2_min + x;

				pixel_tmp = pixels[xy];

				pixels[xy] = pixels[xy2];
				pixels[xy2] = pixel_tmp;
			}
		}

		return pixels;
	}

	// GET

	public static int get8BitPixelValue(int pixel) {
		return Color.red(pixel);
	}

	public static int get8BitPixelValue__avarage(int pixel) {
		return (int) (0.299 * Color.red(pixel) + 0.587 * Color.green(pixel) + 0.114 * Color
				.blue(pixel));
	}
}
