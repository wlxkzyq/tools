package tools.mygenerator.api;
/** 
* 数据库外键信息
* @author 作者 : zyq
* 创建时间：2016年12月17日 下午3:51:38 
* @version 
*/
public class IntrospectedForeignKey {
	
	private String pkTableCatalog;
	private String pkTableSchema;
	private String pkTableName;
	private String pkColumnName;
	private String fkTableCatalog;
	private String fkTableSchema;
	private String fkTableName;
	private String fkColumnName;
	private short keySeq;
	/**
	 * What happens to foreign key when primary is updated
	 */
	private short updateRule;
	/**
	 * What happens to the foreign key when primary is deleted
	 */
	private short deleteRule;
	
	private String fkName;
	/**
	 * can the evaluation of foreign key constraints be deferred until commit 
	 */
	private short deferrAbility;
	public String getPkTableCatalog() {
		return pkTableCatalog;
	}
	public void setPkTableCatalog(String pkTableCatalog) {
		this.pkTableCatalog = pkTableCatalog;
	}
	public String getPkTableSchema() {
		return pkTableSchema;
	}
	public void setPkTableSchema(String pkTableSchema) {
		this.pkTableSchema = pkTableSchema;
	}
	public String getPkTableName() {
		return pkTableName;
	}
	public void setPkTableName(String pkTableName) {
		this.pkTableName = pkTableName;
	}
	public String getPkColumnName() {
		return pkColumnName;
	}
	public void setPkColumnName(String pkColumnName) {
		this.pkColumnName = pkColumnName;
	}
	public String getFkTableCatalog() {
		return fkTableCatalog;
	}
	public void setFkTableCatalog(String fkTableCatalog) {
		this.fkTableCatalog = fkTableCatalog;
	}
	public String getFkTableSchema() {
		return fkTableSchema;
	}
	public void setFkTableSchema(String fkTableSchema) {
		this.fkTableSchema = fkTableSchema;
	}
	public String getFkTableName() {
		return fkTableName;
	}
	public void setFkTableName(String fkTableName) {
		this.fkTableName = fkTableName;
	}
	public String getFkColumnName() {
		return fkColumnName;
	}
	public void setFkColumnName(String fkColumnName) {
		this.fkColumnName = fkColumnName;
	}
	public short getKeySeq() {
		return keySeq;
	}
	public void setKeySeq(short keySeq) {
		this.keySeq = keySeq;
	}
	public short getUpdateRule() {
		return updateRule;
	}
	public void setUpdateRule(short updateRule) {
		this.updateRule = updateRule;
	}
	public short getDeleteRule() {
		return deleteRule;
	}
	public void setDeleteRule(short deleteRule) {
		this.deleteRule = deleteRule;
	}
	public String getFkName() {
		return fkName;
	}
	public void setFkName(String fkName) {
		this.fkName = fkName;
	}
	public short getDeferrAbility() {
		return deferrAbility;
	}
	public void setDeferrAbility(short deferrAbility) {
		this.deferrAbility = deferrAbility;
	}
	

}
