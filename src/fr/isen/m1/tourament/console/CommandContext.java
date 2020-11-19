package fr.isen.m1.tourament.console;

import java.util.Arrays;
import java.util.Optional;

public class CommandContext {

    public final String parsedCommandName;
    public final String[] args;
    public final String originalCommand;
    public final String prefix;

    public CommandContext(String cmd, Optional<String> _prefix) {
        String[] splitCmd = cmd.split(" ");
        this.parsedCommandName = splitCmd[0].toLowerCase();
        this.args = Arrays.copyOfRange(splitCmd, 1, splitCmd.length);
        this.originalCommand = cmd;
        this.prefix = _prefix.orElse(null);
    }
}
