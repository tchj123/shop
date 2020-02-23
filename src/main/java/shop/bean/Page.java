package shop.bean;

import java.util.LinkedList;
import java.util.List;

public class Page<T> {
	private int totalPage;
	private int curretnPage;
	private List<T> data;
	public Page() {
		data=new LinkedList<>();
	}
	public Page(int totalPage, int curretnPage) {
		this.totalPage = totalPage;
		this.curretnPage = curretnPage;
		data=new LinkedList<>();
	}
	public Page(int totalPage, int curretnPage, List<T> data) {
		this.totalPage = totalPage;
		this.curretnPage = curretnPage;
		this.data = data;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurretnPage() {
		return curretnPage;
	}
	public void setCurretnPage(int curretnPage) {
		this.curretnPage = curretnPage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
