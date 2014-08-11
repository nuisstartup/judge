package nuis.startup.judge;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * ゲームプレイ中Activity
 *
 */
public class CardplayActivity extends Activity implements OnClickListener{

	////　メンバ
	//　ユーザー1
	private User user1 = new User("カール");
	//　ユーザー2
	private User user2 = new User("レオンハルト");
	//　1個の対戦
	private Game game;

	//　対戦中ビュー
	private CardplayView cardplayView;
	//　決定ボタン
	private Button btnOk;
	//　メッセージ
	private TextView txtMessage;
	
	
	/**
	 * 
	 * @return
	 */
	public User getUser1() {
		return user1;
	}

	/**
	 * 
	 * @return
	 */
	public User getUser2() {
		return user2;
	}

	/**
	 * 
	 * @return
	 */
	public Game getGame() {
		return game;
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		//　レイアウト
		final LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		//　対戦の初期化
		this.game = new Game(user1, user2);
		//　第1試合開始
		this.game.startPlay(user1);
		
		//　対戦中ビューの生成
		this.cardplayView = new CardplayView(this);
		this.cardplayView.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
		));
		
		//　決定ボタン
		btnOk = new Button(this);
		btnOk.setText("決定");
		btnOk.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
		));
		//　ボタンクリックイベントをリッスンする
		btnOk.setOnClickListener(this);

		//　メッセージ
		txtMessage = new TextView(this);
		txtMessage.setText("3ポイント先取したユーザーが勝者です。");
		txtMessage.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
		));

		//　レイアウトに追加
		layout.addView(this.btnOk);
		layout.addView(this.txtMessage);
		layout.addView(this.cardplayView);
		
		this.txtMessage.setText(this.game.getDispMessage());

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			//　Backボタンが押された場合終了するか確認する
			AlertDialog.Builder bld = new AlertDialog.Builder(this);
			bld.setMessage("対戦を終了しますか？");
			bld.setPositiveButton("戻る", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					//　終了する
					finish();
				}
			});
			bld.setNegativeButton("終了しない", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					//　終了しない
				}
			});
			bld.setCancelable(true);
			AlertDialog dlg = bld.create();
			dlg.show();
			return false;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.cardplayView.touched(event);
	}
	

	@Override
	public void onClick(View v) {
		if(v == this.btnOk){
			//　決定ボタンの場合
			if(this.game.isWaitingUserTurn()){
				//　待っていた場合
				//　ユーザーの番に変更
				this.game.nextUserTurn();

			}else if(this.game.isPuttingCard()){
				//　カードを出した場合
				//　カードを更新し、待ち状態へ変更
				this.game.putCard(this.cardplayView.getSelectedCurrentHandCard());
			
			}else if(this.game.isJudging()){
				//　ジャッジした場合

			}

			this.txtMessage.setText(this.game.getDispMessage());
			this.cardplayView.invalidate();
		}
	}

}




