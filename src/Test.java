/**
 * Xi Gong
 * Sep 6, 2013
 */

public class Test {
	public static void main(String[] args) {
		String test = new String("abc 1");
//		System.out.println(test.split(" ")[1]);
		Team myTeam = new Team(test.split(" ")[0], Integer.parseInt(test.split(" ")[1]));
		System.out.println("Team: " + myTeam.name);
		System.out.println("Capacity: " + myTeam.ratingScore);
	}
}
