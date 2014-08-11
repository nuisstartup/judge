package nuis.startup.judge;

public class WaitingStartPlay extends WaitingUserTurnState{
	////　メンバ

	/**
	 * 
	 * @param firstUser
	 */
	public WaitingStartPlay(User firstUser) {
		super(firstUser);
	}

	/**
	 * 先行ユーザーを取得
	 * @return
	 */
	public User getFirstUser(){
		return super.getUserWaitingFor();
	}
	
	@Override
	public String getPlayStateMessage() {
		// TODO Auto-generated method stub
		return "試合を開始します。" + getFirstUser().getName() + "さんの番です。";
	}

}
