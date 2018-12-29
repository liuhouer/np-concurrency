package cn.northpark.concurrency.publish;

import java.util.Arrays;

import cn.northpark.concurrency.annotaion.NotRecommand;
import cn.northpark.concurrency.annotaion.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jeyy
 *	这个代码通过public访问级别发布了类的域，在类的任何外部的线程都可以访问这些域
 *  我们无法保证其他线程会不会修改这个域，从而使私有域内的值错误（上述代码中就对私有域进行了修改）
 */
@Slf4j
@NotThreadSafe
@NotRecommand
public class UnsafePublish {

	//调用类的公共方法可以改变类的私有属性的值
	private String[] status = {"1","2","3"};
	
	//类的非私有方法，返回私有对象的引用
	public String[] getStatus() {
		return status;
	}
	
	public static void main(String[] args) {
		UnsafePublish up = new UnsafePublish();
		log.info("{}",Arrays.toString(up.getStatus()));
		
		up.status[0]="999";
		log.info("{}",Arrays.toString(up.getStatus()));
		
	}
}

