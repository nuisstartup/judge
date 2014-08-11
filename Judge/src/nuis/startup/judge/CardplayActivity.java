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
 * �Q�[���v���C��Activity
 *
 */
public class CardplayActivity extends Activity implements OnClickListener{

	////�@�����o
	//�@���[�U�[1
	private User user1 = new User("�J�[��");
	//�@���[�U�[2
	private User user2 = new User("���I���n���g");
	//�@1�̑ΐ�
	private Game game;

	//�@�ΐ풆�r���[
	private CardplayView cardplayView;
	//�@����{�^��
	private Button btnOk;
	//�@���b�Z�[�W
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

		//�@���C�A�E�g
		final LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		//�@�ΐ�̏�����
		this.game = new Game(user1, user2);
		//�@��1�����J�n
		this.game.startPlay(user1);
		
		//�@�ΐ풆�r���[�̐���
		this.cardplayView = new CardplayView(this);
		this.cardplayView.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
		));
		
		//�@����{�^��
		btnOk = new Button(this);
		btnOk.setText("����");
		btnOk.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
		));
		//�@�{�^���N���b�N�C�x���g�����b�X������
		btnOk.setOnClickListener(this);

		//�@���b�Z�[�W
		txtMessage = new TextView(this);
		txtMessage.setText("3�|�C���g��悵�����[�U�[�����҂ł��B");
		txtMessage.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
		));

		//�@���C�A�E�g�ɒǉ�
		layout.addView(this.btnOk);
		layout.addView(this.txtMessage);
		layout.addView(this.cardplayView);
		
		this.txtMessage.setText(this.game.getDispMessage());

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			//�@Back�{�^���������ꂽ�ꍇ�I�����邩�m�F����
			AlertDialog.Builder bld = new AlertDialog.Builder(this);
			bld.setMessage("�ΐ���I�����܂����H");
			bld.setPositiveButton("�߂�", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					//�@�I������
					finish();
				}
			});
			bld.setNegativeButton("�I�����Ȃ�", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					//�@�I�����Ȃ�
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
			//�@����{�^���̏ꍇ
			if(this.game.isWaitingUserTurn()){
				//�@�҂��Ă����ꍇ
				//�@���[�U�[�̔ԂɕύX
				this.game.nextUserTurn();

			}else if(this.game.isPuttingCard()){
				//�@�J�[�h���o�����ꍇ
				//�@�J�[�h���X�V���A�҂���Ԃ֕ύX
				this.game.putCard(this.cardplayView.getSelectedCurrentHandCard());
			
			}else if(this.game.isJudging()){
				//�@�W���b�W�����ꍇ

			}

			this.txtMessage.setText(this.game.getDispMessage());
			this.cardplayView.invalidate();
		}
	}

}




