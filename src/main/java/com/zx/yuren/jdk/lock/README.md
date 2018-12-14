#### synchronized
      javap -c SynchronizedTest
      警告: 二进制文件SynchronizedTest包含com.zx.yuren.jdk.concurrent.lock.SynchronizedTest
      Compiled from "SynchronizedTest.java"
      public class com.zx.yuren.jdk.concurrent.lock.SynchronizedTest {
        public com.zx.yuren.jdk.concurrent.lock.SynchronizedTest();
          Code:
             0: aload_0       
             1: invokespecial #2                  // Method java/lang/Object."<init>":()V
             4: return        
      
        public static void main(java.lang.String[]);
          Code:
             0: ldc           #3                  // class com/zx/yuren/jdk/concurrent/lock/SynchronizedTest
             2: dup           
             3: astore_1      
             4: **monitorenter** 
             5: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
             8: ldc           #5                  // String >>>>>
            10: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
            13: aload_1       
            14: **monitorexit**   
            15: goto          23
            18: astore_2      
            19: aload_1       
            20: monitorexit   
            21: aload_2       
            22: athrow        
            23: bipush        8
            25: invokestatic  #7                  // Method java/util/concurrent/Executors.newFixedThreadPool:(I)Ljava/util/concurrent/ExecutorService;
            28: astore_1      
            29: iconst_0      
            30: istore_2      
            31: iload_2       
            32: bipush        100
            34: if_icmpge     56
            37: aload_1       
            38: new           #8                  // class com/zx/yuren/jdk/concurrent/lock/SynchronizedTest$1
            41: dup           
            42: invokespecial #9                  // Method com/zx/yuren/jdk/concurrent/lock/SynchronizedTest$1."<init>":()V
            45: invokeinterface #10,  2           // InterfaceMethod java/util/concurrent/ExecutorService.execute:(Ljava/lang/Runnable;)V
            50: iinc          2, 1
            53: goto          31
            56: aload_1       
            57: invokeinterface #11,  1           // InterfaceMethod java/util/concurrent/ExecutorService.shutdown:()V
            62: return        
          Exception table:
             from    to  target type
                 5    15    18   any
                18    21    18   any
      
        static void access$000();
          Code:
             0: invokestatic  #1                  // Method test01:()V
             3: return        
      
        static {};
          Code:
             0: bipush        100
             2: invokestatic  #19                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
             5: putstatic     #17                 // Field tickets:Ljava/lang/Integer;
             8: return        
      }

      同步块的入口和出口分别有 monitorenter,monitorexit 指令。
      
#### ReentrantLock
     公平锁 & 非公平锁
     
     简单理解
     tryLock  立即返回的，就是做了一下cas（可重入，如果是当前线程state+1）
     
     lock 阻塞的  成功state+1
     
     非公平：不管三七二十一 进来直接CAS先抢锁（可能前一个线程unpark了头部线程，现在进来的线程直接和头部线程并发抢，抢到就不用排队了，所以叫非公平锁）
     公平：锁空，尝试CAS抢锁，失败进队列；锁已经被别人抢走了，直接进队列
     
     unlock  state-1 = 0  释放锁成功  唤醒头部队列的那个线程

