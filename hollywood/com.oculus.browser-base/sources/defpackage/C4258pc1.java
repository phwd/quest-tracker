package defpackage;

import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Objects;

/* renamed from: pc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4258pc1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4599rc1 f11076a;

    public C4258pc1(C4599rc1 rc1) {
        this.f11076a = rc1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4599rc1 rc1 = this.f11076a;
        C1686ac1 ac1 = (C1686ac1) obj;
        Objects.requireNonNull(rc1);
        int i = 0;
        if (ac1 == null) {
            AbstractC1220Ua0.a("TabSuggestDetailed", "TabSuggestionFeedback is null", new Object[0]);
            return;
        }
        String str = "";
        int i2 = ac1.f9438a.b;
        if (i2 == 0) {
            str = "Grouping";
        } else if (i2 == 1) {
            str = "Closing";
        }
        int i3 = ac1.b;
        if (i3 == 0) {
            AbstractC3535lK0.a("TabsSuggestions.NotConsidered." + str);
            synchronized (rc1.I) {
                int i4 = rc1.I.getInt("BackoffIdxKey", 0);
                long[] jArr = C4599rc1.F;
                int min = Math.min(i4, jArr.length - 1);
                SharedPreferences.Editor edit = rc1.I.edit();
                edit.putLong("BackoffCountKey", jArr[min]);
                edit.putInt("BackoffIdxKey", min + 1);
                edit.putLong("LastTimestamp", System.currentTimeMillis());
                edit.apply();
            }
        } else if (i3 != 1) {
            if (i3 == 2) {
                AbstractC3535lK0.a("TabsSuggestions.Considered.Accepted." + str);
            }
            HashSet hashSet = new HashSet();
            for (C3665m61 m61 : ac1.f9438a.f9352a) {
                hashSet.add(Integer.valueOf(m61.I));
            }
            int i5 = 0;
            for (Integer num : ac1.c) {
                if (hashSet.contains(Integer.valueOf(num.intValue()))) {
                    i++;
                } else {
                    i5++;
                }
            }
            AbstractC3364kK0.c("Tabs.Suggestions.NumSuggestionsChanged." + str, (ac1.f9438a.f9352a.size() - i) + i5);
        } else {
            AbstractC3535lK0.a("TabsSuggestions.Considered.Dismissed." + str);
        }
    }
}
