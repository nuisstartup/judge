package nuis.startup.judge;

public class WaitingStartPlay extends WaitingUserTurnState{
	////�@�����o

	/**
	 * 
	 * @param firstUser
	 */
	public WaitingStartPlay(User firstUser) {
		super(firstUser);
	}

	/**
	 * ��s���[�U�[���擾
	 * @return
	 */
	public User getFirstUser(){
		return super.getUserWaitingFor();
	}
	
	@Override
	public String getPlayStateMessage() {
		// TODO Auto-generated method stub
		return "�������J�n���܂��B" + getFirstUser().getName() + "����̔Ԃł��B";
	}

}
