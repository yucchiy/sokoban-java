package jp.co.cyberagent;

/**
 * A class for CLI
 */
public class App {

	public static void main(String[] args) {
        try (Game game = new Game(new GuiTerminalImpl())) {
            game.play();
        } catch (Exception e) {
            // Todo: Error processing
            System.out.println(e.getMessage());
        } finally {
            // Todo: Do something
        }
	}
}
