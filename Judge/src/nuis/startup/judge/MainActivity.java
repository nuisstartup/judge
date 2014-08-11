package nuis.startup.judge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * �N������Activity
 *
 */
public class MainActivity extends Activity implements android.view.View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//�@�J�n�{�^����ID���擾
		final Button bStart = (Button)findViewById(R.id.bStart);
		//�@���b�X������
		bStart.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		//�@�Q�[���v���C��Activity�J�n
		final Intent intent = new Intent(this, CardplayActivity.class);
		//�@�J�n
		startActivity(intent);
	}
}
