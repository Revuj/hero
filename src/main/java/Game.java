import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Hero hero;
    public Game() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();   // resize screen if necessary
        hero = new Hero(10, 10);
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        System.out.println(key);
    }

    public int run() throws IOException {
        draw();
        KeyStroke key = screen.readInput();
        if (key.getKeyType() == KeyType.Character) {
            char character = key.getCharacter();
            switch(character) {
                case 'w':
                    hero.moveUp();
                    break;
                case 'a':
                    hero.moveLeft();
                    break;
                case 's':
                    hero.moveDown();
                    break;
                case 'd':
                    hero.moveRight();
                    break;
                case 'q':
                    screen.close();
                    break;
            }
        }
        else if (key.getKeyType() == KeyType.EOF) {
            return 1;
        }
        processKey(key);
        return 0;
    }


}
