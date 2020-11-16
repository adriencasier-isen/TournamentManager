package fr.isen.m1.tourament.console;

import java.util.Arrays;

public class CommandContext {

    public final String parsedCommandName;
    public final String[] args;
    public final String originalCommand;

    public CommandContext(String cmd) {
        String[] splitCmd = cmd.split(" ");
        this.parsedCommandName = splitCmd[0].toLowerCase();
        this.args = Arrays.copyOfRange(splitCmd, 1, splitCmd.length);
        this.originalCommand = cmd;
    }
}
