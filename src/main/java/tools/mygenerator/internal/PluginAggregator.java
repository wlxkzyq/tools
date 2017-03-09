package tools.mygenerator.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import tools.mygenerator.api.IntrospectedTable;
import tools.mygenerator.api.Plugin;
import tools.mygenerator.config.Context;

/** 
* 插件聚合器
* @author 作者 : zyq
* 创建时间：2017年3月9日 下午9:36:59 
* @version 
*/
public class PluginAggregator implements Plugin{
	private List<Plugin> plugins;
	
	public PluginAggregator() {
        plugins = new ArrayList<Plugin>();
    }
	
	/**
	 * 添加插件
	 * @param plugin	待添加的插件
	 */
    public void addPlugin(Plugin plugin) {
        plugins.add(plugin);
    }

	@Override
	public void setContext(Context context) {
		throw new UnsupportedOperationException("插件聚合器不允许设置设置context！！！");
	}

	@Override
	public void setProperties(Properties properties) {
		throw new UnsupportedOperationException("插件聚合器不允许设置设置属性！！！");
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		for (Plugin plugin : plugins) {
            plugin.initialized(introspectedTable);
        }
	}

	@Override
	public boolean validate(List<String> warnings) {
		throw new UnsupportedOperationException("插件聚合器不允许检查！！！");
	}

}
