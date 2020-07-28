// Custom
#include "native.h"

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

jint JNI(main) {
	LOG("main()");

	return RET_OK;
}

jint JNI(onSurfaceCreated, jint width, jint height) {
	m_heightmap = Heightmap::getInstance();

	if (m_heightmap != NULL) {
		LOG("onSurfaceCreated(jWidth='%d', jHeight='%d')", width, height);

		return RET_OK;
	} else {
		return RET_ERROR;
	}
}
jint JNI(onSurfaceChanged, jint width, jint height) {
	if (m_heightmap != NULL) {
		LOG("onSurfaceChanged(jWidth='%d', jHeight='%d')", width, height);

		return m_heightmap->onSurfaceChanged(width, height);
	} else {
		return RET_ERROR;
	}
}
jint JNI(surfaceDestroyed) {
	if (m_heightmap != NULL) {
		LOG("surfaceDestroyed()");

		Heightmap::destroyInstance();

		return RET_OK;
	} else {
		return RET_ERROR;
	}
}

jint JNI(initArray, jintArray arr, jint width, jint height, jfloat scale) {
	if (m_heightmap != NULL) {
		LOG("initArray(jintArray='%d', jWidth='%d', jHeight='%d', jScale='%f')",
				env->GetArrayLength(arr), width, height, scale);

		return m_heightmap->initArray(importIntArray(env, arr), width, height,
				scale);
	} else {
		return RET_ERROR;
	}
}
jint JNI(onDrawFrame) {
	if (m_heightmap != NULL) {
		LOG("onDrawFrame()");

		return m_heightmap->onDrawFrame();
	} else {
		return RET_ERROR;
	}
}
jint JNI(toggleFlag, jint flag) {
	if (m_heightmap != NULL) {
		LOG("toggleFlag(jFlag='%d')", flag);

		return m_heightmap->toggleFlag(flag);
	} else {
		return RET_ERROR;
	}
}
