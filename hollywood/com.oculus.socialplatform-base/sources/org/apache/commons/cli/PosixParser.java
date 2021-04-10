package org.apache.commons.cli;

import X.AnonymousClass006;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Deprecated
public class PosixParser extends Parser {
    public Option currentOption;
    public boolean eatTheRest;
    public Options options;
    public final List<String> tokens = new ArrayList();

    private void init() {
        this.eatTheRest = false;
        this.tokens.clear();
    }

    public void burstToken(String str, boolean z) {
        List<String> list;
        int i;
        int i2 = 1;
        while (true) {
            int length = str.length();
            if (i2 < length) {
                String valueOf = String.valueOf(str.charAt(i2));
                if (this.options.hasOption(valueOf)) {
                    this.tokens.add(AnonymousClass006.A07(HelpFormatter.DEFAULT_OPT_PREFIX, valueOf));
                    Option option = this.options.getOption(valueOf);
                    this.currentOption = option;
                    if (option.hasArg() && length != (i = i2 + 1)) {
                        list = this.tokens;
                        str = str.substring(i);
                        break;
                    }
                    i2++;
                } else if (z) {
                    processNonOptionToken(str.substring(i2), true);
                    return;
                } else {
                    list = this.tokens;
                }
            } else {
                return;
            }
        }
        list.add(str);
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

    @Override // org.apache.commons.cli.Parser
    public String[] flatten(Options options2, String[] strArr, boolean z) throws ParseException {
        String substring;
        init();
        this.options = options2;
        Iterator<String> it = Arrays.asList(strArr).iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(next) || HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(next)) {
                this.tokens.add(next);
            } else {
                if (next.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
                    int indexOf = next.indexOf(61);
                    if (indexOf == -1) {
                        substring = next;
                    } else {
                        substring = next.substring(0, indexOf);
                    }
                    List<String> matchingOptions = options2.getMatchingOptions(substring);
                    if (!matchingOptions.isEmpty()) {
                        if (matchingOptions.size() <= 1) {
                            Option option = options2.getOption(matchingOptions.get(0));
                            this.currentOption = option;
                            this.tokens.add(AnonymousClass006.A07(HelpFormatter.DEFAULT_LONG_OPT_PREFIX, option.longOpt));
                            if (indexOf != -1) {
                                this.tokens.add(next.substring(indexOf + 1));
                            }
                        } else {
                            throw new AmbiguousOptionException(substring, matchingOptions);
                        }
                    }
                } else if (next.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                    if (next.length() != 2 && !options2.hasOption(next)) {
                        if (!options2.getMatchingOptions(next).isEmpty()) {
                            List<String> matchingOptions2 = options2.getMatchingOptions(next);
                            if (matchingOptions2.size() <= 1) {
                                next = AnonymousClass006.A07(HelpFormatter.DEFAULT_OPT_PREFIX, options2.getOption(matchingOptions2.get(0)).longOpt);
                            } else {
                                throw new AmbiguousOptionException(next, matchingOptions2);
                            }
                        } else {
                            burstToken(next, z);
                        }
                    }
                    processOptionToken(next, z);
                }
                processNonOptionToken(next, z);
            }
            gobble(it);
        }
        List<String> list = this.tokens;
        return (String[]) list.toArray(new String[list.size()]);
    }
}
