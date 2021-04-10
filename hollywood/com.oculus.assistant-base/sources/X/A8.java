package X;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.facebook.acra.AppComponentStats;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.hyperthrift.HyperThriftBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class A8 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.settings.AssistantSettingsManager$ttsVoiceConfigUpdateSubscriber$1$onEvent$3";
    public final /* synthetic */ C0668el A00;
    public final /* synthetic */ C0790hd A01;
    public final /* synthetic */ AbstractC0106Ak A02;
    public final /* synthetic */ String A03;

    public A8(C0790hd hdVar, AbstractC0106Ak ak, String str, C0668el elVar) {
        this.A01 = hdVar;
        this.A02 = ak;
        this.A03 = str;
        this.A00 = elVar;
    }

    public final void run() {
        Object A002;
        C0821iR iRVar = (C0821iR) this.A02.A2L();
        Map map = (Map) iRVar.A01.A00(0);
        ArrayList arrayList = new ArrayList();
        Map map2 = (Map) iRVar.A00.A00(0);
        if (map2 == null) {
            map2 = C0486aW.A00;
        }
        for (Map.Entry entry : map2.entrySet()) {
            String str = (String) entry.getKey();
            HyperThriftBase hyperThriftBase = (HyperThriftBase) entry.getValue();
            List<HyperThriftBase> list = (List) hyperThriftBase.A00(0);
            if (list == null) {
                list = C0485aV.A00;
            }
            for (HyperThriftBase hyperThriftBase2 : list) {
                String str2 = (String) hyperThriftBase2.A00(0);
                if (str2 != null) {
                    if (map == null || !map.containsKey(str)) {
                        A002 = hyperThriftBase.A00(1);
                    } else {
                        A002 = map.get(str);
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("persona_id", str2);
                    contentValues.put("locale", str);
                    contentValues.put(AppComponentStats.ATTRIBUTE_NAME, (String) hyperThriftBase2.A00(1));
                    contentValues.put("desc", (String) hyperThriftBase2.A00(2));
                    contentValues.put("sample_url", (String) hyperThriftBase2.A00(3));
                    contentValues.put("selected", Boolean.valueOf(C0514bB.A05(str2, A002)));
                    arrayList.add(contentValues);
                }
            }
        }
        String str3 = this.A03;
        String str4 = iRVar.A02;
        C0514bB.A02(str3, "userId");
        Uri.Builder appendPath = AE.A00().buildUpon().appendPath(Integer.toString(1)).appendPath("tts_personas").appendPath(str3);
        if (str4 != null) {
            appendPath.appendPath(str4);
        }
        Uri build = appendPath.build();
        C0514bB.A01(build, "uri.build()");
        C0668el elVar = null;
        try {
            ContentResolver contentResolver = this.A01.A00.A00.getContentResolver();
            Object[] array = arrayList.toArray(new ContentValues[0]);
            if (array != null) {
                contentResolver.bulkInsert(build, (ContentValues[]) array);
                C0668el elVar2 = this.A00;
                if (elVar2 != null && elVar2.A00.isSampled()) {
                    elVar2.A00();
                }
                C0112Aq.A00().A01(new C0789hc(true, OacrConstants.AUTO_SPEECH_DOMAIN));
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (IllegalArgumentException e) {
            C0668el elVar3 = this.A00;
            if (elVar3 != null) {
                elVar = elVar3;
            }
            if (elVar != null && elVar.A00.isSampled()) {
                elVar3.A00.A1D("error_message", "ContentProvider threw an exception");
                elVar3.A00();
            }
            StringBuilder sb = new StringBuilder("ContentProvider threw exception for uri:");
            sb.append(build);
            sb.append(", probably because of a malformed uri, check your userId format, is it a string of integer? userId:  ");
            sb.append(str3);
            C0139Dd.A0L("AssistantSettingsManager", sb.toString(), e);
            C0112Aq.A00().A01(new C0789hc(false, "ContentProvider threw an exception"));
        }
    }
}
