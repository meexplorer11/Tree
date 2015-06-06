package tree;

/**
 * BST implementation which allows duplicate keys.<br/>
 * Each node has a duplicate count, by default this count is 1.<br/>
 * While adding if a duplicate key is found, it increments the duplicate count<br/>
 * of the node by 1 otherwise a new node is added. <br/>
 * 
 * <pre>
 * For input 12, 10, 20, 9, 11, 10, 12, 12
 * tree would be-
 * 
 *        12(3)
 *       /    \
 *     10(2)  20(1)		
 *    /    \
 *  9(1)   11(1)
 *  </pre>
 */
public class DuplicateKeysTree {

	private BNode root;
	
	public DuplicateKeysTree() {
		root = null;
	}
	
	public void add(int value) {
		if(root == null) 
			root = new BNode(value);
		
		root.add(value);
	}
	
	public void inorderTraversal() {
		if(root == null)
			System.out.println("Tree is empty!");
		
		root.inorderTraversal();
	}
	
	public void remove(int value) {
		if(root == null)
			System.out.println("Tree is empty, nothing to remove !");
		else if(root.value == value) {
			if(root.value > 1) {
				root.duplicateCount--;
				System.out.println("Value deleted !");
			} else {
				BNode tempNode = new BNode(0);
				tempNode.left = root;
				root.remove(value, tempNode);
				root = tempNode.left;
			}
		} else {
			root.remove(value, null);
		}
	}
	
	private static final class BNode {
		BNode left;
		BNode right;
		int value;
		int duplicateCount;
		
		private BNode(int value) {
			this.left = this.right = null;
			this.value = value;
			duplicateCount = 1;
		}

		/**
		 * If target node is found and its duplicate count is greater than 1,<br/>
		 * decrement count by 1.<br/>
		 * If duplicate count is 1, delete the node. 
		 */
		private void remove(int value, BNode parentNode) {
			if(value < this.value) {
				if(this.left != null)
					this.left.remove(value, this);
				else
					System.out.println("Value not found !");
			} else if(value > this.value) {
				if(this.right != null)
					this.right.remove(value, this);
				else
					System.out.println("Value not found !");
			} else {
				if(this.duplicateCount > 1) {
					this.duplicateCount--;
				} else {
					if(this.left != null && this.right != null) {
						 this.value = this.right.minValue();
						 this.right.remove(this.value, this);
					} else if(parentNode.left == this) {
						parentNode.left = this.left != null ? this.left : this.right;
					} else if(parentNode.right == this) {
						parentNode.right = this.left != null ? this.left : this.right;
					}
				}
				System.out.println("Value deleted !");
			}
		}

		private int minValue() {
			if(this.left != null)
				return this.left.minValue();
			
			return this.value;
		}
		
		private void inorderTraversal() {
			if(this.left != null)
				this.left.inorderTraversal();
			
			System.out.print(this.value+"("+duplicateCount+"), ");
			
			if(this.right != null)
				this.right.inorderTraversal();
		}

		/**
		 * If duplicate key is found, increment its duplicate count by 1, <br/>
		 * if not keep calling add method recursively on left and right sub-trees.
		 */
		private void add(int value) {
			if(value == this.value) {
				this.duplicateCount++;
			} else if(value < this.value) {
				if(this.left != null)
					this.left.add(value);
				else
					this.left = new BNode(value);
				
			} else if(value > this.value) {
				if(this.right != null)
					this.right.add(value);
				else
					this.right = new BNode(value);
				
			}
		}
	}
}
