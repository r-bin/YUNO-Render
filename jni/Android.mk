LOCAL_PATH:= $(call my-dir)

MY_DIR_SRC       := src
MY_DIR_INC       := inc
MY_DIR_LIB       := lib

include $(CLEAR_VARS)

MY_TARGET        := native

LOCAL_MODULE     := $(MY_TARGET)
#LOCAL_SRC_FILES  := $(wildcard $(MY_DIR_SRC)/*.cpp)
LOCAL_SRC_FILES  := $(MY_DIR_SRC)/$(MY_TARGET).cpp \
                    $(MY_DIR_SRC)/heightmap/Polygon.cpp \
                    $(MY_DIR_SRC)/heightmap/Heightmap.cpp
LOCAL_C_INCLUDES := $(MY_DIR_INC)
LOCAL_CFLAGS     := -Wall
LOCAL_LDLIBS     := -llog \
                    -lGLESv1_CM

include $(BUILD_SHARED_LIBRARY)