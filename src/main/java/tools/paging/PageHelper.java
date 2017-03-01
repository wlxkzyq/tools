package tools.paging;
/** 
* 分页辅助类
* 使用分页插件进行分页的实体类需要继承此类
* @author 作者 : zyq
* 创建时间：2016年11月2日 下午5:12:23 
* @version 
*/
public class PageHelper {
	/**
	 * 当前页
	 */
	private int page;
	/**
	 * 一页显示几条
	 */
	private int rows;
	/**
	 * 开始条数
	 */
	private Integer start;
	/**
	 * 总条数
	 */
	private int total;
	public Integer getStart(){
		if(page==0)return 1;
		start=(page-1)*rows;
		return start;
	}
	public Integer getEnd(){
		return rows==0?null:rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public static void main(String[] args) {
		System.out.println(new PageHelper().getStart());
	}
	
}
