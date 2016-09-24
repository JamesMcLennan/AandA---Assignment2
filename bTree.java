
public class bTree {
	
	private bTree root;
	private bTree left;
	private bTree right;
	private String data;
	
	public bTree(String data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.root = null;
	}
	
	public void addInTree(bTree node, bTree root) {
		//Check if root has been set.
		if(root == null) {
			root = node;
		}
	}
	
}