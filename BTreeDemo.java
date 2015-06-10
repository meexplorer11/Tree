package tree;

public class BTreeDemo {

	public static void main(String[] args) {
		int[] input = {5,3,18,4,2,9,19};
		BTree tree = new BTree();
		for(int value : input) {
			System.out.println("Adding input- "+value+" >"+tree.add(value));
		}
		
		System.out.println("\nInorder traversal-----");
		tree.inorderTraversal();
		
		System.out.println("\nPreorder traversal-----");
		tree.preorderTraversal();
		
		System.out.println("\nPostorder traversal-----");
		tree.postorderTraversal();
		
		System.out.println("\n\nFind 18- "+tree.search(18));
		System.out.println("Find 9- "+tree.search(9));
		
		System.out.println("\nMin value in this tree- "+tree.minValue());
		System.out.println("Max value in this tree- "+tree.maxValue());
		
		System.out.println("\nSize of the tree- "+tree.size());
		
		tree.reverseLevelOrderTraversal();
		
		System.out.println("\nHeight of the tree- "+tree.height());
		
		System.out.println("\nNo of leaf nodes- "+tree.noOfLeafNodes());
		System.out.print("Leaf nodes- ");
		tree.printLeafNodes();
		
		System.out.println("\n\nNo of Full nodes- "+tree.noOfFullNodes());
		System.out.print("Full nodes- ");
		tree.printFullNodes();
		
		System.out.println("\n\nNo of Half nodes- "+tree.noOfHalfNodes());
		System.out.print("Half nodes- ");
		tree.printHalfNodes();
		
		System.out.println("\n\nCheck if two trees are identical starts---");
		BTree tree1 = new BTree();
		for(int value : input) {
			tree1.add(value);
		}
		System.out.println("isStructurallyIdentical ----"+tree.isStructurallyIdentical(tree.getRoot(), tree1.getRoot()));
		
		tree1 = new BTree();
		input = new int[]{18,4,2,9,19};
		for(int value : input) {
			tree1.add(value);
		}
		System.out.println("isStructurallyIdentical ----"+tree.isStructurallyIdentical(tree.getRoot(), tree1.getRoot()));
		System.out.println("\nCheck if two trees are identical end----------");
		
		System.out.println("\nLevel with maximum sum- "+tree.levelWithMaximumSum(true)+", sum- "+tree.levelWithMaximumSum(false));
		
		System.out.println("\nPrinting all root to leaf paths-");
		tree.printRootToLeafPaths();
		
		System.out.println("\n\nChecksum-");
		System.out.println("Is there a path with sum 10- "+tree.checkSum(10));
		System.out.println("Is there a path with sum 32- "+tree.checkSum(32));
		System.out.println("Is there a path with sum 50- "+tree.checkSum(50));
		
		System.out.println("\n\nSum of all nodes- "+tree.sumOfAllNodes());
		
		/*System.out.println("\n\nConverting to its mirror image- ");
		tree.mirrorImage();
		System.out.println("\nInorder traversal-----");
		tree.inorderTraversal();*/
		
		System.out.println("\n\nPrint all ancestors- ");
		System.out.print("Ancestors of 9- ");
		tree.printAncestors(9);
		
		System.out.println("\n\nFindin lowest common ancestor-");
		System.out.println("LCA of 9 and 19- "+tree.findLowestCommonAncestor(9, 19));
		System.out.println("LCA of 2 and 19- "+tree.findLowestCommonAncestor(2, 19));
		
		System.out.println("\n\nRemove Half node starts-----");
		BTree t = new BTree();
		input = new int[]{7,8,10,9,2,5,4,6};
		for(int value : input) {
			t.add(value);
		}
		t.inorderTraversal();
		System.out.println("\nIs this a full binary tree- "+t.isFullBinaryTree());
		t.removeHalfNodes();
		System.out.println("\nAfter removing half nodes-");
		t.inorderTraversal();
		System.out.println("\nRemove Half node ends-----");
		
		tree.findKthLargestNode(2);
		
		System.out.println("\nIs this a full binary tree- "+tree.isFullBinaryTree());
		tree.findLeftLeaves();
	}

}
