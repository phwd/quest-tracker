package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: d61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2128d61 {

    /* renamed from: a  reason: collision with root package name */
    public int f9748a = -1;
    public Tab b;
    public C3452kt c;
    public boolean d;
    public WindowAndroid e;
    public Integer f;
    public Integer g;
    public boolean h;
    public LoadUrlParams i;
    public AbstractC3226ja1 j;
    public WebContents k;
    public C61 l;
    public boolean m;
    public C0797Nb1 n;
    public byte[] o;

    public static C2128d61 b(boolean z) {
        C2128d61 d61 = new C2128d61();
        d61.c(z ? 1 : 0);
        return d61;
    }

    /*  JADX ERROR: StackOverflowError in pass: MarkFinallyVisitor
        java.lang.StackOverflowError
        	at jadx.core.dex.nodes.InsnNode.isSame(InsnNode.java:303)
        	at jadx.core.dex.instructions.IfNode.isSame(IfNode.java:122)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.sameInsns(MarkFinallyVisitor.java:451)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.compareBlocks(MarkFinallyVisitor.java:436)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.checkBlocksTree(MarkFinallyVisitor.java:408)
        	at jadx.core.dex.visitors.MarkFinallyVisitor.checkBlocksTree(MarkFinallyVisitor.java:411)
        */
    public org.chromium.chrome.browser.tab.Tab a() {
        /*
        // Method dump skipped, instructions count: 680
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2128d61.a():org.chromium.chrome.browser.tab.Tab");
    }

    public final C2128d61 c(int i2) {
        this.g = Integer.valueOf(i2);
        return this;
    }

    public C2128d61 d(int i2) {
        this.f = Integer.valueOf(i2);
        return this;
    }
}
