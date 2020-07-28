// Custom
#include "Heightmap.h"

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

Heightmap::Heightmap() {
	LOG("Heightmap::Heightmap()");

	m_width = 0;
	m_height = 0;

	m_heightmap = NULL;
	setSize(0, 0, 1 / 3);
}
Heightmap::~Heightmap() {
	LOG("Heightmap::~Heightmap()");

	if (m_heightmap != NULL) {
		delete[] m_heightmap;
		m_heightmap = NULL;
	}
	setSize(0, 0, 0);

	for (int i = 0; i < (int) m_polygon.size(); i++) {
		delete m_polygon.at(i);
	}
	m_polygon.clear();
}

int Heightmap::initArray(int *heightmap, int width, int height, float scale) {
	LOG(
			"Heightmap::initArray(heightmap='%d', width='%d', height='%d', scale='%f')",
			width * height, width, height, scale);

	m_width = width;
	m_height = height;

	if (m_heightmap != 0) {
		delete[] m_heightmap;
		m_heightmap = NULL;
	}
	m_heightmap = heightmap;
	setSize(width, height, scale);

	for (int i = 0; i < height - 1; i++) {
		m_polygon.push_back(new Polygon(0, i, heightmap, width, scale));
	}

	return RET_OK;
}
int Heightmap::onDrawFrame() {
	LOG("Heightmap::onDrawFrame()");

	return draw();
}
int Heightmap::toggleFlag(int flag) {
	LOG("Heightmap::toggleFlag(flag='%d')", flag);

	for (int i = 0; i < (int) m_polygon.size(); i++) {
		m_polygon.at(i)->toggleFlag(flag);
	}

	return RET_OK;
}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

int Heightmap::draw() {
	glEnableClientState(GL_VERTEX_ARRAY);

	drawPolygon();
	drawBoundingBox();

	glDisableClientState(GL_VERTEX_ARRAY);

	return RET_OK;
}
int Heightmap::drawPolygon() {
	if (m_polygon.size() > 0) {
		for (int i = 0; i < (int) m_polygon.size(); i++) {
			m_polygon.at(i)->draw();
		}

		return RET_OK;
	} else {
		return RET_ERROR;
	}
}
int Heightmap::drawBoundingBox() {
	GLfloat texture[POLYGON_SIZE_RECT] = POLYGON_CREATE_RECT(m_width, m_height);

	glEnable(GL_BLEND);
	glEnable(GL_ALPHA_BITS);

	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

	glColor4x(FCOLOR(0.5), FCOLOR(0.5), FCOLOR(0.5), FCOLOR(0.1));
	glVertexPointer(3, GL_FLOAT, 0, texture);
	glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);

	glDisable(GL_ALPHA_BITS);
	glDisable(GL_BLEND);

	return RET_OK;
}
