package nuis.startup.judge;

/**
 * 1つの対戦を表す：
 * 対戦は3ポイント先取が決まるまでを表す。gameと表す。
 * 試合は1ポイントを与える勝敗までを表す。playと表す。
 *
 */
public class Game {
	////　メンバ
	//　ユーザー1
	private User user1;
	//　ユーザー1
	private User user2;
	
	////　ワーク
	//　何試合目か
	private int cntPlay;
	//　ユーザーの現在ポイント
	private int pointUser1;
	private int pointUser2;

	//　ユーザーの試合状態
	private UserTurn userTurn1;
	private UserTurn userTurn2;
	private WaitingUserTurnState waiting1;
	private WaitingUserTurnState waiting2;
	private WaitingStartPlay startPlayState;
	private JudgeState judgeState;
	

	//　現在の番のユーザー
	private User currentUser;
	//　現在の試合状態
	private PlayState currentPlayState;

	
	public Game(User user1, User user2){
		this.user1 = user1;
		this.user2 = user2;
	}
	
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
	 * カレントユーザーの持ち札を取得
	 * @param user
	 * @return
	 */
	public Card [] getHandCardsCurrentUser(){
		if(this.currentPlayState instanceof UserTurn){
			return ((UserTurn)this.currentPlayState).getHandCards();
		}else{
			return null;
		}
	}

	/**
	 * カレントユーザーの取得札を取得
	 * @param user
	 * @return
	 */
	public Card [] getAcquiredCardsCurrentUser(){
		return null;
	}

	/**
	 * カレントユーザーのカード1を取得
	 * @return
	 */
	public Card getCard1CurrentUser(){
		return null;
	}

	/**
	 * カレントユーザーのカード2を取得
	 * @return
	 */
	public Card getCard2CurrentUser(){
		return null;
	}

	/**
	 * 相手ユーザーの持ち札を取得
	 * @param user
	 * @return
	 */
	public Card [] getHandCardsCounterUser(){
		final UserTurn counter = getCounterUserTurn();
		return counter.getHandCards();
	}

	/**
	 * 相手ユーザーの取得札を取得
	 * @param user
	 * @return
	 */
	public Card [] getAcquiredCardsCounterUser(){
		return null;
	}

	/**
	 * 相手ユーザーのカード1を取得
	 * @return
	 */
	public Card getCard1CounterUser(){
		return null;
	}

	/**
	 * 相手ユーザーのカード2を取得
	 * @return
	 */
	public Card getCard2CounterUser(){
		return null;
	}

	/**
	 * 対戦の勝者を取得
	 * @return
	 */
	public User getGameWinner(){
		return null;
	}

	/**
	 * 対戦が終了した：
	 * 3ポイント先取で終了
	 * @return
	 */
	public boolean gameFinished(){
		return this.pointUser1 >= 3 || this.pointUser2 >= 3;
	}

	/**
	 * 何試合目かを取得
	 * @return
	 */
	public int getCntPlay(){
		return this.cntPlay;
	}

	/**
	 * 現在の番のユーザーを取得
	 * @return
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * 番でないユーザーを取得
	 * @return
	 */
	public User getCounterUser(){
		User ret = null;
		if(this.currentUser != null){
			ret = this.currentUser == this.user1 ? this.user2 : this.user1;
		}
		return ret;
	}

	/**
	 * 現在の番のユーザー試合状態を取得
	 * @return
	 */
	public PlayState getCurrentPlayState(){
		return this.currentPlayState;
	}

	/**
	 * 番でないユーザー試合状態を取得
	 * @return
	 */
	public UserTurn getCounterUserTurn(){
		UserTurn ret = null;
		if(this.currentPlayState != null){
			ret = this.currentPlayState == this.userTurn1 ? this.userTurn2 : this.userTurn1;
		}
		return ret;
	}

	/**
	 * 試合開始
	 * @param firstUser
	 */
	public void startPlay(User firstUser){
		//　ユーザー試合状態を初期化
		this.userTurn1 = new UserTurn(this.user1, Card.SPADE);
		this.userTurn2 = new UserTurn(this.user2, Card.HEART);
		this.waiting1 = new WaitingUserTurnState(this.user1);
		this.waiting2 = new WaitingUserTurnState(this.user2);

		//　現在の番のユーザーを設定する
		this.currentUser = firstUser;
		
		//　現在の試合状態を設定
		this.startPlayState = new WaitingStartPlay(firstUser);
		this.currentPlayState = this.startPlayState;

		//　試合数を進める
		cntPlay++;
	}

	/**
	 * カードを出し、待ち状態へ変更
	 * 契約：　UserTurn状態の時に呼び出すこと
	 * @return
	 */
	public void putCard(Card card){
		final UserTurn ut = (UserTurn)this.currentPlayState;
		//　現在の試合状態へカード変更を設定する
		ut.putCard(card);
		//　選択状態を解除する
		final Card [] currentHandCards = getHandCardsCurrentUser();
		for(Card c: currentHandCards){
			c.setSelected(false);
		}
		
		if(false){
			//　もし1試合終了時ならジャッジする
			judge();
			//　ジャッジ状態に変更
			this.currentPlayState = this.judgeState;

		}else{
			//　1試合終了でなければユーザー番待ち状態に変更する
			if(ut == this.userTurn1){
				//　ユーザー2待ち状態に変更する
				this.currentPlayState = this.waiting2;
			}else{
				//　ユーザー1待ち状態に変更する
				this.currentPlayState = this.waiting1;
			}
		}
		
	}

	/**
	 * 待ち状態からユーザーの番に変更する
	 * 契約：　待ち状態で呼び出すこと
	 */
	public void nextUserTurn(){
		final WaitingUserTurnState wsp = (WaitingUserTurnState)this.currentPlayState;
		//　待っているユーザーを取得
		final User user = wsp.getUserWaitingFor();
		//　試合状態を変更
		this.currentPlayState = user == this.user1 ? this.userTurn1 : this.userTurn2;
	}

	/**
	 * 表示メッセージを取得
	 * @return
	 */
	public String getDispMessage(){
		return getCurrentPlayState().getPlayStateMessage();
	}

	/**
	 * ユーザー待ち状態だった
	 * @return
	 */
	public boolean isWaitingUserTurn() {
		if(this.currentPlayState == this.userTurn1 || this.currentPlayState == this.userTurn2){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * カードを出す状態だった
	 * @return
	 */
	public boolean isPuttingCard() {
		if(this.currentPlayState == this.userTurn1 || this.currentPlayState == this.userTurn2){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * ジャッジする状態だった
	 * @return
	 */
	public boolean isJudging() {
		return false;
	}

	
	//　ジャッジする
	private void judge(){
		
	}

}








