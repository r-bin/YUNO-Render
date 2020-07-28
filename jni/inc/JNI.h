#ifndef JNI2_H_
#define JNI2_H_

///////////////////////////////////////////////////////////////////////////////////////////////

// ANDROID
#include <jni.h>
#include <inc/Debug.h>

///////////////////////////////////////////////////////////////////////////////////////////////

#define RET_OK (0)
#define RET_ERROR (-1)

#define FCOLOR(F) ((F) > 1 ? 1 : ((F) < 0 ? 0 : ((F) * 0x10000)))

#define VERTICES_PER_POLYGON (3)
#define POLYGON_SIZE_RECT (4 * VERTICES_PER_POLYGON)
#define POLYGON_SIZE_N(N) ((N) * VERTICES_PER_POLYGON)

#define POLYGON_CREATE_RECT(W,H) {\
		(0),(H),(0), \
		(0),(0),(0), \
		(W),(H),(0), \
		(W),(0),(0), \
	}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

inline int* importIntArray(JNIEnv *env, jintArray arr) {
	int max = env->GetArrayLength(arr);
	jint buf[max];
	env->GetIntArrayRegion(arr, 0, max, buf);

	int *array = new int[max];
	for (int i = 0; i < max; ++i) {
		array[i] = buf[i];
	}

	return array;
}

/*
 inline GLfloat* createRect(int width, int height) {
 GLfloat texture[POLYGON_SIZE_RECT] = { 0, height, 0, // top-left
 0, 0, 0, // bottom-left
 width, height, 0, // top-right
 width, 0, 0, // bottom-right
 };

 return texture;
 }
 */

///////////////////////////////////////////////////////////////////////////////////////////////
#endif /* JNI2_H_ */
