package util;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSV {
    protected String fileName = "DATA_IA/Data.csv";
    protected String winner;
    protected String nbPlayers;
    protected String nbTeams;
    protected String gridSize;
    protected String depth;

    protected List<List<String>> records;

    public CSV(String winner, String nbPlayers, String nbTeams, String gridSize, String depth) {
        this.winner = winner;
        this.nbPlayers = nbPlayers;
        this.nbTeams = nbTeams;
        this.depth = depth;
        this.gridSize = gridSize;
        this.records= new ArrayList<>();
    }

    public CSV() {
        this.records = new ArrayList<>();
    }

      public void addDataCSV(String winner, String nbPlayers, String nbTeams, String gridSize,String typeOfPlayers, String depth, String coordonees ) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName, true))) {
            StringBuilder strb = new StringBuilder();
            if (!this.records.isEmpty()) {
                for (List<String> record : this.records) {
                    for (String value : record) {
                        strb.append(value).append(",");
                    }
                    strb.append("\n");
                }
            }
            
            strb.append(gridSize.isEmpty() ? "None" : gridSize).append(",");
            strb.append(nbPlayers.isEmpty() ? "None" : nbPlayers).append(",");
            strb.append(nbTeams.isEmpty() ? "None" : nbTeams).append(",");
            strb.append(typeOfPlayers.isEmpty() ? "None" : typeOfPlayers).append(",");;
            strb.append(winner.isEmpty() ? "None" : winner).append(",");;
            strb.append(depth.isEmpty() ? "None" : depth).append(",");;
            strb.append(coordonees.isEmpty() ? "None" : coordonees).append("\n");
           

            writer.write(strb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initDataCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName, true))) {
            StringBuilder strb = new StringBuilder();

            strb.append("Type of grid, ");
            strb.append("Number of players, ");
            strb.append("Number of teams, ");
            strb.append("Type of players, ");
            strb.append("winner, ");
            strb.append("Depth, ");
            strb.append("Coordonees Winner \n");

            writer.write(strb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readCSVFile() {
        try (Scanner scanner = new Scanner(new File(this.fileName))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public List<List<String>> getRecords() {
        return this.records;
    }
}
