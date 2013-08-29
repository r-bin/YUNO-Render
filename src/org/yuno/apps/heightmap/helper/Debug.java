package org.yuno.apps.heightmap.helper;

import org.yuno.apps.heightmap.BuildConfig;

import android.util.Log;

public class Debug {
	public static final boolean ENABLE = BuildConfig.DEBUG;

	public static void log(Class<?> clazz, String msg) {
		if (ENABLE) {
			Log.d(clazz.getSimpleName(), msg);
		}
	}
}
