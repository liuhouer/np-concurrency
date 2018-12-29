package cn.northpark.concurrency.publish;

import cn.northpark.concurrency.annotaion.NotRecommand;
import cn.northpark.concurrency.annotaion.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jeyy
 *  <pre>
 *  这个内部类的实例里面包含了对封装实例的私有域对象的引用，在对象没有被正确构造完成之前就会被发布，有可能有不安全的因素在里面，会导致this引用在构造期间溢出的错误。
 *  上述代码在函数构造过程中启动了一个线程。无论是隐式的启动还是显式的启动，都会造成这个this引用的溢出。新线程总会在所属对象构造完毕之前就已经看到它了。
 *  因此要在构造函数中创建线程，那么不要启动它，而是应该采用一个专有的start或者初始化的方法统一启动线程
 *  这里其实我们可以采用工厂方法和私有构造函数来完成对象创建和监听器的注册等等，这样才可以避免错误
 *  ——————————————————————————————————————————————————-
 *  如果不正确的发布对象会导致两种错误： 
 *  （1）发布线程意外的任何线程都可以看到被发布对象的过期的值 
 *  （2）线程看到的被发布线程的引用是最新的，然而被发布对象的状态却是过期的
 * </pre>	
 */
@Slf4j
@NotThreadSafe
@NotRecommand
public class Escape {

	private int thisCanBeEscape = 0;
	
	public Escape() {
		new InnerClass();
	}
	
	public class InnerClass{
		//内部类构造方法调用外部类的私有域
		public InnerClass() {
			log.info("{}",Escape.this.thisCanBeEscape);
		}
	}
	
	public static void main(String[] args) {
		new Escape();
	}
}

