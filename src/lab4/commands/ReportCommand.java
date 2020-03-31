package lab4.commands;

public class ReportCommand implements Command {
    public ReportCommand(String message) {
        errMessage = message;
    }

    @Override
    public boolean execute() {
        System.out.println(errMessage);
        return true;
    }

    private String errMessage;
}
