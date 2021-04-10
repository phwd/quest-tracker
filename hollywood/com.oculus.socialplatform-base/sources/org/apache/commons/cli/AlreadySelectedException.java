package org.apache.commons.cli;

import X.AnonymousClass006;

public class AlreadySelectedException extends ParseException {
    public static final long serialVersionUID = 3674381532418544760L;
    public OptionGroup group;
    public Option option;

    public Option getOption() {
        return this.option;
    }

    public OptionGroup getOptionGroup() {
        return this.group;
    }

    public AlreadySelectedException(String str) {
        super(str);
    }

    public AlreadySelectedException(OptionGroup optionGroup, Option option2) {
        super(AnonymousClass006.A0D("The option '", option2.getKey(), "' was specified but an option from this group ", "has already been selected: '", optionGroup.selected, "'"));
        this.group = optionGroup;
        this.option = option2;
    }
}
