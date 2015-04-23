package jp.co.cyberagent;

import java.util.Arrays;

/**
 * A class for CLI
 */
public class App {

	public static void main(String[] args) {

        String[] fieldString = {
                "######",
                "#.   #",
                "#o   #",
                "#   p#",
                "######"
        };

        FieldOption option = new FieldOption(Arrays.asList(fieldString));
        Field field = new Field(option);

        try (Game game = new Game(new GuiTerminalImpl(), new GameState(field))) {
            game.play();
        } catch (Exception e) {
            // Todo: Error processing
            System.out.println(e.getMessage());
        } finally {
            // Todo: Do something
        }
	}
}
