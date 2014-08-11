package nuis.startup.judge;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;

/**
 * �Q�[���v���C��View
 *
 */
public class CardplayView extends View {
	//�@�Q�[���v���C��Activity
	private CardplayActivity cardplay;

//�@�^�b�`���ꂽ���W
//�f�o�b�O�p
//�f�o�b�O�p
//�f�o�b�O�p
float xTouched;
float yTouched;
StringBuilder dbgStrb = new StringBuilder();
	
	/**
	 * �R���X�g���N�^
	 * @param cardplay
	 */
	public CardplayView(CardplayActivity cardplay) {
		super(cardplay);
		//�@�X�[�p�[�N���X����Activity���擾������@���킩��Ȃ������̂�
		//�@���̃N���X�ŕێ����邱�Ƃɂ���
		this.cardplay = cardplay;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		//�@�y�C���g����
		final Paint p = new Paint();
		p.setStyle(Style.STROKE);

//�f�o�b�O�p������
//dbgStrb.append(this.cardplay.getGame().isPuttingCard() + ":" + this.cardplay.getGame().isWaitingUserTurn() + "; " );
//canvas.drawText(dbgStrb.toString(), 10, 20, p);

		//�@�ΐ���擾
		final Game game = this.cardplay.getGame();
		
		//�@������Ԃ��擾
		final PlayState playState = game.getCurrentPlayState();
		
		if(playState instanceof WaitingUserTurnState){
			//�@���[�U�[�̌��҂��̎�
		}else if(playState instanceof UserTurn){
			//�@���[�U�[�̔Ԃ̎�
			
			//�@�����D���擾
			final Card [] currentHandCards = game.getHandCardsCurrentUser();
			final Card [] counterHandCards = game.getHandCardsCounterUser();
			//�@�擾�D���擾
			final Card [] currentAcqCards = game.getAcquiredCardsCurrentUser();
			final Card [] counterAcqCards = game.getAcquiredCardsCurrentUser();
			//�@�J�[�h1, 2���擾
			final Card currentCard1 = game.getCard1CurrentUser();
			final Card currentCard2 = game.getCard2CurrentUser();
			final Card counterCard1 = game.getCard1CounterUser();
			final Card counterCard2 = game.getCard2CounterUser();

			//�@����̎莝���J�[�h��`��
			if(counterHandCards != null){
				for (int i = 0; i < counterHandCards.length; i++) {
					final Card card = counterHandCards[i];
					final int suit = card.getSuit();
					p.setColor(Color.GRAY);
					final float dx = 50;
					final float dy = 0;
					final float x = 10 + i * dx;
					final float y = 300 + 10 + i * dy;
					final float w = 40;
					final float h = 80;
					canvas.drawRect(x, y, x + w, y + h, p);
				}
			}
			
			//�@����̎擾�J�[�h��`��
			if(counterAcqCards != null){
				for (int i = 0; i < counterAcqCards.length; i++) {
				}
			}

			//�@�����̎莝���J�[�h��`��
			if(currentHandCards != null){
				for (int i = 0; i < currentHandCards.length; i++) {
					final Card card = currentHandCards[i];
					final int suit = card.getSuit();
					if(suit == Card.SPADE || suit == Card.CLUB){
						p.setColor(Color.BLACK);
					}else if(suit == Card.HEART || suit == Card.DIAMOND){
						p.setColor(Color.RED);
					}else{
						p.setColor(Color.GREEN);
					}
					final float dx = 50;
					final float dy = 0;
					final XYWH xywh = getCurrentHandCardXYWH(i);
					canvas.drawRect(xywh.x, xywh.y, xywh.x + xywh.w, xywh.y + xywh.h, p);
					canvas.drawText(card.getNumberString(), xywh.x + xywh.w / 2, xywh.y + xywh.h / 2, p);
					if(card.isSelected()){
						p.setColor(Color.RED);
						canvas.drawRect(xywh.x - 2, xywh.y - 2, xywh.x + xywh.w + 4, xywh.y + xywh.h + 4, p);
					}
				}
			}
			
			
			//�@�����̎擾�J�[�h��`��
			if(currentAcqCards != null){
				for (int i = 0; i < currentAcqCards.length; i++) {
				}
			}

			
			////�@����̏�J�[�h��`��
			//�@�J�[�h1�i�\�j
			if(counterCard1 != null){
				final Card card = counterCard1;
				final int suit = card.getSuit();
				if(suit == Card.SPADE || suit == Card.CLUB){
					p.setColor(Color.BLACK);
				}else if(suit == Card.HEART || suit == Card.DIAMOND){
					p.setColor(Color.RED);
				}else{
					p.setColor(Color.GREEN);
				}
				final float dx = 50;
				final float dy = 0;
				final float x = 100;
				final float y = 300;
				final float w = 160;
				final float h = 320;
				canvas.drawRect(x, y, x + w, y + h, p);
				canvas.drawText(card.getNumberString(), x + w / 2, y + h / 2, p);
			}
			//�@�J�[�h2�i����j
			if(counterCard2 != null){
				final Card card = counterCard2;
				final int suit = card.getSuit();
				if(suit == Card.SPADE || suit == Card.CLUB){
					p.setColor(Color.BLACK);
				}else if(suit == Card.HEART || suit == Card.DIAMOND){
					p.setColor(Color.RED);
				}else{
					p.setColor(Color.GREEN);
				}
				final float dx = 50;
				final float dy = 0;
				final float x = 100;
				final float y = 300;
				final float w = 160;
				final float h = 320;
				canvas.drawRect(x, y, x + w, y + h, p);
				canvas.drawText(card.getNumberString(), x + w / 2, y + h / 2, p);
			}
			
			//�@�����̏�J�[�h��`��
			//�@�J�[�h1�i�\�j
			if(currentCard1 != null){
				final Card card = currentCard1;
				final int suit = card.getSuit();
				if(suit == Card.SPADE || suit == Card.CLUB){
					p.setColor(Color.BLACK);
				}else if(suit == Card.HEART || suit == Card.DIAMOND){
					p.setColor(Color.RED);
				}else{
					p.setColor(Color.GREEN);
				}
				final float dx = 50;
				final float dy = 0;
				final float x = 100;
				final float y = 300;
				final float w = 160;
				final float h = 320;
				canvas.drawRect(x, y, x + w, y + h, p);
				canvas.drawText(card.getNumberString(), x + w / 2, y + h / 2, p);
			}
			//�@�J�[�h2�i����j
			if(currentCard2 != null){
				final Card card = currentCard2;
				final int suit = card.getSuit();
				if(suit == Card.SPADE || suit == Card.CLUB){
					p.setColor(Color.BLACK);
				}else if(suit == Card.HEART || suit == Card.DIAMOND){
					p.setColor(Color.RED);
				}else{
					p.setColor(Color.GREEN);
				}
				final float dx = 50;
				final float dy = 0;
				final float x = 100;
				final float y = 300;
				final float w = 160;
				final float h = 320;
				canvas.drawRect(x, y, x + w, y + h, p);
				canvas.drawText(card.getNumberString(), x + w / 2, y + h / 2, p);
			}
			
			
//�@�`��e�X�g
p.setColor(Color.RED);
//�@y�����j���[�Ƃ��̕���������Ă���炵��
canvas.drawRect(this.xTouched - 60, this.yTouched - 240, this.xTouched - 60 + 60, this.yTouched - 240 + 60, p);


		}

	}
	
	/**
	 * �^�b�`���ꂽ
	 * @param e
	 * @return
	 */
	public boolean touched(MotionEvent e){
		final float x = e.getX();
		final float y = e.getY();
		this.xTouched = x;
		this.yTouched = y;

		if(this.cardplay.getGame().isPuttingCard()){
			//�@�J�[�h�I����Ԃ̎�
			
			//�@���݂̎莝���J�[�h�Ƀ^�b�`���Ă��邩�`�F�b�N
			final Card [] currentHandCards = this.cardplay.getGame().getHandCardsCurrentUser();
			for (int i = 0; i < currentHandCards.length; i++) {
				final XYWH xywh = getCurrentHandCardXYWH(i);
				final float x1 = xywh.x;
				final float x2 = xywh.x + xywh.w;
				final float y1 = xywh.y;
				final float y2 = xywh.y + xywh.h;
				if(
						x1 <= this.xTouched - 60 && this.xTouched - 60 <= x2
						&& y1 <= this.yTouched - 240 && this.yTouched - 240 <= y2
				){
					//�@�^�b�`���ꂽ�Ƃ���
					currentHandCards[i].setSelected(true);
				}else{
					//�@�^�b�`���ꂽ�Ƃ��Ȃ�
					currentHandCards[i].setSelected(false);
				}
			}
			
			//�@�ĕ`��˗�
			invalidate();
		}
		
		return true;
	}
	
	//�@���݂̔Ԃ̃��[�U�[�̃J�[�h�`��ʒu���擾
	//�@this.cardplay.getGame().getHandCardsCurrentUser()�̖߂�l�z��̃C���f�b�N�X���w�肳��Ă��邱�Ƃ�O��ɂ���
	private XYWH getCurrentHandCardXYWH(int index){
		final XYWH xywh = new XYWH();
		xywh.x = index * 50;
		xywh.y = 500;
		xywh.w = 50;
		xywh.h = 160;
		return xywh;
	}
	
	/**
	 * �I�����ꂽ�J�[�h���擾
	 * @return �I�����ꂽ�J�[�h���� = null
	 */
	public Card getSelectedCurrentHandCard(){
		final Card [] currentHandCards = this.cardplay.getGame().getHandCardsCurrentUser();
		for (int i = 0; i < currentHandCards.length; i++) {
			if(currentHandCards[i].isSelected()){
				return currentHandCards[i];
			}
		}
		return null;
	}

	
	/**
	 * ��`���ێ��p�N���X
	 * @author chika
	 *
	 */
	private class XYWH {
		float x;
		float y;
		float w;
		float h;
	}
	

}



