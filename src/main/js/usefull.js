
/**
 * 复选框值回显工具方法
 * @param selected	已经被选中的复选框字符串（比如：1,2,5）
 * @param boxName	复选框的name属性（比如 <pre><input type="checkbox" name="hobbys"></pre>）
 * @returns
 */
function checkboxToll(selected,boxName){
	var s=selected.split(",");
	var nodes=document.getElementsByName(boxName);
	for(var i=0;i<s.length;i++){
		var si=s[i];
		for(var j=0;j<nodes.length;j++){
			if(si==nodes[j].value){
				nodes[j].checked=true;
				break;
			}
		}
	}
}