package com.bnt.sample.concurrent.zsxq;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * threadlocal
 * threadlocal的使用场景
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/1/24 10:10 bnt
 * @history
 */
public class ThreadLocalTest {
    /**
     * 2个线程使用SimpleDateFormat
     */
    static class ThreadLocalDemo01 {
        public static void main(String[] args) throws InterruptedException {
            new Thread(() -> {
                String date = new ThreadLocalDemo01().date(1);
                System.out.println(date);
            }).start();
            Thread.sleep(100);
            new Thread(() -> {
                String date = new ThreadLocalDemo01().date(2);
                System.out.println(date);
            }).start();
        }

        public String date(int seconds) {
            Date date = new Date(1000L * seconds);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            return simpleDateFormat.format(date);
        }
    }

    /**
     * 10个线程使用 SimpleDateFormat
     * for循环10次，每个线程都在date方法创建SimpleDateFormat对象
     */
    static class ThreadLocalDemo02 {
        public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                new Thread(() -> {
                    String date = new ThreadLocalDemo02().date(finalI);
                    System.out.println(date);
                }).start();
                Thread.sleep(100);
            }
        }

        public String date(int seconds) {
            Date date = new Date(1000L * seconds);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            return simpleDateFormat.format(date);
        }
    }

    /**
     * 提交1000个任务，每个任务都在date方法创建SimpleDateFormat对象
     * 线程池实现线程复用
     */
    static class ThreadLocalDemo03 {
        public static ExecutorService threadPool = Executors.newFixedThreadPool(16);

        public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                threadPool.submit(() -> {
                    String date = new ThreadLocalDemo03().date(finalI);
                    System.out.println(date);
                });
            }
            threadPool.shutdown();
        }

        public String date(int seconds) {
            Date date = new Date(1000L * seconds);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            return simpleDateFormat.format(date);
        }
    }


    /**
     * 将 SimpleDateFormat 提取成公共的变量，会有线程不安全问题
     */
    static class ThreadLocalDemo04 {
        public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
        static SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");

        public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                threadPool.submit(() -> {
                    String date = new ThreadLocalDemo04().date(finalI);
                    System.out.println(date);
                });
            }
            threadPool.shutdown();
        }

        public String date(int seconds) {
            Date date = new Date(1000L * seconds);
            return dateFormat.format(date);
        }
    }

    /**
     * 解决公共SimpleDateFormat对象线程不安全问题
     */
    static class ThreadLocalDemo05 {
        public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
        static SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");

        public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                threadPool.submit(() -> {
                    String date = new ThreadLocalDemo05().date(finalI);
                    System.out.println(date);
                });
            }
            threadPool.shutdown();
        }

        public String date(int seconds) {
            Date date = new Date(1000L * seconds);
            String s = null;
            synchronized (ThreadLocalDemo05.class) {
                s = dateFormat.format(date);
            }
            return s;
        }
    }

    /**
     * 使用了 ThreadLocal 帮每个线程去生成它自己的 simpleDateFormat 对象,一共16个
     */
    static class ThreadLocalDemo06 {
        public static ExecutorService threadPool = Executors.newFixedThreadPool(16);

        public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                threadPool.submit(() -> {
                    String date = new ThreadLocalDemo06().date(finalI);
                    System.out.println(date);
                });
            }
            threadPool.shutdown();
        }

        public String date(int seconds) {
            Date date = new Date(1000L * seconds);
            SimpleDateFormat s = ThreadSafeFormatter.dateFormatThreadLocal.get();
            return s.format(date);
        }

        static class ThreadSafeFormatter {
            public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat("mm:ss");
                }
            };
        }
    }

}
