//==================================================================
// 《剑指Offer——名企面试官精讲典型编程题》代码
// 本书原作者：何海涛
// 仓库作者：淡定但丁
//==================================================================

// 面试题2：实现Singleton模式
// 题目：设计一个类，我们只能生成该类的一个实例。

#include <iostream>  // std::cout
#include <mutex>     // std::mutex
#include <pthread.h> // pthread_create

/* 不好的解法，只适用于单线程环境 */
/*------------------- 普通懒汉式实现--线程不安全 -------------------*/

class SingleInstance
{
public:
    // 获取单例对象
    static SingleInstance *getInstance();
    // 释放单例，进程退出时调用
    static void deleteInstance();
    // 打印单例地址
    void Print();

private:
    // 将其构造和析构成为私有的, 禁止外部构造和析构
    SingleInstance(){};
    ~SingleInstance(){};
    // 将其拷贝构造和赋值构造成为私有函数, 禁止外部拷贝和赋值
    SingleInstance(const SingleInstance &signal);
    const SingleInstance &operator=(const SingleInstance &signal);

private:
    // 唯一单例对象指针
    static SingleInstance *m_SingleInstance;
};

// 初始化静态成员变量
SingleInstance *SingleInstance::m_SingleInstance = NULL;

SingleInstance *SingleInstance::getInstance()
{
    if (m_SingleInstance == NULL)
    {
        m_SingleInstance = new (std::nothrow) SingleInstance; // 没有加锁是线程不安全的，当线程并发时会创建多个实例
    }
    return m_SingleInstance;
}

void SingleInstance::deleteInstance()
{
    if (m_SingleInstance)
    {
        delete m_SingleInstance;
        m_SingleInstance = NULL;
    }
}

void SingleInstance::Print()
{
    std::cout << "我的实例内存地址是:" << this << std::endl;
}

/* 不好的解法二：虽然在多线程环境中能工作，但效率不高 */
/*--------------------- 加锁的懒汉式实现 ----------------------------*/
class SingleInstance
{

public:
    // 获取单实例对象
    static SingleInstance *getInstance();

private:
    // 将其构造和析构成为私有的, 禁止外部构造和析构
    SingleInstance(){};
    ~SingleInstance(){};

    // 将其拷贝构造和赋值构造成为私有函数, 禁止外部拷贝和赋值
    SingleInstance(const SingleInstance &signal);
    const SingleInstance &operator=(const SingleInstance &signal);

private:
    // 唯一单实例对象指针
    static SingleInstance *m_SingleInstance;
    static std::mutex m_Mutex;
};

SingleInstance * SingleInstance::getInstance()
{
    //  每次调用 GetInstance的方法都加锁，锁的开销毕竟还是有点大的。
    std::unique_lock<std::mutex> lock(m_Mutex);
    if (m_SingleInstance == nullptr) 
    {
        m_SingleInstance = new (std::nothrow) SingleInstance();
    }

    return m_SingleInstance;
}


/* 可行的解法：加同步锁前两次判断实例是否已存在 */
/*--------------------- 双检锁的懒汉式实现 ----------------------------*/

class SingleInstance
{

public:
    // 获取单实例对象
    static SingleInstance *getInstance();

    //释放单实例，进程退出时调用
    static void deleteInstance();
	
    // 打印实例地址
    void print();

private:
    // 将其构造和析构成为私有的, 禁止外部构造和析构
    SingleInstance();
    ~SingleInstance();

    // 将其拷贝构造和赋值构造成为私有函数, 禁止外部拷贝和赋值
    SingleInstance(const SingleInstance &signal);
    const SingleInstance &operator=(const SingleInstance &signal);

private:
    // 唯一单实例对象指针
    static SingleInstance *m_SingleInstance;
    static std::mutex m_Mutex;
};

//初始化静态成员变量
SingleInstance *SingleInstance::m_SingleInstance = nullptr;
std::mutex SingleInstance::m_Mutex;

// 注意：不能返回指针的引用，否则存在外部被修改的风险！
SingleInstance * SingleInstance::getInstance()
{
    //  这里使用了两个 if 判断语句的技术称为双检锁；好处是，只有判断指针为空的时候才加锁，
    //  避免每次调用 GetInstance的方法都加锁，锁的开销毕竟还是有点大的。
    if (m_SingleInstance == nullptr) 
    {
        std::unique_lock<std::mutex> lock(m_Mutex); // 加锁
        if (m_SingleInstance == nullptr)
        {
            volatile auto temp = new (std::nothrow) SingleInstance();
            m_SingleInstance = temp;
        }
    }

    return m_SingleInstance;
}

void SingleInstance::deleteInstance()
{
    std::unique_lock<std::mutex> lock(m_Mutex); // 加锁
    if (m_SingleInstance)
    {
        delete m_SingleInstance;
        m_SingleInstance = nullptr;
    }
}

void SingleInstance::print()
{
	std::cout << "我的实例内存地址是:" << this << std::endl;
}



/* 推荐的解法：使用静态局部变量实现 */
/*--------------------- 局部静态变量实现懒汉式 ----------------------------*/

class SingleInstance
{
public:
    // 将拷贝构造函数和拷贝运算符声明为删除，或放到private中
    SingleInstance(const SingleInstance &) = delete;
    SingleInstance &operator=(const SingleInstance &) = delete;

    // 获取单例对象
    static SingleInstance &get_instance()
    {
        // 关键点
        static SingleInstance instance;    // 局部静态变量
        return instance;
    }
    // 不推荐，返回指针的方式
    /*static Singleton* get_instance(){
        static Singleton instance;
        return &instance;
  }*/
private:
    SingleInstance(){};
};