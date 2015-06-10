package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


/**
 * 
 * @author Vivek Maurya
 *<pre>
 * Sample tree for this class
 * 
 * 
 *      5             --------- Level 0
 *     / \
 *    /   \
 *   3     18         --------- Level 1
 *  / \    / \
 * 2   4  9  19       --------- Level 2
 *</pre> 
 */
public class BTree {

	private BNode root;
	private static int nodeCounter;
	
	public BTree() {
		root = null;
		nodeCounter = 0;
	}
	
	public void findLeftLeaves() {
		if(root == null)
			System.out.println("Tree is empty !");
		
		List<BNode> nodeList = new ArrayList<BNode>();
		root.findLeftLeaves(nodeList);
		
		if(nodeList.isEmpty())
			System.out.println("No left leaves !");
		else {
			System.out.println("Left leaves- ");
			for(BNode node : nodeList) {
				System.out.print(node.value+", ");
			}
		}
	}
	
	public boolean isFullBinaryTree() {
		if(root == null)
			return true;
		
		return root.isFullBinaryTree();
	}
	
	public void findKthLargestNode(int k) {
		if(root == null)
			System.out.println("Tree is empty !");
		
		if(k == -1)
			System.out.println("Index should be positive number.");
		
		root.findKthLargestNode(k);
	}
	
	public void removeHalfNodes() {
		if(root == null)
			System.out.println("Tree is empty, nothing to remove !");
		
		root.removeHalfNodes(root);
	}
	
	public int findLowestCommonAncestor(int node1, int node2) {
		if(root == null)
			return -1;
		
		return root.findLowestCommonAncestor(node1, node2);
	}
	
	
	public void printRootToLeafPaths() {
		Deque<Integer> nodeQ = new LinkedList<Integer>();
		root.printRootToLeafPaths(nodeQ);
	}
	
	public boolean checkSum(int sum) {
		if(root == null)
			return (sum == 0);
		
		return root.checkSum(sum);
	}
	
	public int sumOfAllNodes() {
		if(root == null)
			return 0;
		
		return root.sumOfAllNodes();
	}
	
	public void mirrorImage() {
		if(root == null)
			return;

		root.mirrorImage();
	}
	
	/**
	 * Returns true if two trees are mirrors of each other.<br/>
	 * Value of node doesn't matter just their structure must be same. 
	 */
	public boolean isMirrorImage(BNode root1, BNode root2) {
		if (root1 == null && root2 == null)
			return true;

		if (root1 == null || root2 == null)
			return false;

		return isMirrorImage(root1.getLeft(), root2.getRight())
				&& isMirrorImage(root1.getRight(), root2.getLeft());
	}
	
	public void printAncestors(int value) {
		if(root == null)
			System.out.println("Tree is empty");
		
		if(root.getValue() == value)
			System.out.println("Node is root of the tree, so no ancestors !");
		else {
			Queue<Integer> nodeQ = new LinkedList<Integer>();
			root.printAncestors(nodeQ, value);
		}
	}
	
	/**
	 * Returns true if both trees are structurally identical<br/>
	 * If both nodes are null, trees are identical.<br/>
	 * If either one is non-null, trees are not identical.<br/>
	 * If both nodes are not null-<br/>
	 * Check whether current node's value is equal or not.<br/>
	 * <strong>a)</strong> If values are not equal, trees are not identical.<br/>
	 * <strong>b)</strong> If values are equal, recursively check for left/right sub-tree of both trees. 
	 */
	public boolean isStructurallyIdentical(BNode root1, BNode root2) {
		if (root1 == null && root2 == null)
			return true;

		if (root1 == null || root2 == null)
			return false;

		return (root1.getValue() == root2.getValue())
				&& isStructurallyIdentical(root1.getLeft(), root2.getLeft())
				&& isStructurallyIdentical(root1.getRight(), root2.getRight());
	}

	/**
	 * Returns level which has maximum sum of nodes if <code>onlyLevel</code> is true
	 * otherwise returns maximum sum of a particular level.<br/>
	 * For sample tree of this class, this method returns 'Level 2' and sum '34' of all level 2 nodes.
	 */
	public int levelWithMaximumSum(boolean onlyLevel) {
		if(root == null)
			return 0;
		
		int levelSum = 0, maxSum = 0;
		int level = 0, maxLevel = 0;
		Queue<BNode> nodeQ = new LinkedList<BNode>();
		nodeQ.offer(root);
		nodeQ.offer(null);
		
		BNode currentNode = null;
		while(!nodeQ.isEmpty()) {
			currentNode = nodeQ.poll();
			
			if(currentNode == null) {
				if(levelSum > maxSum) {
					maxSum = levelSum;
					maxLevel = level;
				}
				levelSum = 0;
				level++;
				
				if(!nodeQ.isEmpty())
					nodeQ.offer(null);
				
			} else {
				levelSum += currentNode.getValue();
				
				if(currentNode.getLeft() != null)
					nodeQ.offer(currentNode.getLeft());
				
				if(currentNode.getRight() != null)
					nodeQ.offer(currentNode.getRight());
			}
		}
		
		if(onlyLevel)
			return maxLevel;
		
		return maxSum;
	}
	
	public BNode getRoot() {
		return root;
	}
	
	public int height() {
		if (root == null)
			return 0;

		return root.height();
	}

	public int noOfLeafNodes() {
		if (root == null)
			return 0;

		List<BNode> nodeList = new ArrayList<BNode>();
		root.getLeafNodes(nodeList);

		return nodeList.size();
	}

	public void printLeafNodes() {
		if (root == null)
			return;

		List<BNode> nodeList = new ArrayList<BNode>();
		root.getLeafNodes(nodeList);

		for (BNode node : nodeList) {
			System.out.print(node.value + ", ");
		}
	}

	public int noOfFullNodes() {
		if (root == null)
			return 0;

		List<BNode> nodeList = new ArrayList<BNode>();
		root.getFullNodes(nodeList);

		return nodeList.size();
	}

	public void printFullNodes() {
		if (root == null)
			return;

		List<BNode> nodeList = new ArrayList<BNode>();
		root.getFullNodes(nodeList);

		for (BNode node : nodeList) {
			System.out.print(node.value + ", ");
		}
	}

	public int noOfHalfNodes() {
		if (root == null)
			return 0;

		List<BNode> nodeList = new ArrayList<BNode>();
		root.getHalfNodes(nodeList);

		return nodeList.size();
	}

	public void printHalfNodes() {
		if (root == null)
			return;

		List<BNode> nodeList = new ArrayList<BNode>();
		root.getHalfNodes(nodeList);

		for (BNode node : nodeList) {
			System.out.print(node.value + ", ");
		}
	}
	
	/**
	 * If root is null(tree is empty), create root node and return this node<br/>
	 * otherwise call add method. 
	 */
	public boolean add(int value) {
		if(root == null) {
			root = new BNode(value);
			return true;
		} else {
			return root.add(value);
		}
	}
	
	/**
	 * If root is null(tree is empty), there is nothing to remove.<br/>
	 * If node to be removed is the root node-<br/>
	 * <strong>a)</strong> Create a temporary node as root's parent.<br/>
	 * <strong>b)</strong> Remove root node.<br/>
	 * <strong>c)</strong> Make temporary node's left child as tree's root.<br/>
	 * 
	 */
	public boolean remove(int value) {
		boolean result = false;
		if(root == null) {
			result = false;
		} else if(value == root.value) {
			BNode tempNode = new BNode(0);
			tempNode.setLeft(root);
			result = root.remove(value, tempNode);
			root = tempNode.getLeft();
		} else {
			result = root.remove(value, null);
		}
		
		return result;
	}
	
	/**
	 * If root is null(tree is empty), return false<br/>
	 * If value is equal to node's value, return true<br/>
	 * otherwise call search recursively.
	 */
	public boolean search(int value) {
		if(root == null) {
			return false;
		} else if(value == root.value) {
			return true;
		} else {
			return root.search(value);
		}
	
	}
	
	public int size() {
		if(root == null)
			return 0;
		
		return root.size();
	}
	

	/**
	 * Traverse tree in reverse level order
	 * Output should be - 2,4,9,19,3,18,5
	 */
	public void reverseLevelOrderTraversal() {
		Stack<Integer> nodeStack = new Stack<Integer>();
		nodeStack.push(root.value);
		root.reverseLevelOrderTraversal(nodeStack);
		
		while(!nodeStack.isEmpty()) {
			System.out.print(nodeStack.pop()+", ");
		}
	}
	
	public void inorderTraversal() {
		root.inorderTraversal();
	}
	
	public void preorderTraversal() {
		root.preorderTraversal();
	}
	
	public void postorderTraversal() {
		root.postorderTraversal();
	}
	
	public int minValue() {
		if(root == null)
			return 0;
		
		return root.minValue();
	}
	
	public int maxValue() {
		if(root == null)
			return 0;
		
		return root.maxValue();
	}
	
	/**
	 * Actual Tree implementation. 
	 *
	 */
	private static final class BNode {
		BNode left;
		BNode right;
		int value;
		
		public BNode(int value) {
			left = null;
			right = null;
			this.value = value;
		}
		
		/**
		 * Method finds all left leaf nodes of a Tree.<br/>
		 * Leaf node which is left child of a node. 
		 */
		private void findLeftLeaves(List<BNode> nodeList) {
			if(isLeaf(this.left))
				nodeList.add(this.left);
			else if(this.left != null)
				this.left.findLeftLeaves(nodeList);
			
			if(this.right != null)
				this.right.findLeftLeaves(nodeList);
		}
		
		private boolean isLeaf(BNode node) {
			if(node == null)
				return false;
			
			if(node.left == null && node.right == null)
				return true;
			
			return false;
		}

		/**
		 * In full binary tree, every node has 0 or 2 children.<br/>
		 * If node's left and right sub-trees are null, this sub-tree is full tree.<br/>
		 * If both children are not null, check recursively for both children.
		 */
		private boolean isFullBinaryTree() {
			if(this.left == null && this.right == null)
				return true;
			
			if(this.left != null && this.right != null)
				return this.left.isFullBinaryTree() && this.right.isFullBinaryTree();
			
			return false;
		}

		/**
		 * Traverse the tree in reverse inorder, in this way the values<br/>
		 * would be in decreasing order.<br/>
		 * Count visited nodes and compare node counter with passed K.<br/>
		 * If node counter is equal to K, it means current node is Kth largest<br/> 
		 * in the Tree.
		 */
		private void findKthLargestNode(int k) {
			if(nodeCounter >= k)
				return ;
			
			if(this.right != null)
				this.right.findKthLargestNode(k);
			
			nodeCounter++;
			
			if(nodeCounter == k)
				System.out.println(k+"th largest value in this tree is- "+this.value);
			
			if(this.left != null)
				this.left.findKthLargestNode(k);
			
		}

		/**
		 * Method to remove Half nodes in a tree.<br/>
		 * If one of its left and right child is null for a node, remove<br/>
		 * this node and assign its non-null child to its parent node. 
		 */
		private void removeHalfNodes(BNode parent) {
			if((this.left == null && this.right != null) || (this.left != null && this.right == null)) {
				if(parent.left == this) {
					parent.left = this.left != null ? this.left : this.right;
					parent.left.removeHalfNodes(parent);
				}
				
				if(parent.right == this) {
					parent.right = this.left != null ? this.left : this.right;
					parent.right.removeHalfNodes(parent);
				}
			} else {
				if(this.left != null)
					this.left.removeHalfNodes(this);
				
				if(this.right != null)
					this.right.removeHalfNodes(this);
			}
			
		}

		/**
		 * Lowest common ancestor(LCA) of two nodes is the lowest node which has<br/>
		 * two nodes as its descendants.<br/>
		 * If current node's value lies between node1 and node2, this node is LCA.<br/>
		 * If node's value is greater than both node1 and node2, it means we are in the right sub-tree,<br/>
		 * in this case search LCA in left sub-tree. <br/>
		 * If node's value is less than both node1 and node2, it means we are in the left sub-tree,<br/>
		 * in this case search LCA in right sub-tree. 
		 */
		private int findLowestCommonAncestor(int node1, int node2) {
			if(this.value > node1 && this.value < node2)
				return this.value;
			else if(this.value > node1 && this.value > node2)
				return this.left.findLowestCommonAncestor(node1, node2);
			else if(this.value < node1 && this.value < node2)
				return this.right.findLowestCommonAncestor(node1, node2);

			return -1;
		}

		/**
		 * Method prints all ancestors of a node.<br/>
		 * Idea is to search the value in the tree.<br/>
		 * Visit a node and if it is not the target node, put this node in a queue.<br/>
		 * Once a node is found, print all the elements of the queue.  
		 */
		private void printAncestors(Queue<Integer> nodeQ, int value) {
			if(value == this.value) {
				while(!nodeQ.isEmpty()) {
					System.out.print(nodeQ.poll()+", ");
				}
			}
			else if(value < this.value) {
				nodeQ.offer(this.value);
				if(this.left != null)
					this.left.printAncestors(nodeQ, value);
			}
			else if(value > this.value) {
				nodeQ.offer(this.value);
				if(this.right != null)
					this.right.printAncestors(nodeQ, value);
			}
		}

		/**
		 * Converts this tree to its mirror image.<br/>
		 * Swap left and right child of each node and swap recursively.
		 */
		private void mirrorImage() {
			BNode temp = this.left;
			this.left = this.right;
			this.right = temp;
			
			if(this.left != null)
				this.left.mirrorImage();
			
			if(this.right != null)
				this.right.mirrorImage();
		}

		/**
		 * Method returns sum of all nodes. 
		 */
		private int sumOfAllNodes() {
			return this.value+(this.left != null ? this.left.sumOfAllNodes() : 0)+
					(this.right != null ? this.right.sumOfAllNodes() : 0);
		}

		/**
		 * Method returns true, if there exists a path from root to any leaf node in such a way<br/>
		 * that sum of all nodes in this path is equal to given sum.<br/>
		 * While visiting a node, subtract node's value from given sum.<br/>
		 * While traversing path from root to leaf node, for a leaf node if difference is equal to 0,<br/>
		 * it means path exists with the given sum.<br/>
		 * For non-leaf nodes, keep checking recursively until all leaf nodes are visited.<br/> 
		 */
		private boolean checkSum(int sum) {
			int remainingSum = sum - this.value;
			if(this.left == null && this.right == null)
				return (remainingSum == 0);
			
			return ((this.left != null ? this.left.checkSum(remainingSum) : false) 
					|| (this.right != null ? this.right.checkSum(remainingSum) : false));
		}

		/**
		 * Prints all root to leaf node paths.<br/>
		 * Keep adding the current node in the Queue until a leaf node is found,<br/>
		 * then print the Queue and remove last added node. 
		 */
		private void printRootToLeafPaths(Deque<Integer> nodeQ) {
			nodeQ.offer(this.value);
			if(this.left == null && this.right == null) {
				System.out.println(nodeQ);
			} else {
				if(this.left != null)
					this.left.printRootToLeafPaths(nodeQ);
				
				nodeQ.pollLast();
				
				if(this.right != null)
					this.right.printRootToLeafPaths(nodeQ);
				
				nodeQ.pollLast();
			}
				
		}

		public BNode getLeft() {
			return left;
		}

		public void setLeft(BNode left) {
			this.left = left;
		}

		public BNode getRight() {
			return right;
		}

		public void setRight(BNode right) {
			this.right = right;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		/**
		 * <p>
		 * Method to add a value in the tree.<br/>
		 * If value to added-<br/>
		 * 
		 * <strong>1.</strong> Is equal to current node's value, it means a node already exists.<br/> 
		 * In this case, do not create duplicate node.<br/>
		 * 
		 * <strong>2.</strong> Is less than current node's value, go to its left sub-tree.<br/>
		 * While traversing left, if there exists a node whose left node is null it means <br/>
		 * it is a leaf node, create a new node as its left child and return true.<br/>
		 * 
		 * <strong>3.</strong> Is greater than current node's value, go to its right sub-tree.<br/>
		 * While traversing right, if there exists a node whose right node is null it means<br/>
		 * it is a leaf node, create a new node as its right child and return true.<br/>
		 * </p> 
		 */
		private boolean add(int value) {
			if(value == this.value) {
				return false;
			} else if(value < this.value) {
				if(this.left == null) {
					left = new BNode(value);
					return true;
				} else {
					return this.left.add(value);
				}
			} else if(value > this.value) {
				if(this.right == null) {
					right = new BNode(value);
					return true;
				} else {
					return this.right.add(value);
				}
			}
			
			return false;
		}
		
		/**
		 * <strong>1.</strong> If value is equal to node's value, return true.<br/>
		 * <strong>2.</strong> If value is less than node's value, keep searching its left recursively.<br/>
		 * <strong>3.</strong> If value is greater than node's value, keep searching its right recursively.<br/>
		 * 
		 * While searching left and right sub-trees, if leaf node is encountered,<br/>
		 * it means value doesn't exist in tree.  
		 */
		private boolean search(int value) {
			if(value == this.value) {
				return true;
			} else if(value < this.value) {
				if(this.left == null) {
					return false;
				} else {
					return this.left.search(value);
				}
			} else if(value > this.value) {
				if(this.right == null) {
					return false;
				} else {
					return this.right.search(value);
				}
			}
			
			return false;
		}
		
		/**
		 * To delete a node, first find the value in the tree.<br/>
		 * If value is less than/greater than node's value, keep searching its<br/>
		 * left/right sub-trees.<br/> 
		 * If leaf node is encountered and value is not found,<br/>
		 * return false as there is nothing to delete.<br/><br/>
		 * 
		 * If a node is found with given value and-<br/>
		 * <strong>1.</strong> Node has left and right child nodes.<br/>
		 * <strong>a)</strong> Find the minimum value in the right sub-tree(Suppose this node is M)<br/>
		 * <strong>b)</strong> Replace this minimum value with the node's value<br/>
		 * <strong>c)</strong> Then delete node M from the right sub-tree<br/>
		 * 
		 * <strong>2.</strong> Node has only left or right child.<br/>
		 * If node to be deleted is left child, assign its single child to its parent's left/right  
		 */
		private boolean remove(int value, BNode parent) {
			if(value < this.value) {
				if(this.left != null) {
					return this.left.remove(value, this);
				} else {
					return false;
				}
			} else if(value > this.value) {
				if(this.right != null) {
					return this.right.remove(value, this);
				} else {
					return false;
				}
			} else {
				if(this.left != null && this.right != null) {
					this.value = right.minValue();
					right.remove(this.value, this);
				} else if(parent.left == this) {
					parent.left = this.left != null ? this.left : this.right;
				} else if(parent.right == this) {
					parent.right = this.left != null ? this.left : this.right;
				}
				return true;
			}
		}
		
		/**
		 * Method returns the height of the tree. 
		 */
		private int height() {
			int leftHeight = 0, rightHeight = 0;

			if (this.left != null)
				leftHeight = this.left.height();

			if (this.right != null)
				rightHeight = this.right.height();

			if (leftHeight > rightHeight)
				return leftHeight + 1;
			return rightHeight + 1;
		}

		/**
		 * Returns all leaf nodes.<br/>
		 * A node is leaf node if its left and right both children are null.<br/>
		 * If a node is leaf node, add it to the list otherwise check the same <br/>
		 * for its left/right sub-trees recursively. 
		 */
		private void getLeafNodes(List<BNode> nodeList) {
			if (this.left == null && this.right == null)
				nodeList.add(this);

			if (this.left != null)
				this.left.getLeafNodes(nodeList);

			if (this.right != null)
				this.right.getLeafNodes(nodeList);
		}

		/**
		 * Returns all Full nodes.<br/>
		 * A node is full node if its left and right both children are not null.<br/>
		 * If a node is full node, add it to the list otherwise check the same <br/>
		 * for its left/right sub-trees recursively. 
		 */
		private void getFullNodes(List<BNode> nodeList) {
			if (this.left != null && this.right != null)
				nodeList.add(this);

			if (this.left != null)
				this.left.getFullNodes(nodeList);

			if (this.right != null)
				this.right.getFullNodes(nodeList);
		}

		/**
		 * Returns all Half nodes.<br/>
		 * A node is half node if it has only one child, left or right<br/>
		 * If a node is half node, add it to the list otherwise check the same <br/>
		 * for its left/right sub-trees recursively. 
		 */
		private void getHalfNodes(List<BNode> nodeList) {
			if ((this.left != null && this.right == null)
					|| (this.left == null && this.right != null))
				nodeList.add(this);

			if (this.left != null)
				this.left.getHalfNodes(nodeList);

			if (this.right != null)
				this.right.getHalfNodes(nodeList);
		}
		
		/**
		 * While going left, if left child of a node is null.<br/>
		 * It means this node is the leftmost and its value is the minimum value. 
		 */
		private int minValue() {
			if(this.left == null) {
				return this.value;
			}
			return this.left.minValue();
		}
		
		/**
		 * While going right, if right child of a node is null.<br/>
		 * It means this node is the rightmost and its value is the maximum value. 
		 */
		private int maxValue() {
			if(this.right == null) {
				return this.value;
			}
			
			return this.right.maxValue();
		}
		
		private int size() {
			return 1 + (this.left != null ? this.left.size() : 0)
					+ (this.right != null ? this.right.size() : 0);
		}
		
		private void inorderTraversal() {
			if(this.left != null)
				this.left.inorderTraversal();
			
			System.out.print(this.value+", ");
			
			if(this.right != null)
				this.right.inorderTraversal();
		}
		
		private void preorderTraversal() {
			System.out.print(this.value+", ");

			if(this.left != null)
				this.left.preorderTraversal();
			
			if(this.right != null)
				this.right.preorderTraversal();
		}
		
		private void postorderTraversal() {
			if(this.left != null)
				this.left.inorderTraversal();
			
			if(this.right != null)
				this.right.inorderTraversal();
			
			System.out.print(this.value+", ");
		}
		
		private void reverseLevelOrderTraversal(Stack<Integer> nodeStack) {
			if(this.right != null)
				nodeStack.push(this.right.value);
			
			if(this.left != null)
				nodeStack.push(this.left.value);
			
			if(this.right != null)
				this.right.reverseLevelOrderTraversal(nodeStack);
			
			if(this.left != null)
				this.left.reverseLevelOrderTraversal(nodeStack);
		}
	}
}
