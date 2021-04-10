package org.apache.commons.cli;

import X.AnonymousClass006;

public class MissingArgumentException extends ParseException {
    public static final long serialVersionUID = -7098538588704965017L;
    public Option option;

    public Option getOption() {
        return this.option;
    }

    public MissingArgumentException(String str) {
        super(str);
    }

    public MissingArgumentException(Option option2) {
        super(AnonymousClass006.A07("Missing argument for option: ", option2.getKey()));
        this.option = option2;
    }
}
