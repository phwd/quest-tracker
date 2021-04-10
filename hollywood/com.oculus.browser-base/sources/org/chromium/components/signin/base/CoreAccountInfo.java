package org.chromium.components.signin.base;

import android.accounts.Account;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CoreAccountInfo {

    /* renamed from: a  reason: collision with root package name */
    public final CoreAccountId f10892a;
    public final String b;
    public final String c;

    public CoreAccountInfo(CoreAccountId coreAccountId, String str, String str2) {
        this.f10892a = coreAccountId;
        this.b = str;
        this.c = str2;
    }

    public static Account a(CoreAccountInfo coreAccountInfo) {
        if (coreAccountInfo == null) {
            return null;
        }
        return V1.b(coreAccountInfo.getEmail());
    }

    public static String b(CoreAccountInfo coreAccountInfo) {
        if (coreAccountInfo == null) {
            return null;
        }
        return coreAccountInfo.getEmail();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CoreAccountInfo)) {
            return false;
        }
        CoreAccountInfo coreAccountInfo = (CoreAccountInfo) obj;
        if (!this.f10892a.equals(coreAccountInfo.f10892a) || !this.b.equals(coreAccountInfo.b) || !this.c.equals(coreAccountInfo.c)) {
            return false;
        }
        return true;
    }

    public String getEmail() {
        return this.b;
    }

    public String getGaiaId() {
        return this.c;
    }

    public CoreAccountId getId() {
        return this.f10892a;
    }

    public int hashCode() {
        int hashCode = this.b.hashCode();
        return this.c.hashCode() + ((hashCode + (this.f10892a.hashCode() * 31)) * 31);
    }

    public String toString() {
        return String.format("CoreAccountInfo{id[%s], name[%s]}", getId(), getEmail());
    }
}
