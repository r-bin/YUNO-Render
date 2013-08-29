package org.yuno.apps.heightmap;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;

public class SimpleGLSurfaceView extends GLSurfaceView implements Renderer {
	// EVENT

	public SimpleGLSurfaceView(Context context) {
		super(context);
	}

	// PUBLIC

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
	}

	@Override
	public void onDrawFrame(GL10 gl) {
	}
}
