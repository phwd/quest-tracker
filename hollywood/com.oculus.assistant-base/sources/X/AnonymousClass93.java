package X;

import com.facebook.assistant.clientplatform.keyboard.modeltypeahead.SupplementalDictionarySuggestionProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

/* renamed from: X.93  reason: invalid class name */
public final class AnonymousClass93 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.keyboard.modeltypeahead.SupplementalDictionarySuggestionProvider$1";
    public final /* synthetic */ SupplementalDictionarySuggestionProvider A00;

    public AnonymousClass93(SupplementalDictionarySuggestionProvider supplementalDictionarySuggestionProvider) {
        this.A00 = supplementalDictionarySuggestionProvider;
    }

    public final void run() {
        boolean z;
        SupplementalDictionarySuggestionProvider supplementalDictionarySuggestionProvider = this.A00;
        String str = supplementalDictionarySuggestionProvider.A05;
        if (str == null) {
            z = false;
        } else {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(supplementalDictionarySuggestionProvider.A04.getResources().getAssets().open(str)));
                try {
                    int i = 0;
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        String trim = readLine.trim();
                        supplementalDictionarySuggestionProvider.A00.A01(trim.toLowerCase(Locale.US), Integer.valueOf(i));
                        supplementalDictionarySuggestionProvider.A01.add(trim);
                        i++;
                    }
                } catch (IOException e) {
                    C0139Dd.A0L("com.facebook.assistant.clientplatform.keyboard.modeltypeahead.SupplementalDictionarySuggestionProvider", "could not create supplementary vocab trie", e);
                    supplementalDictionarySuggestionProvider.A00 = new h9();
                    supplementalDictionarySuggestionProvider.A01 = new ArrayList();
                }
                try {
                    bufferedReader.close();
                    z = true;
                } catch (IOException e2) {
                    C0139Dd.A0L("com.facebook.assistant.clientplatform.keyboard.modeltypeahead.SupplementalDictionarySuggestionProvider", "Error closing reader!", e2);
                    z = false;
                }
            } catch (IOException e3) {
                C0139Dd.A0L("com.facebook.assistant.clientplatform.keyboard.modeltypeahead.SupplementalDictionarySuggestionProvider", "could not read corpus into memory", e3);
                supplementalDictionarySuggestionProvider.A03 = false;
                throw new IllegalArgumentException("File name is null");
            }
        }
        supplementalDictionarySuggestionProvider.A02 = z;
        supplementalDictionarySuggestionProvider.A03 = false;
    }
}
