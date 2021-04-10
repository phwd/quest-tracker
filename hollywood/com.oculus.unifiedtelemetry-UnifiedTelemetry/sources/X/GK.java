package X;

import com.facebook.infer.annotation.NullsafeStrict;
import com.oculus.logging.analytics2.DebugEventBaseParameterHandler;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NullsafeStrict
@NotThreadSafe
public class GK {
    public long A00;
    public long A01 = -1;
    public long A02;
    public long A03 = 0;
    @Nullable
    public Fe A04;
    @Nullable
    public GO A05;
    @Nullable
    public YE A06;
    @Nullable
    public YE A07;
    @Nullable
    public Boolean A08;
    @Nullable
    public Integer A09;
    @Nullable
    public String A0A;
    @Nullable
    public String A0B;
    public boolean A0C;
    public boolean A0D;
    public volatile boolean A0E;

    public boolean A08() {
        this.A0C = true;
        return true;
    }

    private final void A00() {
        String str;
        if (!this.A0C) {
            str = "isSampled was not invoked, how can you have known?";
        } else if (!this.A0E) {
            str = "Expected mutability";
        } else {
            return;
        }
        throw new IllegalStateException(str);
    }

    @Deprecated
    public GK A04(String str, String str2) {
        YE.A00(this.A06, str, str2);
        return this;
    }

    public GK A01(long j) {
        A00();
        this.A01 = j;
        return this;
    }

    public GK A02(String str, @Nullable Boolean bool) {
        A00();
        YE.A00(A06(), str, bool);
        synchronized (this) {
            if (bool != null) {
                this.A00 += 4;
            }
        }
        return this;
    }

    public GK A03(String str, @Nullable Number number) {
        A00();
        YE.A00(A06(), str, number);
        synchronized (this) {
            if (number != null) {
                this.A00 += 4;
            }
        }
        return this;
    }

    public GK A05(String str, @Nullable String str2) {
        A00();
        YE.A00(A06(), str, str2);
        synchronized (this) {
            if (str2 != null) {
                this.A00 += (long) str2.length();
            }
        }
        return this;
    }

    public YE A06() {
        GO go;
        A00();
        if (this.A07 == null) {
            YE A022 = this.A04.A0C.A02();
            this.A07 = A022;
            YE ye = this.A06;
            if (!(ye == null || (go = this.A05) == null)) {
                ye.A0C(go.getExtraJsonKey(), A022);
            }
        }
        return this.A07;
    }

    public void A07() {
        String str;
        AbstractC00126j<? extends GK> r3;
        long j;
        String str2;
        Fe fe;
        Fg fg;
        A00();
        if (!(this.A08 != null || (fe = this.A04) == null || (fg = fe.A09) == null)) {
            this.A08 = Boolean.valueOf(fg.A2G());
        }
        if (this.A01 == -1) {
            this.A01 = System.currentTimeMillis();
        }
        YE A062 = A06();
        long j2 = this.A02;
        double d = (double) j2;
        if (j2 >= 0) {
            d /= 1000.0d;
        }
        YE.A00(A062, "pigeon_reserved_keyword_requested_latency", Double.valueOf(d));
        GO go = this.A05;
        if (go != null) {
            YE.A00(this.A06, "log_type", go.getProtocolValue());
        }
        Boolean bool = this.A08;
        if (bool != null) {
            YE ye = this.A06;
            if (bool.booleanValue()) {
                str2 = "true";
            } else {
                str2 = "false";
            }
            YE.A00(ye, "bg", str2);
        }
        YE.A00(this.A06, DebugEventBaseParameterHandler.ParamKeys.TIME, Double.valueOf(((double) this.A01) / 1000.0d));
        String str3 = this.A0B;
        if (str3 != null) {
            YE.A00(this.A06, DebugEventBaseParameterHandler.ParamKeys.MODULE_NAME, str3);
        }
        YE.A00(this.A06, "name", this.A0A);
        Integer num = this.A09;
        if (num != null) {
            YE.A00(this.A06, "sampling_rate", num);
        }
        YE.A00(this.A06, "tags", Long.valueOf(this.A03));
        Fe fe2 = this.A04;
        long j3 = -1;
        if (this.A0D) {
            j3 = -2;
        }
        this.A02 = j3;
        if (fe2 != null) {
            this.A06.A03 = false;
            YE ye2 = this.A07;
            if (ye2 != null) {
                ye2.A03 = false;
            }
            this.A0E = false;
            this.A04.A0B.A03();
            YE ye3 = this.A06;
            if (ye3 != null) {
                ye3.A03 = false;
                YE ye4 = this.A07;
                if (ye4 != null) {
                    ye4.A03 = false;
                }
                this.A07 = null;
                this.A06 = null;
                Fe fe3 = this.A04;
                if (fe3 != null) {
                    j = Long.MAX_VALUE;
                } else {
                    j = 0;
                }
                if (this.A00 < j) {
                    fe3.A08.A4L(this.A0A, ye3, this.A02);
                }
            }
            if (this.A0E) {
                str = "Expected immutability";
            } else if (this.A06 == null && this.A07 == null) {
                Fe fe4 = this.A04;
                if (fe4 == null) {
                    r3 = null;
                } else {
                    r3 = fe4.A07;
                }
                this.A0B = null;
                this.A0A = null;
                this.A05 = null;
                this.A0D = false;
                this.A08 = null;
                this.A01 = -1;
                this.A04 = null;
                this.A0C = false;
                this.A00 = 0;
                this.A03 = 0;
                if (r3 != null) {
                    r3.A4e(this);
                    return;
                }
                return;
            } else {
                str = "Must call ejectBaseParameters before release";
            }
            throw new IllegalStateException(str);
        }
        throw new NullPointerException("builder was not acquired or was acquired without config");
    }
}
