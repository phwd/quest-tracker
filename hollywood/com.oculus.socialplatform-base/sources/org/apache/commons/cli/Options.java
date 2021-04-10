package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Options implements Serializable {
    public static final long serialVersionUID = 1;
    public final Map<String, Option> longOpts = new LinkedHashMap();
    public final Map<String, OptionGroup> optionGroups = new HashMap();
    public final List<Object> requiredOpts = new ArrayList();
    public final Map<String, Option> shortOpts = new LinkedHashMap();

    public Options addOptionGroup(OptionGroup optionGroup) {
        if (optionGroup.required) {
            this.requiredOpts.add(optionGroup);
        }
        for (Option option : optionGroup.optionMap.values()) {
            option.required = false;
            addOption(option);
            this.optionGroups.put(option.getKey(), optionGroup);
        }
        return this;
    }

    public OptionGroup getOptionGroup(Option option) {
        return this.optionGroups.get(option.getKey());
    }

    public Collection<OptionGroup> getOptionGroups() {
        return new HashSet(this.optionGroups.values());
    }

    public List getRequiredOptions() {
        return Collections.unmodifiableList(this.requiredOpts);
    }

    public List<Option> helpOptions() {
        return new ArrayList(this.shortOpts.values());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ Options: [ short ");
        sb.append(this.shortOpts.toString());
        sb.append(" ] [ long ");
        sb.append(this.longOpts);
        sb.append(" ]");
        return sb.toString();
    }

    public List<String> getMatchingOptions(String str) {
        String stripLeadingHyphens = Util.stripLeadingHyphens(str);
        ArrayList arrayList = new ArrayList();
        if (this.longOpts.keySet().contains(stripLeadingHyphens)) {
            return Collections.singletonList(stripLeadingHyphens);
        }
        for (String str2 : this.longOpts.keySet()) {
            if (str2.startsWith(stripLeadingHyphens)) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    public Option getOption(String str) {
        Map<String, Option> map;
        String stripLeadingHyphens = Util.stripLeadingHyphens(str);
        if (this.shortOpts.containsKey(stripLeadingHyphens)) {
            map = this.shortOpts;
        } else {
            map = this.longOpts;
        }
        return map.get(stripLeadingHyphens);
    }

    public Collection<Option> getOptions() {
        return Collections.unmodifiableCollection(helpOptions());
    }

    public boolean hasLongOption(String str) {
        return this.longOpts.containsKey(Util.stripLeadingHyphens(str));
    }

    public boolean hasOption(String str) {
        String stripLeadingHyphens = Util.stripLeadingHyphens(str);
        if (this.shortOpts.containsKey(stripLeadingHyphens) || this.longOpts.containsKey(stripLeadingHyphens)) {
            return true;
        }
        return false;
    }

    public boolean hasShortOption(String str) {
        return this.shortOpts.containsKey(Util.stripLeadingHyphens(str));
    }

    public Options addOption(String str, String str2) {
        addOption(str, null, false, str2);
        return this;
    }

    public Options addOption(String str, String str2, boolean z, String str3) {
        addOption(new Option(str, str2, z, str3));
        return this;
    }

    public Options addOption(String str, boolean z, String str2) {
        addOption(str, null, z, str2);
        return this;
    }

    public Options addOption(Option option) {
        String key = option.getKey();
        if (option.hasLongOpt()) {
            this.longOpts.put(option.longOpt, option);
        }
        if (option.required) {
            if (this.requiredOpts.contains(key)) {
                List<Object> list = this.requiredOpts;
                list.remove(list.indexOf(key));
            }
            this.requiredOpts.add(key);
        }
        this.shortOpts.put(key, option);
        return this;
    }
}
