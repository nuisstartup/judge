package nuis.startup.judge;

/**
 * 1���̃J�[�h��\��
 * @author chika
 *
 */
public class Card {
	//�@�萔
	public static final int SPADE = 0;
	public static final int HEART = 1;
	public static final int DIAMOND = 2;
	public static final int CLUB = 3;
	public static final int JOKER = 4;
	
	////�@�����o
	//�@��
	private int number;
	//�@�}�[�N
	private int suit;
	//�@�G�D�ł���
	boolean picture;
	//�@A�`K�ł���
	boolean aToK;
	//�@�I������Ă���
	boolean selected;

	/**
	 * �R���X�g���N�^
	 * @param number ����
	 * @param suit �}�[�N
	 */
	public Card(int number, int suit) {
		this.number = number;
		this.suit = suit;
		//�@���ɂ�鏉���ݒ�
		if(10 <= number && number <= 13){
			this.picture = true;
		}else{
			this.picture = false;
		}
		if(number == 1 || (11 <= number && number <= 13)){
			this.aToK = true;
		}else{
			this.aToK = false;
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * 
	 * @return
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPicture() {
		return picture;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isAToK() {
		return aToK;
	}

	/**
	 * �����̕�����\�����擾
	 * @return
	 */
	public String getNumberString(){
		String ret = "error";
		if(this.suit == JOKER){
			ret = "Joker";
		}else{
			switch(this.number){
			case 11:
				ret = "J";
				break;
			case 12:
				ret = "Q";
				break;
			case 13:
				ret = "K";
				break;
			default:
				ret = "" + number;
				break;
			}
		}
		return ret;
	}

	
}




