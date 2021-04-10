package com.oculus.localmedia;

import X.AnonymousClass006;
import android.util.Log;
import com.oculus.vrshell.notifications.NotificationUri;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaQuery {
    public static final int DEFAULT_PAGE_SIZE = 1000;
    public String Cursor;
    public int DepthLevel;
    public int DepthOriginalLevel;
    public int DepthSize;
    public int DeviceId;
    public String ExternalHost;
    public String Filters;
    public boolean IsSortAscending;
    public String MediaId;
    public String Path;
    public String QueryId;
    public int Size;
    public MediaSort Sort;
    public int Types;

    public static class Builder {
        public String Cursor = null;
        public int DepthLevel = 0;
        public int DepthOriginalLevel = 0;
        public int DepthSize = 0;
        public int DeviceId = 0;
        public String ExternalHost = null;
        public String Filters = "";
        public boolean IsSortAscending = false;
        public String MediaId = "";
        public String Path = null;
        public String QueryId = "";
        public int Size = Integer.MAX_VALUE;
        public MediaSort Sort = MediaSort.NAME;
        public int Types;

        public static Builder fromJson(String str) {
            Builder builder = new Builder();
            try {
                JSONObject jSONObject = new JSONObject(str);
                builder.QueryId = jSONObject.optString("queryId", "");
                builder.Types = MediaQueryUtils.parseMediaTypes(jSONObject.optString("type", ""));
                builder.DeviceId = jSONObject.optInt(NotificationUri.DEVICE, 0);
                builder.Path = jSONObject.optString("path", null);
                builder.DepthLevel = jSONObject.optInt("depth", 0);
                builder.DepthSize = jSONObject.optInt("depthsize", 0);
                builder.Size = jSONObject.optInt("size", 0);
                builder.Cursor = jSONObject.optString("index", null);
                builder.Sort = MediaQueryUtils.parseSortType(jSONObject.optString("sort", ""));
                builder.IsSortAscending = jSONObject.optBoolean("order", jSONObject.optBoolean("asc", true));
                builder.Filters = jSONObject.optString("filters", "");
                builder.MediaId = jSONObject.optString("mediaId", "");
                return builder;
            } catch (Exception unused) {
                throw new IllegalArgumentException(AnonymousClass006.A07("Invalid query json : ", str));
            }
        }

        public static Builder fromMedia(MediaType mediaType, String str) {
            Builder builder = new Builder();
            builder.Types = mediaType.getValue();
            builder.MediaId = str;
            return builder;
        }

        public static Builder fromQuery(MediaQuery mediaQuery) {
            Builder builder = new Builder();
            builder.Types = mediaQuery.Types;
            builder.Size = mediaQuery.Size;
            builder.Cursor = mediaQuery.Cursor;
            builder.Sort = mediaQuery.Sort;
            builder.IsSortAscending = mediaQuery.IsSortAscending;
            builder.Filters = mediaQuery.Filters;
            builder.Path = mediaQuery.Path;
            builder.DepthLevel = mediaQuery.DepthLevel;
            builder.DepthOriginalLevel = mediaQuery.DepthOriginalLevel;
            builder.DepthSize = mediaQuery.DepthSize;
            builder.DeviceId = mediaQuery.DeviceId;
            builder.MediaId = mediaQuery.MediaId;
            return builder;
        }

        public static Builder fromType(MediaType mediaType) {
            Builder builder = new Builder();
            builder.Types = mediaType.getValue();
            return builder;
        }

        public Builder addType(MediaType mediaType) {
            this.Types = mediaType.getValue() | this.Types;
            return this;
        }

        public MediaQuery build() {
            int i = this.Types;
            if (i == 0) {
                Log.e(LocalMediaManager.TAG, "Media Query need to contain at least one type");
                return null;
            }
            int i2 = this.Size;
            if (i2 <= 0) {
                this.Size = 1000;
                i2 = 1000;
            }
            int i3 = this.DepthLevel;
            if (i3 > 0 && this.DepthOriginalLevel == 0) {
                this.DepthOriginalLevel = i3;
            }
            return new MediaQuery(this.QueryId, i, this.Path, i2, this.Cursor, this.Sort, this.IsSortAscending, this.Filters, i3, this.DepthOriginalLevel, this.DepthSize, this.MediaId, this.DeviceId, this.ExternalHost);
        }

        public Builder recurse(String str) {
            this.Path = str;
            int i = this.DepthLevel - 1;
            this.DepthLevel = i;
            if (i == 0) {
                int value = this.Types & (MediaType.FOLDER.getValue() ^ -1);
                this.Types = value;
                if (value == 0) {
                    this.Types = MediaType.IMAGE.getValue() | MediaType.VIDEO.getValue();
                }
                int i2 = this.DepthSize;
                if (i2 == 0) {
                    i2 = this.Size;
                }
                this.Size = i2;
            }
            return this;
        }

        public Builder setCursor(String str) {
            this.Cursor = str;
            return this;
        }

        public Builder setExternalHost(String str) {
            this.ExternalHost = str;
            return this;
        }

        public Builder setFilters(String str) {
            this.Filters = str;
            return this;
        }

        public Builder setIsSortAscending(boolean z) {
            this.IsSortAscending = z;
            return this;
        }

        public Builder setSize(int i) {
            this.Size = i;
            return this;
        }

        public Builder setSort(MediaSort mediaSort) {
            this.Sort = mediaSort;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isSmartQuery() {
        if (MediaSmartQueryUtils.getSmartQueryType(this.Path) != null) {
            return true;
        }
        return false;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.QueryId;
            if (str != null) {
                jSONObject.put("queryId", str);
            }
            jSONObject.put("type", MediaQueryUtils.serializeMediaTypes(this.Types));
            jSONObject.put(NotificationUri.DEVICE, this.DeviceId);
            jSONObject.put("depth", this.QueryId);
            jSONObject.put("size", this.Size);
            String str2 = this.Cursor;
            if (str2 != null) {
                jSONObject.put("index", str2);
            }
            jSONObject.put("sort", this.Sort.toString().toLowerCase());
            jSONObject.put("order", this.IsSortAscending);
            String str3 = this.Path;
            if (str3 != null) {
                jSONObject.put("path", str3);
            }
            int i = this.DepthSize;
            if (i > 0) {
                jSONObject.put("depthsize", i);
            }
            String str4 = this.Filters;
            if (str4 != null) {
                jSONObject.put("filters", str4);
            }
            String str5 = this.MediaId;
            if (str5 != null) {
                jSONObject.put("mediaId", str5);
            }
            return jSONObject;
        } catch (JSONException unused) {
            Log.e(LocalMediaManager.TAG, "Could not serialize to JSON query");
            return null;
        }
    }

    public String toString() {
        return toJSON().toString();
    }

    public MediaQuery(String str, int i, String str2, int i2, String str3, MediaSort mediaSort, boolean z, String str4, int i3, int i4, int i5, String str5, int i6, String str6) {
        this.QueryId = str;
        this.Types = i;
        this.Size = i2;
        this.Cursor = str3;
        this.Sort = mediaSort;
        this.IsSortAscending = z;
        this.Filters = str4;
        this.DepthLevel = i3;
        this.DepthOriginalLevel = i4;
        this.DepthSize = i5;
        this.DeviceId = i6;
        this.MediaId = str5;
        this.ExternalHost = str6;
        this.Path = i6 == 0 ? MediaQueryUtils.normalizePath(str2) : str2;
    }
}
