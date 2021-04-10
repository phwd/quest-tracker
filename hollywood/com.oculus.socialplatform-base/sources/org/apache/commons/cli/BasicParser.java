package org.apache.commons.cli;

@Deprecated
public class BasicParser extends Parser {
    @Override // org.apache.commons.cli.Parser
    public String[] flatten(Options options, String[] strArr, boolean z) {
        return strArr;
    }
}
