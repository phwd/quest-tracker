package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.site_engagement.SiteEngagementService;

/* renamed from: UZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UZ0 implements AbstractC3062ic1 {
    /* JADX DEBUG: Multi-variable search result rejected for r2v6, resolved type: java.util.LinkedList */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.AbstractC3062ic1
    public List a(C3836n61 n61) {
        List list;
        double d;
        double d2;
        if (n61 == null || (list = n61.f10472a) == null || list.size() < N.M37SqSAy("CloseTabSuggestions", "close_tab_suggestions_stale_time_ms", 3) || (!N.M6bsIDpc("CloseTabSuggestions", "close_tab_features_time_last_used_enabled", false) && !N.M6bsIDpc("CloseTabSuggestions", "close_tab_features_site_engagement_enabled", false))) {
            return null;
        }
        ArrayList<C3665m61> arrayList = new ArrayList();
        arrayList.addAll(n61.f10472a);
        if (N.M6bsIDpc("CloseTabSuggestions", "close_tab_features_time_last_used_enabled", false)) {
            String MMltG$kc = N.MMltG$kc("CloseTabSuggestions", "close_tab_features_time_last_used_transform");
            if (MMltG$kc == null) {
                AbstractC1220Ua0.a("AdvStaleTabSuggest", "Time last used enabled but no transform string provided", new Object[0]);
                return null;
            }
            char c = !MMltG$kc.equals("MEAN_VARIANCE") ? !MMltG$kc.equals("DAY_NORMALIZATION") ? (char) 2 : 0 : 1;
            List<C3665m61> list2 = n61.f10472a;
            HashMap hashMap = new HashMap();
            if (c == 0) {
                for (C3665m61 m61 : list2) {
                    hashMap.put(m61, Double.valueOf(((double) (System.currentTimeMillis() - m61.H)) / ((double) TimeUnit.DAYS.toMillis(1))));
                }
            } else if (c == 1) {
                if (list2 == null || list2.isEmpty()) {
                    d = 0.0d;
                } else {
                    double d3 = 0.0d;
                    for (C3665m61 m612 : list2) {
                        d3 += (double) m612.H;
                    }
                    d = d3 / ((double) list2.size());
                }
                for (C3665m61 m613 : list2) {
                    hashMap.put(m613, Double.valueOf(((double) m613.H) - d));
                }
                Collection<Double> values = hashMap.values();
                if (values == null || values.size() <= 1) {
                    d2 = 1.0d;
                } else {
                    d2 = 0.0d;
                    for (Double d4 : values) {
                        d2 += d4.doubleValue() * d4.doubleValue();
                    }
                }
                for (C3665m61 m614 : list2) {
                    hashMap.put(m614, Double.valueOf(((Double) hashMap.get(m614)).doubleValue() / Math.sqrt(d2)));
                }
            }
            double MQdjXFDB = N.MQdjXFDB("CloseTabSuggestions", "close_tab_features_time_last_used_threshold", 0.5d);
            arrayList = new ArrayList();
            for (Map.Entry entry : hashMap.entrySet()) {
                if (c == 1) {
                    if (((Double) entry.getValue()).doubleValue() < MQdjXFDB) {
                        arrayList.add((C3665m61) entry.getKey());
                    }
                } else if (c == 0 && ((Double) entry.getValue()).doubleValue() > MQdjXFDB) {
                    arrayList.add((C3665m61) entry.getKey());
                }
            }
        }
        if (N.M6bsIDpc("CloseTabSuggestions", "close_tab_features_site_engagement_enabled", false)) {
            double MQdjXFDB2 = N.MQdjXFDB("CloseTabSuggestions", "close_tab_features_site_engagement_threshold", 100.0d);
            LinkedList linkedList = new LinkedList();
            for (C3665m61 m615 : arrayList) {
                Objects.requireNonNull(m615);
                SiteEngagementService siteEngagementService = (SiteEngagementService) N.Ml4trBg9(Profile.b());
                String str = m615.K;
                long j = siteEngagementService.f10898a;
                if ((j == 0 ? 0.0d : N.MVTED07I(j, siteEngagementService, str)) < MQdjXFDB2) {
                    linkedList.add(m615);
                }
            }
            arrayList = linkedList;
        }
        return Arrays.asList(new C1529Zb1(arrayList, 1, "StaleTabSuggestionProvider"));
    }
}
