public class Point {
    private int x;

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double calcDistTo(Point point) {
        return Math.sqrt((point.getX() - x) * (point.getX() - x) + (point.getY() - y) * (point.getY() - y));
    }
}
