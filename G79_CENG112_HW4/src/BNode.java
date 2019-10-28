
public class BNode<T> {
	private T data;
	private BNode<T> right;
	private BNode<T> left;
	public BNode() {
		this(null);
	}
	public BNode(T data) {
		this(data,null,null);
	}
	public BNode(T data,BNode<T> right,BNode<T> left) {
		this.data=data;
		this.setRight(right);
		this.setLeft(left);
	}
	public T getData() {
		return this.data;
	}
	public void setData(T data) {
		this.data=data;
	}
	public BNode<T> getRight() {
		return right;
	}
	public void setRight(BNode<T> right) {
		this.right = right;
	}
	public BNode<T> getLeft() {
		return left;
	}
	public void setLeft(BNode<T> left) {
		this.left = left;
	}
	public boolean hasLeft() {
		return left!=null;
	}
	public boolean hasRight() {
		return right!=null;
	}
	public boolean isLeaf() {
		return (left==null)&(right==null);
	}
	public BNode<T> copy(){
		BNode<T> copied = new BNode<>(data);
		if(right!=null)
			copied.setRight(right.copy());
		if(left!=null)
			copied.setLeft(left.copy());
		return copied;
	}
	public int getHeight() {
		return getHeight(this);
	}
	private int getHeight(BNode<T> node) {
		int height = 0;
		if(node!=null)
			height=1+Math.max(getHeight(node.left),getHeight(node.right));
		return height;
	}
	public int getNumberOfNodes() {
		int leftNodes=0;
		int rightNodes=0;
		if(right!=null)
			rightNodes=right.getNumberOfNodes();
		if(left!=null)
			leftNodes=left.getNumberOfNodes();
		return 1+rightNodes+leftNodes;
	}
}
