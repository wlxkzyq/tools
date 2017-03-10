package tools.mygenerator.config;
/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月10日 上午10:54:38 
* @version 
*/
public abstract class TypedPropertyHolder extends PropertyHolder{
	
	   private String configurationType;

	    /**
		 *  
		 */
	    public TypedPropertyHolder() {
	        super();
	    }

	    public String getConfigurationType() {
	        return configurationType;
	    }

	    /**
	     * Sets the value of the type specified in the configuration. If the special
	     * value DEFAULT is specified, then the value will be ignored.
	     * 
	     * @param configurationType
	     *            the type specified in the configuration
	     */
	    public void setConfigurationType(String configurationType) {
	        if (!"DEFAULT".equalsIgnoreCase(configurationType)) { //$NON-NLS-1$
	            this.configurationType = configurationType;
	        }
	    }

}
