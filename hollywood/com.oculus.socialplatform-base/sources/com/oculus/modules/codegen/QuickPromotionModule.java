package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.appmanager.assets.database.assetcontract.AssetContract;
import com.oculus.horizon.logging.LoggingValues;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class QuickPromotionModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "QuickPromotionModule";

    public static class QPAction extends NativeModuleParcel {
        public boolean dismiss_promotion;
        public double limit;
        public String title_text;
        public String url;

        public static final QPAction makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPAction qPAction = new QPAction();
            qPAction.setFromJSONObject(jSONObject);
            return qPAction;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("dismiss_promotion", this.dismiss_promotion);
                jSONObject.put("limit", this.limit);
                String str = this.title_text;
                if (str != null) {
                    jSONObject.put("title_text", str);
                }
                String str2 = this.url;
                if (str2 != null) {
                    jSONObject.put("url", str2);
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            this.dismiss_promotion = jSONObject.optBoolean("dismiss_promotion");
            this.limit = jSONObject.optDouble("limit", 0.0d);
            String str = null;
            if (jSONObject.isNull("title_text")) {
                optString = null;
            } else {
                optString = jSONObject.optString("title_text");
            }
            this.title_text = optString;
            if (!jSONObject.isNull("url")) {
                str = jSONObject.optString("url");
            }
            this.url = str;
        }
    }

    public enum QPApplication {
        HOME,
        LIVING_ROOM
    }

    public static class QPCreative extends NativeModuleParcel {
        public QPImage auto_size_image;
        public String content_text;
        public QPAction dismiss_action;
        public QPAction primary_action;
        public QPAction secondary_action;
        public String title_text;

        public static final QPCreative makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPCreative qPCreative = new QPCreative();
            qPCreative.setFromJSONObject(jSONObject);
            return qPCreative;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                QPImage qPImage = this.auto_size_image;
                if (qPImage != null) {
                    jSONObject.put("auto_size_image", qPImage.convertToJSONObject());
                }
                String str = this.content_text;
                if (str != null) {
                    jSONObject.put("content_text", str);
                }
                QPAction qPAction = this.dismiss_action;
                if (qPAction != null) {
                    jSONObject.put("dismiss_action", qPAction.convertToJSONObject());
                }
                QPAction qPAction2 = this.primary_action;
                if (qPAction2 != null) {
                    jSONObject.put(LoggingValues.PDP_TAP_TARGET.PRIMARY_ACTION, qPAction2.convertToJSONObject());
                }
                QPAction qPAction3 = this.secondary_action;
                if (qPAction3 != null) {
                    jSONObject.put("secondary_action", qPAction3.convertToJSONObject());
                }
                String str2 = this.title_text;
                if (str2 != null) {
                    jSONObject.put("title_text", str2);
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            QPImage makeFromJSONObject;
            String optString;
            QPAction makeFromJSONObject2;
            QPAction makeFromJSONObject3;
            QPAction makeFromJSONObject4;
            String str = null;
            if (jSONObject.isNull("auto_size_image")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = QPImage.makeFromJSONObject(jSONObject.optJSONObject("auto_size_image"));
            }
            this.auto_size_image = makeFromJSONObject;
            if (jSONObject.isNull("content_text")) {
                optString = null;
            } else {
                optString = jSONObject.optString("content_text");
            }
            this.content_text = optString;
            if (jSONObject.isNull("dismiss_action")) {
                makeFromJSONObject2 = null;
            } else {
                makeFromJSONObject2 = QPAction.makeFromJSONObject(jSONObject.optJSONObject("dismiss_action"));
            }
            this.dismiss_action = makeFromJSONObject2;
            if (jSONObject.isNull(LoggingValues.PDP_TAP_TARGET.PRIMARY_ACTION)) {
                makeFromJSONObject3 = null;
            } else {
                makeFromJSONObject3 = QPAction.makeFromJSONObject(jSONObject.optJSONObject(LoggingValues.PDP_TAP_TARGET.PRIMARY_ACTION));
            }
            this.primary_action = makeFromJSONObject3;
            if (jSONObject.isNull("secondary_action")) {
                makeFromJSONObject4 = null;
            } else {
                makeFromJSONObject4 = QPAction.makeFromJSONObject(jSONObject.optJSONObject("secondary_action"));
            }
            this.secondary_action = makeFromJSONObject4;
            if (!jSONObject.isNull("title_text")) {
                str = jSONObject.optString("title_text");
            }
            this.title_text = str;
        }
    }

    public static class QPFilter extends NativeModuleParcel {
        public List<QPParameter> extra_datas;
        public String filter_type;
        public String unknown_action;
        public QPParameter value;

        public static final QPFilter makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPFilter qPFilter = new QPFilter();
            qPFilter.setFromJSONObject(jSONObject);
            return qPFilter;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("extra_datas", NativeModuleParcel.convertParcelListToJSONArray(this.extra_datas));
                jSONObject.put("filter_type", this.filter_type);
                jSONObject.put("unknown_action", this.unknown_action);
                QPParameter qPParameter = this.value;
                if (qPParameter != null) {
                    jSONObject.put("value", qPParameter.convertToJSONObject());
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            QPParameter makeFromJSONObject;
            this.extra_datas = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("extra_datas"), QPParameter.class);
            this.filter_type = jSONObject.optString("filter_type");
            this.unknown_action = jSONObject.optString("unknown_action");
            if (jSONObject.isNull("value")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = QPParameter.makeFromJSONObject(jSONObject.optJSONObject("value"));
            }
            this.value = makeFromJSONObject;
        }
    }

    public static class QPImage extends NativeModuleParcel {
        public String dimensionless_cache_key;
        public double height;
        public boolean is_silhouette;
        public String mime_type;
        public String name;
        public double scale;
        public String uri;
        public double width;

        public static final QPImage makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPImage qPImage = new QPImage();
            qPImage.setFromJSONObject(jSONObject);
            return qPImage;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("dimensionless_cache_key", this.dimensionless_cache_key);
                jSONObject.put("height", this.height);
                jSONObject.put("is_silhouette", this.is_silhouette);
                jSONObject.put("mime_type", this.mime_type);
                jSONObject.put("name", this.name);
                jSONObject.put("scale", this.scale);
                jSONObject.put("uri", this.uri);
                jSONObject.put("width", this.width);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.dimensionless_cache_key = jSONObject.optString("dimensionless_cache_key");
            this.height = jSONObject.optDouble("height", 0.0d);
            this.is_silhouette = jSONObject.optBoolean("is_silhouette");
            this.mime_type = jSONObject.optString("mime_type");
            this.name = jSONObject.optString("name");
            this.scale = jSONObject.optDouble("scale", 0.0d);
            this.uri = jSONObject.optString("uri");
            this.width = jSONObject.optDouble("width", 0.0d);
        }
    }

    public static class QPParameter extends NativeModuleParcel {
        public Boolean bool_value;
        public String color_value;
        public Double float_value;
        public Double int_value;
        public String name;
        public boolean required;
        public String string_value;
        public String uri_value;

        public static final QPParameter makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPParameter qPParameter = new QPParameter();
            qPParameter.setFromJSONObject(jSONObject);
            return qPParameter;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("bool_value", this.bool_value);
                jSONObject.put("color_value", this.color_value);
                jSONObject.put("float_value", this.float_value);
                jSONObject.put("int_value", this.int_value);
                jSONObject.put("name", this.name);
                jSONObject.put(AssetContract.AssetTableColumns.REQUIRED, this.required);
                jSONObject.put("string_value", this.string_value);
                jSONObject.put("uri_value", this.uri_value);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            Boolean valueOf;
            String optString;
            Double valueOf2;
            Double valueOf3;
            String optString2;
            String str = null;
            if (jSONObject.isNull("bool_value")) {
                valueOf = null;
            } else {
                valueOf = Boolean.valueOf(jSONObject.optBoolean("bool_value"));
            }
            this.bool_value = valueOf;
            if (jSONObject.isNull("color_value")) {
                optString = null;
            } else {
                optString = jSONObject.optString("color_value");
            }
            this.color_value = optString;
            if (jSONObject.isNull("float_value")) {
                valueOf2 = null;
            } else {
                valueOf2 = Double.valueOf(jSONObject.optDouble("float_value", 0.0d));
            }
            this.float_value = valueOf2;
            if (jSONObject.isNull("int_value")) {
                valueOf3 = null;
            } else {
                valueOf3 = Double.valueOf(jSONObject.optDouble("int_value", 0.0d));
            }
            this.int_value = valueOf3;
            this.name = jSONObject.optString("name");
            this.required = jSONObject.optBoolean(AssetContract.AssetTableColumns.REQUIRED);
            if (jSONObject.isNull("string_value")) {
                optString2 = null;
            } else {
                optString2 = jSONObject.optString("string_value");
            }
            this.string_value = optString2;
            if (!jSONObject.isNull("uri_value")) {
                str = jSONObject.optString("uri_value");
            }
            this.uri_value = str;
        }
    }

    public static class QPPromotion extends NativeModuleParcel {
        public boolean bypass_surface_delay;
        public QPPromotionContextual_filters contextual_filters;
        public boolean is_holdout;
        public String logging_data;
        public double max_impressions;
        public List<QPCreative> promotion_creatives;
        public String promotion_id;
        public QPTemplate promotion_template;
        public double surface_id;
        public List<String> triggers;

        public static final QPPromotion makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotion qPPromotion = new QPPromotion();
            qPPromotion.setFromJSONObject(jSONObject);
            return qPPromotion;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("bypass_surface_delay", this.bypass_surface_delay);
                jSONObject.put("contextual_filters", this.contextual_filters.convertToJSONObject());
                jSONObject.put("is_holdout", this.is_holdout);
                jSONObject.put("logging_data", this.logging_data);
                jSONObject.put("max_impressions", this.max_impressions);
                jSONObject.put("promotion_creatives", NativeModuleParcel.convertParcelListToJSONArray(this.promotion_creatives));
                jSONObject.put("promotion_id", this.promotion_id);
                jSONObject.put("promotion_template", this.promotion_template.convertToJSONObject());
                jSONObject.put("surface_id", this.surface_id);
                jSONObject.put("triggers", NativeModuleParcel.convertStringListToJSONArray(this.triggers));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.bypass_surface_delay = jSONObject.optBoolean("bypass_surface_delay");
            this.contextual_filters = QPPromotionContextual_filters.makeFromJSONObject(jSONObject.optJSONObject("contextual_filters"));
            this.is_holdout = jSONObject.optBoolean("is_holdout");
            this.logging_data = jSONObject.optString("logging_data");
            this.max_impressions = jSONObject.optDouble("max_impressions", 0.0d);
            this.promotion_creatives = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("promotion_creatives"), QPCreative.class);
            this.promotion_id = jSONObject.optString("promotion_id");
            this.promotion_template = QPTemplate.makeFromJSONObject(jSONObject.optJSONObject("promotion_template"));
            this.surface_id = jSONObject.optDouble("surface_id", 0.0d);
            this.triggers = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("triggers"));
        }
    }

    public static class QPPromotionContextual_filters extends NativeModuleParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClauses> clauses;
        public List<QPFilter> filters;

        public static final QPPromotionContextual_filters makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotionContextual_filters qPPromotionContextual_filters = new QPPromotionContextual_filters();
            qPPromotionContextual_filters.setFromJSONObject(jSONObject);
            return qPPromotionContextual_filters;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("clause_type", this.clause_type);
                List<QPFilter> list = this.filters;
                if (list != null) {
                    jSONObject.put("filters", NativeModuleParcel.convertParcelListToJSONArray(list));
                }
                List<QPPromotionContextual_filtersClauses> list2 = this.clauses;
                if (list2 != null) {
                    jSONObject.put("clauses", NativeModuleParcel.convertParcelListToJSONArray(list2));
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            ArrayList convertJSONArrayToParcelList;
            ArrayList arrayList = null;
            if (jSONObject.isNull("clause_type")) {
                optString = null;
            } else {
                optString = jSONObject.optString("clause_type");
            }
            this.clause_type = optString;
            if (jSONObject.isNull("filters")) {
                convertJSONArrayToParcelList = null;
            } else {
                convertJSONArrayToParcelList = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("filters"), QPFilter.class);
            }
            this.filters = convertJSONArrayToParcelList;
            if (!jSONObject.isNull("clauses")) {
                arrayList = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("clauses"), QPPromotionContextual_filtersClauses.class);
            }
            this.clauses = arrayList;
        }
    }

    public static class QPPromotionContextual_filtersClauses extends NativeModuleParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClausesClauses> clauses;
        public List<QPFilter> filters;

        public static final QPPromotionContextual_filtersClauses makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotionContextual_filtersClauses qPPromotionContextual_filtersClauses = new QPPromotionContextual_filtersClauses();
            qPPromotionContextual_filtersClauses.setFromJSONObject(jSONObject);
            return qPPromotionContextual_filtersClauses;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("clause_type", this.clause_type);
                List<QPFilter> list = this.filters;
                if (list != null) {
                    jSONObject.put("filters", NativeModuleParcel.convertParcelListToJSONArray(list));
                }
                List<QPPromotionContextual_filtersClausesClauses> list2 = this.clauses;
                if (list2 != null) {
                    jSONObject.put("clauses", NativeModuleParcel.convertParcelListToJSONArray(list2));
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            ArrayList convertJSONArrayToParcelList;
            ArrayList arrayList = null;
            if (jSONObject.isNull("clause_type")) {
                optString = null;
            } else {
                optString = jSONObject.optString("clause_type");
            }
            this.clause_type = optString;
            if (jSONObject.isNull("filters")) {
                convertJSONArrayToParcelList = null;
            } else {
                convertJSONArrayToParcelList = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("filters"), QPFilter.class);
            }
            this.filters = convertJSONArrayToParcelList;
            if (!jSONObject.isNull("clauses")) {
                arrayList = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("clauses"), QPPromotionContextual_filtersClausesClauses.class);
            }
            this.clauses = arrayList;
        }
    }

    public static class QPPromotionContextual_filtersClausesClauses extends NativeModuleParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClausesClausesClauses> clauses;
        public List<QPFilter> filters;

        public static final QPPromotionContextual_filtersClausesClauses makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotionContextual_filtersClausesClauses qPPromotionContextual_filtersClausesClauses = new QPPromotionContextual_filtersClausesClauses();
            qPPromotionContextual_filtersClausesClauses.setFromJSONObject(jSONObject);
            return qPPromotionContextual_filtersClausesClauses;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("clause_type", this.clause_type);
                List<QPFilter> list = this.filters;
                if (list != null) {
                    jSONObject.put("filters", NativeModuleParcel.convertParcelListToJSONArray(list));
                }
                List<QPPromotionContextual_filtersClausesClausesClauses> list2 = this.clauses;
                if (list2 != null) {
                    jSONObject.put("clauses", NativeModuleParcel.convertParcelListToJSONArray(list2));
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            ArrayList convertJSONArrayToParcelList;
            ArrayList arrayList = null;
            if (jSONObject.isNull("clause_type")) {
                optString = null;
            } else {
                optString = jSONObject.optString("clause_type");
            }
            this.clause_type = optString;
            if (jSONObject.isNull("filters")) {
                convertJSONArrayToParcelList = null;
            } else {
                convertJSONArrayToParcelList = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("filters"), QPFilter.class);
            }
            this.filters = convertJSONArrayToParcelList;
            if (!jSONObject.isNull("clauses")) {
                arrayList = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("clauses"), QPPromotionContextual_filtersClausesClausesClauses.class);
            }
            this.clauses = arrayList;
        }
    }

    public static class QPPromotionContextual_filtersClausesClausesClauses extends NativeModuleParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClausesClausesClausesClauses> clauses;
        public List<QPFilter> filters;

        public static final QPPromotionContextual_filtersClausesClausesClauses makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotionContextual_filtersClausesClausesClauses qPPromotionContextual_filtersClausesClausesClauses = new QPPromotionContextual_filtersClausesClausesClauses();
            qPPromotionContextual_filtersClausesClausesClauses.setFromJSONObject(jSONObject);
            return qPPromotionContextual_filtersClausesClausesClauses;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("clause_type", this.clause_type);
                List<QPFilter> list = this.filters;
                if (list != null) {
                    jSONObject.put("filters", NativeModuleParcel.convertParcelListToJSONArray(list));
                }
                List<QPPromotionContextual_filtersClausesClausesClausesClauses> list2 = this.clauses;
                if (list2 != null) {
                    jSONObject.put("clauses", NativeModuleParcel.convertParcelListToJSONArray(list2));
                }
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            ArrayList convertJSONArrayToParcelList;
            ArrayList arrayList = null;
            if (jSONObject.isNull("clause_type")) {
                optString = null;
            } else {
                optString = jSONObject.optString("clause_type");
            }
            this.clause_type = optString;
            if (jSONObject.isNull("filters")) {
                convertJSONArrayToParcelList = null;
            } else {
                convertJSONArrayToParcelList = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("filters"), QPFilter.class);
            }
            this.filters = convertJSONArrayToParcelList;
            if (!jSONObject.isNull("clauses")) {
                arrayList = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("clauses"), QPPromotionContextual_filtersClausesClausesClausesClauses.class);
            }
            this.clauses = arrayList;
        }
    }

    public static class QPPromotionContextual_filtersClausesClausesClausesClauses extends NativeModuleParcel {
        public String clause_type;

        public static final QPPromotionContextual_filtersClausesClausesClausesClauses makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotionContextual_filtersClausesClausesClausesClauses qPPromotionContextual_filtersClausesClausesClausesClauses = new QPPromotionContextual_filtersClausesClausesClausesClauses();
            qPPromotionContextual_filtersClausesClausesClausesClauses.setFromJSONObject(jSONObject);
            return qPPromotionContextual_filtersClausesClausesClausesClauses;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("clause_type", this.clause_type);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.clause_type = jSONObject.optString("clause_type");
        }
    }

    public static class QPTemplate extends NativeModuleParcel {
        public String name;
        public List<QPParameter> parameters;

        public static final QPTemplate makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPTemplate qPTemplate = new QPTemplate();
            qPTemplate.setFromJSONObject(jSONObject);
            return qPTemplate;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("name", this.name);
                jSONObject.put("parameters", NativeModuleParcel.convertParcelListToJSONArray(this.parameters));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.name = jSONObject.optString("name");
            this.parameters = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("parameters"), QPParameter.class);
        }
    }

    public static class QPTryToShowEvent extends NativeModuleParcel {
        public List<QPPromotion> promotions;
        public String trigger;

        public static final QPTryToShowEvent makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPTryToShowEvent qPTryToShowEvent = new QPTryToShowEvent();
            qPTryToShowEvent.setFromJSONObject(jSONObject);
            return qPTryToShowEvent;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("promotions", NativeModuleParcel.convertParcelListToJSONArray(this.promotions));
                jSONObject.put("trigger", this.trigger);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.promotions = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("promotions"), QPPromotion.class);
            this.trigger = jSONObject.optString("trigger");
        }
    }

    public abstract void disableSurfaceImpl(double d, Resolver<Void> resolver);

    public abstract void enableSurfaceImpl(double d, Resolver<Void> resolver);

    public abstract void initializeImpl(QPApplication qPApplication, boolean z, boolean z2, boolean z3, boolean z4, Resolver<Void> resolver);

    public abstract void markPromotionActionImpl(QPPromotion qPPromotion, String str, String str2, Resolver<Void> resolver);

    public abstract void markPromotionExposedImpl(QPPromotion qPPromotion, String str, Resolver<Void> resolver);

    public abstract void markPromotionHoldoutExposedImpl(QPPromotion qPPromotion, String str, Resolver<Void> resolver);

    public abstract void markPromotionShownImpl(QPPromotion qPPromotion, String str, Resolver<Void> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public abstract void refetchAllSurfacesImpl(Resolver<Void> resolver);

    public abstract void refetchSurfaceImpl(double d, Resolver<Void> resolver);

    public abstract void setAccessTokenImpl(String str, Resolver<Void> resolver);

    public void shutdownModule() {
    }

    public abstract void signalTriggerImpl(String str, Resolver<Void> resolver);

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("disableSurface", "+dii"));
        arrayList.add(new Pair("enableSurface", "+dii"));
        arrayList.add(new Pair("initialize", "+sbbbbii"));
        arrayList.add(new Pair("markPromotionAction", "+jssii"));
        arrayList.add(new Pair("markPromotionExposed", "+jsii"));
        arrayList.add(new Pair("markPromotionHoldoutExposed", "+jsii"));
        arrayList.add(new Pair("markPromotionShown", "+jsii"));
        arrayList.add(new Pair("refetchAllSurfaces", "+ii"));
        arrayList.add(new Pair("refetchSurface", "+dii"));
        arrayList.add(new Pair("setAccessToken", "+sii"));
        arrayList.add(new Pair("signalTrigger", "+sii"));
        return arrayList;
    }

    public final void emitOnTryToShow(QPTryToShowEvent qPTryToShowEvent) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "QuickPromotionModule_onTryToShow", String.valueOf(qPTryToShowEvent.convertToJSONObject()));
    }

    public final void disableSurface(double d, int i, int i2) {
        disableSurfaceImpl(d, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void enableSurface(double d, int i, int i2) {
        enableSurfaceImpl(d, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final String getModuleName() {
        return "QuickPromotionModule";
    }

    public final void initialize(String str, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2) {
        initializeImpl(QPApplication.valueOf(str), z, z2, z3, z4, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void markPromotionAction(JSONObject jSONObject, String str, String str2, int i, int i2) {
        markPromotionActionImpl(QPPromotion.makeFromJSONObject(jSONObject), str, str2, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void markPromotionExposed(JSONObject jSONObject, String str, int i, int i2) {
        markPromotionExposedImpl(QPPromotion.makeFromJSONObject(jSONObject), str, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void markPromotionHoldoutExposed(JSONObject jSONObject, String str, int i, int i2) {
        markPromotionHoldoutExposedImpl(QPPromotion.makeFromJSONObject(jSONObject), str, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void markPromotionShown(JSONObject jSONObject, String str, int i, int i2) {
        markPromotionShownImpl(QPPromotion.makeFromJSONObject(jSONObject), str, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void refetchAllSurfaces(int i, int i2) {
        refetchAllSurfacesImpl(ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void refetchSurface(double d, int i, int i2) {
        refetchSurfaceImpl(d, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void setAccessToken(String str, int i, int i2) {
        setAccessTokenImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void signalTrigger(String str, int i, int i2) {
        signalTriggerImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }
}
