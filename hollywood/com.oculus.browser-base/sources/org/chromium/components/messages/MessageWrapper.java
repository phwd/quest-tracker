package org.chromium.components.messages;

import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MessageWrapper {

    /* renamed from: a  reason: collision with root package name */
    public long f10854a;
    public final UH0 b;

    public MessageWrapper(long j) {
        this.f10854a = j;
        Map c = UH0.c(AbstractC4619rj0.r);
        TH0 th0 = AbstractC4619rj0.b;
        RunnableC4622rk0 rk0 = new RunnableC4622rk0(this);
        LH0 lh0 = new LH0(null);
        lh0.f8415a = rk0;
        c.put(th0, lh0);
        TH0 th02 = AbstractC4619rj0.c;
        RunnableC4792sk0 sk0 = new RunnableC4792sk0(this);
        LH0 lh02 = new LH0(null);
        lh02.f8415a = sk0;
        c.put(th02, lh02);
        TH0 th03 = AbstractC4619rj0.l;
        C4962tk0 tk0 = new C4962tk0(this);
        LH0 lh03 = new LH0(null);
        lh03.f8415a = tk0;
        c.put(th03, lh03);
        this.b = new UH0(c, null);
    }

    public static MessageWrapper create(long j) {
        return new MessageWrapper(j);
    }

    public void clearNativePtr() {
        this.f10854a = 0;
    }

    public String getDescription() {
        return (String) this.b.g(AbstractC4619rj0.e);
    }

    public int getIconResourceId() {
        return this.b.f(AbstractC4619rj0.g);
    }

    public String getPrimaryButtonText() {
        return (String) this.b.g(AbstractC4619rj0.f11217a);
    }

    public String getSecondaryActionText() {
        return (String) this.b.g(AbstractC4619rj0.j);
    }

    public int getSecondaryIconResourceId() {
        return this.b.f(AbstractC4619rj0.i);
    }

    public String getTitle() {
        return (String) this.b.g(AbstractC4619rj0.d);
    }

    public void setDescription(String str) {
        this.b.m(AbstractC4619rj0.e, str);
    }

    public void setIconResourceId(int i) {
        this.b.l(AbstractC4619rj0.g, i);
    }

    public void setPrimaryButtonText(String str) {
        this.b.m(AbstractC4619rj0.f11217a, str);
    }

    public void setSecondaryActionText(String str) {
        this.b.m(AbstractC4619rj0.j, str);
    }

    public void setSecondaryIconResourceId(int i) {
        this.b.l(AbstractC4619rj0.i, i);
    }

    public void setTitle(String str) {
        this.b.m(AbstractC4619rj0.d, str);
    }
}
