package com.oculus.browser;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class StoryLogging extends RN implements AbstractC0530Iq0 {
    public static final Object F = new Object();
    public static StoryLogging G;
    public Object H = new Object();
    public QN I;

    /* renamed from: J  reason: collision with root package name */
    public HashSet f9711J = new HashSet();
    public HashSet K = new HashSet();
    public HashSet L = new HashSet();
    public boolean M;
    public String N;

    public StoryLogging(QN qn) {
        C0591Jq0.a(this);
        this.M = false;
        this.I = qn;
        qn.b = this;
    }

    public static StoryLogging f() {
        StoryLogging storyLogging;
        synchronized (F) {
            if (G == null) {
                G = new StoryLogging(new QN());
            }
            storyLogging = G;
        }
        return storyLogging;
    }

    public static void logEvent(String str, String str2, String str3, int i) {
        StoryLogging f = f();
        Objects.requireNonNull(f);
        if (str.equals("CTA_CLICK")) {
            synchronized (f.H) {
                f.f9711J.add(new J21(f, str2, str3, i));
            }
        } else if (str.equals("TILE_RENDERED")) {
            synchronized (f.H) {
                f.K.add(new J21(f, str2, str3, i));
            }
        } else if (str.equals("SHELF_RENDERED")) {
            synchronized (f.H) {
                f.L.add(new J21(f, str2, str3, i));
            }
        } else {
            throw new IllegalArgumentException(AbstractC2531fV.f("Feedback must be CTA_CLICK, TILE_RENDERED, or SHELF_RENDERED: ", str));
        }
        f().e();
    }

    @Override // defpackage.AbstractC0530Iq0
    public void a() {
    }

    @Override // defpackage.RN
    public void b(String str) {
        Log.i("StoryLogging", str);
        synchronized (this.H) {
            this.M = false;
        }
        e();
    }

    @Override // defpackage.AbstractC0530Iq0
    public void c(String str) {
        this.N = str;
    }

    public void e() {
        if (!this.M) {
            synchronized (this.H) {
                if (this.f9711J.size() > 0 || this.K.size() > 0 || this.L.size() > 0) {
                    this.M = true;
                    if (this.f9711J.size() > 0) {
                        g("CTA_CLICK", this.f9711J);
                        this.f9711J = new HashSet();
                    }
                    if (this.K.size() > 0) {
                        g("TILE_RENDERED", this.K);
                        this.K = new HashSet();
                    }
                    if (this.L.size() > 0) {
                        g("SHELF_RENDERED", this.L);
                        this.L = new HashSet();
                    }
                }
            }
        }
    }

    public void g(String str, HashSet hashSet) {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                J21 j21 = (J21) it.next();
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(String.format("{  \"story_id\": \"%s\",  \"trace_id\": \"%s\",  \"index\": \"%s\"}", j21.f8265a, j21.b, Integer.valueOf(j21.c)));
            }
            String format = String.format("{  \"input\": {  \"client_mutation_id\": \"%s\",  \"feedback\": \"%s\",  \"story_infos\": [      %s    ]  }}", UUID.randomUUID().toString(), str, sb.toString());
            Log.i("StoryLogging", format);
            this.I.d("https://graph.oculus.com/graphql?access_token=" + this.N + "&doc=" + URLEncoder.encode("mutation meh($input: RegisterRankingEventData!) {  register_ranking_event(data: $input) {    viewer {      user {         id       }    }  }}", "UTF-8") + "&variables=" + URLEncoder.encode(format, "UTF-8"), null, 2, 2);
        } catch (UnsupportedEncodingException e) {
            StringBuilder i = AbstractC2531fV.i("Failed to encode url: ");
            i.append(e.toString());
            Log.e("StoryLogging", i.toString());
        }
    }
}
