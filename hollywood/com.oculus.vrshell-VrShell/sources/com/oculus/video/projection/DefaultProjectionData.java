package com.oculus.video.projection;

import com.oculus.video.projection.ProjectionData;
import org.json.JSONException;
import org.json.JSONObject;

public class DefaultProjectionData extends ProjectionData {
    /* access modifiers changed from: package-private */
    @Override // com.oculus.video.projection.ProjectionData
    public void parse(byte[] bArr) throws IllegalArgumentException {
    }

    DefaultProjectionData(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            if (jSONObject.has("Projection")) {
                String string = jSONObject.getString("Projection");
                this.projectionType = ProjectionType.fromFBProjection(string);
                if (this.projectionType == ProjectionType.UNKNOWN) {
                    this.projectionType = ProjectionType.fromVideoLayout(string);
                }
            }
            if (jSONObject.has("Pose")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("Pose");
                this.pose = new ProjectionData.Pose(jSONObject2.has("Yaw") ? (float) jSONObject2.getDouble("Yaw") : 0.0f, jSONObject2.has("Pitch") ? (float) jSONObject2.getDouble("Pitch") : 0.0f, jSONObject2.has("Roll") ? (float) jSONObject2.getDouble("Roll") : 0.0f);
            }
            this.leftFovProperties = new ProjectionData.FoVProperties(jSONObject.has("FovX") ? (float) jSONObject.getDouble("FovX") : 360.0f, jSONObject.has("FovY") ? (float) jSONObject.getDouble("FovY") : 180.0f, jSONObject.has("Meridian") ? (float) jSONObject.getDouble("Meridian") : 0.0f, jSONObject.has("Horizon") ? (float) jSONObject.getDouble("Horizon") : 0.0f);
            if (jSONObject.has("RightFov")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("RightFov");
                this.rightFovProperties = new ProjectionData.FoVProperties(jSONObject3.has("FovX") ? (float) jSONObject3.getDouble("FovX") : 360.0f, jSONObject3.has("FovY") ? (float) jSONObject3.getDouble("FovY") : 180.0f, jSONObject3.has("Meridian") ? (float) jSONObject3.getDouble("Meridian") : 0.0f, jSONObject3.has("Horizon") ? (float) jSONObject3.getDouble("Horizon") : 0.0f);
            }
            if (this.projectionType == ProjectionType.VR180 && jSONObject.has("VR180MeshData")) {
                try {
                    JSONObject jSONObject4 = new JSONObject(jSONObject.getString("VR180MeshData"));
                    if (jSONObject4.has("ApproximatedProjection")) {
                        this.approxMeshProjection = ProjectionType.fromVideoLayout(jSONObject4.getString("ApproximatedProjection"));
                    }
                } catch (JSONException unused) {
                }
            }
        }
    }

    public DefaultProjectionData(ProjectionType projectionType) {
        this.leftFovProperties = new ProjectionData.FoVProperties(projectionType == ProjectionType.HALF_EQUIRECTANGULAR ? 180.0f : 360.0f, 180.0f, 0.0f, 0.0f);
        this.projectionType = projectionType;
    }

    public DefaultProjectionData(ProjectionType projectionType, float f, float f2) {
        this.leftFovProperties = new ProjectionData.FoVProperties(f, f2, 0.0f, 0.0f);
        this.projectionType = projectionType;
    }
}
