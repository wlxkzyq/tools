package tools.test;

import java.text.DecimalFormat;

import tools.excel.ExcelAnnotation;

/** 
* 当月累计保费
* @author 作者 : zyq
* 创建时间：2016年12月1日 下午12:01:41 
* @version 
*/
public class PremMonth {
	//排名
	@ExcelAnnotation("排名")
	private Integer ranking;
	//名称
	@ExcelAnnotation("名称")
	private String branchName;
	//店主名	
	@ExcelAnnotation("店主名")
	private String name;
	//店主ID
	@ExcelAnnotation("店主ID")
	private Integer id;
	//当月累计保费
	@ExcelAnnotation("当月累计保费")
	private Double totalPrem;
	//下一方案达成保费差距
	@ExcelAnnotation("下一方案达成保费差距")
	private String target;
	//与上一名保费差距
	@ExcelAnnotation("与上一名保费差距")
	private String distance;
	public Integer getRanking() {
		return ranking;
	}
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTotalPrem() {
		DecimalFormat df=new DecimalFormat("#.##");
		return df.format(totalPrem);
	}
	public void setTotalPrem(Double totalPrem) {
		this.totalPrem = totalPrem;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}

	

}
