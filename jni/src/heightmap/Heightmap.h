#ifndef HEIGHTMAP_H_
#define HEIGHTMAP_H_

///////////////////////////////////////////////////////////////////////////////////////////////

// STL
#include <string>
#include <vector>

// OpenGL
#include <GLES/gl.h>
#include <GLES/glext.h>

// Custom
#include <inc/Singleton.h>
#include "Surface.h"
#include "Polygon.h"

///////////////////////////////////////////////////////////////////////////////////////////////

using namespace std;

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

class Heightmap: public Singleton<Heightmap>, public Surface<int, float> {
	friend class Singleton<Heightmap> ;
	friend class Surface<int, float> ;

private:
	int m_width;
	int m_height;

	int *m_heightmap;
	vector<Polygon*> m_polygon;

protected:
	Heightmap();
	~Heightmap();

public:
	int initArray(int *heightmap, int width, int height, float scale);
	int onDrawFrame();
	int toggleFlag(int flag);

private:
	int draw();
	int drawPolygon();
	int drawBoundingBox();

};

///////////////////////////////////////////////////////////////////////////////////////////////

#endif /* HEIGHTMAP_H_ */
