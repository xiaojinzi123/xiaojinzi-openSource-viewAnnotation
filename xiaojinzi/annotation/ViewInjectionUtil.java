package xiaojinzi.annotation;

import java.lang.reflect.Field;

import com.example.demo.L;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * activity的注入工具类
 *
 * @author xiaojinzi
 */
public class ViewInjectionUtil {

	/**
	 * 类的标识
	 */
	private static final String TAG = "ActivityInjectionUtil";

	/**
	 * 对activity中的字段进行注入
	 *
	 * @param act
	 */
	public static void injectView(Activity act) {
		// 获取这个activity中的所有字段
		Field[] fields = act.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			// 循环拿到每一个字段
			Field field = fields[i];
			if (field.isAnnotationPresent(Injection.class)) { // 如果这个字段有注入的注解
				// 获取注解对象
				Injection injection = field.getAnnotation(Injection.class);
				int value = injection.value();
				field.setAccessible(true); // 即使私有的也可以设置数据
				Object view = null;
				try {
					view = act.findViewById(value);
					// 设置字段的属性
					field.set(act, view);
				} catch (Exception e) {
					e.printStackTrace();
					L.s(TAG, "注入属性失败：" + field.getClass().getName() + ":" + field.getName());
				}

				try {
					if (view instanceof View) {
						View v = (View) view;
						// 获取点击事件的触发的方法名称
						String methodName = injection.click();
						EventListener eventListener = null;
						// 如果不是空字符串
						if (!"".equals(methodName)) {
							eventListener = new EventListener(act);
							// 设置点击事件
							v.setOnClickListener(eventListener);
							eventListener.setClickMethodName(methodName);
						}
						methodName = injection.longClick();
						if (!"".equals(methodName)) {
							if (eventListener == null) {
								eventListener = new EventListener(act);
							}
							// 设置点击事件
							v.setOnLongClickListener(eventListener);
							eventListener.setLongClickMethodName(methodName);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

	/**
	 * 对Fragment中的字段进行注入
	 *
	 * @param f
	 * @param contentView
	 */
	public static void injectView(Fragment f, View contentView) {
		// 获取这个activity中的所有字段
		Field[] fields = f.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			// 循环拿到每一个字段
			Field field = fields[i];
			if (field.isAnnotationPresent(Injection.class)) { // 如果这个字段有注入的注解
				// 获取注解对象
				Injection injection = field.getAnnotation(Injection.class);
				int value = injection.value();
				field.setAccessible(true); // 即使私有的也可以设置数据
				Object view = null;
				try {
					view = contentView.findViewById(value);
					field.set(f, view);
				} catch (Exception e) {
					e.printStackTrace();
					L.s(TAG, "注入属性失败：" + field.getClass().getName() + ":" + field.getName());
				}

				try {
					if (view instanceof View) {
						View v = (View) view;
						// 获取点击事件的触发的方法名称
						String methodName = injection.click();
						EventListener eventListener = null;
						// 如果不是空字符串
						if (!"".equals(methodName)) {
							eventListener = new EventListener(f);
							// 设置点击事件
							v.setOnClickListener(eventListener);
							eventListener.setClickMethodName(methodName);
						}
						methodName = injection.longClick();
						if (!"".equals(methodName)) {
							if (eventListener == null) {
								eventListener = new EventListener(f);
							}
							// 设置点击事件
							v.setOnLongClickListener(eventListener);
							eventListener.setLongClickMethodName(methodName);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
