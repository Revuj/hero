import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position;

    public Hero(int x, int y) {
        this.setPosition(new Position(x, y));
    }

    public void setPosition(Position position) {
        Position pos = new Position(position.getX(), position.getY());
        this.position = pos;
    }

    public Position getPosition() {
        return this.position;
    }

    public Position moveUp() {
        return new Position(this.position.getX(), this.position.getY()-1);
    }

    public Position moveDown() {
        return new Position(this.position.getX(), this.position.getY()+1);
    }

    public Position moveLeft() {
        return new Position(this.position.getX()-1, this.position.getY());
    }

    public Position moveRight() {
        return new Position(this.position.getX()+1, this.position.getY());
    }


    public void draw(Screen screen) {
        screen.setCharacter(getPosition().getX(), getPosition().getY(), new TextCharacter('X'));
    }
}
