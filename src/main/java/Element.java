public class Element {
    private Position position;

    public Element(int x, int y) {
        this.setPosition(new Position(x,y));
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
