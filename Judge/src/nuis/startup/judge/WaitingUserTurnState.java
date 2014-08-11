package nuis.startup.judge;

/**
 * 次のユーザー待ち状態
 * @author chika
 *
 */
public class WaitingUserTurnState extends PlayState {
	////　メンバ
	//　待つユーザー
	private User userWaitingFor;
	
	/**
	 * 
	 * @param userWaitingFor
	 */
	public WaitingUserTurnState(User userWaitingFor) {
		this.userWaitingFor = userWaitingFor;
	}

	/**
	 * 
	 * @return
	 */
	public User getUserWaitingFor() {
		return userWaitingFor;
	}

	/**
	 * 
	 */
	public String getPlayStateMessage() {
		return this.userWaitingFor.getName() + "さんの番です。交代してください。";
	}


}
