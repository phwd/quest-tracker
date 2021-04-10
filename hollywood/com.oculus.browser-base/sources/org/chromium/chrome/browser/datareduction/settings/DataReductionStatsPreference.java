package org.chromium.chrome.browser.datareduction.settings;

import J.N;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.third_party.android.datausagechart.ChartDataUsageView;
import org.chromium.third_party.android.datausagechart.ChartNetworkSeriesView;
import org.chromium.third_party.android.datausagechart.NetworkStatsHistory;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DataReductionStatsPreference extends Preference {
    public TextView A0;
    public TextView B0;
    public TextView C0;
    public Button D0;
    public ChartDataUsageView E0;
    public DataReductionSiteBreakdownView F0;
    public boolean G0;
    public boolean H0;
    public int I0;
    public long J0;
    public long K0;
    public long L0;
    public CharSequence M0;
    public CharSequence N0;
    public String O0;
    public String P0;
    public NetworkStatsHistory t0;
    public NetworkStatsHistory u0;
    public List v0;
    public ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 w0;
    public LinearLayout x0;
    public TextView y0;
    public TextView z0;

    public DataReductionStatsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l0 = R.layout.f37730_resource_name_obfuscated_RES_2131624082;
    }

    public static String a0(Context context, long j) {
        return DateUtils.formatDateTime(context, j, 65560).toString();
    }

    public static NetworkStatsHistory b0(long[] jArr, int i) {
        int i2;
        int i3;
        long j;
        long j2;
        long j3;
        long j4;
        int i4;
        long j5;
        long[] jArr2 = jArr;
        int length = i > jArr2.length ? jArr2.length : i;
        long j6 = 86400000;
        NetworkStatsHistory networkStatsHistory = new NetworkStatsHistory(86400000, length, 2);
        long b = DataReductionProxySettings.d().b() - (((long) length) * 86400000);
        int length2 = jArr2.length - length;
        int i5 = 0;
        int i6 = 0;
        while (length2 < jArr2.length) {
            long max = Math.max(jArr2[length2], 0L);
            long j7 = (((long) i6) * j6) + b;
            long j8 = j7 + 3600000;
            if (((max < 0 || 0 < 0 || 0 < 0 || 0 < 0 || 0 < 0) ? 1 : i5) == 0) {
                long j9 = networkStatsHistory.F;
                long j10 = ((j9 - (j8 % j9)) % j9) + j8;
                long j11 = j7 - (j7 % j9);
                while (j11 < j10) {
                    int binarySearch = Arrays.binarySearch(networkStatsHistory.H, i5, networkStatsHistory.G, j11);
                    if (binarySearch < 0) {
                        int i7 = ~binarySearch;
                        int i8 = networkStatsHistory.G;
                        long[] jArr3 = networkStatsHistory.H;
                        j5 = b;
                        if (i8 >= jArr3.length) {
                            int max2 = (Math.max(jArr3.length, 10) * 3) / 2;
                            networkStatsHistory.H = Arrays.copyOf(networkStatsHistory.H, max2);
                            long[] jArr4 = networkStatsHistory.I;
                            if (jArr4 != null) {
                                networkStatsHistory.I = Arrays.copyOf(jArr4, max2);
                            }
                            long[] jArr5 = networkStatsHistory.f11013J;
                            if (jArr5 != null) {
                                networkStatsHistory.f11013J = Arrays.copyOf(jArr5, max2);
                            }
                            long[] jArr6 = networkStatsHistory.K;
                            if (jArr6 != null) {
                                networkStatsHistory.K = Arrays.copyOf(jArr6, max2);
                            }
                            long[] jArr7 = networkStatsHistory.L;
                            if (jArr7 != null) {
                                networkStatsHistory.L = Arrays.copyOf(jArr7, max2);
                            }
                            long[] jArr8 = networkStatsHistory.M;
                            if (jArr8 != null) {
                                networkStatsHistory.M = Arrays.copyOf(jArr8, max2);
                            }
                            long[] jArr9 = networkStatsHistory.N;
                            if (jArr9 != null) {
                                networkStatsHistory.N = Arrays.copyOf(jArr9, max2);
                            }
                        }
                        int i9 = networkStatsHistory.G;
                        if (i7 < i9) {
                            int i10 = i7 + 1;
                            int i11 = i9 - i7;
                            long[] jArr10 = networkStatsHistory.H;
                            System.arraycopy(jArr10, i7, jArr10, i10, i11);
                            long[] jArr11 = networkStatsHistory.I;
                            if (jArr11 != null) {
                                System.arraycopy(jArr11, i7, jArr11, i10, i11);
                            }
                            long[] jArr12 = networkStatsHistory.f11013J;
                            if (jArr12 != null) {
                                System.arraycopy(jArr12, i7, jArr12, i10, i11);
                            }
                            long[] jArr13 = networkStatsHistory.K;
                            if (jArr13 != null) {
                                System.arraycopy(jArr13, i7, jArr13, i10, i11);
                            }
                            long[] jArr14 = networkStatsHistory.L;
                            if (jArr14 != null) {
                                System.arraycopy(jArr14, i7, jArr14, i10, i11);
                            }
                            long[] jArr15 = networkStatsHistory.M;
                            if (jArr15 != null) {
                                System.arraycopy(jArr15, i7, jArr15, i10, i11);
                            }
                            long[] jArr16 = networkStatsHistory.N;
                            if (jArr16 != null) {
                                System.arraycopy(jArr16, i7, jArr16, i10, i11);
                            }
                        }
                        networkStatsHistory.H[i7] = j11;
                        long[] jArr17 = networkStatsHistory.I;
                        if (jArr17 != null) {
                            jArr17[i7] = 0;
                        }
                        long[] jArr18 = networkStatsHistory.f11013J;
                        if (jArr18 != null) {
                            jArr18[i7] = 0;
                        }
                        long[] jArr19 = networkStatsHistory.K;
                        if (jArr19 != null) {
                            jArr19[i7] = 0;
                        }
                        long[] jArr20 = networkStatsHistory.L;
                        if (jArr20 != null) {
                            jArr20[i7] = 0;
                        }
                        long[] jArr21 = networkStatsHistory.M;
                        if (jArr21 != null) {
                            jArr21[i7] = 0;
                        }
                        long[] jArr22 = networkStatsHistory.N;
                        if (jArr22 != null) {
                            jArr22[i7] = 0;
                        }
                        networkStatsHistory.G++;
                    } else {
                        j5 = b;
                    }
                    j11 += networkStatsHistory.F;
                    b = j5;
                    i5 = 0;
                }
                long j12 = j8 - j7;
                int h = networkStatsHistory.h(j8);
                long j13 = max;
                long j14 = 0;
                long j15 = 0;
                long j16 = 0;
                long j17 = 0;
                while (true) {
                    if (h < 0) {
                        i2 = length2;
                        i3 = i6;
                        break;
                    }
                    i2 = length2;
                    i3 = i6;
                    long j18 = networkStatsHistory.H[h];
                    long j19 = networkStatsHistory.F + j18;
                    if (j19 < j7) {
                        break;
                    }
                    if (j18 <= j8) {
                        long min = Math.min(j19, j8) - Math.max(j18, j7);
                        if (min > 0) {
                            long j20 = (j13 * min) / j12;
                            long j21 = (j15 * min) / j12;
                            j3 = j8;
                            long j22 = (j16 * min) / j12;
                            j2 = j7;
                            long j23 = (j17 * min) / j12;
                            j = max;
                            long j24 = (j14 * min) / j12;
                            i4 = h;
                            NetworkStatsHistory.b(networkStatsHistory.I, i4, min);
                            NetworkStatsHistory.b(networkStatsHistory.f11013J, i4, j20);
                            j13 -= j20;
                            NetworkStatsHistory.b(networkStatsHistory.K, i4, j21);
                            j15 -= j21;
                            NetworkStatsHistory.b(networkStatsHistory.L, i4, j22);
                            j16 -= j22;
                            NetworkStatsHistory.b(networkStatsHistory.M, i4, j23);
                            j17 -= j23;
                            NetworkStatsHistory.b(networkStatsHistory.N, i4, j24);
                            j14 -= j24;
                            j4 = j12 - min;
                            h = i4 - 1;
                            i6 = i3;
                            j12 = j4;
                            length2 = i2;
                            j8 = j3;
                            j7 = j2;
                            max = j;
                        }
                    }
                    j3 = j8;
                    j = max;
                    j2 = j7;
                    j4 = j12;
                    i4 = h;
                    h = i4 - 1;
                    i6 = i3;
                    j12 = j4;
                    length2 = i2;
                    j8 = j3;
                    j7 = j2;
                    max = j;
                }
                networkStatsHistory.O = max + 0 + networkStatsHistory.O;
                length2 = i2 + 1;
                i6 = i3 + 1;
                jArr2 = jArr;
                b = b;
                i5 = 0;
                j6 = 86400000;
            } else {
                throw new IllegalArgumentException("tried recording negative data");
            }
        }
        return networkStatsHistory;
    }

    public final void c0() {
        Context context = this.F;
        if (this.G0) {
            long j = this.K0;
            long j2 = this.J0;
            long j3 = j + j2;
            long j4 = this.L0 + j2;
            long j5 = this.u0.O;
            this.N0 = YP.a(context, j5);
            long j6 = this.t0.O;
            long j7 = j6 - j5;
            long max = Math.max(j7, 0L);
            this.M0 = YP.a(context, max);
            if (this.H0) {
                this.O0 = a0(context, j4);
                this.P0 = null;
            } else {
                this.O0 = a0(context, j3);
                this.P0 = a0(context, j4);
            }
            AbstractC3364kK0.e("DataReductionProxy.UserViewedOriginalSize", (int) (j6 / 1024), 1, 1000000000, 100);
            AbstractC3364kK0.e("DataReductionProxy.UserViewedSavingsSize", (int) (j7 / 1024), 1, 1000000000, 100);
            List<HC> list = this.v0;
            if (list != null) {
                long j8 = 0;
                long j9 = 0;
                for (HC hc : list) {
                    j8 += hc.a();
                    j9 += hc.b;
                }
                long abs = Math.abs(j8 - max);
                long abs2 = Math.abs(j9 - j5);
                long j10 = j8 + max;
                long j11 = j9 + j5;
                if (j10 > 0 && j11 > 0) {
                    AbstractC3364kK0.g("DataReductionProxy.UserViewedUsageDifferenceWithBreakdown", (int) ((abs2 / j11) * 100), 101);
                    AbstractC3364kK0.g("DataReductionProxy.UserViewedSavingsDifferenceWithBreakdown", (int) ((abs / j10) * 100), 101);
                }
            }
        }
        int i = 8;
        this.y0.setVisibility(this.G0 ? 8 : 0);
        LinearLayout linearLayout = this.x0;
        if (this.G0) {
            i = 0;
        }
        linearLayout.setVisibility(i);
        CharSequence charSequence = "";
        this.B0.setText(this.G0 ? this.O0 : charSequence);
        this.B0.setContentDescription(this.G0 ? context.getString(R.string.f50620_resource_name_obfuscated_RES_2131952379, this.O0) : charSequence);
        this.C0.setText(this.G0 ? this.P0 : charSequence);
        this.C0.setContentDescription(this.G0 ? context.getString(R.string.f50500_resource_name_obfuscated_RES_2131952367, this.P0) : charSequence);
        TextView textView = this.A0;
        if (textView != null) {
            textView.setText(this.G0 ? this.N0 : charSequence);
        }
        TextView textView2 = this.z0;
        if (textView2 != null) {
            if (this.G0) {
                charSequence = this.M0;
            }
            textView2.setText(charSequence);
        }
    }

    public void d0(long j) {
        DataReductionProxySettings d = DataReductionProxySettings.d();
        long[] M4N7SUZB = N.M4N7SUZB(d.c, d);
        DataReductionProxySettings d2 = DataReductionProxySettings.d();
        long[] MmJPih$3 = N.MmJPih$3(d2.c, d2);
        long c = (DataReductionProxySettings.d().c() / 86400000) * 86400000;
        DataReductionProxySettings.d().c();
        long offset = (j - (j % 86400000)) - ((long) TimeZone.getDefault().getOffset(j));
        long b = DataReductionProxySettings.d().b();
        long j2 = 0;
        if (b == 0) {
            b = offset;
        }
        if (b < offset) {
            j2 = (offset - b) / 86400000;
        }
        Long valueOf = Long.valueOf(j2);
        this.J0 = j - offset;
        Long valueOf2 = Long.valueOf(((j - c) / 86400000) + 1);
        this.H0 = false;
        this.I0 = 30;
        boolean z = true;
        if (valueOf2.longValue() < 2) {
            this.H0 = true;
            this.I0 = 2;
        } else if (valueOf2.longValue() < 30) {
            this.I0 = valueOf2.intValue();
        }
        this.t0 = b0(M4N7SUZB, this.I0);
        NetworkStatsHistory b0 = b0(MmJPih$3, this.I0);
        this.u0 = b0;
        if (b0.O / 1024 < 100) {
            z = false;
        }
        this.G0 = z;
        NetworkStatsHistory networkStatsHistory = this.t0;
        this.K0 = (((long) valueOf.intValue()) * 86400000) + (networkStatsHistory.G > 0 ? networkStatsHistory.H[0] : Long.MAX_VALUE) + 86400000;
        this.L0 = (((long) valueOf.intValue()) * 86400000) + this.t0.c();
        if (this.G0 && this.F0 != null && j > NU0.f8549a.h("data_reduction_site_breakdown_allowed_date", Long.MAX_VALUE)) {
            DataReductionProxySettings d3 = DataReductionProxySettings.d();
            int i = this.I0;
            d3.b = new C2147dD(this);
            N.MLJ8wHM0(d3.c, d3, new ArrayList(), i);
        }
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        View view = tf0.G;
        ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 fv1 = this.w0;
        if (fv1 != null) {
            fv1.c();
        }
        ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 fv12 = new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(view);
        this.w0 = fv12;
        fv12.b(new C2317eD(this, view));
        TextView textView = (TextView) tf0.x(R.id.initial_data_savings);
        this.y0 = textView;
        textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, Fs1.a(this.F.getResources(), R.drawable.f29000_resource_name_obfuscated_RES_2131230940, this.F.getTheme()), (Drawable) null, (Drawable) null);
        this.x0 = (LinearLayout) tf0.x(R.id.data_reduction_stats_container);
        this.A0 = (TextView) tf0.x(R.id.data_reduction_usage);
        this.z0 = (TextView) tf0.x(R.id.data_reduction_savings);
        this.B0 = (TextView) tf0.x(R.id.data_reduction_start_date);
        this.C0 = (TextView) tf0.x(R.id.data_reduction_end_date);
        this.F0 = (DataReductionSiteBreakdownView) tf0.x(R.id.breakdown);
        ((FrameLayout.LayoutParams) this.B0.getLayoutParams()).gravity = 3;
        ((FrameLayout.LayoutParams) this.C0.getLayoutParams()).gravity = 5;
        if (this.t0 == null) {
            d0(System.currentTimeMillis());
        } else {
            List list = this.v0;
            if (list != null && this.G0) {
                this.F0.a(list);
            }
        }
        ChartDataUsageView chartDataUsageView = (ChartDataUsageView) tf0.x(R.id.chart);
        this.E0 = chartDataUsageView;
        NetworkStatsHistory networkStatsHistory = this.t0;
        NetworkStatsHistory networkStatsHistory2 = this.u0;
        ChartNetworkSeriesView chartNetworkSeriesView = chartDataUsageView.L;
        chartNetworkSeriesView.L = networkStatsHistory;
        chartNetworkSeriesView.b();
        chartNetworkSeriesView.invalidate();
        ChartNetworkSeriesView chartNetworkSeriesView2 = chartDataUsageView.M;
        chartNetworkSeriesView2.L = networkStatsHistory2;
        chartNetworkSeriesView2.b();
        chartNetworkSeriesView2.invalidate();
        chartDataUsageView.M.setVisibility(networkStatsHistory2 != null ? 0 : 8);
        chartDataUsageView.N = networkStatsHistory;
        if (networkStatsHistory != null) {
            chartDataUsageView.L.T = networkStatsHistory.c();
            chartDataUsageView.M.T = chartDataUsageView.N.c();
        }
        chartDataUsageView.L.c();
        chartDataUsageView.a();
        chartDataUsageView.b();
        chartDataUsageView.requestLayout();
        ChartDataUsageView chartDataUsageView2 = this.E0;
        long j = this.K0;
        long j2 = this.L0;
        boolean a2 = chartDataUsageView2.F.a(j, j2);
        ChartNetworkSeriesView chartNetworkSeriesView3 = chartDataUsageView2.L;
        chartNetworkSeriesView3.P = j;
        chartNetworkSeriesView3.Q = j2;
        if (j2 > chartNetworkSeriesView3.T) {
            chartNetworkSeriesView3.T = j2;
        }
        ChartNetworkSeriesView chartNetworkSeriesView4 = chartDataUsageView2.M;
        chartNetworkSeriesView4.P = j;
        chartNetworkSeriesView4.Q = j2;
        if (j2 > chartNetworkSeriesView4.T) {
            chartNetworkSeriesView4.T = j2;
        }
        chartDataUsageView2.O = j;
        chartDataUsageView2.P = j2;
        if (a2) {
            chartNetworkSeriesView3.b();
            chartDataUsageView2.M.b();
        }
        chartDataUsageView2.L.c();
        chartDataUsageView2.a();
        chartDataUsageView2.b();
        chartDataUsageView2.requestLayout();
        DataReductionProxySettings d = DataReductionProxySettings.d();
        if (N.MdLp8Ai5(d.c, d)) {
            AbstractC1220Ua0.f("DataSaverStats", "Data Saver proxy unreachable when user viewed Data Saver stats", new Object[0]);
        }
        Button button = (Button) tf0.x(R.id.data_reduction_reset_statistics);
        this.D0 = button;
        if (button != null) {
            button.setOnClickListener(new View$OnClickListenerC2659gD(this));
        }
        c0();
    }
}
