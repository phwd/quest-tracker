package defpackage;

import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.url.GURL;

/* renamed from: cd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2038cd implements Runnable {
    public final AutocompleteMatch F;
    public final int G;
    public boolean H;
    public final /* synthetic */ GURL I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ C2379ed f9617J;

    public RunnableC2038cd(C2379ed edVar, AutocompleteMatch autocompleteMatch, int i, GURL gurl) {
        this.f9617J = edVar;
        this.I = gurl;
        this.F = autocompleteMatch;
        this.G = i;
    }

    public void run() {
        this.f9617J.m(this.F, this.G, this.I);
    }
}
