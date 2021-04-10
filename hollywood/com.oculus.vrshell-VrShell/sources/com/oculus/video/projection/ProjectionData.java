package com.oculus.video.projection;

import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ProjectionData {
    ProjectionType approxMeshProjection = ProjectionType.FISHEYE;
    long cubemapPadding;
    FoVProperties leftFovProperties = null;
    Pose pose = null;
    @NonNull
    ProjectionType projectionType = ProjectionType.UNKNOWN;
    FoVProperties rightFovProperties = null;

    /* access modifiers changed from: package-private */
    public abstract void parse(byte[] bArr) throws IllegalArgumentException;

    class Vertex {
        final float u;
        final float v;
        final float x;
        final float y;
        final float z;

        Vertex(float f, float f2, float f3, float f4, float f5) {
            float sqrt = (float) Math.sqrt((double) ((f * f) + (f2 * f2) + (f3 * f3)));
            this.x = f / sqrt;
            this.y = f2 / sqrt;
            this.z = f3 / sqrt;
            this.u = f4;
            this.v = f5;
        }
    }

    public class Pose {
        private final float pitchDegrees;
        private final float rollDegrees;
        private final float yawDegrees;

        Pose(float f, float f2, float f3) {
            this.yawDegrees = f;
            this.pitchDegrees = f2;
            this.rollDegrees = f3;
        }

        public float getYawDegrees() {
            return this.yawDegrees;
        }

        public float getPitchDegrees() {
            return this.pitchDegrees;
        }

        public float getRollDegrees() {
            return this.rollDegrees;
        }
    }

    public class FoVProperties {
        public final float fovXDegrees;
        public final float fovYDegrees;
        public final float horizonDegrees;
        public final float meridianDegrees;

        protected FoVProperties(float f, float f2, float f3, float f4) {
            this.fovXDegrees = f;
            this.fovYDegrees = f2;
            this.meridianDegrees = f3;
            this.horizonDegrees = f4;
        }
    }

    public FoVProperties getLeftFovProperties() {
        return this.leftFovProperties;
    }

    public FoVProperties getRightFovProperties() {
        return this.rightFovProperties;
    }

    @NonNull
    public ProjectionType getProjectionType() {
        return this.projectionType;
    }

    public Pose getPose() {
        return this.pose;
    }

    public long getCubemapPadding() {
        return this.cubemapPadding;
    }

    public String getVR180MeshData() {
        return "{\"ApproximatedProjection\" : \"" + this.approxMeshProjection.videoLayout.toUpperCase() + "\"}";
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0253  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x02d3  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x02da  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x02e6  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x02ff  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void estimateProjectionMetadataFromMesh(com.oculus.video.projection.ProjectionData.Vertex[] r48) {
        /*
        // Method dump skipped, instructions count: 796
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.projection.ProjectionData.estimateProjectionMetadataFromMesh(com.oculus.video.projection.ProjectionData$Vertex[]):void");
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.projectionType != ProjectionType.UNKNOWN) {
            jSONObject.put("Projection", this.projectionType.videoLayout.toUpperCase());
        }
        if (this.pose != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("Yaw", (double) this.pose.getYawDegrees());
            jSONObject2.put("Pitch", (double) this.pose.getPitchDegrees());
            jSONObject2.put("Roll", (double) this.pose.getRollDegrees());
            jSONObject.put("Pose", jSONObject2);
        }
        FoVProperties foVProperties = this.leftFovProperties;
        if (foVProperties != null) {
            jSONObject.put("FovX", (double) foVProperties.fovXDegrees);
            jSONObject.put("FovY", (double) this.leftFovProperties.fovYDegrees);
            jSONObject.put("Meridian", (double) this.leftFovProperties.meridianDegrees);
            jSONObject.put("Horizon", (double) this.leftFovProperties.horizonDegrees);
        }
        if (this.rightFovProperties != null) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("FovX", (double) this.rightFovProperties.fovXDegrees);
            jSONObject3.put("FovY", (double) this.rightFovProperties.fovYDegrees);
            jSONObject3.put("Meridian", (double) this.rightFovProperties.meridianDegrees);
            jSONObject3.put("Horizon", (double) this.rightFovProperties.horizonDegrees);
            jSONObject.put("RightFov", jSONObject3);
        }
        if (this.projectionType == ProjectionType.VR180) {
            jSONObject.put("VR180MeshData", getVR180MeshData());
        }
        return jSONObject;
    }
}
