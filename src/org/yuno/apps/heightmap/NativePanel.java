package org.yuno.apps.heightmap;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.yuno.apps.heightmap.helper.BitmapUtils;
import org.yuno.apps.heightmap.jni.JNI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.view.SurfaceHolder;

public class NativePanel extends Surface2DNavigation {
	public static final float HEIGHTMAP_SCALE = 1 / 3;

	private Bitmap m_heightmap;

	// Event

	public NativePanel(Context context) {
		super(context);

		m_heightmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.heightmap);

		setRenderer(this);
		setRenderMode(RENDERMODE_WHEN_DIRTY);
	}

	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glPushMatrix();

		gl.glScalef(getScale(), getScale(), getScale());

		gl.glTranslatef(getDX(), -getDY(), getDZ());

		gl.glRotatef(getScale(), 0, 0, 1);
		gl.glTranslatef(-m_heightmap.getWidth() / 2,
				-m_heightmap.getWidth() / 2, 0f);

		JNI.callNativeFunction("onDrawFrame");

		gl.glPopMatrix();
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if (height == 0) {
			height = 1;
		}

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		float s = (float) width / height;
		gl.glFrustumf(-s, s, -1, 1, 2, 500);
		GLU.gluLookAt(gl, 0, -150, 75, 0, 0, 0, 0, 1, 0);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glClearColor(1f, 1f, 1f, 1f);

		JNI.callNativeFunction("onSurfaceChanged", getWidth(), getHeight());
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);

		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

		JNI.callNativeFunction("onSurfaceCreated", 0, 0);

		JNI.callNativeFunction("initArray",
				BitmapUtils.convertHeightmap(m_heightmap),
				m_heightmap.getWidth(), m_heightmap.getHeight(),
				HEIGHTMAP_SCALE);

		restore();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		JNI.callNativeFunction("surfaceDestroyed");

		store();

		super.surfaceDestroyed(holder);
	}
}