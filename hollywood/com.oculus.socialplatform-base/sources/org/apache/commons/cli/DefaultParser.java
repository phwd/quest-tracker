package org.apache.commons.cli;

import X.AnonymousClass006;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class DefaultParser implements CommandLineParser {
    public CommandLine cmd;
    public Option currentOption;
    public String currentToken;
    public List expectedOpts;
    public Options options;
    public boolean skipParsing;
    public boolean stopAtNonOption;

    private boolean isJavaProperty(String str) {
        Option option = this.options.getOption(str.substring(0, 1));
        if (option == null) {
            return false;
        }
        int i = option.numberOfArgs;
        return i >= 2 || i == -2;
    }

    public void handleConcatenatedOptions(String str) throws ParseException {
        int i;
        int i2 = 1;
        while (true) {
            int length = str.length();
            if (i2 < length) {
                String valueOf = String.valueOf(str.charAt(i2));
                if (this.options.hasOption(valueOf)) {
                    handleOption(this.options.getOption(valueOf));
                    Option option = this.currentOption;
                    if (option == null || length == (i = i2 + 1)) {
                        i2++;
                    } else {
                        option.addValueForProcessing(str.substring(i));
                        return;
                    }
                } else {
                    if (this.stopAtNonOption && i2 > 1) {
                        str = str.substring(i2);
                    }
                    handleUnknownToken(str);
                    return;
                }
            } else {
                return;
            }
        }
    }

    private void checkRequiredArgs() throws ParseException {
        Option option = this.currentOption;
        if (option != null && option.requiresArg()) {
            throw new MissingArgumentException(this.currentOption);
        }
    }

    private void checkRequiredOptions() throws MissingOptionException {
        if (!this.expectedOpts.isEmpty()) {
            throw new MissingOptionException(this.expectedOpts);
        }
    }

    private void handleLongOption(String str) throws ParseException {
        if (str.indexOf(61) == -1) {
            handleLongOptionWithoutEqual(str);
        } else {
            handleLongOptionWithEqual(str);
        }
    }

    private void handleLongOptionWithEqual(String str) throws ParseException {
        int indexOf = str.indexOf(61);
        String substring = str.substring(indexOf + 1);
        String substring2 = str.substring(0, indexOf);
        List<String> matchingOptions = this.options.getMatchingOptions(substring2);
        if (!matchingOptions.isEmpty()) {
            if (matchingOptions.size() <= 1) {
                Option option = this.options.getOption(matchingOptions.get(0));
                if (option.acceptsArg()) {
                    handleOption(option);
                    this.currentOption.addValueForProcessing(substring);
                    this.currentOption = null;
                    return;
                }
            } else {
                throw new AmbiguousOptionException(substring2, matchingOptions);
            }
        }
        handleUnknownToken(this.currentToken);
    }

    private void handleLongOptionWithoutEqual(String str) throws ParseException {
        List<String> matchingOptions = this.options.getMatchingOptions(str);
        if (matchingOptions.isEmpty()) {
            handleUnknownToken(this.currentToken);
        } else if (matchingOptions.size() <= 1) {
            handleOption(this.options.getOption(matchingOptions.get(0)));
        } else {
            throw new AmbiguousOptionException(str, matchingOptions);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0027, code lost:
        if (r0.selected == null) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleProperties(java.util.Properties r6) throws org.apache.commons.cli.ParseException {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.DefaultParser.handleProperties(java.util.Properties):void");
    }

    private void handleToken(String str) throws ParseException {
        this.currentToken = str;
        if (this.skipParsing) {
            this.cmd.addArg(str);
        } else if (HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(str)) {
            this.skipParsing = true;
        } else {
            Option option = this.currentOption;
            if (option != null && option.acceptsArg() && isArgument(str)) {
                this.currentOption.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(str));
            } else if (str.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
                handleLongOption(str);
            } else if (!str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX) || HelpFormatter.DEFAULT_OPT_PREFIX.equals(str)) {
                handleUnknownToken(str);
            } else {
                handleShortAndLongOption(str);
            }
        }
        Option option2 = this.currentOption;
        if (option2 != null && !option2.acceptsArg()) {
            this.currentOption = null;
        }
    }

    private void handleUnknownToken(String str) throws ParseException {
        if (!str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX) || str.length() <= 1 || this.stopAtNonOption) {
            this.cmd.addArg(str);
            if (this.stopAtNonOption) {
                this.skipParsing = true;
                return;
            }
            return;
        }
        throw new UnrecognizedOptionException(AnonymousClass006.A07("Unrecognized option: ", str), str);
    }

    private boolean isLongOption(String str) {
        String substring;
        if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX) && str.length() != 1) {
            int indexOf = str.indexOf("=");
            if (indexOf == -1) {
                substring = str;
            } else {
                substring = str.substring(0, indexOf);
            }
            if (!this.options.getMatchingOptions(substring).isEmpty() || (getLongPrefix(str) != null && !str.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX))) {
                return true;
            }
        }
        return false;
    }

    private boolean isShortOption(String str) {
        if (!str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX) || str.length() < 2 || !this.options.hasShortOption(str.substring(1, 2))) {
            return false;
        }
        return true;
    }

    private void updateRequiredOptions(Option option) throws AlreadySelectedException {
        if (option.required) {
            this.expectedOpts.remove(option.getKey());
        }
        if (this.options.getOptionGroup(option) != null) {
            OptionGroup optionGroup = this.options.getOptionGroup(option);
            if (optionGroup.required) {
                this.expectedOpts.remove(optionGroup);
            }
            optionGroup.setSelected(option);
        }
    }

    private String getLongPrefix(String str) {
        String str2;
        String stripLeadingHyphens = Util.stripLeadingHyphens(str);
        int length = stripLeadingHyphens.length() - 2;
        while (true) {
            str2 = null;
            if (length <= 1) {
                break;
            }
            str2 = stripLeadingHyphens.substring(0, length);
            if (this.options.hasLongOption(str2)) {
                break;
            }
            length--;
        }
        return str2;
    }

    private void handleOption(Option option) throws ParseException {
        checkRequiredArgs();
        Option option2 = (Option) option.clone();
        updateRequiredOptions(option2);
        this.cmd.addOption(option2);
        if (!option2.hasArg()) {
            option2 = null;
        }
        this.currentOption = option2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        if (r7.options.hasShortOption(r4) != false) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleShortAndLongOption(java.lang.String r8) throws org.apache.commons.cli.ParseException {
        /*
        // Method dump skipped, instructions count: 209
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.DefaultParser.handleShortAndLongOption(java.lang.String):void");
    }

    private boolean isArgument(String str) {
        if (!isOption(str)) {
            return true;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private boolean isNegativeNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private boolean isOption(String str) {
        if (isLongOption(str) || isShortOption(str)) {
            return true;
        }
        return false;
    }

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options2, String[] strArr) throws ParseException {
        return parse(options2, strArr, (Properties) null);
    }

    public CommandLine parse(Options options2, String[] strArr, Properties properties) throws ParseException {
        return parse(options2, strArr, properties, false);
    }

    public CommandLine parse(Options options2, String[] strArr, Properties properties, boolean z) throws ParseException {
        this.options = options2;
        this.stopAtNonOption = z;
        this.skipParsing = false;
        this.currentOption = null;
        this.expectedOpts = new ArrayList(Collections.unmodifiableList(options2.requiredOpts));
        for (OptionGroup optionGroup : options2.getOptionGroups()) {
            optionGroup.setSelected(null);
        }
        this.cmd = new CommandLine();
        if (strArr != null) {
            for (String str : strArr) {
                handleToken(str);
            }
        }
        checkRequiredArgs();
        handleProperties(properties);
        checkRequiredOptions();
        return this.cmd;
    }

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options2, String[] strArr, boolean z) throws ParseException {
        return parse(options2, strArr, null, z);
    }
}
