package nuis.startup.judge;

import java.util.ArrayList;

/**
 * ユーザーの番状態：
 * @author chika
 *
 */
public class UserTurn extends PlayState {
	//　ユーザー
	private User user;
	
	//　ユーザーの現在持ち札のリスト
	private ArrayList<Card> aHandCard = new ArrayList<>();
	//　ユーザーの使用済み札のリスト
	private ArrayList<Card> aDoneCard = new ArrayList<>();
	//　ユーザーの場札1
	private Card baCard1;
	//　ユーザーの場札2
	private Card baCard2;

	

	public UserTurn(User user, int cardSuit) {
		super();
		
		this.user = user;

		////　カードを14枚づつ配る
		//　1～13
		for (int i = 0; i < 13; i++) {
			this.aHandCard.add(new Card(i + 1, cardSuit));
		}
		//　ジョーカー
		this.aHandCard.add(new Card(0, Card.JOKER));
	
	
	}

	/**
	 * 持ち札を取得
	 * @param user
	 * @return
	 */
	public Card[] getHandCards() {
		Card [] ret = new Card[this.aHandCard.size()];
		ret = this.aHandCard.toArray(ret);
		return ret;
	}

	/**
	 * 使用済み札を取得
	 * @param user
	 * @return
	 */
	public Card[] getDoneCards() {
		Card [] ret = new Card[this.aDoneCard.size()];
		ret = this.aDoneCard.toArray(ret);
		return ret;
	}
	
	/**
	 * カードを出す
	 * 
	 * @return
	 */
	public void putCard(Card card){


//　動作テスト
if(card != null){
this.aHandCard.remove(card);
}

	
	}

	/**
	 * 
	 */
	public String getPlayStateMessage(){
		return this.user.getName() + "さんの番です。";
	}


}




