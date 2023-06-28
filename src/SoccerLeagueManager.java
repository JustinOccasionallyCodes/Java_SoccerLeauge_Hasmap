import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SoccerLeagueManager {
    private List<String> matches;
    private Map<String, Integer> teamPoints;

    // Constructor
    public SoccerLeagueManager() {
        matches = new ArrayList<>();
        teamPoints = new TreeMap<>(); // Changed the HashMap to TreeMap
    }

    // Read matches from a file
    public void readMatchesFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                matches.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Calculate team points based on match results
    public void calculatePoints() {
        for (String match : matches) {
            // Split the match into teams and scores
            String[] parts = match.split(",");
            String team1 = parts[0].trim().split(" ")[0];
            String team2 = parts[1].trim().split(" ")[0];
            int score1 = Integer.parseInt(parts[0].trim().split(" ")[1]);
            int score2 = Integer.parseInt(parts[1].trim().split(" ")[1]);

            // Initialize team points if they don't exist in the map
            teamInit(team1);
            teamInit(team2);

            // Update points based on the match result
            if (score1 > score2) {
                // If team1 wins then +3
                teamPoints.put(team1, teamPoints.get(team1) + 3);
            } else if (score1 < score2) {
                // If team2 wins then +3
                teamPoints.put(team2, teamPoints.get(team2) + 3);
            } else {
                // If it's a draw +1
                teamPoints.put(team1, teamPoints.get(team1) + 1);
                teamPoints.put(team2, teamPoints.get(team2) + 1);
            }
        }
    }


    // Initialize a team's points if it doesn't exist in the map
    private void teamInit(String team) {
        teamPoints.putIfAbsent(team, 0);
    }

    // Sorted ranking of teams based on points -> desc
    public List<Map.Entry<String, Integer>> getSortedRanking() {
        // Create a new ArrayList and initialize it with the entries from the 'teamPoints' map
        List<Map.Entry<String, Integer>> sortedRanking = new ArrayList<>(teamPoints.entrySet());

        // Sort the sortedRanking
        Collections.sort(sortedRanking, (o1, o2) -> {
            // Compare points descending order
            int pointsComparison = Integer.compare(o2.getValue(), o1.getValue());


            if (pointsComparison != 0) {
                //winner
                return pointsComparison;
            } else {
                // Draw
                return o1.getKey().compareTo(o2.getKey());
            }
        });


        return sortedRanking;
    }
}

