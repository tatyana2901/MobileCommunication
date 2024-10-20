import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BasicStation {
    private String operator;
    private Point coordinate;
    private int radius;

    public BasicStation(String operator, Point coordinate, int radius) {
        this.operator = operator;
        this.coordinate = coordinate;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public boolean isUserWithinRadius(Point userCoordinate) {
        return !(coordinate.calcDistTo(userCoordinate) > radius);
    }

    public static ArrayList<String> consolidate(ArrayList<BasicStation> array, Point userCoordinate) {


        HashMap<String, Integer> hm = new HashMap<>();
        ArrayList<String> strings = new ArrayList<>();

        array.forEach(basicStation -> {
            if (!basicStation.isUserWithinRadius(userCoordinate)) {
                if (hm.containsKey(basicStation.getOperator())) {
                    int x;
                } else {
                    hm.put(basicStation.getOperator(), 0);
                    strings.add(basicStation.getOperator());
                }
            } else {
                if (hm.containsKey(basicStation.getOperator())) {

                    Integer result = hm.get(basicStation.getOperator()) + 1;
                    hm.put(basicStation.getOperator(), result);
                } else {
                    hm.put(basicStation.getOperator(), 1);
                    strings.add(basicStation.getOperator());
                }
            }
        });

        for (int i = 0; i < strings.size() ; i++) {
            String t;
            if (hm.containsKey(t = strings.get(i))) {
                strings.set(i,(t + " " + hm.get(t)));
                hm.remove(t);
        }}
        return strings;
    }

    public static void printResult(ArrayList <String> arrayList) {
        System.out.println(arrayList.size());
        arrayList.forEach(System.out::println);
    }

    public static Point getUserCoordinateFromFile(String filename) {
        List<String> data = null;
        try {
            data = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] strings = data.getLast().split(" ");
        int x = Integer.parseInt(strings[0]);
        int y = Integer.parseInt(strings[1]);

        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "BasicStation{" +
                "operator='" + operator + '\'' +
                ", coordinate=" + coordinate.getX() + coordinate.getY() +
                ", radius=" + radius +
                '}';
    }

    public static ArrayList<BasicStation> getDataFromFile(String fileName) {
        List<String> data = null;
        try {
            data = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        data.removeLast();
        data.removeFirst();
        ArrayList<BasicStation> bc = new ArrayList<>();
        for (int i = 0; i < data.size(); i = i + 2) {
            String[] strings = data.get(i + 1).split(" ");
            int x = Integer.parseInt(strings[0]);
            int y = Integer.parseInt(strings[1]);
            int r = Integer.parseInt(strings[2]);
            bc.add(new BasicStation(data.get(i), new Point(x, y), r));
        }
        return bc;
    }


}
