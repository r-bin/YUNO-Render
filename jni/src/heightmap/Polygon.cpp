// Custom
#include "Polygon.h"

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

Polygon::Polygon(GLfloat x, GLfloat y, int array[], int width, float scale) {
	for (int i = 0; i <= 2; i++) {
		flag[i] = true;
	}

	N = 2 * width;

	GLfloat dz[2 * width];
	for (int i = 0; i < N; i += 2) {
		dz[i + 0] = array[(int) (i / 2 + (y + 0) * width)] * scale;
		dz[i + 1] = array[(int) (i / 2 + (y + 1) * width)] * scale;
	}

	vertex = new GLfloat[N * 3];
	for (int i = 0, j = 0; i < width; i++) {
		vertex[j++] = i; // X
		vertex[j++] = y + 0; // Y
		vertex[j++] = dz[i * 2 + 0] + 1; // Z

		vertex[j++] = i; // X
		vertex[j++] = y + 1; // Y
		vertex[j++] = dz[i * 2 + 1] + 1; // Z
	}

	color = new GLfloat[N * 4];
	for (int i = 0, j = 0; i < N; i++) {
		if (i % 3 != 0) {
			color[j++] = 0.5 - dz[i] / 255; // R
			color[j++] = 0.5 - dz[i] / 255; // G
			color[j++] = 0.5 - dz[i] / 255; // B
			color[j++] = 1.0; // A
		} else {
			color[j++] = 0.5 - dz[i] / 255 / 1.25; // R
			color[j++] = 0.5 - dz[i] / 255 / 1.25; // G
			color[j++] = 0.5 - dz[i] / 255 / 1.25; // B
			color[j++] = 1.0; // A
		}
	}
}
Polygon::~Polygon() {
	delete[] vertex;
	delete[] color;
}

///////////////////////////////////////////////////////////////////////////////////////////////

void Polygon::toggleFlag(int f) {
	flag[f] = !flag[f];
}

void Polygon::draw() {
	if (flag[0]) {
		glEnableClientState(GL_COLOR_ARRAY);

		glColorPointer(4, GL_FLOAT, 0, color);
	} else {
		glColor4x(FCOLOR(0.5), FCOLOR(0.5), FCOLOR(0.5), FCOLOR(1.0));
	}
	glVertexPointer(3, GL_FLOAT, 0, vertex);
	if (flag[1]) {
		glDrawArrays(GL_TRIANGLE_STRIP, 0, N);
	}
	if (flag[0]) {
		glDisableClientState(GL_COLOR_ARRAY);
	}
	if (flag[2]) {
		glColor4x(FCOLOR(0.0), FCOLOR(0.0), FCOLOR(0.0), FCOLOR(1.0));
		glDrawArrays(GL_LINE_STRIP, 0, N);
	}
}
