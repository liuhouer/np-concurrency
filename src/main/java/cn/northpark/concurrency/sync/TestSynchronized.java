package cn.northpark.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestSynchronized {

	/**
	 * 修饰一个代码块
	 */
	public void test1() {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				log.info("test1-->{}", i);
			}
		}
	}

	/**
	 * 修饰一个方法
	 */
	public synchronized void test2() {
		for (int i = 0; i < 10; i++) {
			log.info("test1-->{}", i);
		}
	}

	public static void main(String[] args) {
		TestSynchronized t1 = new TestSynchronized();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(() -> {
			t1.test2();
		});
		executorService.execute(() -> {
			t1.test2();
		});
	}
}
