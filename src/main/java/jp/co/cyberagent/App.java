package jp.co.cyberagent;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import java.io.IOException;
import java.util.Arrays;

/**
 * A class for CLI
 */
public class App {

    @Option(name = "-h", aliases = "--help", usage = "print usage message and exit")
    private boolean showUsage;

    @Option(name = "-l", aliases = "--limit", usage = "set limit")
    private int limit = 30;

    @Argument(index = 0, metaVar = "arguments...", handler = StringArrayOptionHandler.class)
    private String[] arguments;

    public static void main(String[] args) throws IOException, InterruptedException {

        String[] fieldString = {
                "######",
                "#.   #",
                "#o   #",
                "#   p#",
                "######"
        };

        App app = new App();

        CmdLineParser parser = new CmdLineParser(app);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            parser.printUsage(System.err);
        }

        if (app.showUsage) {
            parser.printUsage(System.err);
            return;
        }

        FieldOption option = new FieldOption(Arrays.asList(fieldString));
        while (true) {
            Field field = new Field(option);
            try (Game game = new Game(new GuiTerminalImpl(), new GameState(field, app.limit))) {
                game.play();
                if (!game.retry) break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
	}
}
