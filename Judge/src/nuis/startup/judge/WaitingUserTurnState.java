package nuis.startup.judge;

/**
 * ���̃��[�U�[�҂����
 * @author chika
 *
 */
public class WaitingUserTurnState extends PlayState {
	////�@�����o
	//�@�҂��[�U�[
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
		return this.userWaitingFor.getName() + "����̔Ԃł��B��サ�Ă��������B";
	}


}
