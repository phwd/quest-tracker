package org.apache.commons.cli;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OptionGroup implements Serializable {
    public static final long serialVersionUID = 1;
    public final Map<String, Option> optionMap = new HashMap();
    public boolean required;
    public String selected;

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

    public String getSelected() {
        return this.selected;
    }

    public boolean isRequired() {
        return this.required;
    }

    public void setSelected(Option option) throws AlreadySelectedException {
        String key;
        if (option == null) {
            key = null;
        } else {
            String str = this.selected;
            if (str == null || str.equals(option.getKey())) {
                key = option.getKey();
            } else {
                throw new AlreadySelectedException(this, option);
            }
        }
        this.selected = key;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Option> it = this.optionMap.values().iterator();
        String str = "[";
        while (true) {
            sb.append(str);
            while (it.hasNext()) {
                Option next = it.next();
                String str2 = next.opt;
                if (str2 != null) {
                    sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                } else {
                    sb.append(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                    str2 = next.longOpt;
                }
                sb.append(str2);
                String str3 = next.description;
                if (str3 != null) {
                    sb.append(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
                    sb.append(str3);
                }
                if (it.hasNext()) {
                    str = ", ";
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public void setRequired(boolean z) {
        this.required = z;
    }
}
