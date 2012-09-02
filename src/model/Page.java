package model;

public class Page extends Model {
	
	private int id;
	private String url;
	private int size;
	
	public Page() {
	}
	
	public Page(int id, String url, int size) {
		this.id = id;
		this.url = url;
		this.size = size;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "\t[Page: id: " + this.id + ", size: " + this.size + ", url: " + this.url + "]";
	}

}
