package cn.northpark.concurrency.threadLocal;

/**
 * @author jeyy
 * ThreadLocal基础的添加、删除、获取操作。
 */
public class RequestHolder {

	public final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();
	
	public static void add(Long id) {
		requestHolder.set(id);
	}
	
	public static Long get() {
		return requestHolder.get();
	}
	
	public static void remove() {
		requestHolder.remove();
	}
}

