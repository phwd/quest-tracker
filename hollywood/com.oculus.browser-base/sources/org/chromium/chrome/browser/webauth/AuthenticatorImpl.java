package org.chromium.chrome.browser.webauth;

import J.N;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialRequestOptions;
import com.google.android.gms.fido.fido2.api.common.FidoAppIdExtension;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions;
import com.google.android.gms.fido.fido2.api.common.UserVerificationMethodExtension;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import org.chromium.base.ContextUtils;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.url.Origin;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AuthenticatorImpl extends SW implements AbstractC5274vb {
    public final RenderFrameHost F;
    public boolean G;
    public Origin H;
    public Long I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC1555Zl f10806J;
    public AbstractC1555Zl K;
    public Queue L = new LinkedList();

    public AuthenticatorImpl(long j, RenderFrameHost renderFrameHost) {
        this.F = renderFrameHost;
        this.H = renderFrameHost.d();
        this.I = Long.valueOf(j);
    }

    public static AuthenticatorImpl create(long j, RenderFrameHost renderFrameHost) {
        return new AuthenticatorImpl(j, renderFrameHost);
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        close();
    }

    @Override // defpackage.AbstractC5274vb
    public void Z(C5065uI0 ui0, AbstractC4764sb sbVar) {
        if (this.G) {
            sbVar.a(1, null);
            return;
        }
        this.K = sbVar;
        Context applicationContext = ContextUtils.getApplicationContext();
        if (applicationContext == null || AbstractC4652ru0.a(applicationContext, "com.google.android.gms") < 16890000) {
            f0(7);
            return;
        }
        this.G = true;
        C5759yP a2 = C5759yP.a();
        RenderFrameHost renderFrameHost = this.F;
        Origin origin = this.H;
        Objects.requireNonNull(a2);
        AP ap = new AP();
        ap.f7669a = this;
        if (ap.c == null) {
            ap.c = Bx1.a(renderFrameHost);
        }
        ap.e = 2;
        if (!ap.c()) {
            AbstractC1220Ua0.a("Fido2Request", "Google Play Services' Fido2PrivilegedApi is not available.", new Object[0]);
            ap.e(21);
            return;
        }
        int g = renderFrameHost.g(ui0.f, origin);
        if (g != 0) {
            ap.e(g);
            return;
        }
        if (ui0.i != null) {
            ap.f = true;
        }
        List b = BP.b(ui0.g);
        String str = ui0.i;
        AuthenticationExtensions authenticationExtensions = new AuthenticationExtensions(str != null ? new FidoAppIdExtension(str) : null, null, new UserVerificationMethodExtension(ui0.k));
        byte[] bArr = ui0.d;
        Objects.requireNonNull(bArr, "null reference");
        Double valueOf = Double.valueOf(BP.a(ui0.e));
        String str2 = ui0.f;
        Objects.requireNonNull(str2, "null reference");
        PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions = new PublicKeyCredentialRequestOptions(bArr, valueOf, str2, b, null, null, null, authenticationExtensions);
        Uri parse = Uri.parse(ap.b(origin));
        BrowserPublicKeyCredentialRequestOptions.x(parse);
        new BrowserPublicKeyCredentialRequestOptions(publicKeyCredentialRequestOptions, parse);
        throw null;
    }

    @Override // defpackage.AbstractC5274vb
    public void c0(AbstractC4934tb tbVar) {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (applicationContext == null) {
            tbVar.a(Boolean.FALSE);
        } else if (!N.M09VlOh_("WebAuthentication")) {
            tbVar.a(Boolean.FALSE);
        } else if (AbstractC4652ru0.a(applicationContext, "com.google.android.gms") < 16890000) {
            tbVar.a(Boolean.FALSE);
        } else {
            this.L.add(tbVar);
            C5759yP a2 = C5759yP.a();
            RenderFrameHost renderFrameHost = this.F;
            Objects.requireNonNull(a2);
            AP ap = new AP();
            ap.b = this;
            if (ap.c == null) {
                ap.c = Bx1.a(renderFrameHost);
            }
            if (!ap.c()) {
                AbstractC1220Ua0.a("Fido2Request", "Google Play Services' Fido2PrivilegedApi is not available.", new Object[0]);
                ((AbstractC1494Yl) ((AuthenticatorImpl) ap.b).L.poll()).a(Boolean.FALSE);
                ap.b = null;
                return;
            }
            throw null;
        }
    }

    @Override // defpackage.AbstractC5274vb
    public void cancel() {
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
        this.G = false;
        this.f10806J = null;
        this.K = null;
    }

    @Override // defpackage.SW
    public void f0(Integer num) {
        AbstractC1555Zl zl = this.f10806J;
        if (zl != null) {
            zl.a(num, null);
        } else {
            AbstractC1555Zl zl2 = this.K;
            if (zl2 != null) {
                zl2.a(num, null);
            }
        }
        close();
    }

    public void getAssertionBridge(ByteBuffer byteBuffer) {
        CC[] ccArr = C5065uI0.b;
        Z(C5065uI0.d(new C4709sD(new C2740gj0(byteBuffer, new ArrayList()))), new C5784yb(this));
    }

    public void isUserVerifyingPlatformAuthenticatorAvailableBridge() {
        if (Build.VERSION.SDK_INT < 28) {
            N.MEBqzPtO(this.I.longValue(), false);
        } else {
            c0(new C5954zb(this));
        }
    }

    @Override // defpackage.AbstractC5274vb
    public void j(C4554rI0 ri0, AbstractC5104ub ubVar) {
        if (this.G) {
            ubVar.a(1, null);
            return;
        }
        this.f10806J = ubVar;
        Context applicationContext = ContextUtils.getApplicationContext();
        if (applicationContext == null || AbstractC4652ru0.a(applicationContext, "com.google.android.gms") < 16890000) {
            f0(7);
            return;
        }
        this.G = true;
        C5759yP a2 = C5759yP.a();
        RenderFrameHost renderFrameHost = this.F;
        Origin origin = this.H;
        Objects.requireNonNull(a2);
        AP ap = new AP();
        ap.f7669a = this;
        if (ap.c == null) {
            ap.c = Bx1.a(renderFrameHost);
        }
        ap.e = 1;
        if (!ap.c()) {
            AbstractC1220Ua0.a("Fido2Request", "Google Play Services' Fido2PrivilegedApi is not available.", new Object[0]);
            ap.e(21);
            return;
        }
        int c = renderFrameHost.c(ri0.d.d, origin);
        if (c != 0) {
            ap.e(c);
            return;
        }
        try {
            PublicKeyCredentialCreationOptions d = BP.d(ri0);
            Uri parse = Uri.parse(ap.b(origin));
            BrowserPublicKeyCredentialCreationOptions.x(parse);
            new BrowserPublicKeyCredentialCreationOptions(d, parse);
            throw null;
        } catch (NoSuchAlgorithmException unused) {
            ap.e(11);
        }
    }

    public void makeCredentialBridge(ByteBuffer byteBuffer) {
        CC[] ccArr = C4554rI0.b;
        j(C4554rI0.d(new C4709sD(new C2740gj0(byteBuffer, new ArrayList()))), new C5614xb(this));
    }

    public void setEffectiveOrigin(Origin origin) {
        this.H = origin;
    }

    public AuthenticatorImpl(RenderFrameHost renderFrameHost) {
        this.F = renderFrameHost;
        this.H = renderFrameHost.d();
    }
}
