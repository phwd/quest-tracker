package X;

import com.facebook.acra.ErrorReporter;

public class PK {
    public final StringBuilder A00;

    public final void A00(String str) {
        StringBuilder sb = this.A00;
        sb.append('|');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == 0 || charAt == '\r' || charAt == ';' || charAt == '|' || charAt == '\t' || charAt == '\n') {
                charAt = ' ';
            }
            sb.append(charAt);
        }
    }

    public final String toString() {
        return this.A00.toString();
    }

    public PK(char c) {
        StringBuilder sb = new StringBuilder((int) ErrorReporter.SIGQUIT_PROCESS_NAME_BUFFER_SIZE);
        this.A00 = sb;
        sb.append(c);
    }
}
