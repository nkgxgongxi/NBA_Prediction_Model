/**
 * Xi Gong
 * Sep 6, 2013
 */
import java.io.*;
import java.util.*;
import java.text.*;

public class Simulation {
	Map<String, Team> teams = new HashMap<String, Team>();
	ArrayList<Game> games = new ArrayList<Game>();
	int counter = 0;
	
	
	public void demo() {
		printDate();
		initlization();
		updateRecords();
		if(valiadation())
			Logger.log("The game numbers are consistent.");
		else
			Logger.log("There is probably some bugs in the program.");
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
		Team.gameNum = games.size()*2/teams.size();
//		System.out.println(Team.gameNum);
		for(Game g : games) {
			Team home = teams.get(g.homeTeam);
			Team road = teams.get(g.roadTeam);
			int homeScore = home.updateDynamicScore(g.homeTeam);
			int roadScore = road.updateDynamicScore(g.homeTeam);
			if(g.getWinner(homeScore, roadScore).equals(home.name)) {
				home.updateWin();
				road.updateLoss();
			}
			else {
				home.updateLoss();
				road.updateWin();
			}
		}
	}
	
	public boolean valiadation(){
		for(String s : teams.keySet()) {
			if((teams.get(s).winNum + teams.get(s).lossNum) != Team.gameNum)
				return false;
		}
		return true;
	}
	
	public void printDate() {
		DateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date current = new Date();
		Logger.log(myFormat.format(current));
	}
	
	public void getResult() {
		Map<String, Integer> ranking = new HashMap<String, Integer>();
		for(String s : teams.keySet()) {
			ranking.put(s, teams.get(s).winNum);
		}
		ranking = sortByValue(ranking);
		printResult(ranking);
		
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
				return (0 - (o1.getValue()).compareTo(o2.getValue()));
			}
		});
		Map<K, V> result = new LinkedHashMap<K, V>();
		for(Map.Entry<K, V> entry : list) {
			result.put( entry.getKey(), entry.getValue() );
		}
		return result;
	}
	
	public void printResult(Map<String, Integer> sum) {
		Logger.log("Team Name  Win Loss");
		for(String s : sum.keySet()) {
			String teamName = s;
			Logger.log(teamName + "  " + sum.get(teamName) + "  " + (Team.gameNum - sum.get(teamName)));
		}
	}

	
	public static void main(String[] args) {
		Simulation myTest = new Simulation();
		myTest.demo();
	}
}
