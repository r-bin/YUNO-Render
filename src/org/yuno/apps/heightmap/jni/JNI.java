package org.yuno.apps.heightmap.jni;

import org.yuno.apps.heightmap.helper.Debug;

public class JNI {
	private static native int main();
	private static native int onSurfaceCreated(int w, int h);
	private static native int onSurfaceChanged(int w, int h);
	private static native int surfaceDestroyed();
	private static native int onDrawFrame();
	private static native int toggleFlag(int flag);
	private static native int initArray(int[] array, int width, int height,
			float scale);

	// PUBLIC

	public static void loadLibrary(String name) {
		boolean ret = false;

		Debug.log(JNI.class, "Loading native library '" + name + "'...");

		try {
			System.loadLibrary(name);
			ret = true;
		} catch (UnsatisfiedLinkError e) {
			Debug.log(JNI.class, "›  " + e);
		} catch (Exception e) {
			Debug.log(JNI.class, "›  " + e);
		} finally {
			if (ret) {
				Debug.log(JNI.class, ">  success!");
			} else {
				Debug.log(JNI.class, ">  failed.");
			}
		}

		callNativeFunction("main");
	}

	public static void callNativeFunction(String name, Object... param) {
		int ret = -1;

		Debug.log(JNI.class, "Calling native function '" + name + "'...");

		try {
			if (name.equalsIgnoreCase("main")) {
				ret = main();
			} else if (name.equalsIgnoreCase("onSurfaceCreated")) {
				ret = onSurfaceCreated((Integer) param[0], (Integer) param[1]);
			} else if (name.equalsIgnoreCase("onSurfaceChanged")) {
				ret = onSurfaceChanged((Integer) param[0], (Integer) param[1]);
			} else if (name.equalsIgnoreCase("surfaceDestroyed")) {
				ret = surfaceDestroyed();
			} else if (name.equalsIgnoreCase("onDrawFrame")) {
				ret = onDrawFrame();
			} else if (name.equalsIgnoreCase("toggleFlag")) {
				ret = toggleFlag((Integer) param[0]);
			} else if (name.equalsIgnoreCase("initArray")) {
				ret = initArray((int[]) param[0], (Integer) param[1],
						(Integer) param[2], (Float) param[3]);
			}
		} catch (UnsatisfiedLinkError e) {
			Debug.log(JNI.class, ">  " + e);
		} catch (Exception e1) {
			Debug.log(JNI.class, ">  " + e1);
		} finally {
			if (ret >= 0) {
				Debug.log(JNI.class, ">  success!");
			} else {
				Debug.log(JNI.class, ">  failed.");
			}
		}
	}
}
