package xiaojinzi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 运行时期有效,和针对的是字段
 * 
 * @author xiaojinzi
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Injection {

	/**
	 * 字段对应布局文件的id
	 * 
	 * @return
	 */
	int value();

	/**
	 * 点击事件
	 * @return
	 */
	String click() default "";

	/**
	 * 长按的点击事件
	 * @return
	 */
	String longClick() default "";

}
