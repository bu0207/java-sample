package com.bnt.sample.concurrent.zsxq;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.List;
import java.util.concurrent.*;

/**
 * https://learn.lianglianglee.com/%e4%b8%93%e6%a0%8f/Java%20%e5%b9%b6%e5%8f%91%e7%bc%96%e7%a8%8b%2078%20%e8%ae%b2-%e5%ae%8c/06%20%e4%b8%80%e5%85%b1%e6%9c%89%e5%93%aa%203%20%e7%b1%bb%e7%ba%bf%e7%a8%8b%e5%ae%89%e5%85%a8%e9%97%ae%e9%a2%98%ef%bc%9f.md
 * 06-
 *
 * @author bnt
 * @version 1.0.0
 * @create 2023/12/27 16:56 bnt
 * @history
 */
public class ThreadTest2 {
    volatile static int i;

    /**
     * 线程安全问题-运行结果错误
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main1(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
        thread1.join();
        thread2.join();
        System.out.println(i);
    }

    public static class MayDeadLock {
        Object o1 = new Object();
        Object o2 = new Object();

        public void thread1() throws InterruptedException {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + "成功拿到一把锁");
                Thread.sleep(500);
                synchronized (o2) {
                    System.out.println("线程1成功拿到两把锁");
                }
            }
        }

        public void thread2() throws InterruptedException {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "成功拿到一把锁");
                Thread.sleep(500);
                synchronized (o1) {
                    System.out.println("线程2成功拿到两把锁");
                }
            }
        }
    }

    /**
     * 死锁
     *
     * @param args
     */
    public static void main2(String[] args) {
        MayDeadLock mayDeadLock = new MayDeadLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mayDeadLock.thread1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mayDeadLock.thread2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 用固定线程数的线程池执行10000个任务
     *
     * @param args
     */
    public static void main3(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int j = 0; j < 1000; j++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread Name: " + Thread.currentThread().getName());
                }
            });
        }
    }

    /**
     * CachedThreadPool-可缓存线程池
     *
     * @param args
     */
    public static void main4(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int j = 0; j < 100000; j++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread Name: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    /**
     * ScheduledThreadPool
     *
     * @param args
     */
    public static void main5(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(() -> {
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }, 10, TimeUnit.SECONDS);
//        // 关闭线程池
//        scheduledExecutorService.shutdown();

        ScheduledExecutorService scheduledExecutorService2 = Executors.newScheduledThreadPool(10);
        scheduledExecutorService2.scheduleAtFixedRate(() -> {
            System.out.println(System.currentTimeMillis() + "Thread Name: " + Thread.currentThread().getName());
        }, 10, 10, TimeUnit.SECONDS);
//        // 关闭线程池
//        scheduledExecutorService2.shutdown();

        ScheduledExecutorService scheduledExecutorService3 = Executors.newScheduledThreadPool(10);
        scheduledExecutorService3.scheduleWithFixedDelay(() -> {
            System.out.println(System.currentTimeMillis() + "Thread Name: " + Thread.currentThread().getName());
        }, 10, 10, TimeUnit.SECONDS);
        // 关闭线程池
//        scheduledExecutorService3.shutdown();
    }

    /**
     * SingleThreadExecutor
     *
     * @param args
     */
    public static void main6(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int j = 0; j < 100; j++) {
            final int taskId = j;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Executing task " + taskId + " with thread: " + "Thread Name: " + Thread.currentThread().getName());
                }
            });
        }
        service.shutdown();
    }

    /**
     * SingleThreadScheduledExecutor
     *
     * @param args
     */
    public static void main7(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(System.currentTimeMillis() + "Thread Name: " + Thread.currentThread().getName());
        }, 10, 10, TimeUnit.SECONDS);
    }

    /**
     * 线程池拒绝策略-CallerRunsPolicy
     *
     * @param args
     */
    public static void main10(String[] args) {
        // 创建一个线程池，设置核心线程数为2，最大线程数为4，设置等待队列容量为2，并使用CallerRunsPolicy作为拒绝策略
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // 核心线程数
                4, // 最大线程数
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(2), // 等待队列容量
                new ThreadPoolExecutor.CallerRunsPolicy()); // 使用CallerRunsPolicy作为拒绝策略

        // 提交8个任务给线程池
        for (int i = 1; i <= 8; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Executing task " + taskId + " with thread: " + Thread.currentThread().getName());
                try {
                    // 模拟任务执行耗时
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 关闭线程池
        executor.shutdown();
    }

    static class Fibonacci extends RecursiveTask<Integer> {
        private int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        /**
         * The main computation performed by this task.
         *
         * @return the result of the computation
         */
        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            f2.fork();
            return f2.join() + f1.join();
        }
    }

    /**
     * ForkJoinPool
     */
    public static void main11(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 10; i++) {
            ForkJoinTask task = forkJoinPool.submit(new Fibonacci(10));
            System.out.println(task.get());
        }
    }

    /**
     * 自定义线程工厂
     */
    public static void main(String[] args) {
        ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
        ThreadFactory factory = builder.setNameFormat("my-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 30,
                0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1), factory,
                new CustomRejectionHandler());
        for (int j = 0; j < 100; j++) {
            int finalJ = j;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("任务" + finalJ + "Thread Name: " + Thread.currentThread().getName());
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
                }
            });
        }
        System.out.println(threadPoolExecutor.isShutdown());
        System.out.println(threadPoolExecutor.isTerminated());
        threadPoolExecutor.shutdown();
//        List<Runnable> runnables = threadPoolExecutor.shutdownNow();
//        System.out.println("正在运行中线程：" + runnables.size());
        System.out.println(threadPoolExecutor.isShutdown());
        System.out.println(threadPoolExecutor.isTerminated());
    }

    private static class CustomRejectionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r + "被拒绝执行");
            executor.submit(r);
        }
    }
}
