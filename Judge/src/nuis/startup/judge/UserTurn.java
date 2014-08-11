package nuis.startup.judge;

import java.util.ArrayList;

/**
 * ���[�U�[�̔ԏ�ԁF
 * @author chika
 *
 */
public class UserTurn extends PlayState {
	//�@���[�U�[
	private User user;
	
	//�@���[�U�[�̌��ݎ����D�̃��X�g
	private ArrayList<Card> aHandCard = new ArrayList<>();
	//�@���[�U�[�̎g�p�ςݎD�̃��X�g
	private ArrayList<Card> aDoneCard = new ArrayList<>();
	//�@���[�U�[�̏�D1
	private Card baCard1;
	//�@���[�U�[�̏�D2
	private Card baCard2;

	

	public UserTurn(User user, int cardSuit) {
		super();
		
		this.user = user;

		////�@�J�[�h��14���Âz��
		//�@1�`13
		for (int i = 0; i < 13; i++) {
			this.aHandCard.add(new Card(i + 1, cardSuit));
		}
		//�@�W���[�J�[
		this.aHandCard.add(new Card(0, Card.JOKER));
	
	
	}

	/**
	 * �����D���擾
	 * @param user
	 * @return
	 */
	public Card[] getHandCards() {
		Card [] ret = new Card[this.aHandCard.size()];
		ret = this.aHandCard.toArray(ret);
		return ret;
	}

	/**
	 * �g�p�ςݎD���擾
	 * @param user
	 * @return
	 */
	public Card[] getDoneCards() {
		Card [] ret = new Card[this.aDoneCard.size()];
		ret = this.aDoneCard.toArray(ret);
		return ret;
	}
	
	/**
	 * �J�[�h���o��
	 * 
	 * @return
	 */
	public void putCard(Card card){


//�@����e�X�g
if(card != null){
this.aHandCard.remove(card);
}

	
	}

	/**
	 * 
	 */
	public String getPlayStateMessage(){
		return this.user.getName() + "����̔Ԃł��B";
	}


}




