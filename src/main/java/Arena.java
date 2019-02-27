import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Walls> walls;

    public Arena(Hero hero, int width, int height) {
        this.hero = hero;
        this.width = width;
        this.height = height;
        this.walls = createWalls();
    }

    private List<Walls> createWalls() {
        List<Walls> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Walls(c, 0));
            walls.add(new Walls(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Walls(0, r));
            walls.add(new Walls(width - 2, r));
        }

        return walls;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Walls wall : walls)
            wall.draw(graphics);
    }

    public int processKey(KeyStroke key) {

        if (key.getKeyType() == KeyType.Character) {
            char character = key.getCharacter();
            switch(character) {
                case 'w':
                    moveHero(hero.moveUp());
                    break;
                case 'a':
                    moveHero(hero.moveLeft());
                    break;
                case 's':
                    moveHero(hero.moveDown());
                    break;
                case 'd':
                    moveHero(hero.moveRight());
                    break;
                case 'q':
                    return 2;
            }
        }
        else if (key.getKeyType() == KeyType.EOF) {
            return 1;
        }
        return 0;
    }

    private boolean canHeroMove(Position position) {
        if (position.getX() > width - 3 || position.getX() < 2 || position.getY() > height - 2|| position.getY() < 1)
            return false;
        else
            return true;
    }

    private void moveHero(Position position) {

        if (canHeroMove(position))
            hero.setPosition(position);
    }
}
