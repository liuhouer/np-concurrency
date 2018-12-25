package cn.northpark.concurrency.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import cn.northpark.concurrency.annotaion.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class TestAtomicInteger {

	// 请求总数
	static int totalCount = 5000;

	// 同时并发执行的线程数
	static int threadTotal = 200;

	// 计数器
	public static AtomicInteger count = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		// 限制同时执行的线程数
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(totalCount);

		for (int i = 0; i < totalCount; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					add();
					semaphore.release();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				countDownLatch.countDown();

			});
		}

		countDownLatch.await();
		executorService.shutdown();
		log.info("count{}", count);
	}

	public static void add() {
		count.incrementAndGet();
		// count.getAndIncrement();
	}
}
