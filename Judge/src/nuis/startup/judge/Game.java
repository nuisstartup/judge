package nuis.startup.judge;

/**
 * 1�̑ΐ��\���F
 * �ΐ��3�|�C���g��悪���܂�܂ł�\���Bgame�ƕ\���B
 * ������1�|�C���g��^���鏟�s�܂ł�\���Bplay�ƕ\���B
 *
 */
public class Game {
	////�@�����o
	//�@���[�U�[1
	private User user1;
	//�@���[�U�[1
	private User user2;
	
	////�@���[�N
	//�@�������ڂ�
	private int cntPlay;
	//�@���[�U�[�̌��݃|�C���g
	private int pointUser1;
	private int pointUser2;

	//�@���[�U�[�̎������
	private UserTurn userTurn1;
	private UserTurn userTurn2;
	private WaitingUserTurnState waiting1;
	private WaitingUserTurnState waiting2;
	private WaitingStartPlay startPlayState;
	private JudgeState judgeState;
	

	//�@���݂̔Ԃ̃��[�U�[
	private User currentUser;
	//�@���݂̎������
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
	 * �J�����g���[�U�[�̎����D���擾
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
	 * �J�����g���[�U�[�̎擾�D���擾
	 * @param user
	 * @return
	 */
	public Card [] getAcquiredCardsCurrentUser(){
		return null;
	}

	/**
	 * �J�����g���[�U�[�̃J�[�h1���擾
	 * @return
	 */
	public Card getCard1CurrentUser(){
		return null;
	}

	/**
	 * �J�����g���[�U�[�̃J�[�h2���擾
	 * @return
	 */
	public Card getCard2CurrentUser(){
		return null;
	}

	/**
	 * ���胆�[�U�[�̎����D���擾
	 * @param user
	 * @return
	 */
	public Card [] getHandCardsCounterUser(){
		final UserTurn counter = getCounterUserTurn();
		return counter.getHandCards();
	}

	/**
	 * ���胆�[�U�[�̎擾�D���擾
	 * @param user
	 * @return
	 */
	public Card [] getAcquiredCardsCounterUser(){
		return null;
	}

	/**
	 * ���胆�[�U�[�̃J�[�h1���擾
	 * @return
	 */
	public Card getCard1CounterUser(){
		return null;
	}

	/**
	 * ���胆�[�U�[�̃J�[�h2���擾
	 * @return
	 */
	public Card getCard2CounterUser(){
		return null;
	}

	/**
	 * �ΐ�̏��҂��擾
	 * @return
	 */
	public User getGameWinner(){
		return null;
	}

	/**
	 * �ΐ킪�I�������F
	 * 3�|�C���g���ŏI��
	 * @return
	 */
	public boolean gameFinished(){
		return this.pointUser1 >= 3 || this.pointUser2 >= 3;
	}

	/**
	 * �������ڂ����擾
	 * @return
	 */
	public int getCntPlay(){
		return this.cntPlay;
	}

	/**
	 * ���݂̔Ԃ̃��[�U�[���擾
	 * @return
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * �ԂłȂ����[�U�[���擾
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
	 * ���݂̔Ԃ̃��[�U�[������Ԃ��擾
	 * @return
	 */
	public PlayState getCurrentPlayState(){
		return this.currentPlayState;
	}

	/**
	 * �ԂłȂ����[�U�[������Ԃ��擾
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
	 * �����J�n
	 * @param firstUser
	 */
	public void startPlay(User firstUser){
		//�@���[�U�[������Ԃ�������
		this.userTurn1 = new UserTurn(this.user1, Card.SPADE);
		this.userTurn2 = new UserTurn(this.user2, Card.HEART);
		this.waiting1 = new WaitingUserTurnState(this.user1);
		this.waiting2 = new WaitingUserTurnState(this.user2);

		//�@���݂̔Ԃ̃��[�U�[��ݒ肷��
		this.currentUser = firstUser;
		
		//�@���݂̎�����Ԃ�ݒ�
		this.startPlayState = new WaitingStartPlay(firstUser);
		this.currentPlayState = this.startPlayState;

		//�@��������i�߂�
		cntPlay++;
	}

	/**
	 * �J�[�h���o���A�҂���Ԃ֕ύX
	 * �_��F�@UserTurn��Ԃ̎��ɌĂяo������
	 * @return
	 */
	public void putCard(Card card){
		final UserTurn ut = (UserTurn)this.currentPlayState;
		//�@���݂̎�����ԂփJ�[�h�ύX��ݒ肷��
		ut.putCard(card);
		//�@�I����Ԃ���������
		final Card [] currentHandCards = getHandCardsCurrentUser();
		for(Card c: currentHandCards){
			c.setSelected(false);
		}
		
		if(false){
			//�@����1�����I�����Ȃ�W���b�W����
			judge();
			//�@�W���b�W��ԂɕύX
			this.currentPlayState = this.judgeState;

		}else{
			//�@1�����I���łȂ���΃��[�U�[�ԑ҂���ԂɕύX����
			if(ut == this.userTurn1){
				//�@���[�U�[2�҂���ԂɕύX����
				this.currentPlayState = this.waiting2;
			}else{
				//�@���[�U�[1�҂���ԂɕύX����
				this.currentPlayState = this.waiting1;
			}
		}
		
	}

	/**
	 * �҂���Ԃ��烆�[�U�[�̔ԂɕύX����
	 * �_��F�@�҂���ԂŌĂяo������
	 */
	public void nextUserTurn(){
		final WaitingUserTurnState wsp = (WaitingUserTurnState)this.currentPlayState;
		//�@�҂��Ă��郆�[�U�[���擾
		final User user = wsp.getUserWaitingFor();
		//�@������Ԃ�ύX
		this.currentPlayState = user == this.user1 ? this.userTurn1 : this.userTurn2;
	}

	/**
	 * �\�����b�Z�[�W���擾
	 * @return
	 */
	public String getDispMessage(){
		return getCurrentPlayState().getPlayStateMessage();
	}

	/**
	 * ���[�U�[�҂���Ԃ�����
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
	 * �J�[�h���o����Ԃ�����
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
	 * �W���b�W�����Ԃ�����
	 * @return
	 */
	public boolean isJudging() {
		return false;
	}

	
	//�@�W���b�W����
	private void judge(){
		
	}

}








