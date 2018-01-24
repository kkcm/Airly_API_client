package main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class CommandLineAnalizer {
    private HelpFormatter formatter;
    private CommandLine cmd;
    private Options options;

    public CommandLineAnalizer(CommandLine cmd, Options options){
        this.formatter = new HelpFormatter();
        this.cmd = cmd;
        this.options = options;
    }

    public void optionReader (){
        if (cmd.hasOption("help")){
            formatter.printHelp(" ", options);
        }
    }


}
