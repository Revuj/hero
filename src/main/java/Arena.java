import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.*;

import static java.lang.StrictMath.abs;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Walls> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(Hero hero, int width, int height) {
        this.hero = hero;
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 4) + 2, random.nextInt(height - 2) + 1));
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            monsters.add(new Monster(random.nextInt(width - 4) + 2, random.nextInt(height - 2) + 1));
        return monsters;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        for (Walls wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        for (Monster monster : monsters)
            monster.draw(graphics);
        hero.draw(graphics);
    }

    public int processKey(KeyStroke key) {

        if (key.getKeyType() == KeyType.Character) {
            char character = key.getCharacter();
            switch(character) {
                case 'w':
                    if (moveHero(hero.moveUp()) != 0)
                        return 2;
                    break;
                case 'a':
                    if (moveHero(hero.moveLeft()) != 0)
                        return 2;
                    break;
                case 's':
                    if (moveHero(hero.moveDown()) != 0)
                        return 2;
                    break;
                case 'd':
                    if (moveHero(hero.moveRight()) != 0)
                        return 2;
                    break;
                case 'q':
                    return 2;
            }
            if (updateMonsters() == 1)
                return 2;
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

    public boolean canMonsterMove(Position position) {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(position))
                return false;
        }
        return true;
    }

    private void retrieveCoins() {

        Position heroPos = hero.getPosition();

        for (Iterator<Coin> it = coins.listIterator(); it.hasNext();) {
            if (heroPos.equals(it.next().getPosition())) {
                it.remove();
            }
        }
    }

    private int isHeroAlive() {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(hero.getPosition())) {
                return 1;
            }
        }
        return 0;
    }

    private int moveHero(Position position) {

        if (canHeroMove(position)) {
            hero.setPosition(position);
            retrieveCoins();
            if (isHeroAlive() == 0)
                return 0;
            else
                return 1;
        }
        return 0;
    }

    private int updateMonsters() {
        for (Monster monster : monsters) {
            int deltaX = hero.getPosition().getX() - monster.getPosition().getX();
            int deltaY = hero.getPosition().getY() - monster.getPosition().getY();

            Position newPos;

            if (abs(deltaX) > abs(deltaY)) {
                if (deltaX > 0)
                    newPos = monster.moveRight();
                else
                    newPos = monster.moveLeft();
                System.out.println("moving!?");
            }
            else {
                if (deltaY > 0)
                    newPos = monster.moveDown();
                else
                    newPos = monster.moveUp();
            }
            if (canMonsterMove(newPos))
                monster.setPosition(newPos);
        }
        if (isHeroAlive() == 0)
            return 0;
        else
            return 1;
    }

    public void spawnCoin() {
        Random random = new Random();
        if (coins.size() < 8 && random.nextInt(30) == 25) {
            coins.add(new Coin(random.nextInt(width - 4) + 2, random.nextInt(height - 2) + 1));
        }
    }
}
