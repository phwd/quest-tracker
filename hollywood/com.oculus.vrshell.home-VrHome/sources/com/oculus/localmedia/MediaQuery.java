package com.oculus.localmedia;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaQuery {
    private static final int DEFAULT_PAGE_SIZE = 1000;
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

    private MediaQuery(String queryId, int types, String path, int size, String cursor, MediaSort sort, boolean isSortAscending, String filters, int depth, int depthOriginal, int depthSize, String mediaId, int deviceId, String externalHost) {
        this.QueryId = queryId;
        this.Types = types;
        this.Size = size;
        this.Cursor = cursor;
        this.Sort = sort;
        this.IsSortAscending = isSortAscending;
        this.Filters = filters;
        this.DepthLevel = depth;
        this.DepthOriginalLevel = depthOriginal;
        this.DepthSize = depthSize;
        this.DeviceId = deviceId;
        this.MediaId = mediaId;
        this.ExternalHost = externalHost;
        this.Path = this.DeviceId == 0 ? MediaQueryUtils.normalizePath(path) : path;
    }

    public static Builder builder() {
        return new Builder();
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            if (this.QueryId != null) {
                json.put("queryId", this.QueryId);
            }
            json.put("type", MediaQueryUtils.serializeMediaTypes(this.Types));
            json.put("device", this.DeviceId);
            json.put("depth", this.QueryId);
            json.put("size", this.Size);
            if (this.Cursor != null) {
                json.put("index", this.Cursor);
            }
            json.put("sort", this.Sort.toString().toLowerCase());
            json.put("order", this.IsSortAscending);
            if (this.Path != null) {
                json.put("path", this.Path);
            }
            if (this.DepthSize > 0) {
                json.put("depthsize", this.DepthSize);
            }
            if (this.Filters != null) {
                json.put("filters", this.Filters);
            }
            if (this.MediaId == null) {
                return json;
            }
            json.put("mediaId", this.MediaId);
            return json;
        } catch (JSONException e) {
            Log.e(LocalMediaManager.TAG, "Could not serialize to JSON query");
            return null;
        }
    }

    public boolean isSmartQuery() {
        return MediaSmartQueryUtils.getSmartQueryType(this.Path) != null;
    }

    public String toString() {
        return toJSON().toString();
    }

    public static class Builder {
        private String Cursor = null;
        private int DepthLevel = 0;
        private int DepthOriginalLevel = 0;
        private int DepthSize = 0;
        private int DeviceId = 0;
        private String ExternalHost = null;
        private String Filters = "";
        private boolean IsSortAscending = false;
        private String MediaId = "";
        private String Path = null;
        private String QueryId = "";
        private int Size = Integer.MAX_VALUE;
        private MediaSort Sort = MediaSort.NAME;
        private int Types;

        public static Builder fromJson(String jsonQuery) {
            Builder builder = new Builder();
            try {
                JSONObject queryObject = new JSONObject(jsonQuery);
                builder.QueryId = queryObject.optString("queryId", "");
                builder.Types = MediaQueryUtils.parseMediaTypes(queryObject.optString("type", ""));
                builder.DeviceId = queryObject.optInt("device", 0);
                builder.Path = queryObject.optString("path", null);
                builder.DepthLevel = queryObject.optInt("depth", 0);
                builder.DepthSize = queryObject.optInt("depthsize", 0);
                builder.Size = queryObject.optInt("size", 0);
                builder.Cursor = queryObject.optString("index", null);
                builder.Sort = MediaQueryUtils.parseSortType(queryObject.optString("sort", ""));
                builder.IsSortAscending = queryObject.optBoolean("order", queryObject.optBoolean("asc", true));
                builder.Filters = queryObject.optString("filters", "");
                builder.MediaId = queryObject.optString("mediaId", "");
                return builder;
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid query json : " + jsonQuery);
            }
        }

        public static Builder fromType(MediaType mediaType) {
            Builder builder = new Builder();
            builder.Types = mediaType.getValue();
            return builder;
        }

        public static Builder fromMedia(MediaType mediaType, String mediaId) {
            Builder builder = new Builder();
            builder.Types = mediaType.getValue();
            builder.MediaId = mediaId;
            return builder;
        }

        public static Builder fromQuery(MediaQuery query) {
            Builder builder = new Builder();
            builder.Types = query.Types;
            builder.Size = query.Size;
            builder.Cursor = query.Cursor;
            builder.Sort = query.Sort;
            builder.IsSortAscending = query.IsSortAscending;
            builder.Filters = query.Filters;
            builder.Path = query.Path;
            builder.DepthLevel = query.DepthLevel;
            builder.DepthOriginalLevel = query.DepthOriginalLevel;
            builder.DepthSize = query.DepthSize;
            builder.DeviceId = query.DeviceId;
            builder.MediaId = query.MediaId;
            return builder;
        }

        public Builder addType(MediaType mediaType) {
            this.Types |= mediaType.getValue();
            return this;
        }

        public Builder setSize(int size) {
            this.Size = size;
            return this;
        }

        public Builder setCursor(String cursor) {
            this.Cursor = cursor;
            return this;
        }

        public Builder setSort(MediaSort sort) {
            this.Sort = sort;
            return this;
        }

        public Builder setIsSortAscending(boolean isSortAscending) {
            this.IsSortAscending = isSortAscending;
            return this;
        }

        public Builder setFilters(String filters) {
            this.Filters = filters;
            return this;
        }

        public Builder setExternalHost(String externalHost) {
            this.ExternalHost = externalHost;
            return this;
        }

        public Builder recurse(String path) {
            this.Path = path;
            this.DepthLevel--;
            if (this.DepthLevel == 0) {
                this.Types &= MediaType.FOLDER.getValue() ^ -1;
                if (this.Types == 0) {
                    this.Types = MediaType.IMAGE.getValue() | MediaType.VIDEO.getValue();
                }
                this.Size = this.DepthSize != 0 ? this.DepthSize : this.Size;
            }
            return this;
        }

        public MediaQuery build() {
            if (this.Types == 0) {
                Log.e(LocalMediaManager.TAG, "Media Query need to contain at least one type");
                return null;
            }
            if (this.Size <= 0) {
                this.Size = MediaQuery.DEFAULT_PAGE_SIZE;
            }
            if (this.DepthLevel > 0 && this.DepthOriginalLevel == 0) {
                this.DepthOriginalLevel = this.DepthLevel;
            }
            return new MediaQuery(this.QueryId, this.Types, this.Path, this.Size, this.Cursor, this.Sort, this.IsSortAscending, this.Filters, this.DepthLevel, this.DepthOriginalLevel, this.DepthSize, this.MediaId, this.DeviceId, this.ExternalHost);
        }
    }
}
