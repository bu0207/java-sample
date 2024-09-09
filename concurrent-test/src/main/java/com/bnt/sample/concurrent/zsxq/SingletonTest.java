package com.bnt.sample.concurrent.zsxq;

/**
 * 单例模式-双重检查锁
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/2/4 17:32 bnt
 * @history
 */
public class SingletonTest {
    private static volatile SingletonTest singletonTest;

    private SingletonTest() {

    }

    /**
     * 这种写法是可以保证线程安全的，假设有两个线程同时到达 synchronized 语句块，
     * 那么实例化代码只会由其中先抢到锁的线程执行一次，
     * 而后抢到锁的线程会在第二个 if 判断中发现 singleton 不为 null，
     * 所以跳过创建实例的语句。
     * 再后面的其他线程再来调用 getInstance 方法时，只需判断第一次的 if (singleton == null) ，
     * 然后会跳过整个 if 块，直接 return 实例化后的对象。
     *
     * @return
     */
    public static SingletonTest getInstance() {
        if (singletonTest == null) {
            synchronized (SingletonTest.class) {
                if (singletonTest == null) {
                    singletonTest = new SingletonTest();
                }
            }
        }
        return singletonTest;
    }
}
