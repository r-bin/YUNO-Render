package org.yuno.apps.heightmap;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class Surface2DNavigation extends SimpleGLSurfaceView {
	public static final float SCALE_MIN = 0.1f;
	public static final float SCALE_MAX = 100f;

	public static final float ANGLE_MIN = 0f;
	public static final float ANGLE_MAX = 360f;

	public static final float DELTA_SCALE = 5f;

	protected Context m_activity;

	private ScaleGestureDetector m_scaleDetector;
	private boolean m_multiTouch = false;
	private float m_posX, m_posY;
	private float m_downX, m_downY;
	private float m_dX, m_dY;

	private float m_scale = 1f;
	private float m_angle = 0f;

	private OnTouchListener m_touchListener;

	// EVENT

	public Surface2DNavigation(Context context) {
		super(context);

		m_activity = context;

		init(context);

		setOnTouchListener(m_touchListener);
	}

	// PRIVATE

	private void init(Context context) {
		m_scaleDetector = new ScaleGestureDetector(context,
				new ScaleGestureDetector.SimpleOnScaleGestureListener() {
					@Override
					public boolean onScale(ScaleGestureDetector detector) {
						m_scale *= detector.getScaleFactor();
						m_scale = Math.max(SCALE_MIN,
								Math.min(m_scale, SCALE_MAX));

						return true;
					}
				});

		m_touchListener = new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				m_scaleDetector.onTouchEvent(event);

				if (event.getPointerCount() == 1) {
				} else {
					m_multiTouch = true;
				}

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if (m_multiTouch) {
					} else {
						m_downX = event.getX();
						m_downY = event.getY();

						m_dX = 0;
						m_dY = 0;
					}

					break;
				case MotionEvent.ACTION_MOVE:
					if (m_multiTouch) {
					} else {
						m_dX = m_downX - event.getX();
						m_dY = m_downY - event.getY();
					}

					break;
				case MotionEvent.ACTION_UP:
					if (m_multiTouch) {
						m_multiTouch = false;
					} else {
					}

					m_posX -= m_dX;
					m_posY -= m_dY;

					m_dX = 0;
					m_dY = 0;

					break;
				}

				requestRender();

				return true;
			}
		};
	}

	protected void store() {
		SharedPreferences settings = m_activity.getSharedPreferences(null, 0);
		SharedPreferences.Editor editor = settings.edit();

		editor.putFloat("angle", m_angle);
		editor.putFloat("scale", m_scale);
		editor.putFloat("x", m_posX);
		editor.putFloat("y", m_posY);

		editor.commit();
	}

	protected void restore() {
		SharedPreferences settings = m_activity.getSharedPreferences(null, 0);

		m_angle = settings.getFloat("angle", m_angle);
		m_scale = settings.getFloat("scale", m_scale);
		m_posX = settings.getFloat("x", m_posX);
		m_posY = settings.getFloat("y", m_posY);
	}

	// SET

	public void setAngle(float angle) {
		m_angle = Math.max(ANGLE_MIN, Math.min(angle, ANGLE_MAX));

		requestRender();
	}

	// GET

	public float getAngle() {
		return m_angle;
	}

	public float getScale() {
		return m_scale;
	}

	public float getDX() {
		return (m_posX - m_dX) / DELTA_SCALE;
	}

	public float getDY() {
		return (m_posY - m_dY) / DELTA_SCALE;
	}

	public float getDZ() {
		return 0;
	}
}
