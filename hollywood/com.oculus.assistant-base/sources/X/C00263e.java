package X;

import android.text.TextUtils;
import com.oculus.assistant.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: X.3e  reason: invalid class name and case insensitive filesystem */
public final class C00263e extends AbstractC1409yv {
    @Override // X.AbstractC1409yv
    public final void A01(AnonymousClass8F r7, String str) {
        Pattern compile = Pattern.compile("\"res\":\\s*\"([^\"]*)\"\\s*,\"package\"\\s*:\\s*\"([^\"]*)\"");
        while (true) {
            Matcher matcher = compile.matcher(str);
            while (matcher.find()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                if (!TextUtils.isEmpty(group2) && !TextUtils.isEmpty(group)) {
                    int identifier = BX.A00().getResources().getIdentifier(group, "drawable", group2);
                    if (identifier == 0) {
                        identifier = R.drawable.voice_assistant_filled_24;
                    }
                    StringBuilder sb = new StringBuilder("\"res\":");
                    sb.append(identifier);
                    sb.append(",\"package\":\"");
                    sb.append(group2);
                    sb.append("\"");
                    str = matcher.replaceFirst(sb.toString());
                }
            }
            super.A01(r7, str);
            return;
        }
    }
}
