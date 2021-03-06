#ifndef SINGLETON_H_
#define SINGLETON_H_

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

template<typename T>
class Singleton {
private:
	static T* m_instance;

protected:
	Singleton() {
	}

public:
	static T* getInstance() {
		if (m_instance == NULL) {
			m_instance = new T();
		}

		return m_instance;
	}
	static void destroyInstance() {
		if (m_instance != NULL) {
			delete m_instance;
			m_instance = NULL;
		}
	}
	virtual ~Singleton() {
	}
};

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

template<typename T> T* Singleton<T>::m_instance = 0;

///////////////////////////////////////////////////////////////////////////////////////////////

#endif /* SINGLETON_H_ */
