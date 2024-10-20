import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
       BasicStation.printResult(
                BasicStation.consolidate(BasicStation.getDataFromFile("notes.txt"),BasicStation.getUserCoordinateFromFile("notes.txt")));
    }
}