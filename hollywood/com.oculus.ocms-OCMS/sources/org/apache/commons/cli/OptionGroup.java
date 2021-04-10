package org.apache.commons.cli;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OptionGroup implements Serializable {
    private static final long serialVersionUID = 1;
    private final Map<String, Option> optionMap = new HashMap();
    private boolean required;
    private String selected;

    public OptionGroup addOption(Option option) {
        this.optionMap.put(option.getKey(), option);
        return this;
    }

    public Collection<String> getNames() {
        return this.optionMap.keySet();
    }

    public Collection<Option> getOptions() {
        return this.optionMap.values();
    }

    public void setSelected(Option option) throws AlreadySelectedException {
        if (option == null) {
            this.selected = null;
            return;
        }
        String str = this.selected;
        if (str == null || str.equals(option.getKey())) {
            this.selected = option.getKey();
            return;
        }
        throw new AlreadySelectedException(this, option);
    }

    public String getSelected() {
        return this.selected;
    }

    public void setRequired(boolean z) {
        this.required = z;
    }

    public boolean isRequired() {
        return this.required;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Option> it = getOptions().iterator();
        sb.append("[");
        while (it.hasNext()) {
            Option next = it.next();
            if (next.getOpt() != null) {
                sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                sb.append(next.getOpt());
            } else {
                sb.append(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                sb.append(next.getLongOpt());
            }
            if (next.getDescription() != null) {
                sb.append(" ");
                sb.append(next.getDescription());
            }
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
