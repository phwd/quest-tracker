package org.apache.commons.cli;

public class AlreadySelectedException extends ParseException {
    private static final long serialVersionUID = 3674381532418544760L;
    private OptionGroup group;
    private Option option;

    public AlreadySelectedException(String str) {
        super(str);
    }

    public AlreadySelectedException(OptionGroup optionGroup, Option option2) {
        this("The option '" + option2.getKey() + "' was specified but an option from this group " + "has already been selected: '" + optionGroup.getSelected() + "'");
        this.group = optionGroup;
        this.option = option2;
    }

    public OptionGroup getOptionGroup() {
        return this.group;
    }

    public Option getOption() {
        return this.option;
    }
}
