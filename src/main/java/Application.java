import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        Game game = null;
        try {
            game = new Game();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                if (game.run() == 1)
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
