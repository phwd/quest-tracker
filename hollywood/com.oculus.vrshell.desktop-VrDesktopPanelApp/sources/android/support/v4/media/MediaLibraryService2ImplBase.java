package android.support.v4.media;

import android.support.v4.media.MediaLibraryService2;

class MediaLibraryService2ImplBase extends MediaSessionService2ImplBase {
    MediaLibraryService2ImplBase() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f  */
    @Override // android.support.v4.media.MediaSessionService2ImplBase, android.support.v4.media.MediaSessionService2.SupportLibraryImpl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.IBinder onBind(android.content.Intent r5) {
        /*
            r4 = this;
            java.lang.String r0 = r5.getAction()
            int r1 = r0.hashCode()
            r2 = 901933117(0x35c2683d, float:1.4484464E-6)
            r3 = 1
            if (r1 == r2) goto L_0x001e
            r2 = 1665850838(0x634addd6, float:3.7422273E21)
            if (r1 == r2) goto L_0x0014
        L_0x0013:
            goto L_0x0028
        L_0x0014:
            java.lang.String r1 = "android.media.browse.MediaBrowserService"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0013
            r0 = r3
            goto L_0x0029
        L_0x001e:
            java.lang.String r1 = "android.media.MediaLibraryService2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0013
            r0 = 0
            goto L_0x0029
        L_0x0028:
            r0 = -1
        L_0x0029:
            if (r0 == 0) goto L_0x003f
            if (r0 == r3) goto L_0x0032
            android.os.IBinder r0 = super.onBind(r5)
            return r0
        L_0x0032:
            android.support.v4.media.MediaLibraryService2$MediaLibrarySession r0 = r4.getSession()
            android.support.v4.media.MediaLibraryService2$MediaLibrarySession$SupportLibraryImpl r0 = r0.getImpl()
            android.os.IBinder r0 = r0.getLegacySessionBinder()
            return r0
        L_0x003f:
            android.support.v4.media.MediaLibraryService2$MediaLibrarySession r0 = r4.getSession()
            android.os.IBinder r0 = r0.getSessionBinder()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaLibraryService2ImplBase.onBind(android.content.Intent):android.os.IBinder");
    }

    @Override // android.support.v4.media.MediaSessionService2ImplBase, android.support.v4.media.MediaSessionService2.SupportLibraryImpl
    public MediaLibraryService2.MediaLibrarySession getSession() {
        return (MediaLibraryService2.MediaLibrarySession) super.getSession();
    }

    @Override // android.support.v4.media.MediaSessionService2ImplBase, android.support.v4.media.MediaSessionService2.SupportLibraryImpl
    public int getSessionType() {
        return 2;
    }
}
