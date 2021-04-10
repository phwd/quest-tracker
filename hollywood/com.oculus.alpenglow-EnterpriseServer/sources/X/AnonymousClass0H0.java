package X;

import android.annotation.SuppressLint;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import com.oculus.alpenglow.R;
import com.oculus.util.constants.OculusConstants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

@SuppressLint({"RestrictedAPI"})
/* renamed from: X.0H0  reason: invalid class name */
public final class AnonymousClass0H0 extends AnonymousClass0MP implements View.OnClickListener {
    public int A00 = -1;
    public int A01 = -1;
    public int A02 = 1;
    public ColorStateList A03;
    public int A04 = -1;
    public int A05 = -1;
    public int A06 = -1;
    public int A07 = -1;
    public final SearchableInfo A08;
    public final Context A09;
    public final WeakHashMap<String, Drawable.ConstantState> A0A;
    public final int A0B;
    public final SearchView A0C;

    private Drawable A00(String str) {
        Drawable drawable;
        Uri parse;
        InputStream openInputStream;
        int i;
        Drawable drawable2 = null;
        if (str != null && !str.isEmpty() && !OculusConstants.DEFAULT_ENTERPRISE_USER_ID.equals(str)) {
            try {
                int parseInt = Integer.parseInt(str);
                StringBuilder sb = new StringBuilder();
                sb.append("android.resource://");
                Context context = this.A09;
                sb.append(context.getPackageName());
                sb.append("/");
                sb.append(parseInt);
                String sb2 = sb.toString();
                WeakHashMap<String, Drawable.ConstantState> weakHashMap = this.A0A;
                Drawable.ConstantState constantState = weakHashMap.get(sb2);
                if (constantState == null) {
                    drawable = null;
                } else {
                    drawable = constantState.newDrawable();
                }
                if (drawable == null) {
                    Drawable drawable3 = context.getDrawable(parseInt);
                    if (drawable3 != null) {
                        weakHashMap.put(sb2, drawable3.getConstantState());
                    }
                    return drawable3;
                }
            } catch (NumberFormatException unused) {
                WeakHashMap<String, Drawable.ConstantState> weakHashMap2 = this.A0A;
                Drawable.ConstantState constantState2 = weakHashMap2.get(str);
                if (constantState2 == null) {
                    drawable = null;
                } else {
                    drawable = constantState2.newDrawable();
                }
                if (drawable == null) {
                    parse = Uri.parse(str);
                    try {
                        if ("android.resource".equals(parse.getScheme())) {
                            try {
                                String authority = parse.getAuthority();
                                if (!TextUtils.isEmpty(authority)) {
                                    try {
                                        Resources resourcesForApplication = ((AbstractC03680cx) this).A01.getPackageManager().getResourcesForApplication(authority);
                                        List<String> pathSegments = parse.getPathSegments();
                                        if (pathSegments != null) {
                                            int size = pathSegments.size();
                                            if (size == 1) {
                                                try {
                                                    i = Integer.parseInt(pathSegments.get(0));
                                                } catch (NumberFormatException unused2) {
                                                    throw new FileNotFoundException("Single path segment is not a resource ID: " + parse);
                                                }
                                            } else if (size == 2) {
                                                i = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
                                            } else {
                                                throw new FileNotFoundException("More than two path segments: " + parse);
                                            }
                                            if (i != 0) {
                                                drawable2 = resourcesForApplication.getDrawable(i);
                                            } else {
                                                throw new FileNotFoundException("No resource found for: " + parse);
                                            }
                                        } else {
                                            throw new FileNotFoundException("No path: " + parse);
                                        }
                                    } catch (PackageManager.NameNotFoundException unused3) {
                                        throw new FileNotFoundException("No package found for authority: " + parse);
                                    }
                                } else {
                                    throw new FileNotFoundException("No authority: " + parse);
                                }
                            } catch (Resources.NotFoundException unused4) {
                                throw new FileNotFoundException("Resource does not exist: " + parse);
                            }
                        } else {
                            openInputStream = this.A09.getContentResolver().openInputStream(parse);
                            if (openInputStream != null) {
                                drawable2 = Drawable.createFromStream(openInputStream, null);
                                try {
                                    openInputStream.close();
                                } catch (IOException e) {
                                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + parse, e);
                                }
                            } else {
                                throw new FileNotFoundException("Failed to open " + parse);
                            }
                        }
                    } catch (FileNotFoundException e2) {
                        Log.w("SuggestionsAdapter", "Icon not found: " + parse + ", " + e2.getMessage());
                        drawable2 = null;
                    }
                    if (drawable2 != null) {
                        weakHashMap2.put(str, drawable2.getConstantState());
                        return drawable2;
                    }
                }
            } catch (Resources.NotFoundException unused5) {
                Log.w("SuggestionsAdapter", AnonymousClass006.A05("Icon resource not found: ", str));
                return null;
            } catch (Throwable th) {
                try {
                    openInputStream.close();
                } catch (IOException e3) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + parse, e3);
                }
                throw th;
            }
            return drawable;
        }
        return drawable2;
    }

    public static String A01(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }

    @Override // X.AbstractC00900Bt, X.AbstractC03680cx
    public final CharSequence A1r(Cursor cursor) {
        String A012;
        if (cursor != null) {
            String A013 = A01(cursor, cursor.getColumnIndex("suggest_intent_query"));
            if (A013 == null) {
                SearchableInfo searchableInfo = this.A08;
                if (!searchableInfo.shouldRewriteQueryFromData() || (A013 = A01(cursor, cursor.getColumnIndex("suggest_intent_data"))) == null) {
                    if (!searchableInfo.shouldRewriteQueryFromText() || (A012 = A01(cursor, cursor.getColumnIndex("suggest_text_1"))) == null) {
                        return null;
                    }
                    return A012;
                }
            }
            return A013;
        }
        return null;
    }

    @Override // X.AbstractC03680cx
    public final boolean hasStableIds() {
        return false;
    }

    public AnonymousClass0H0(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.mSuggestionRowLayout);
        this.A0C = searchView;
        this.A08 = searchableInfo;
        this.A0B = searchView.mSuggestionCommitIconResId;
        this.A09 = context;
        this.A0A = weakHashMap;
    }

    @Override // X.AbstractC00900Bt, X.AbstractC03680cx
    public final void A1g(Cursor cursor) {
        try {
            super.A1g(cursor);
            if (cursor != null) {
                this.A05 = cursor.getColumnIndex("suggest_text_1");
                this.A06 = cursor.getColumnIndex("suggest_text_2");
                this.A07 = cursor.getColumnIndex("suggest_text_2_url");
                this.A00 = cursor.getColumnIndex("suggest_icon_1");
                this.A01 = cursor.getColumnIndex("suggest_icon_2");
                this.A04 = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e);
        }
    }

    @Override // X.AbstractC00900Bt, X.AbstractC03680cx
    public final Cursor A7W(CharSequence charSequence) {
        String charSequence2;
        Cursor cursor;
        String suggestAuthority;
        if (charSequence == null) {
            charSequence2 = "";
        } else {
            charSequence2 = charSequence.toString();
        }
        SearchView searchView = this.A0C;
        if (searchView.getVisibility() == 0 && searchView.getWindowVisibility() == 0) {
            try {
                SearchableInfo searchableInfo = this.A08;
                String[] strArr = null;
                if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
                    cursor = null;
                } else {
                    Uri.Builder fragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
                    String suggestPath = searchableInfo.getSuggestPath();
                    if (suggestPath != null) {
                        fragment.appendEncodedPath(suggestPath);
                    }
                    fragment.appendPath("search_suggest_query");
                    String suggestSelection = searchableInfo.getSuggestSelection();
                    if (suggestSelection != null) {
                        strArr = new String[]{charSequence2};
                    } else {
                        fragment.appendPath(charSequence2);
                    }
                    fragment.appendQueryParameter("limit", String.valueOf(50));
                    cursor = ((AbstractC03680cx) this).A01.getContentResolver().query(fragment.build(), null, suggestSelection, strArr, null);
                }
                if (cursor != null) {
                    cursor.getCount();
                    return cursor;
                }
            } catch (RuntimeException e) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e);
            }
        }
        return null;
    }

    @Override // X.AnonymousClass0MP, X.AbstractC03680cx
    public final View A03(Context context, Cursor cursor, ViewGroup viewGroup) {
        View A032 = super.A03(context, cursor, viewGroup);
        A032.setTag(new AnonymousClass05S(A032));
        ((ImageView) A032.findViewById(R.id.edit_query)).setImageResource(this.A0B);
        return A032;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r11v3, resolved type: android.text.SpannableString */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0129, code lost:
        if (r0 != null) goto L_0x00a6;
     */
    @Override // X.AbstractC03680cx
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(android.view.View r25, android.content.Context r26, android.database.Cursor r27) {
        /*
        // Method dump skipped, instructions count: 425
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0H0.A04(android.view.View, android.content.Context, android.database.Cursor):void");
    }

    @Override // X.AbstractC03680cx
    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View A022 = A02(((AbstractC03680cx) this).A01, ((AbstractC03680cx) this).A02, viewGroup);
            if (A022 != null) {
                ((AnonymousClass05S) A022.getTag()).A03.setText(e.toString());
            }
            return A022;
        }
    }

    @Override // X.AbstractC03680cx
    public final View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View A032 = A03(((AbstractC03680cx) this).A01, ((AbstractC03680cx) this).A02, viewGroup);
            if (A032 != null) {
                ((AnonymousClass05S) A032.getTag()).A03.setText(e.toString());
            }
            return A032;
        }
    }

    public final void notifyDataSetChanged() {
        Bundle extras;
        super.notifyDataSetChanged();
        Cursor A3I = A3I();
        if (A3I != null && (extras = A3I.getExtras()) != null) {
            extras.getBoolean("in_progress");
        }
    }

    public final void notifyDataSetInvalidated() {
        Bundle extras;
        super.notifyDataSetInvalidated();
        Cursor A3I = A3I();
        if (A3I != null && (extras = A3I.getExtras()) != null) {
            extras.getBoolean("in_progress");
        }
    }

    public final void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.A0C.onQueryRefine((CharSequence) tag);
        }
    }
}
