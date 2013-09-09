/**
 * @author Xi Gong
 *
 */
public class Team {
	static int gameNum = 0;
	String name;
//	Div div;
	/**
	 * ratingScore describes the capability of a team with a numerical number
	 */
	int ratingScore; 
	int winNum;
	int lossNum;
	boolean lastWin;
	boolean lastLoss;
	int winningStreak;
	int losingStreak;
	int dynamicScore;
	
	Team(String name, int ratingScore) {
		this.name = name;
//		setDiv(name);
		this.ratingScore = ratingScore;
		this.winNum = 0;
		this.lossNum = 0;
		this.lastWin = false;
		this.lastLoss = false;
		this.winningStreak = 0;
		this.losingStreak = 0;
		this.dynamicScore = ratingScore;
	}
	
//	public void setDiv(String teamName){
//		if(teamName.equals("") || teamName.equals("") || teamName.equals("") || teamName.equals("") || teamName.equals(""))
//			this.div = PA;
//	}
	public void updateWin() {
		winNum++;
		lastWin = true;
		winningStreak++;
		losingStreak = 0;
		lastLoss = false;
	}
	
	public void updateLoss() {
		lossNum++;
		lastLoss = true;
		losingStreak++;
		winningStreak = 0;
		lastWin = false;
	}
	
	public boolean isVisitor(String homeTeam) {
		if(name != homeTeam)
			return true;
		else
			return false;
	}
	
	public int updateDynamicScore(String homeTeam) {
		if(this.isVisitor(homeTeam))
			dynamicScore *= 0.95;
		dynamicScore = Preprocess.toClose(dynamicScore*Math.pow(0.98, winningStreak)*Math.pow(1.02, losingStreak));
		if(dynamicScore <= 0 || Math.abs(dynamicScore - ratingScore) > 0.3*ratingScore)
			dynamicScore = ratingScore;
		return dynamicScore;
	}
}
