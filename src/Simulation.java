/**
 * Xi Gong
 * Sep 6, 2013
 */
import java.io.*;
import java.util.*;
import java.text.*;

public class Simulation {
	HashMap<String, Team> teams = new HashMap<String, Team>();
	ArrayList<Game> games = new ArrayList<Game>();
	int counter = 0;
	
	
	public void demo() {
		printDate();
		initlization();
		updateRecords();
		getResult();
		printDate();
	}
	
	public void initlization() {
		Properties prop = new Properties();
		try{
			prop.load(new FileInputStream("config.properties"));
			Scanner s1 = new Scanner(new FileInputStream(prop.getProperty("teamrec")));
			while(s1.hasNextLine()) {
				String buffer = s1.nextLine();
				teams.put(buffer.split(",")[0], new Team(buffer.split(",")[0], Integer.parseInt(buffer.split(",")[1])));
			}
			s1.close();
			Scanner s2 = new Scanner(new FileInputStream(prop.getProperty("gamerec")));
			while(s2.hasNextLine()) {
				counter++;
				String[] buffer = s2.nextLine().split(",");
				Date temp = null;
				try {
					temp = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH).parse(buffer[0]);
				} catch(ParseException ex) {
					ex.printStackTrace();
				}
				games.add(new Game(counter, temp, buffer[1], buffer[2]));
			}
			s2.close();
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void updateRecords() {
		int count = 0;
		int num = games.size();
		for(Game g : games) {
			count++;
			Team home = teams.get(g.homeTeam);
			Team road = teams.get(g.roadTeam);
			int homeScore = home.ratingScore;
			int roadScore = road.ratingScore;
			if(g.getWinner(homeScore, roadScore).equals(home.name)) {
				home.updateWin();
				road.updateLoss();
			}
			else {
				home.updateLoss();
				road.updateWin();
			}
			if(count % 50 == 0) {
				Logger.log("Has processed " + (count*100.0/num) +"%");
			}
		}
		Logger.log("Done.");
	}
	
	public void printDate() {
		DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date current = new Date();
		Logger.log(myFormat.format(current));
	}
	
	public void getResult() {
		//The reason of using treeMap here is that it's automatically sorted.
		TreeMap<Integer, String> sum = new TreeMap<Integer, String>();
		for(String s : teams.keySet()) {
			sum.put(teams.get(s).winNum, s);
		}
		printResult(sum);
		
	}
	
	public void printResult(TreeMap<Integer, String> sum) {
		Logger.log("Team Name  Win Loss");
		for(Map.Entry<Integer, String> entry : sum.entrySet()) {
			String teamName = entry.getValue();
			Logger.log(teamName + "  " + teams.get(teamName).winNum + "  " + teams.get(teamName).lossNum);
		}
	}

	
	public static void main(String[] args) {
		Simulation myTest = new Simulation();
		myTest.demo();
	}
}
