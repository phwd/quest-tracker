package org.chromium.chrome.browser.tabmodel;

import J.N;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabModelObserverJniBridge extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public long f10778a;
    public TabModel b;

    public TabModelObserverJniBridge(long j, TabModel tabModel) {
        this.f10778a = j;
        this.b = tabModel;
    }

    public static TabModelObserverJniBridge create(long j, TabModel tabModel) {
        TabModelObserverJniBridge tabModelObserverJniBridge = new TabModelObserverJniBridge(j, tabModel);
        tabModel.n(tabModelObserverJniBridge);
        return tabModelObserverJniBridge;
    }

    @Override // defpackage.AbstractC5783ya1
    public final void A(int i, boolean z) {
        N.MD9N_bFw(this.f10778a, this, i, z);
    }

    @Override // defpackage.AbstractC5783ya1
    public final void B(Tab tab, int i, int i2) {
        N.MMKEWgan(this.f10778a, this, tab, i, i2);
    }

    @Override // defpackage.AbstractC5783ya1
    public final void C(Tab tab, int i, int i2) {
        N.M15BMjns(this.f10778a, this, tab, i, i2);
    }

    @Override // defpackage.AbstractC5783ya1
    public final void D(List list, boolean z) {
        N.M8YIOTao(this.f10778a, this, list.toArray());
    }

    @Override // defpackage.AbstractC5783ya1
    public void E() {
    }

    @Override // defpackage.AbstractC5783ya1
    public final void F(Tab tab) {
        N.MRzpb9vm(this.f10778a, this, tab);
    }

    @Override // defpackage.AbstractC5783ya1
    public final void G(Tab tab) {
        N.MkEswtJX(this.f10778a, this, tab);
    }

    @Override // defpackage.AbstractC5783ya1
    public final void H(Tab tab) {
        N.MJ8c7fEV(this.f10778a, this, tab);
    }

    @Override // defpackage.AbstractC5783ya1
    public final void I(Tab tab) {
        N.M9V3veZz(this.f10778a, this, tab);
    }

    @Override // defpackage.AbstractC5783ya1
    public final void J(Tab tab, int i) {
        N.MMKCgOHG(this.f10778a, this, tab, i);
    }

    @Override // defpackage.AbstractC5783ya1
    public final void K(Tab tab, boolean z) {
        N.MSSvav7n(this.f10778a, this, tab, z);
    }

    public final void detachFromTabModel() {
        this.b.w(this);
        this.f10778a = 0;
        this.b = null;
    }

    @Override // defpackage.AbstractC5783ya1
    public final void y() {
        N.M2XM7FhU(this.f10778a, this);
    }

    @Override // defpackage.AbstractC5783ya1
    public final void z(Tab tab, int i, int i2) {
        N.M7iC4IGa(this.f10778a, this, tab, i);
    }
}
