package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C5560xD0();
    public final int F;
    public final long G;
    public final long H;
    public final float I;

    /* renamed from: J  reason: collision with root package name */
    public final long f9453J;
    public final int K;
    public final CharSequence L;
    public final long M;
    public List N;
    public final long O;
    public final Bundle P;
    public PlaybackState Q;

    public PlaybackStateCompat(int i, long j, long j2, float f, long j3, int i2, CharSequence charSequence, long j4, List list, long j5, Bundle bundle) {
        this.F = i;
        this.G = j;
        this.H = j2;
        this.I = f;
        this.f9453J = j3;
        this.K = i2;
        this.L = charSequence;
        this.M = j4;
        this.N = new ArrayList(list);
        this.O = j5;
        this.P = bundle;
    }

    public static PlaybackStateCompat b(Object obj) {
        ArrayList arrayList;
        CustomAction customAction;
        if (obj == null) {
            return null;
        }
        PlaybackState playbackState = (PlaybackState) obj;
        List<PlaybackState.CustomAction> customActions = playbackState.getCustomActions();
        if (customActions != null) {
            ArrayList arrayList2 = new ArrayList(customActions.size());
            for (PlaybackState.CustomAction customAction2 : customActions) {
                if (customAction2 != null) {
                    PlaybackState.CustomAction customAction3 = customAction2;
                    Bundle extras = customAction3.getExtras();
                    C0571Jh0.a(extras);
                    customAction = new CustomAction(customAction3.getAction(), customAction3.getName(), customAction3.getIcon(), extras);
                    customAction.f9454J = customAction3;
                } else {
                    customAction = null;
                }
                arrayList2.add(customAction);
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        Bundle extras2 = playbackState.getExtras();
        C0571Jh0.a(extras2);
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(playbackState.getState(), playbackState.getPosition(), playbackState.getBufferedPosition(), playbackState.getPlaybackSpeed(), playbackState.getActions(), 0, playbackState.getErrorMessage(), playbackState.getLastPositionUpdateTime(), arrayList, playbackState.getActiveQueueItemId(), extras2);
        playbackStateCompat.Q = playbackState;
        return playbackStateCompat;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "PlaybackState {" + "state=" + this.F + ", position=" + this.G + ", buffered position=" + this.H + ", speed=" + this.I + ", updated=" + this.M + ", actions=" + this.f9453J + ", error code=" + this.K + ", error message=" + this.L + ", custom actions=" + this.N + ", active item id=" + this.O + "}";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.F);
        parcel.writeLong(this.G);
        parcel.writeFloat(this.I);
        parcel.writeLong(this.M);
        parcel.writeLong(this.H);
        parcel.writeLong(this.f9453J);
        TextUtils.writeToParcel(this.L, parcel, i);
        parcel.writeTypedList(this.N);
        parcel.writeLong(this.O);
        parcel.writeBundle(this.P);
        parcel.writeInt(this.K);
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public final class CustomAction implements Parcelable {
        public static final Parcelable.Creator CREATOR = new C5730yD0();
        public final String F;
        public final CharSequence G;
        public final int H;
        public final Bundle I;

        /* renamed from: J  reason: collision with root package name */
        public PlaybackState.CustomAction f9454J;

        public CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.F = str;
            this.G = charSequence;
            this.H = i;
            this.I = bundle;
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            StringBuilder i = AbstractC2531fV.i("Action:mName='");
            i.append((Object) this.G);
            i.append(", mIcon=");
            i.append(this.H);
            i.append(", mExtras=");
            i.append(this.I);
            return i.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.F);
            TextUtils.writeToParcel(this.G, parcel, i);
            parcel.writeInt(this.H);
            parcel.writeBundle(this.I);
        }

        public CustomAction(Parcel parcel) {
            this.F = parcel.readString();
            this.G = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.H = parcel.readInt();
            this.I = parcel.readBundle(C0571Jh0.class.getClassLoader());
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.F = parcel.readInt();
        this.G = parcel.readLong();
        this.I = parcel.readFloat();
        this.M = parcel.readLong();
        this.H = parcel.readLong();
        this.f9453J = parcel.readLong();
        this.L = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.N = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.O = parcel.readLong();
        this.P = parcel.readBundle(C0571Jh0.class.getClassLoader());
        this.K = parcel.readInt();
    }
}
