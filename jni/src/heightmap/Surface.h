#ifndef SURFACE_H_
#define SURFACE_H_

///////////////////////////////////////////////////////////////////////////////////////////////

#include <inc/JNI.h>

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

template<typename I, typename F>
class Surface {
private:
	I m_width;
	I m_height;

	F m_scale;

protected:
	Surface() {
	}
	~Surface() {
	}

public:
	int onSurfaceCreated(I width, I height);
	int onSurfaceChanged(I width, I height);
	int surfaceDestroyed();

protected:
	void setSize(I width, I height) {
		m_width = width;
		m_height = height;
	}
	void setSize(I width, I height, F scale) {
		setSize(width, scale);
		setScale(scale);
	}

	void setScale(F scale) {
		m_scale = scale;
	}

public:
	I getWidth() const {
		return m_width;
	}
	I getHeight() const {
		return m_height;
	}

	F getScale() const {
		return m_scale;
	}
};

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

template<typename I, typename F> int Surface<I, F>::onSurfaceCreated(I width,
		I height) {
	LOG("Heightmap::onSurfaceCreated(width='%d', height='%d')", width, height);

	setSize(width, height);

	return RET_OK;
}
template<typename I, typename F> int Surface<I, F>::onSurfaceChanged(I width,
		I height) {
	LOG("Heightmap::onSurfaceChanged(width='%d', height='%d')", width, height);

	setSize(width, height);

	return RET_OK;
}
template<typename I, typename F> int Surface<I, F>::surfaceDestroyed() {
	LOG("Heightmap::surfaceDestroyed()");

	setSize(0, 0);

	return RET_OK;
}

///////////////////////////////////////////////////////////////////////////////////////////////

#endif /* SURFACE_H_ */
