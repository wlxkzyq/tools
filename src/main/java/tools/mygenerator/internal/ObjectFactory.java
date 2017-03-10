package tools.mygenerator.internal;

import static tools.mygenerator.internal.util.messages.Messages.getString;

import tools.mygenerator.api.Plugin;
import tools.mygenerator.config.Context;
import tools.mygenerator.config.PluginConfiguration;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月10日 上午11:07:15 
* @version 
*/
public class ObjectFactory {

	/**
	 * 创建<code>Plugin</code>插件对象
	 * @param context
	 * @param pluginConfiguration
	 * @return
	 */
    public static Plugin createPlugin(Context context,
            PluginConfiguration pluginConfiguration) {
        Plugin plugin = (Plugin) createInternalObject(pluginConfiguration
                .getConfigurationType());
        plugin.setContext(context);
        plugin.setProperties(pluginConfiguration.getProperties());
        return plugin;
    }
    
    /**
     * 通过类名获取类的实例
     * @param type	类名
     * @return
     */
    public static Object createInternalObject(String type) {
        Object answer;

        try {
            Class<?> clazz = internalClassForName(type);

            answer = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(getString(
                    "RuntimeError.6", type), e); //$NON-NLS-1$

        }

        return answer;
    }
    
    /**
     * 根据类名获取<code>Class</code>对象
     * @param type	类名
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> internalClassForName(String type)
            throws ClassNotFoundException {
        Class<?> clazz = null;

        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            clazz = Class.forName(type, true, cl);
        } catch (Exception e) {
            // ignore - failsafe below
        }

        if (clazz == null) {
            clazz = Class.forName(type, true, ObjectFactory.class.getClassLoader());
        }

        return clazz;
    }
}
