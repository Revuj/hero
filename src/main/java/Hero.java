import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
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


    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
}
