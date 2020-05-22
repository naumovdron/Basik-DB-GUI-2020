package lab4.command;

public class ReportCommand implements Command {
    public ReportCommand(String message) {
        this.message = message;
    }

    @Override
    public boolean execute() {
        System.out.println(message);
        return true;
    }

    private String message;
}
