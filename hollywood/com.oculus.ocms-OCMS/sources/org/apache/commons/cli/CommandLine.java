package org.apache.commons.cli;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class CommandLine implements Serializable {
    private static final long serialVersionUID = 1;
    private final List<String> args = new LinkedList();
    private final List<Option> options = new ArrayList();

    protected CommandLine() {
    }

    public boolean hasOption(String str) {
        return this.options.contains(resolveOption(str));
    }

    public boolean hasOption(char c) {
        return hasOption(String.valueOf(c));
    }

    @Deprecated
    public Object getOptionObject(String str) {
        try {
            return getParsedOptionValue(str);
        } catch (ParseException e) {
            PrintStream printStream = System.err;
            printStream.println("Exception found converting " + str + " to desired type: " + e.getMessage());
            return null;
        }
    }

    public Object getParsedOptionValue(String str) throws ParseException {
        String optionValue = getOptionValue(str);
        Option resolveOption = resolveOption(str);
        if (resolveOption == null || optionValue == null) {
            return null;
        }
        return TypeHandler.createValue(optionValue, resolveOption.getType());
    }

    public Object getOptionObject(char c) {
        return getOptionObject(String.valueOf(c));
    }

    public String getOptionValue(String str) {
        String[] optionValues = getOptionValues(str);
        if (optionValues == null) {
            return null;
        }
        return optionValues[0];
    }

    public String getOptionValue(char c) {
        return getOptionValue(String.valueOf(c));
    }

    public String[] getOptionValues(String str) {
        ArrayList arrayList = new ArrayList();
        for (Option option : this.options) {
            if (str.equals(option.getOpt()) || str.equals(option.getLongOpt())) {
                arrayList.addAll(option.getValuesList());
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.cli.Option resolveOption(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r4 = org.apache.commons.cli.Util.stripLeadingHyphens(r4)
            java.util.List<org.apache.commons.cli.Option> r0 = r3.options
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x002c
            java.lang.Object r1 = r0.next()
            org.apache.commons.cli.Option r1 = (org.apache.commons.cli.Option) r1
            java.lang.String r2 = r1.getOpt()
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0021
            return r1
        L_0x0021:
            java.lang.String r2 = r1.getLongOpt()
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x000a
            return r1
        L_0x002c:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.CommandLine.resolveOption(java.lang.String):org.apache.commons.cli.Option");
    }

    public String[] getOptionValues(char c) {
        return getOptionValues(String.valueOf(c));
    }

    public String getOptionValue(String str, String str2) {
        String optionValue = getOptionValue(str);
        return optionValue != null ? optionValue : str2;
    }

    public String getOptionValue(char c, String str) {
        return getOptionValue(String.valueOf(c), str);
    }

    public Properties getOptionProperties(String str) {
        Properties properties = new Properties();
        for (Option option : this.options) {
            if (str.equals(option.getOpt()) || str.equals(option.getLongOpt())) {
                List<String> valuesList = option.getValuesList();
                if (valuesList.size() >= 2) {
                    properties.put(valuesList.get(0), valuesList.get(1));
                } else if (valuesList.size() == 1) {
                    properties.put(valuesList.get(0), "true");
                }
            }
        }
        return properties;
    }

    public String[] getArgs() {
        String[] strArr = new String[this.args.size()];
        this.args.toArray(strArr);
        return strArr;
    }

    public List<String> getArgList() {
        return this.args;
    }

    /* access modifiers changed from: protected */
    public void addArg(String str) {
        this.args.add(str);
    }

    /* access modifiers changed from: protected */
    public void addOption(Option option) {
        this.options.add(option);
    }

    public Iterator<Option> iterator() {
        return this.options.iterator();
    }

    public Option[] getOptions() {
        List<Option> list = this.options;
        return (Option[]) list.toArray(new Option[list.size()]);
    }
}
