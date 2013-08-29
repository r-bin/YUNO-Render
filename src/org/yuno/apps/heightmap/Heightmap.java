package org.yuno.apps.heightmap;

import org.yuno.apps.heightmap.jni.JNI;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Heightmap extends Activity {
	private NativePanel m_panel;

	// EVENT

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initJni();
		initLayout();

		addSeekBar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, Menu.NONE, R.string.menu_0);
		menu.add(1, 1, Menu.NONE, R.string.menu_1);
		menu.add(2, 2, Menu.NONE, R.string.menu_2);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		JNI.callNativeFunction("toggleFlag", item.getItemId());
		m_panel.requestRender();

		return true;
	}

	// PRIVATE

	private void initJni() {
		JNI.loadLibrary("native");
	}

	private void initLayout() {
		m_panel = new NativePanel(this);
		setContentView(m_panel);
	}

	private void addSeekBar() {
		SharedPreferences settings = this.getSharedPreferences(null, 0);

		SeekBar sb = new SeekBar(this);
		sb.setMax(360);
		sb.setProgress(settings.getInt("seekbar", 0));
		sb.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		sb.setPadding(10, 0, 10, 0);
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar seekBar) {
				SharedPreferences settings = Heightmap.this
						.getSharedPreferences(null, 0);
				SharedPreferences.Editor editor = settings.edit();

				editor.putInt("seekbar", seekBar.getProgress());

				editor.commit();
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				m_panel.setAngle(360 / seekBar.getMax() * progress);
			}
		});

		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setVerticalGravity(Gravity.BOTTOM);
		ll.addView(sb);

		addContentView(ll, new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
	}
}