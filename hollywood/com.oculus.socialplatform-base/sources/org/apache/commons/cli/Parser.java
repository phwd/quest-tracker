package org.apache.commons.cli;

import X.AnonymousClass006;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

@Deprecated
public abstract class Parser implements CommandLineParser {
    public CommandLine cmd;
    public Options options;
    public List requiredOptions;

    public abstract String[] flatten(Options options2, String[] strArr, boolean z) throws ParseException;

    private void updateRequiredOptions(Option option) throws ParseException {
        if (option.required) {
            this.requiredOptions.remove(option.getKey());
        }
        if (this.options.getOptionGroup(option) != null) {
            OptionGroup optionGroup = this.options.getOptionGroup(option);
            if (optionGroup.required) {
                this.requiredOptions.remove(optionGroup);
            }
            optionGroup.setSelected(option);
        }
    }

    public void checkRequiredOptions() throws MissingOptionException {
        if (!this.requiredOptions.isEmpty()) {
            throw new MissingOptionException(this.requiredOptions);
        }
    }

    public Options getOptions() {
        return this.options;
    }

    public List getRequiredOptions() {
        return this.requiredOptions;
    }

    public void processOption(String str, ListIterator<String> listIterator) throws ParseException {
        if (this.options.hasOption(str)) {
            Option option = (Option) this.options.getOption(str).clone();
            updateRequiredOptions(option);
            if (option.hasArg()) {
                processArgs(option, listIterator);
            }
            this.cmd.addOption(option);
            return;
        }
        throw new UnrecognizedOptionException(AnonymousClass006.A07("Unrecognized option: ", str), str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0027, code lost:
        if (r0.selected == null) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processProperties(java.util.Properties r6) throws org.apache.commons.cli.ParseException {
        /*
        // Method dump skipped, instructions count: 122
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.Parser.processProperties(java.util.Properties):void");
    }

    public void setOptions(Options options2) {
        this.options = options2;
        this.requiredOptions = new ArrayList(Collections.unmodifiableList(options2.requiredOpts));
    }

    public void processArgs(Option option, ListIterator<String> listIterator) throws ParseException {
        while (true) {
            if (!listIterator.hasNext()) {
                break;
            }
            String next = listIterator.next();
            if (this.options.hasOption(next) && next.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
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
        if (option.getValues() == null && !option.optionalArg) {
            throw new MissingArgumentException(option);
        }
    }

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options2, String[] strArr) throws ParseException {
        return parse(options2, strArr, null, false);
    }

    public CommandLine parse(Options options2, String[] strArr, Properties properties) throws ParseException {
        return parse(options2, strArr, properties, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a7, code lost:
        if (r10 != false) goto L_0x00a0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0079  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.cli.CommandLine parse(org.apache.commons.cli.Options r7, java.lang.String[] r8, java.util.Properties r9, boolean r10) throws org.apache.commons.cli.ParseException {
        /*
        // Method dump skipped, instructions count: 183
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.Parser.parse(org.apache.commons.cli.Options, java.lang.String[], java.util.Properties, boolean):org.apache.commons.cli.CommandLine");
    }

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options2, String[] strArr, boolean z) throws ParseException {
        return parse(options2, strArr, null, z);
    }
}
