package nuis.startup.judge;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;

/**
 * ゲームプレイ中View
 *
 */
public class CardplayView extends View {
	//　ゲームプレイ中Activity
	private CardplayActivity cardplay;

//　タッチされた座標
//デバッグ用
//デバッグ用
//デバッグ用
float xTouched;
float yTouched;
StringBuilder dbgStrb = new StringBuilder();
	
	/**
	 * コンストラクタ
	 * @param cardplay
	 */
	public CardplayView(CardplayActivity cardplay) {
		super(cardplay);
		//　スーパークラスからActivityを取得する方法がわからなかったので
		//　このクラスで保持することにする
		this.cardplay = cardplay;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		//　ペイント生成
		final Paint p = new Paint();
		p.setStyle(Style.STROKE);

//デバッグ用文字列
//dbgStrb.append(this.cardplay.getGame().isPuttingCard() + ":" + this.cardplay.getGame().isWaitingUserTurn() + "; " );
//canvas.drawText(dbgStrb.toString(), 10, 20, p);

		//　対戦を取得
		final Game game = this.cardplay.getGame();
		
		//　試合状態を取得
		final PlayState playState = game.getCurrentPlayState();
		
		if(playState instanceof WaitingUserTurnState){
			//　ユーザーの交代待ちの時
		}else if(playState instanceof UserTurn){
			//　ユーザーの番の時
			
			//　持ち札を取得
			final Card [] currentHandCards = game.getHandCardsCurrentUser();
			final Card [] counterHandCards = game.getHandCardsCounterUser();
			//　取得札を取得
			final Card [] currentAcqCards = game.getAcquiredCardsCurrentUser();
			final Card [] counterAcqCards = game.getAcquiredCardsCurrentUser();
			//　カード1, 2を取得
			final Card currentCard1 = game.getCard1CurrentUser();
			final Card currentCard2 = game.getCard2CurrentUser();
			final Card counterCard1 = game.getCard1CounterUser();
			final Card counterCard2 = game.getCard2CounterUser();

			//　相手の手持ちカードを描画
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
			
			//　相手の取得カードを描画
			if(counterAcqCards != null){
				for (int i = 0; i < counterAcqCards.length; i++) {
				}
			}

			//　自分の手持ちカードを描画
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
			
			
			//　自分の取得カードを描画
			if(currentAcqCards != null){
				for (int i = 0; i < currentAcqCards.length; i++) {
				}
			}

			
			////　相手の場カードを描画
			//　カード1（表）
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
			//　カード2（判定）
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
			
			//　自分の場カードを描画
			//　カード1（表）
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
			//　カード2（判定）
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
			
			
//　描画テスト
p.setColor(Color.RED);
//　yがメニューとかの分だけずれているらしい
canvas.drawRect(this.xTouched - 60, this.yTouched - 240, this.xTouched - 60 + 60, this.yTouched - 240 + 60, p);


		}

	}
	
	/**
	 * タッチされた
	 * @param e
	 * @return
	 */
	public boolean touched(MotionEvent e){
		final float x = e.getX();
		final float y = e.getY();
		this.xTouched = x;
		this.yTouched = y;

		if(this.cardplay.getGame().isPuttingCard()){
			//　カード選択状態の時
			
			//　現在の手持ちカードにタッチしているかチェック
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
					//　タッチされたとする
					currentHandCards[i].setSelected(true);
				}else{
					//　タッチされたとしない
					currentHandCards[i].setSelected(false);
				}
			}
			
			//　再描画依頼
			invalidate();
		}
		
		return true;
	}
	
	//　現在の番のユーザーのカード描画位置を取得
	//　this.cardplay.getGame().getHandCardsCurrentUser()の戻り値配列のインデックスが指定されていることを前提にする
	private XYWH getCurrentHandCardXYWH(int index){
		final XYWH xywh = new XYWH();
		xywh.x = index * 50;
		xywh.y = 500;
		xywh.w = 50;
		xywh.h = 160;
		return xywh;
	}
	
	/**
	 * 選択されたカードを取得
	 * @return 選択されたカード無し = null
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
	 * 矩形情報保持用クラス
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



