package org.chromium.base.library_loader;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import java.io.IOException;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Linker {

    /* renamed from: a  reason: collision with root package name */
    public static Linker f10597a;
    public final Object b = new Object();
    public LibInfo c;
    public boolean d = true;
    public long e = -1;
    public boolean f;
    public int g = 0;

    public Linker() {
        f10597a = this;
    }

    public static native long nativeGetRandomBaseLoadAddress();

    public void a(boolean z) {
    }

    public final void b() {
        if (this.g == 0) {
            C2474f80 f80 = C2474f80.f9900a;
            System.loadLibrary("chromium_android_linker");
            if (this.d) {
                this.e = nativeGetRandomBaseLoadAddress();
            }
            this.g = 1;
        }
    }

    public final void c(String str, boolean z) {
        synchronized (this.b) {
            b();
            try {
                d(str, z);
                if (!this.f && this.c != null && this.g == 3) {
                    a(true);
                }
            } finally {
                this.f = false;
            }
        }
    }

    public abstract void d(String str, boolean z);

    public void e(String str) {
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class LibInfo implements Parcelable {
        public static final Parcelable.Creator CREATOR = new I80();
        public String F;
        public long mLoadAddress;
        public long mLoadSize;
        public int mRelroFd = -1;
        public long mRelroSize;
        public long mRelroStart;

        public LibInfo() {
        }

        public int describeContents() {
            return 1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.F);
            parcel.writeLong(this.mLoadAddress);
            parcel.writeLong(this.mLoadSize);
            parcel.writeLong(this.mRelroStart);
            parcel.writeLong(this.mRelroSize);
            parcel.writeInt(this.mRelroFd >= 0 ? 1 : 0);
            int i2 = this.mRelroFd;
            if (i2 >= 0) {
                try {
                    ParcelFileDescriptor fromFd = ParcelFileDescriptor.fromFd(i2);
                    fromFd.writeToParcel(parcel, 0);
                    fromFd.close();
                } catch (IOException e) {
                    AbstractC1220Ua0.a("Linker", "Can't write LibInfo file descriptor to parcel", e);
                }
            }
        }

        public LibInfo(Parcel parcel) {
            this.F = parcel.readString();
            this.mLoadAddress = parcel.readLong();
            this.mLoadSize = parcel.readLong();
            this.mRelroStart = parcel.readLong();
            this.mRelroSize = parcel.readLong();
            if (parcel.readInt() != 0) {
                ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                if (parcelFileDescriptor != null) {
                    this.mRelroFd = parcelFileDescriptor.detachFd();
                    return;
                }
                return;
            }
            this.mRelroFd = -1;
        }
    }
}
