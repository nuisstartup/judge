package nuis.startup.judge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * 起動時のActivity
 *
 */
public class MainActivity extends Activity implements android.view.View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//　開始ボタンのIDを取得
		final Button bStart = (Button)findViewById(R.id.bStart);
		//　リッスンする
		bStart.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		//　ゲームプレイ中Activity開始
		final Intent intent = new Intent(this, CardplayActivity.class);
		//　開始
		startActivity(intent);
	}
}
