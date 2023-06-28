import java.util.*;

public class SoccerLeague {
    public static void main(String[] args) {
        // Create an instance of SoccerLeagueManager
        SoccerLeagueManager leagueManager = new SoccerLeagueManager();

        // Read matches from a file named "matches.txt"
        leagueManager.readMatchesFromFile("matches.txt");

        // Calculate points for each team based on the match results
        leagueManager.calculatePoints();

        // Get the sorted ranking of teams based on their points
        List<Map.Entry<String, Integer>> sortedRanking = leagueManager.getSortedRanking();

        // Print the Soccer League Ranking
        System.out.println("Soccer League Ranking:");
        //Placement
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sortedRanking) {
            // Print the rank, team name, and points
            System.out.println(rank + ". " + entry.getKey() + " " + entry.getValue());
            rank++;
        }
    }
}
