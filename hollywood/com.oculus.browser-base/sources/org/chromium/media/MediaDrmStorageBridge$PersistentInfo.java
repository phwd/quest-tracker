package org.chromium.media;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaDrmStorageBridge$PersistentInfo {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f10980a;
    public final byte[] b;
    public final String c;
    public final int d;

    public MediaDrmStorageBridge$PersistentInfo(byte[] bArr, byte[] bArr2, String str, int i) {
        this.f10980a = bArr;
        this.b = bArr2;
        this.c = str;
        this.d = i;
    }

    public static MediaDrmStorageBridge$PersistentInfo create(byte[] bArr, byte[] bArr2, String str, int i) {
        return new MediaDrmStorageBridge$PersistentInfo(bArr, bArr2, str, i);
    }

    public byte[] emeId() {
        return this.f10980a;
    }

    public byte[] keySetId() {
        return this.b;
    }

    public int keyType() {
        return this.d;
    }

    public String mimeType() {
        return this.c;
    }
}
