import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseballElimination {
    private final int numberOfTeams;
    private final List<String> teamNames;
    private final List<Team> teams;
    private final Map<String, Team> teamMap;
    private List<String> certificate;
    private FlowNetwork network;

    public BaseballElimination(String filename) {
        In in = new In(filename);
        this.numberOfTeams = Integer.parseInt(in.readLine());
        this.teamNames = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.teamMap = new HashMap<>();

        String[] lines = in.readAllLines();
        for (int i = 0; i < lines.length; i++) {
            String parts = lines[i].trim().replaceAll("\\s+", " ");
            String[] split = parts.split(" ");

            String teamName = split[0];
            teamNames.add(teamName);

            int wins = Integer.parseInt(split[1]);
            int losses = Integer.parseInt(split[2]);
            int remaining = Integer.parseInt(split[3]);

            Team team = new Team(teamName, wins, losses, remaining, i);

            int index = 0;
            for (int j = 4; j < split.length; j++) {
                team.gamesAgainstTeams[index++] = Integer.parseInt(split[j]);
            }

            teams.add(team);
            teamMap.put(teamName, team);
        }
    }

    public int numberOfTeams() {
        return numberOfTeams;
    }

    public Iterable<String> teams() {
        return teamNames;
    }

    public int wins(String team) {
        if (team == null || !teamMap.containsKey(team)) {
            throw new IllegalArgumentException();
        }

        return teamMap.get(team).wins;
    }

    public int losses(String team) {
        if (team == null || !teamMap.containsKey(team)) {
            throw new IllegalArgumentException();
        }

        return teamMap.get(team).losses;
    }

    public int remaining(String team) {
        if (team == null || !teamMap.containsKey(team)) {
            throw new IllegalArgumentException();
        }

        return teamMap.get(team).remaining;
    }

    public int against(String team1, String team2) {
        if (team1 == null || team2 == null || !teamMap.containsKey(team1) || !teamMap.containsKey(team2)) {
            throw new IllegalArgumentException();
        }

        Team firstTeam = teamMap.get(team1);
        int secondTeamIndex = teamMap.get(team2).index;
        return firstTeam.gamesAgainstTeams[secondTeamIndex];
    }

    public boolean isEliminated(String team) {
        if (team == null || !teamMap.containsKey(team)) {
            throw new IllegalArgumentException();
        }

        if (trivial(team)) {
            return true;
        }

        populateNetwork(team);

        for (FlowEdge edge : network.edges()) {
            if (edge.from() == 0 && edge.capacity() != edge.flow()) {
                return true;
            }
        }

        return false;
    }

    public Iterable<String> certificateOfElimination(String team) {
        if (team == null || !teamMap.containsKey(team)) {
            throw new IllegalArgumentException();
        }

        if (!isEliminated(team)) {
            return null;
        }

        return certificate;
    }

    private void populateNetwork(String team) {
        int n = numberOfTeams - 2;
        int numberOfMatches = n * (n + 1) / 2;
        this.network = new FlowNetwork(2 + numberOfTeams - 1 + numberOfMatches);

        Team t1 = teamMap.get(team);
        List<String> filteredTeams = new ArrayList<>(teamNames);
        filteredTeams.remove(team);

        int temp = 1;
        for (int i = 0; i < filteredTeams.size(); i++) {
            Team t2 = teamMap.get(filteredTeams.get(i));
            for (int j = i + 1; j < filteredTeams.size(); j++) {
                int remaining = t2.gamesAgainstTeams[teamNames.indexOf(filteredTeams.get(j))];
                network.addEdge(new FlowEdge(0, temp, remaining));
                network.addEdge(new FlowEdge(temp, 1 + numberOfMatches + i, Double.POSITIVE_INFINITY));
                network.addEdge(new FlowEdge(temp++, 1 + numberOfMatches + j, Double.POSITIVE_INFINITY));
            }
            int capacity = t1.wins + t1.remaining - t2.wins;
            network.addEdge(new FlowEdge(1 + numberOfMatches + i, network.V() - 1, capacity));
        }

        FordFulkerson fordFulkerson = new FordFulkerson(network, 0, network.V() - 1);

        this.certificate = new ArrayList<>();
        for (int i = 0; i < filteredTeams.size(); i++) {
            if (fordFulkerson.inCut(1 + numberOfMatches + i)) {
                certificate.add(filteredTeams.get(i));
            }
        }
    }

    private boolean trivial(String team) {
        int possibleWins = teamMap.get(team).wins + teamMap.get(team).remaining;

        return teamMap.values().stream().anyMatch(t -> t.wins > possibleWins);
    }

    private class Team {
        private String name;
        private int wins;
        private int losses;
        private int remaining;
        private int index;
        private int[] gamesAgainstTeams;

        private Team(String name, int wins, int losses, int remaining, int index) {
            this.name = name;
            this.wins = wins;
            this.losses = losses;
            this.remaining = remaining;
            this.index = index;
            this.gamesAgainstTeams = new int[numberOfTeams];
        }
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }

}
