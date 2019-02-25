import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int width;
    private int height;
    private Hero hero;

    public Arena(Hero hero) {
        this.hero = hero;
    }

    public void draw(Screen screen) {
        hero.draw(screen);
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

    private void moveHero(Position position) {
        hero.setPosition(position);
    }
}
