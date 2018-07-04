package cn.kgc.util;

import java.util.List;
import java.util.Map;

public class PageUtil<T>{
	public Integer index;
	public Integer pageSize;
	public Integer totalCount;
	public Integer totalPage;
	public List<Map<String,Object>> list;
	
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public PageUtil(Integer index, Integer pageSize, Integer totalCount,
			Integer totalPage) {
		super();
		this.index = index;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
	}
	public PageUtil() {
		super();
	}
	
}
