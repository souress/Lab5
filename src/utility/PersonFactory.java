package utility;

public class PersonFactory {
    private final Console console;
    private Integer id;

    public PersonFactory(Integer id, Console console) {
        this.id = id;
        this.console = console;
    }

}
