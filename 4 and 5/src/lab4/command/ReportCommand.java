package lab4.command;

import strategy.OutStrategy;

public class ReportCommand implements Command {
    private String message;
    private OutStrategy outStrategy;

    public ReportCommand(String message, OutStrategy outStrategy) {
        this.message = message;
        this.outStrategy = outStrategy;
    }

    @Override
    public boolean execute() {
        outStrategy.out(message);
        return true;
    }
}
