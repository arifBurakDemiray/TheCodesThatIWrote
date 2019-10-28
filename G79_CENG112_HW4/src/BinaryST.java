
public class BinaryST<T extends Comparable<? super T>> implements IBinaryST<T>{
	private BNode<T> root;
	public BNode<T> getRoot() {
		return root;
	}
	public void setRoot(BNode<T> root) {
		this.root = root;
	}
	public BinaryST() {
		root=null;
	}
	public BinaryST(T rootData) {
		root = new BNode<>(rootData);
	}
	public BinaryST(T rootData,BinaryST<T> right,BinaryST<T> left) {
		privateSetTree(rootData,right,left);
	}
	public void setTree(T rootData) {
		root = new BNode<T>(rootData);
	}
	public void setTree(T rootData,BinaryST<T> right,BinaryST<T> left) {
		privateSetTree(rootData,right,left);
	}
	private void privateSetTree(T rootData,BinaryST<T> right,BinaryST<T> left) {
		root = new BNode<>(rootData);
		if(right!=null)
			root.setRight(right.root);
		if(left!=null)
			root.setLeft(left.root);
	}
	public T getRootData() {
		T dataOf = null;
		if(root!=null)
			dataOf=root.getData();
		return dataOf;
	}
	public boolean isEmpty() {
		return root==null;
	}
	public void clear() {
		root=null;
	}
	public T getEntry(T entry) {
		return search(root,entry);
	}
	private T search(BNode<T> rootNode,T entry) {
		T result = null;
		if(rootNode!=null) {
			T rootData=rootNode.getData();
			if(entry.equals(rootData))
				result=rootData;
			else if(entry.compareTo(rootData)<0)
				result = search(rootNode.getLeft(),entry);
			else
				result = search(rootNode.getRight(),entry);
		}
		return result;
	}
	public boolean contains(T entry) {
		return getEntry(entry)!=null;
	}
	public T add(T entry) {
		T result = null;
		if(isEmpty())
			setRoot(new BNode<>(entry));
		else
			result=addNode(getRoot(),entry);
		return result;
	}
	private T addNode(BNode<T> node,T entry) {
		assert node!=null;
		T result=null;
		int compared = entry.compareTo(node.getData());
		if(compared==0) {
			result=node.getData();
			node.setData(entry);
		}
		else if(compared < 0) {
			if(node.hasLeft())
				result = addNode(node.getLeft(),entry);
			else
				node.setLeft(new BNode<>(entry));
		}
		else {
			assert compared > 0;
			if(node.hasRight())
				result=addNode(node.getRight(),entry);
			else
				node.setRight(new BNode<>(entry));
		}
		return result;
	}
	public void inorderTraverse() {
		inorderTraverse(root);
	}
	private void inorderTraverse(BNode<T> node) {
		if(node!=null) {
			inorderTraverse(node.getLeft());
			System.out.println(node.getData());
			inorderTraverse(node.getRight());
		}
	}
	}



