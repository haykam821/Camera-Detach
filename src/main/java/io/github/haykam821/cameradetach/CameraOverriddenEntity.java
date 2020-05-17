package io.github.haykam821.cameradetach;

public interface CameraOverriddenEntity {
	float getCameraPitch();
	float getCameraYaw();

	void setCameraPitch(float pitch);
	void setCameraYaw(float yaw);
}
