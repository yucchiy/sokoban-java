package jp.co.cyberagent;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import java.io.BufferedReader;
import java.io.FileReader;
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
            return;
        }

        if (app.showUsage) {
            parser.printUsage(System.err);
            return;
        }

        FieldOption option = new FieldOption(Arrays.asList(fieldString));

        GameState state = null;
        try (JsonReader reader = new JsonReader(new BufferedReader(new FileReader("./state.json")))) {
            Gson gson = new Gson();
            state = gson.fromJson(reader, GameState.class);
        } catch (Exception e) {
            e.printStackTrace();
            state = new GameState(new Field(option), app.limit);
        }

        if (state == null || state.field == null || state.actionStack == null) state = new GameState(new Field(option), app.limit);

        while (true) {
            try (Game game = new Game(new GuiTerminalImpl(), state)) {
                game.play();
                if (!game.retry) break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
	}
}
