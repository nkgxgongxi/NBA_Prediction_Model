/**
 * Xi Gong
 * Sep 6, 2013
 */
import java.util.Date;
import java.util.Random;

public class Game {
	int gameId;
	Date date;
	String homeTeam;
	String roadTeam;
	String winner;
	Game(int id, Date d, String h, String r) {
		this.gameId = id;
		this.date = d;
		this.homeTeam = h;
		this.roadTeam = r;
		this.winner = null;
	}
	
	public String getWinner(int homeScore, int roadScore) {
		Random randomGenerator = new Random();
		if(homeScore > roadScore) {
			this.winner = this.homeTeam;
			return this.homeTeam;
		}
		else if(homeScore < roadScore) {
			this.winner = this.roadTeam;
			return this.roadTeam;
		}
		else {
			int randomInt = randomGenerator.nextInt();
//			System.out.println(randomInt);
			if(randomInt%2 == 0)
				return this.homeTeam;
			else
				return this.roadTeam;
		}
	}
	
//	public static void main(String[] args) {
//		for(int i = 0; i < 5; i++)
//			System.out.println(new Game(i, null, "a", "b").getWinner(50, 50));
//	}
}
