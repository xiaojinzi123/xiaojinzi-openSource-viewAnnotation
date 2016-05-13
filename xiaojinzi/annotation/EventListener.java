package xiaojinzi.annotation;

import java.lang.reflect.Method;

import com.example.demo.L;

import android.view.View;

/**
 * Created by cxj on 2016/1/21.
 * 
 * @author 小金子
 */
public class EventListener implements View.OnClickListener, View.OnLongClickListener {

	/**
	 * 类的标识
	 */
	private String tag = "EventListener";

	/**
	 * 设置是否有日志的输出
	 */
	private boolean isLog = false;

	/**
	 * 反射中要被调用方法的对象,通过构造方法进来
	 */
	private Object receiver = null;

	/**
	 * 点击事件的方法名字
	 */
	private String clickMethodName = "";

	/**
	 * 长按事件的方法的名字
	 */
	private String longClickMethodName = "";

	/**
	 * 设置点击事件的方法名字
	 *
	 * @param clickMethodName
	 */
	public void setClickMethodName(String clickMethodName) {
		this.clickMethodName = clickMethodName;
	}

	/**
	 * 设置长按的点击事件的方法的名字
	 *
	 * @param longClickMethodName
	 */
	public void setLongClickMethodName(String longClickMethodName) {
		this.longClickMethodName = longClickMethodName;
	}

	/**
	 * 构造函数
	 * 
	 * @param receiver
	 *            控件所在的activity或者Fragment
	 */
	public EventListener(Object receiver) {
		this.receiver = receiver;
	}

	@Override
	public void onClick(View v) {
		Method method = null;
		try {
			method = receiver.getClass().getMethod(clickMethodName);
			if (method != null) {
				// 调用该方法
				method.invoke(receiver);
			}
		} catch (Exception e) {
			if (isLog)
				L.s(tag, "寻找无参数列表的方法:" + clickMethodName + "失败");
		}
		try {
			if (method == null) {
				method = receiver.getClass().getMethod(clickMethodName, View.class);
				if (method != null) {
					method.invoke(receiver, v);
				}
			}
		} catch (Exception e) {
			if (isLog)
				L.s(tag, "寻找带有View类型参数的方法:" + clickMethodName + "失败");
		}
	}

	@Override
	public boolean onLongClick(View v) {
		Method method = null;
		try {
			method = receiver.getClass().getMethod(longClickMethodName);
			if (method != null) {
				// 调用该方法
				method.invoke(receiver);
			}
		} catch (Exception e) {
			if (isLog)
				L.s(tag, "寻找无参数列表的方法:" + longClickMethodName + "失败");
		}
		try {
			if (method == null) {
				method = receiver.getClass().getMethod(longClickMethodName, View.class);
				if (method != null) {
					method.invoke(receiver, v);
				}
			}
		} catch (Exception e) {
			if (isLog)
				L.s(tag, "寻找带有View类型参数的方法:" + longClickMethodName + "失败");
		}
		return true;
	}

}
