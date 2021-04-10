package org.apache.commons.cli;

import com.facebook.ipc.activity.ActivityConstants;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

@Deprecated
public abstract class Parser implements CommandLineParser {
    protected CommandLine cmd;
    private Options options;
    private List requiredOptions;

    /* access modifiers changed from: protected */
    public abstract String[] flatten(Options options2, String[] strArr, boolean z) throws ParseException;

    /* access modifiers changed from: protected */
    public void setOptions(Options options2) {
        this.options = options2;
        this.requiredOptions = new ArrayList(options2.getRequiredOptions());
    }

    /* access modifiers changed from: protected */
    public Options getOptions() {
        return this.options;
    }

    /* access modifiers changed from: protected */
    public List getRequiredOptions() {
        return this.requiredOptions;
    }

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options2, String[] strArr) throws ParseException {
        return parse(options2, strArr, null, false);
    }

    public CommandLine parse(Options options2, String[] strArr, Properties properties) throws ParseException {
        return parse(options2, strArr, properties, false);
    }

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options2, String[] strArr, boolean z) throws ParseException {
        return parse(options2, strArr, null, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0099, code lost:
        if (r9 != false) goto L_0x0065;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009e A[LOOP:3: B:31:0x009e->B:44:0x009e, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0050 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.cli.CommandLine parse(org.apache.commons.cli.Options r6, java.lang.String[] r7, java.util.Properties r8, boolean r9) throws org.apache.commons.cli.ParseException {
        /*
        // Method dump skipped, instructions count: 191
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.Parser.parse(org.apache.commons.cli.Options, java.lang.String[], java.util.Properties, boolean):org.apache.commons.cli.CommandLine");
    }

    /* access modifiers changed from: protected */
    public void processProperties(Properties properties) throws ParseException {
        if (properties != null) {
            Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String obj = propertyNames.nextElement().toString();
                Option option = this.options.getOption(obj);
                if (option != null) {
                    OptionGroup optionGroup = this.options.getOptionGroup(option);
                    boolean z = (optionGroup == null || optionGroup.getSelected() == null) ? false : true;
                    if (!this.cmd.hasOption(obj) && !z) {
                        String property = properties.getProperty(obj);
                        if (option.hasArg()) {
                            if (option.getValues() == null || option.getValues().length == 0) {
                                try {
                                    option.addValueForProcessing(property);
                                } catch (RuntimeException unused) {
                                }
                            }
                        } else if (!"yes".equalsIgnoreCase(property) && !"true".equalsIgnoreCase(property) && !ActivityConstants.Extras.WATCH_FEED_INJECTION.equalsIgnoreCase(property)) {
                        }
                        this.cmd.addOption(option);
                        updateRequiredOptions(option);
                    }
                } else {
                    throw new UnrecognizedOptionException("Default option wasn't defined", obj);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkRequiredOptions() throws MissingOptionException {
        if (!getRequiredOptions().isEmpty()) {
            throw new MissingOptionException(getRequiredOptions());
        }
    }

    public void processArgs(Option option, ListIterator<String> listIterator) throws ParseException {
        while (true) {
            if (!listIterator.hasNext()) {
                break;
            }
            String next = listIterator.next();
            if (getOptions().hasOption(next) && next.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                listIterator.previous();
                break;
            } else {
                try {
                    option.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(next));
                } catch (RuntimeException unused) {
                    listIterator.previous();
                }
            }
        }
        if (option.getValues() == null && !option.hasOptionalArg()) {
            throw new MissingArgumentException(option);
        }
    }

    /* access modifiers changed from: protected */
    public void processOption(String str, ListIterator<String> listIterator) throws ParseException {
        if (getOptions().hasOption(str)) {
            Option option = (Option) getOptions().getOption(str).clone();
            updateRequiredOptions(option);
            if (option.hasArg()) {
                processArgs(option, listIterator);
            }
            this.cmd.addOption(option);
            return;
        }
        throw new UnrecognizedOptionException("Unrecognized option: " + str, str);
    }

    private void updateRequiredOptions(Option option) throws ParseException {
        if (option.isRequired()) {
            getRequiredOptions().remove(option.getKey());
        }
        if (getOptions().getOptionGroup(option) != null) {
            OptionGroup optionGroup = getOptions().getOptionGroup(option);
            if (optionGroup.isRequired()) {
                getRequiredOptions().remove(optionGroup);
            }
            optionGroup.setSelected(option);
        }
    }
}
