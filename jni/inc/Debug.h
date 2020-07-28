#ifndef DEBUG_H_
#define DEBUG_H_

///////////////////////////////////////////////////////////////////////////////////////////////

// ANDROID
#include <jni.h>
#include <android/log.h>

// STL
#include <string>

///////////////////////////////////////////////////////////////////////////////////////////////

#define DEBUG_ENABLE true
#define DEBUG_MACRO true
#define DEBUG_TAG "[JNI]"

#ifdef DEBUG_MACRO
#ifdef DEBUG_ENABLE
#define LLOG(LVL,FMT,...) __android_log_print((LVL),(DEBUG_TAG),FMT,##__VA_ARGS__);

#define LOG(FMT,...) LLOG(ANDROID_LOG_DEBUG,(FMT),##__VA_ARGS__);
#define ERR(FMT,...) LLOG(ANDROID_LOG_ERROR,(FMT),##__VA_ARGS__);
#else
#define LOG(FMT,...)
#define ERR(FMT,...)
#endif /* DEBUG_ENABLE */
#endif /* DEBUG_MACRO */

///////////////////////////////////////////////////////////////////////////////////////////////

using namespace std;

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

#ifndef DEBUG_MACRO
inline int LLOG(android_LogPriority prio, string fmt, ...) {
#ifdef DEBUG_ENABLE
	va_list arg;
	va_start(arg, fmt);

	return __android_log_vprint(prio, DEBUG_TAG, fmt.c_str(), arg);
#else
	return 0;
#endif /* DEBUG_ENABLE */
}
inline int LOG(string fmt, ...) {
	va_list arg;
	va_start(arg, fmt);

	return LLOG(ANDROID_LOG_DEBUG, fmt, arg);
}
inline int ERR(string fmt, ...) {
	va_list arg;
	va_start(arg, fmt);

	return LLOG(ANDROID_LOG_ERROR, fmt, arg);
}
#endif /* DEBUG_MACRO */

///////////////////////////////////////////////////////////////////////////////////////////////

#endif /* POLYGON_H_ */
