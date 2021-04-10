package org.apache.commons.cli;

public class MissingArgumentException extends ParseException {
    private static final long serialVersionUID = -7098538588704965017L;
    private Option option;

    public MissingArgumentException(String str) {
        super(str);
    }

    public MissingArgumentException(Option option2) {
        this("Missing argument for option: " + option2.getKey());
        this.option = option2;
    }

    public Option getOption() {
        return this.option;
    }
}
