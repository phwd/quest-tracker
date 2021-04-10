package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.text.BreakIterator;

/* renamed from: X.91  reason: invalid class name */
public final class AnonymousClass91 {
    public final String A00;
    public final String A01;
    public final String A02;
    public final String A03;

    public AnonymousClass91(String str, String str2) {
        this.A01 = str;
        this.A02 = str2;
        BreakIterator characterInstance = BreakIterator.getCharacterInstance();
        characterInstance.setText(str);
        int last = characterInstance.last();
        String str3 = OacrConstants.AUTO_SPEECH_DOMAIN;
        if (last <= 1) {
            this.A00 = str3;
            this.A03 = str;
            return;
        }
        String[] split = str.split("[.?!, ](?!.*[.?!, ])");
        String str4 = split[0];
        if (split.length == 2) {
            this.A03 = split[1];
        } else {
            String str5 = this.A01;
            BreakIterator characterInstance2 = BreakIterator.getCharacterInstance();
            characterInstance2.setText(str5);
            int last2 = characterInstance2.last();
            BreakIterator characterInstance3 = BreakIterator.getCharacterInstance();
            characterInstance3.setText(str4);
            if (last2 == characterInstance3.last()) {
                this.A03 = split[0];
                this.A00 = str3;
            }
            this.A03 = str3;
        }
        str3 = str4;
        this.A00 = str3;
    }
}
