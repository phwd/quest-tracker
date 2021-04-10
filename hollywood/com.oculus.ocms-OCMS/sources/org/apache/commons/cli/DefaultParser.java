package org.apache.commons.cli;

import com.facebook.ipc.activity.ActivityConstants;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class DefaultParser implements CommandLineParser {
    protected CommandLine cmd;
    protected Option currentOption;
    protected String currentToken;
    protected List expectedOpts;
    protected Options options;
    protected boolean skipParsing;
    protected boolean stopAtNonOption;

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options2, String[] strArr) throws ParseException {
        return parse(options2, strArr, (Properties) null);
    }

    public CommandLine parse(Options options2, String[] strArr, Properties properties) throws ParseException {
        return parse(options2, strArr, properties, false);
    }

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options2, String[] strArr, boolean z) throws ParseException {
        return parse(options2, strArr, null, z);
    }

    public CommandLine parse(Options options2, String[] strArr, Properties properties, boolean z) throws ParseException {
        this.options = options2;
        this.stopAtNonOption = z;
        this.skipParsing = false;
        this.currentOption = null;
        this.expectedOpts = new ArrayList(options2.getRequiredOptions());
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

    private void handleProperties(Properties properties) throws ParseException {
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
                                option.addValueForProcessing(property);
                            }
                        } else if (!"yes".equalsIgnoreCase(property) && !"true".equalsIgnoreCase(property) && !ActivityConstants.Extras.WATCH_FEED_INJECTION.equalsIgnoreCase(property)) {
                        }
                        handleOption(option);
                        this.currentOption = null;
                    }
                } else {
                    throw new UnrecognizedOptionException("Default option wasn't defined", obj);
                }
            }
        }
    }

    private void checkRequiredOptions() throws MissingOptionException {
        if (!this.expectedOpts.isEmpty()) {
            throw new MissingOptionException(this.expectedOpts);
        }
    }

    private void checkRequiredArgs() throws ParseException {
        Option option = this.currentOption;
        if (option != null && option.requiresArg()) {
            throw new MissingArgumentException(this.currentOption);
        }
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

    private boolean isArgument(String str) {
        return !isOption(str) || isNegativeNumber(str);
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
        return isLongOption(str) || isShortOption(str);
    }

    private boolean isShortOption(String str) {
        return str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX) && str.length() >= 2 && this.options.hasShortOption(str.substring(1, 2));
    }

    private boolean isLongOption(String str) {
        String str2;
        if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX) && str.length() != 1) {
            int indexOf = str.indexOf("=");
            if (indexOf == -1) {
                str2 = str;
            } else {
                str2 = str.substring(0, indexOf);
            }
            if (!this.options.getMatchingOptions(str2).isEmpty()) {
                return true;
            }
            return getLongPrefix(str) != null && !str.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
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
        throw new UnrecognizedOptionException("Unrecognized option: " + str, str);
    }

    private void handleLongOption(String str) throws ParseException {
        if (str.indexOf(61) == -1) {
            handleLongOptionWithoutEqual(str);
        } else {
            handleLongOptionWithEqual(str);
        }
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

    private void handleLongOptionWithEqual(String str) throws ParseException {
        int indexOf = str.indexOf(61);
        String substring = str.substring(indexOf + 1);
        String substring2 = str.substring(0, indexOf);
        List<String> matchingOptions = this.options.getMatchingOptions(substring2);
        if (matchingOptions.isEmpty()) {
            handleUnknownToken(this.currentToken);
        } else if (matchingOptions.size() <= 1) {
            Option option = this.options.getOption(matchingOptions.get(0));
            if (option.acceptsArg()) {
                handleOption(option);
                this.currentOption.addValueForProcessing(substring);
                this.currentOption = null;
                return;
            }
            handleUnknownToken(this.currentToken);
        } else {
            throw new AmbiguousOptionException(substring2, matchingOptions);
        }
    }

    private void handleShortAndLongOption(String str) throws ParseException {
        String stripLeadingHyphens = Util.stripLeadingHyphens(str);
        int indexOf = stripLeadingHyphens.indexOf(61);
        if (stripLeadingHyphens.length() == 1) {
            if (this.options.hasShortOption(stripLeadingHyphens)) {
                handleOption(this.options.getOption(stripLeadingHyphens));
            } else {
                handleUnknownToken(str);
            }
        } else if (indexOf != -1) {
            String substring = stripLeadingHyphens.substring(0, indexOf);
            String substring2 = stripLeadingHyphens.substring(indexOf + 1);
            if (substring.length() == 1) {
                Option option = this.options.getOption(substring);
                if (option == null || !option.acceptsArg()) {
                    handleUnknownToken(str);
                    return;
                }
                handleOption(option);
                this.currentOption.addValueForProcessing(substring2);
                this.currentOption = null;
            } else if (isJavaProperty(substring)) {
                handleOption(this.options.getOption(substring.substring(0, 1)));
                this.currentOption.addValueForProcessing(substring.substring(1));
                this.currentOption.addValueForProcessing(substring2);
                this.currentOption = null;
            } else {
                handleLongOptionWithEqual(str);
            }
        } else if (this.options.hasShortOption(stripLeadingHyphens)) {
            handleOption(this.options.getOption(stripLeadingHyphens));
        } else if (!this.options.getMatchingOptions(stripLeadingHyphens).isEmpty()) {
            handleLongOptionWithoutEqual(str);
        } else {
            String longPrefix = getLongPrefix(stripLeadingHyphens);
            if (longPrefix != null && this.options.getOption(longPrefix).acceptsArg()) {
                handleOption(this.options.getOption(longPrefix));
                this.currentOption.addValueForProcessing(stripLeadingHyphens.substring(longPrefix.length()));
                this.currentOption = null;
            } else if (isJavaProperty(stripLeadingHyphens)) {
                handleOption(this.options.getOption(stripLeadingHyphens.substring(0, 1)));
                this.currentOption.addValueForProcessing(stripLeadingHyphens.substring(1));
                this.currentOption = null;
            } else {
                handleConcatenatedOptions(str);
            }
        }
    }

    private String getLongPrefix(String str) {
        String stripLeadingHyphens = Util.stripLeadingHyphens(str);
        for (int length = stripLeadingHyphens.length() - 2; length > 1; length--) {
            String substring = stripLeadingHyphens.substring(0, length);
            if (this.options.hasLongOption(substring)) {
                return substring;
            }
        }
        return null;
    }

    private boolean isJavaProperty(String str) {
        Option option = this.options.getOption(str.substring(0, 1));
        if (option == null || (option.getArgs() < 2 && option.getArgs() != -2)) {
            return false;
        }
        return true;
    }

    private void handleOption(Option option) throws ParseException {
        checkRequiredArgs();
        Option option2 = (Option) option.clone();
        updateRequiredOptions(option2);
        this.cmd.addOption(option2);
        if (option2.hasArg()) {
            this.currentOption = option2;
        } else {
            this.currentOption = null;
        }
    }

    private void updateRequiredOptions(Option option) throws AlreadySelectedException {
        if (option.isRequired()) {
            this.expectedOpts.remove(option.getKey());
        }
        if (this.options.getOptionGroup(option) != null) {
            OptionGroup optionGroup = this.options.getOptionGroup(option);
            if (optionGroup.isRequired()) {
                this.expectedOpts.remove(optionGroup);
            }
            optionGroup.setSelected(option);
        }
    }

    /* access modifiers changed from: protected */
    public void handleConcatenatedOptions(String str) throws ParseException {
        int i;
        for (int i2 = 1; i2 < str.length(); i2++) {
            String valueOf = String.valueOf(str.charAt(i2));
            if (this.options.hasOption(valueOf)) {
                handleOption(this.options.getOption(valueOf));
                if (this.currentOption != null && str.length() != (i = i2 + 1)) {
                    this.currentOption.addValueForProcessing(str.substring(i));
                    return;
                }
            } else {
                if (this.stopAtNonOption && i2 > 1) {
                    str = str.substring(i2);
                }
                handleUnknownToken(str);
                return;
            }
        }
    }
}
