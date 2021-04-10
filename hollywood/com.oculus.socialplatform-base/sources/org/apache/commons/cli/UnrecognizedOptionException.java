package org.apache.commons.cli;

public class UnrecognizedOptionException extends ParseException {
    public static final long serialVersionUID = -252504690284625623L;
    public String option;

    public String getOption() {
        return this.option;
    }

    public UnrecognizedOptionException(String str) {
        super(str);
    }

    public UnrecognizedOptionException(String str, String str2) {
        super(str);
        this.option = str2;
    }
}
