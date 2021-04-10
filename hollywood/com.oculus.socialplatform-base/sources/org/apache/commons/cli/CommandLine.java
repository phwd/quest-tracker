package org.apache.commons.cli;

import X.AnonymousClass006;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class CommandLine implements Serializable {
    public static final long serialVersionUID = 1;
    public final List<String> args = new LinkedList();
    public final List<Option> options = new ArrayList();

    public void addArg(String str) {
        this.args.add(str);
    }

    public void addOption(Option option) {
        this.options.add(option);
    }

    public List<String> getArgList() {
        return this.args;
    }

    public String[] getArgs() {
        String[] strArr = new String[this.args.size()];
        this.args.toArray(strArr);
        return strArr;
    }

    public Properties getOptionProperties(String str) {
        Properties properties = new Properties();
        for (Option option : this.options) {
            if (str.equals(option.opt) || str.equals(option.longOpt)) {
                List<String> list = option.values;
                if (list.size() >= 2) {
                    properties.put(list.get(0), list.get(1));
                } else if (list.size() == 1) {
                    properties.put(list.get(0), "true");
                }
            }
        }
        return properties;
    }

    public Option[] getOptions() {
        List<Option> list = this.options;
        return (Option[]) list.toArray(new Option[list.size()]);
    }

    public Iterator<Option> iterator() {
        return this.options.iterator();
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.cli.Option resolveOption(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r3 = org.apache.commons.cli.Util.stripLeadingHyphens(r5)
            java.util.List<org.apache.commons.cli.Option> r0 = r4.options
            java.util.Iterator r2 = r0.iterator()
        L_0x000a:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0027
            java.lang.Object r1 = r2.next()
            org.apache.commons.cli.Option r1 = (org.apache.commons.cli.Option) r1
            java.lang.String r0 = r1.opt
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0026
            java.lang.String r0 = r1.longOpt
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x000a
        L_0x0026:
            return r1
        L_0x0027:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.CommandLine.resolveOption(java.lang.String):org.apache.commons.cli.Option");
    }

    public Object getParsedOptionValue(String str) throws ParseException {
        String optionValue = getOptionValue(str);
        Option resolveOption = resolveOption(str);
        if (resolveOption == null || optionValue == null) {
            return null;
        }
        return TypeHandler.createValue(optionValue, resolveOption.type);
    }

    public Object getOptionObject(char c) {
        return getOptionObject(String.valueOf(c));
    }

    @Deprecated
    public Object getOptionObject(String str) {
        try {
            return getParsedOptionValue(str);
        } catch (ParseException e) {
            System.err.println(AnonymousClass006.A0B("Exception found converting ", str, " to desired type: ", e.getMessage()));
            return null;
        }
    }

    public String getOptionValue(char c) {
        return getOptionValue(String.valueOf(c));
    }

    public String getOptionValue(char c, String str) {
        return getOptionValue(String.valueOf(c), str);
    }

    public String getOptionValue(String str) {
        String[] optionValues = getOptionValues(str);
        if (optionValues == null) {
            return null;
        }
        return optionValues[0];
    }

    public String getOptionValue(String str, String str2) {
        String optionValue = getOptionValue(str);
        return optionValue == null ? str2 : optionValue;
    }

    public String[] getOptionValues(char c) {
        return getOptionValues(String.valueOf(c));
    }

    public String[] getOptionValues(String str) {
        ArrayList arrayList = new ArrayList();
        for (Option option : this.options) {
            if (str.equals(option.opt) || str.equals(option.longOpt)) {
                arrayList.addAll(option.values);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public boolean hasOption(char c) {
        return hasOption(String.valueOf(c));
    }

    public boolean hasOption(String str) {
        return this.options.contains(resolveOption(str));
    }
}
