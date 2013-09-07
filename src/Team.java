/**
 * @author Xi Gong
 *
 */
public class Team {
	String name;
//	Div div;
	/**
	 * ratingScore describes the capability of a team with a numerical number
	 */
	int ratingScore; 
	int winNum;
	int lossNum;
	Team(String name, int ratingScore) {
		this.name = name;
//		setDiv(name);
		this.ratingScore = ratingScore;
		this.winNum = 0;
		this.lossNum = 0;	
	}
	
//	public void setDiv(String teamName){
//		if(teamName.equals("") || teamName.equals("") || teamName.equals("") || teamName.equals("") || teamName.equals(""))
//			this.div = PA;
//	}
	public void updateWin() {
		this.winNum ++;
	}
	
	public void updateLoss() {
		this.lossNum ++;
	}
}
