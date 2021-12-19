package Project;

public class TreeNode<T> {

	private T data;
	private LinkedList<TreeNode<T>> list= new LinkedList<TreeNode<T>>();
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public LinkedList<TreeNode<T>> getList() {
		return list;
	}
	public void setList(LinkedList<TreeNode<T>> list) {
		this.list = list;
	}
	
	public void addNode(TreeNode<T> node) {
		list.InsertAtEnd(node);
	}
}
