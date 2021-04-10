package X;

import android.content.Context;
import com.facebook.assistant.clientplatform.keyboard.modeltypeahead.VocabLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

/* renamed from: X.96  reason: invalid class name */
public final class AnonymousClass96 implements Callable {
    public final /* synthetic */ VocabLoader A00;

    @Override // java.util.concurrent.Callable
    public final Object call() {
        try {
            VocabLoader vocabLoader = this.A00;
            C00698x r7 = vocabLoader.A07;
            String str = r7.A02;
            Context context = vocabLoader.A06;
            BufferedReader A002 = VocabLoader.A00(str, context);
            if (A002 != null) {
                ArrayList arrayList = new ArrayList();
                try {
                    for (String readLine = A002.readLine(); readLine != null; readLine = A002.readLine()) {
                        arrayList.add(readLine.trim());
                    }
                } catch (IOException e) {
                    C0139Dd.A0L("com.facebook.assistant.clientplatform.keyboard.modeltypeahead.VocabLoader", "could not create Trie and vocabulary", e);
                }
                A002.close();
                vocabLoader.A01 = arrayList;
                BufferedReader A003 = VocabLoader.A00(r7.A01, context);
                if (A003 != null) {
                    HashMap hashMap = new HashMap();
                    try {
                        for (String readLine2 = A003.readLine(); readLine2 != null; readLine2 = A003.readLine()) {
                            String trim = readLine2.trim();
                            hashMap.put(trim.toLowerCase(Locale.US), trim);
                        }
                    } catch (IOException e2) {
                        C0139Dd.A0L("com.facebook.assistant.clientplatform.keyboard.modeltypeahead.VocabLoader", "could not create vocab allow list", e2);
                    }
                    A003.close();
                    vocabLoader.A02 = hashMap;
                    List list = vocabLoader.A01;
                    h9 h9Var = new h9();
                    for (int i = 0; i < list.size(); i++) {
                        h9Var.A01((String) list.get(i), Integer.valueOf(i));
                    }
                    vocabLoader.A00 = h9Var;
                    vocabLoader.A03 = true;
                    vocabLoader.A04 = false;
                    return null;
                }
                throw new IllegalArgumentException("File name is null");
            }
            throw new IllegalArgumentException("File name is null");
        } catch (IOException e3) {
            C0139Dd.A0L("com.facebook.assistant.clientplatform.keyboard.modeltypeahead.VocabLoader", "Model Vocab loading error", e3);
            this.A00.A04 = false;
            return null;
        }
    }

    public AnonymousClass96(VocabLoader vocabLoader) {
        this.A00 = vocabLoader;
    }
}
