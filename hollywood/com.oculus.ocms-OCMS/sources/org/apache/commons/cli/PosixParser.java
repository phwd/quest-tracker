package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Deprecated
public class PosixParser extends Parser {
    private Option currentOption;
    private boolean eatTheRest;
    private Options options;
    private final List<String> tokens = new ArrayList();

    private void init() {
        this.eatTheRest = false;
        this.tokens.clear();
    }

    /* access modifiers changed from: protected */
    @Override // org.apache.commons.cli.Parser
    public String[] flatten(Options options2, String[] strArr, boolean z) throws ParseException {
        String str;
        init();
        this.options = options2;
        Iterator<String> it = Arrays.asList(strArr).iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(next) || HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(next)) {
                this.tokens.add(next);
            } else if (next.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
                int indexOf = next.indexOf(61);
                if (indexOf == -1) {
                    str = next;
                } else {
                    str = next.substring(0, indexOf);
                }
                List<String> matchingOptions = options2.getMatchingOptions(str);
                if (matchingOptions.isEmpty()) {
                    processNonOptionToken(next, z);
                } else if (matchingOptions.size() <= 1) {
                    this.currentOption = options2.getOption(matchingOptions.get(0));
                    List<String> list = this.tokens;
                    list.add(HelpFormatter.DEFAULT_LONG_OPT_PREFIX + this.currentOption.getLongOpt());
                    if (indexOf != -1) {
                        this.tokens.add(next.substring(indexOf + 1));
                    }
                } else {
                    throw new AmbiguousOptionException(str, matchingOptions);
                }
            } else if (!next.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                processNonOptionToken(next, z);
            } else if (next.length() == 2 || options2.hasOption(next)) {
                processOptionToken(next, z);
            } else if (!options2.getMatchingOptions(next).isEmpty()) {
                List<String> matchingOptions2 = options2.getMatchingOptions(next);
                if (matchingOptions2.size() <= 1) {
                    Option option = options2.getOption(matchingOptions2.get(0));
                    processOptionToken(HelpFormatter.DEFAULT_OPT_PREFIX + option.getLongOpt(), z);
                } else {
                    throw new AmbiguousOptionException(next, matchingOptions2);
                }
            } else {
                burstToken(next, z);
            }
            gobble(it);
        }
        List<String> list2 = this.tokens;
        return (String[]) list2.toArray(new String[list2.size()]);
    }

    private void gobble(Iterator<String> it) {
        if (this.eatTheRest) {
            while (it.hasNext()) {
                this.tokens.add(it.next());
            }
        }
    }

    private void processNonOptionToken(String str, boolean z) {
        Option option;
        if (z && ((option = this.currentOption) == null || !option.hasArg())) {
            this.eatTheRest = true;
            this.tokens.add(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        }
        this.tokens.add(str);
    }

    private void processOptionToken(String str, boolean z) {
        if (z && !this.options.hasOption(str)) {
            this.eatTheRest = true;
        }
        if (this.options.hasOption(str)) {
            this.currentOption = this.options.getOption(str);
        }
        this.tokens.add(str);
    }

    /* access modifiers changed from: protected */
    public void burstToken(String str, boolean z) {
        int i;
        for (int i2 = 1; i2 < str.length(); i2++) {
            String valueOf = String.valueOf(str.charAt(i2));
            if (this.options.hasOption(valueOf)) {
                List<String> list = this.tokens;
                list.add(HelpFormatter.DEFAULT_OPT_PREFIX + valueOf);
                this.currentOption = this.options.getOption(valueOf);
                if (this.currentOption.hasArg() && str.length() != (i = i2 + 1)) {
                    this.tokens.add(str.substring(i));
                    return;
                }
            } else if (z) {
                processNonOptionToken(str.substring(i2), true);
                return;
            } else {
                this.tokens.add(str);
                return;
            }
        }
    }
}
