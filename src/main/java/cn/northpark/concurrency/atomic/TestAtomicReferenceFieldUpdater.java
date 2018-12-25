package cn.northpark.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import cn.northpark.concurrency.annotaion.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class TestAtomicReferenceFieldUpdater {

	private static AtomicIntegerFieldUpdater<TestAtomicReferenceFieldUpdater> updater = AtomicIntegerFieldUpdater
			.newUpdater(TestAtomicReferenceFieldUpdater.class, "count");

	// AtomicReferenceFieldUpdater 主要用来更新某个类的volatile标识、非static 的字段
	@Getter
	public volatile int count = 100;

	public static void main(String[] args) {
		TestAtomicReferenceFieldUpdater testAtomicReferenceFieldUpdater = new TestAtomicReferenceFieldUpdater();
		if (updater.compareAndSet(testAtomicReferenceFieldUpdater, 100, 120)) {
			log.info("update success ，{}", testAtomicReferenceFieldUpdater.getCount());
		}
		if (updater.compareAndSet(testAtomicReferenceFieldUpdater, 100, 120)) {
			log.info("update success 2，{}", testAtomicReferenceFieldUpdater.getCount());
		} else {
			log.info("update fail ");
		}
	}
}
