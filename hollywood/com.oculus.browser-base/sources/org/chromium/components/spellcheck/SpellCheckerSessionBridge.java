package org.chromium.components.spellcheck;

import J.N;
import android.os.SystemClock;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import java.util.ArrayList;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SpellCheckerSessionBridge implements SpellCheckerSession.SpellCheckerSessionListener {

    /* renamed from: a  reason: collision with root package name */
    public long f10899a;
    public final SpellCheckerSession b = ((TextServicesManager) ContextUtils.getApplicationContext().getSystemService("textservices")).newSpellCheckerSession(null, null, this, true);
    public long c;
    public long d;

    public SpellCheckerSessionBridge(long j) {
        this.f10899a = j;
    }

    public static SpellCheckerSessionBridge create(long j) {
        SpellCheckerSessionBridge spellCheckerSessionBridge = new SpellCheckerSessionBridge(j);
        if (spellCheckerSessionBridge.b == null) {
            return null;
        }
        return spellCheckerSessionBridge;
    }

    public final int[] a(ArrayList arrayList) {
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        return iArr;
    }

    public final void disconnect() {
        this.f10899a = 0;
        this.b.cancel();
        this.b.close();
    }

    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfoArr) {
        this.d = SystemClock.elapsedRealtime();
        if (this.f10899a != 0) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (SentenceSuggestionsInfo sentenceSuggestionsInfo : sentenceSuggestionsInfoArr) {
                if (sentenceSuggestionsInfo != null) {
                    for (int i = 0; i < sentenceSuggestionsInfo.getSuggestionsCount(); i++) {
                        if ((sentenceSuggestionsInfo.getSuggestionsInfoAt(i).getSuggestionsAttributes() & 2) == 2) {
                            arrayList.add(Integer.valueOf(sentenceSuggestionsInfo.getOffsetAt(i)));
                            arrayList2.add(Integer.valueOf(sentenceSuggestionsInfo.getLengthAt(i)));
                            SuggestionsInfo suggestionsInfoAt = sentenceSuggestionsInfo.getSuggestionsInfoAt(i);
                            ArrayList arrayList4 = new ArrayList();
                            for (int i2 = 0; i2 < suggestionsInfoAt.getSuggestionsCount(); i2++) {
                                String suggestionAt = suggestionsInfoAt.getSuggestionAt(i2);
                                if (suggestionAt.charAt(suggestionAt.length() - 1) == 8203) {
                                    suggestionAt = suggestionAt.substring(0, suggestionAt.length() - 1);
                                }
                                arrayList4.add(suggestionAt);
                            }
                            arrayList3.add((String[]) arrayList4.toArray(new String[arrayList4.size()]));
                        }
                    }
                }
            }
            N.M3JV9hBl(this.f10899a, this, a(arrayList), a(arrayList2), (String[][]) arrayList3.toArray(new String[arrayList3.size()][]));
            AbstractC3364kK0.k("SpellCheck.Android.Latency", this.d - this.c);
        }
    }

    public void onGetSuggestions(SuggestionsInfo[] suggestionsInfoArr) {
    }

    public final void requestTextCheck(String str) {
        if (str.endsWith(".")) {
            str = str.substring(0, str.length() - 1);
        }
        this.c = SystemClock.elapsedRealtime();
        this.b.getSentenceSuggestions(new TextInfo[]{new TextInfo(str)}, 0);
    }
}
