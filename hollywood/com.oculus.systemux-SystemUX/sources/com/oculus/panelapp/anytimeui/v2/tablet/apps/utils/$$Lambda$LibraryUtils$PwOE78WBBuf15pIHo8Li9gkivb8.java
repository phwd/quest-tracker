package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import com.oculus.library.model.App;
import java.util.Comparator;
import java.util.Locale;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.-$$Lambda$LibraryUtils$PwOE78WBBuf15pIHo8Li9gkivb8  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$LibraryUtils$PwOE78WBBuf15pIHo8Li9gkivb8 implements Comparator {
    public static final /* synthetic */ $$Lambda$LibraryUtils$PwOE78WBBuf15pIHo8Li9gkivb8 INSTANCE = new $$Lambda$LibraryUtils$PwOE78WBBuf15pIHo8Li9gkivb8();

    private /* synthetic */ $$Lambda$LibraryUtils$PwOE78WBBuf15pIHo8Li9gkivb8() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((App) obj).displayName.toLowerCase(Locale.getDefault()).compareTo(((App) obj2).displayName.toLowerCase(Locale.getDefault()));
    }
}
