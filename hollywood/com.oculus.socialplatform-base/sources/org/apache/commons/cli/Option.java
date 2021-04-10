package org.apache.commons.cli;

import X.AnonymousClass006;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Option implements Serializable, Cloneable {
    public static final int UNINITIALIZED = -1;
    public static final int UNLIMITED_VALUES = -2;
    public static final long serialVersionUID = 1;
    public String argName;
    public String description;
    public String longOpt;
    public int numberOfArgs;
    public final String opt;
    public boolean optionalArg;
    public boolean required;
    public Class<?> type;
    public List<String> values;
    public char valuesep;

    public static final class Builder {
        public String argName;
        public String description;
        public String longOpt;
        public int numberOfArgs;
        public final String opt;
        public boolean optionalArg;
        public boolean required;
        public Class<?> type;
        public char valuesep;

        public Builder hasArgs() {
            this.numberOfArgs = -2;
            return this;
        }

        public Option build() {
            if (this.opt != null || this.longOpt != null) {
                return new Option(this);
            }
            throw new IllegalArgumentException("Either opt or longOpt must be specified");
        }

        public Builder argName(String str) {
            this.argName = str;
            return this;
        }

        public Builder desc(String str) {
            this.description = str;
            return this;
        }

        public Builder longOpt(String str) {
            this.longOpt = str;
            return this;
        }

        public Builder numberOfArgs(int i) {
            this.numberOfArgs = i;
            return this;
        }

        public Builder optionalArg(boolean z) {
            this.optionalArg = z;
            return this;
        }

        public Builder type(Class<?> cls) {
            this.type = cls;
            return this;
        }

        public Builder(String str) throws IllegalArgumentException {
            this.numberOfArgs = -1;
            this.type = String.class;
            OptionValidator.validateOption(str);
            this.opt = str;
        }

        public Builder hasArg() {
            this.numberOfArgs = 1;
            return this;
        }

        public Builder hasArg(boolean z) {
            int i = -1;
            if (z) {
                i = 1;
            }
            this.numberOfArgs = i;
            return this;
        }

        public Builder required() {
            this.required = true;
            return this;
        }

        public Builder required(boolean z) {
            this.required = z;
            return this;
        }

        public Builder valueSeparator() {
            this.valuesep = '=';
            return this;
        }

        public Builder valueSeparator(char c) {
            this.valuesep = c;
            return this;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r1.equals(r5.opt) == false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r3 = 1
            if (r4 == r5) goto L_0x0034
            r2 = 0
            if (r5 == 0) goto L_0x001e
            java.lang.Class r1 = r4.getClass()
            java.lang.Class r0 = r5.getClass()
            if (r1 != r0) goto L_0x001e
            org.apache.commons.cli.Option r5 = (org.apache.commons.cli.Option) r5
            java.lang.String r1 = r4.opt
            if (r1 == 0) goto L_0x001f
            java.lang.String r0 = r5.opt
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0024
        L_0x001e:
            return r2
        L_0x001f:
            java.lang.String r0 = r5.opt
            if (r0 == 0) goto L_0x0024
            return r2
        L_0x0024:
            java.lang.String r1 = r4.longOpt
            java.lang.String r0 = r5.longOpt
            if (r1 == 0) goto L_0x0031
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0034
            return r2
        L_0x0031:
            if (r0 == 0) goto L_0x0034
            return r2
        L_0x0034:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.Option.equals(java.lang.Object):boolean");
    }

    private boolean hasNoValues() {
        return this.values.isEmpty();
    }

    @Deprecated
    public boolean addValue(String str) {
        throw new UnsupportedOperationException("The addValue method is not intended for client use. Subclasses should use the addValueForProcessing method instead. ");
    }

    public void addValueForProcessing(String str) {
        if (this.numberOfArgs != -1) {
            processValue(str);
            return;
        }
        throw new RuntimeException("NO_ARGS_ALLOWED");
    }

    public void clearValues() {
        this.values.clear();
    }

    public String getArgName() {
        return this.argName;
    }

    public int getArgs() {
        return this.numberOfArgs;
    }

    public String getDescription() {
        return this.description;
    }

    public String getKey() {
        String str = this.opt;
        if (str == null) {
            return this.longOpt;
        }
        return str;
    }

    public String getLongOpt() {
        return this.longOpt;
    }

    public String getOpt() {
        return this.opt;
    }

    public Object getType() {
        return this.type;
    }

    public char getValueSeparator() {
        return this.valuesep;
    }

    public String[] getValues() {
        if (this.values.isEmpty()) {
            return null;
        }
        List<String> list = this.values;
        return (String[]) list.toArray(new String[list.size()]);
    }

    public List<String> getValuesList() {
        return this.values;
    }

    public boolean hasArg() {
        int i = this.numberOfArgs;
        if (i > 0 || i == -2) {
            return true;
        }
        return false;
    }

    public boolean hasArgName() {
        String str = this.argName;
        if (str == null || str.length() <= 0) {
            return false;
        }
        return true;
    }

    public boolean hasArgs() {
        int i = this.numberOfArgs;
        if (i > 1 || i == -2) {
            return true;
        }
        return false;
    }

    public boolean hasLongOpt() {
        if (this.longOpt != null) {
            return true;
        }
        return false;
    }

    public boolean hasOptionalArg() {
        return this.optionalArg;
    }

    public boolean hasValueSeparator() {
        if (this.valuesep > 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        String str = this.opt;
        int i2 = 0;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        int i3 = i * 31;
        String str2 = this.longOpt;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return i3 + i2;
    }

    public boolean isRequired() {
        return this.required;
    }

    public boolean requiresArg() {
        if (this.optionalArg) {
            return false;
        }
        if (this.numberOfArgs == -2) {
            return this.values.isEmpty();
        }
        return acceptsArg();
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r3 = this;
            java.lang.String r0 = "[ option: "
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r0)
            java.lang.String r0 = r3.opt
            r2.append(r0)
            java.lang.String r1 = r3.longOpt
            java.lang.String r0 = " "
            if (r1 == 0) goto L_0x0018
            r2.append(r0)
            r2.append(r1)
        L_0x0018:
            r2.append(r0)
            boolean r0 = r3.hasArgs()
            if (r0 == 0) goto L_0x0044
            java.lang.String r0 = "[ARG...]"
        L_0x0023:
            r2.append(r0)
        L_0x0026:
            java.lang.String r1 = " :: "
            r2.append(r1)
            java.lang.String r0 = r3.description
            r2.append(r0)
            java.lang.Class<?> r0 = r3.type
            if (r0 == 0) goto L_0x003a
            r2.append(r1)
            r2.append(r0)
        L_0x003a:
            java.lang.String r0 = " ]"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            return r0
        L_0x0044:
            boolean r0 = r3.hasArg()
            if (r0 == 0) goto L_0x0026
            java.lang.String r0 = " [ARG]"
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.Option.toString():java.lang.String");
    }

    private void add(String str) {
        if (acceptsArg()) {
            this.values.add(str);
            return;
        }
        throw new RuntimeException("Cannot add value, list full.");
    }

    private void processValue(String str) {
        if (hasValueSeparator()) {
            char c = this.valuesep;
            while (true) {
                int indexOf = str.indexOf(c);
                if (indexOf == -1 || this.values.size() == this.numberOfArgs - 1) {
                    break;
                }
                add(str.substring(0, indexOf));
                str = str.substring(indexOf + 1);
            }
        }
        add(str);
    }

    public boolean acceptsArg() {
        if (!hasArg() && !hasArgs() && !this.optionalArg) {
            return false;
        }
        if (this.numberOfArgs <= 0 || this.values.size() < this.numberOfArgs) {
            return true;
        }
        return false;
    }

    @Override // java.lang.Object
    public Object clone() {
        try {
            Option option = (Option) super.clone();
            option.values = new ArrayList(this.values);
            return option;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(AnonymousClass006.A07("A CloneNotSupportedException was thrown: ", e.getMessage()));
        }
    }

    public int getId() {
        return getKey().charAt(0);
    }

    public void setArgName(String str) {
        this.argName = str;
    }

    public void setArgs(int i) {
        this.numberOfArgs = i;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setLongOpt(String str) {
        this.longOpt = str;
    }

    public void setOptionalArg(boolean z) {
        this.optionalArg = z;
    }

    public void setRequired(boolean z) {
        this.required = z;
    }

    public void setValueSeparator(char c) {
        this.valuesep = c;
    }

    public Option(String str, String str2) throws IllegalArgumentException {
        this(str, null, false, str2);
    }

    public Option(String str, String str2, boolean z, String str3) throws IllegalArgumentException {
        this.numberOfArgs = -1;
        this.type = String.class;
        this.values = new ArrayList();
        OptionValidator.validateOption(str);
        this.opt = str;
        this.longOpt = str2;
        if (z) {
            this.numberOfArgs = 1;
        }
        this.description = str3;
    }

    public Option(String str, boolean z, String str2) throws IllegalArgumentException {
        this(str, null, z, str2);
    }

    public Option(Builder builder) {
        this.numberOfArgs = -1;
        this.type = String.class;
        this.values = new ArrayList();
        this.argName = builder.argName;
        this.description = builder.description;
        this.longOpt = builder.longOpt;
        this.numberOfArgs = builder.numberOfArgs;
        this.opt = builder.opt;
        this.optionalArg = builder.optionalArg;
        this.required = builder.required;
        this.type = builder.type;
        this.valuesep = builder.valuesep;
    }

    public static Builder builder() {
        return new Builder(null);
    }

    public static Builder builder(String str) {
        return new Builder(str);
    }

    public String getValue() {
        if (this.values.isEmpty()) {
            return null;
        }
        return this.values.get(0);
    }

    public String getValue(int i) throws IndexOutOfBoundsException {
        if (this.values.isEmpty()) {
            return null;
        }
        return this.values.get(i);
    }

    public String getValue(String str) {
        String value = getValue();
        return value != null ? value : str;
    }

    public void setType(Class<?> cls) {
        this.type = cls;
    }

    @Deprecated
    public void setType(Object obj) {
        this.type = (Class) obj;
    }
}
