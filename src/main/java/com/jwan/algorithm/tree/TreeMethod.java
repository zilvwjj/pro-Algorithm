package com.jwan.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeMethod {
    /**
     *  Maximum Depth of Binary Tree (Easy)
     * 题目描述
     * 求一个二叉树的最大深度。
     * 输入输出样例
     * 输入是一个二叉树，输出是一个整数，表示该树的最大深度。
     * Input:
     *  3
     *  /\
     * 9 20
     *   /\
     *  15 7
     * Output: 3
     * 题解
     * 利用递归，我们可以很方便地求得最大深度。
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        return root != null? 1 + Math.max(maxDepth(root.left),maxDepth(root.right)) : 0;
    }


    /**
     * Balanced Binary Tree (Easy)
     * 题目描述
     * 判断一个二叉树是否平衡。树平衡的定义是，对于树上的任意节点，其两侧节点的最大深度
     * 的差值不得大于 1。
     * 输入输出样例
     * 输入是一个二叉树，输出一个布尔值，表示树是否平衡。
     * Input:
     *      1
     *     /\
     *    2  2
     *   /\
     *  3  3
     * /\
     * 4 4
     * Output: false
     * 题解
     * 解法类似于求树的最大深度，但有两个不同的地方：一是我们需要先处理子树的深度再进行
     * 比较，二是如果我们在处理子树时发现其已经不平衡了，则可以返回一个-1，使得所有其长辈节
     * 点可以避免多余的判断（本题的判断比较简单，做差后取绝对值即可；但如果此处是一个开销较
     * 大的比较过程，则避免重复判断可以节省大量的计算时间）
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {

        return helper(root) != -1;
    }

    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = helper(root.left), right = helper(root.right);

        if (left == -1 || right == -1 || Math.abs(left-right) > 1){
            return -1;
        }

        return 1 + Math.max(left,right);
    }

    /**
     * Diameter of Binary Tree (Easy)
     * 题目描述
     * 输入输出样例
     * Input:
     *      1
     *     /\
     *    2  3
     *   /\
     *  4  5
     * Output: 3
     * 求一个二叉树的最长直径。直径的定义是二叉树上任意两节点之间的无向距离。
     * 输入是一个二叉树，输出一个整数，表示最长直径。
     * 在这个样例中，最长直径是 [4,2,1,3] 和[5,2,1,3]。
     * 题解
     * 同样的，我们可以利用递归来处理树。解题时要注意，在我们处理某个子树时，我们更新的
     * 最长直径值和递归返回的值是不同的。这是因为待更新的最长直径值是经过该子树根节点的最长
     * 直径（即两侧长度）；而函数返回值是以该子树根节点为端点的最长直径值（即一侧长度），使用
     * 这样的返回值才可以通过递归更新父节点的最长直径值）。
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        int[] ans = new int[1];
        diameterOfBinaryTreeHelper(root,ans);
        return ans[0];
    }

    public int diameterOfBinaryTreeHelper(TreeNode root , int[] ans) {
        if (root == null) {
            return 0;
        }

        int left = diameterOfBinaryTreeHelper(root.left,ans), right = diameterOfBinaryTreeHelper(root.right,ans);
        ans[0] = Math.max(ans[0],left+right);
        return 1 + Math.max(left,right);
    }

    /**
     * Path Sum III (Easy)
     * 题目描述
     * 给定一个整数二叉树，求有多少条路径节点值的和等于给定值。
     * 输入输出样例
     * 输入一个二叉树和一个给定整数，输出一个整数，表示有多少条满足条件的路径。
     * Input: sum = 8, tree =
     * 10
     * /\
     * 5  -3
     * /\   \
     * 3  2  11
     * /\  \
     * 3 -2  1
     * Output: 3
     * 题解
     * 递归每个节点时，需要分情况考虑：（1）如果选取该节点加入路径，则之后必须继续加入连
     * 续节点，或停止加入节点（2）如果不选取该节点加入路径，则对其左右节点进行重新进行考虑。
     * 因此一个方便的方法是我们创建一个辅函数，专门用来计算连续加入节点的路径。
     * 在这个样例中，和为8 的路径一共有三个：[[5,3],[5,2,1],[-3,11]]。
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        int ans = 0;
        int curCount = 0;
        if (root != null) {
            ans = pathSumHelper(root,targetSum,curCount,ans);
            ans += pathSum(root.left,targetSum);
            ans += pathSum(root.right,targetSum);
        }
        return ans;
    }

    public int pathSumHelper(TreeNode root,int targetSum,int curCount,int ans){

        if (root==null) return ans;

        if (curCount + root.val == targetSum) {
             ans++;
        }
        ans = pathSumHelper(root.left,targetSum,curCount+root.val,ans);
        ans = pathSumHelper(root.right,targetSum,curCount+root.val,ans);

        return ans;
    }


    /**
     * Symmetric Tree (Easy)
     * 题目描述
     * 判断一个二叉树是否对称。
     *
     * 输入输出样例
     * 输入一个二叉树，输出一个布尔值，表示该树是否对称。
     * Input:
     * 1
     * /\
     * 22
     * /\/\
     * 3443
     * Output: true
     * 题解
     * 判断一个树是否对称等价于判断左右子树是否对称。笔者一般习惯将判断两个子树是否相等
     * 或对称类型的题的解法叫做“四步法”：（1）如果两个子树都为空指针，则它们相等或对称（2）
     * 如果两个子树只有一个为空指针，则它们不相等或不对称（3）如果两个子树根节点的值不相等，
     * 则它们不相等或不对称（4）根据相等或对称要求，进行递归处理
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : isSymmetric(root.left,root.right);
    }

    public boolean isSymmetric(TreeNode left,TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val){
            return false;
        }

        return isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
    }

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return null;
        List<Double> ans = new ArrayList<>();
        List<TreeNode>  row = new ArrayList<>();
        row.add(root);
        while (!row.isEmpty()){
            int count = row.size(), sum = 0;
            for (int i = 0; i < count; i++) {
                TreeNode remove = row.remove(0);
                sum += remove.val;
                if (remove.left !=null) row.add(remove.left);
                if (remove.right !=null) row.add(remove.right);
            }
            ans.add((sum+0.0)/count);
        }

        return ans;
    }

    /**
     * 二叉树转链表 https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
     * Flatten Binary Tree to Linked List
     *
     * Given the root of a binary tree, flatten the tree into a "linked list":
     *
     * The "linked list" should use the same TreeNode class where the right child pointer points
     * to the next node in the list and the left child pointer is always null.
     * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
     * @param root
     */
    public void flatten(TreeNode root) {
        flattenHelper(root,null);
    }

    public TreeNode flattenHelper(TreeNode root , TreeNode pre) {
        if (root == null) {
            return pre;
        }
        TreeNode left = root.left;
        TreeNode right= root.right;
        if (pre != null) {
            pre.left = null;
            pre.right = root;
        }
        pre = root;
        pre = flattenHelper(left,pre);
        pre = flattenHelper(right,pre);
        return pre;

    }

    public void preorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val);
        System.out.print(" ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * 前序遍历Stack
     * @param root
     * @return
     */
    public List<Integer> preorderStack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> buff = new Stack<>();
        buff.push(root);

        while (!buff.isEmpty()){
            TreeNode pop = buff.pop();
            ans.add(pop.val);
            if (pop.right!=null) buff.push(pop.right);
            if (pop.left!=null) buff.push(pop.left);
        }

        return ans;
    }
    /**
     * 144 前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        preorderTraversal(root,ans);
        return ans;
    }

    public List<Integer> preorderTraversal(TreeNode root , List<Integer> ans) {
        if (root == null) {
            return ans;
        }
        ans.add(root.val);
        preorderTraversal(root.left,ans);
        preorderTraversal(root.right,ans);
        return ans;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val);
        System.out.print(" ");
        inorder(root.right);
    }



    public List<Integer> inorderStack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> buff = new Stack<>();
        TreeNode cur = root; //cur == null 左尽头标志 cur不为空 右边有数据
        while (!buff.isEmpty() || cur != null) {
            while (cur != null) {
                buff.push(cur);
                cur = cur.left;
            }
            cur = buff.peek().right;
            ans.add(buff.pop().val);
        }

        return ans;
    }


    /**
     * 94：中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorderTraversal(root,ans);
        return ans;
    }
    public List<Integer> inorderTraversal(TreeNode root ,List<Integer> ans) {
        if (root == null) {
            return ans;
        }
        inorderTraversal(root.left,ans);
        ans.add(root.val);
        inorderTraversal(root.right,ans);
        return ans;
    }


    public void postorder(TreeNode root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val);
        System.out.print(" ");
    }

    public List<Integer> postorderStack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> ansBuff = new Stack<>();
        Stack<TreeNode> buff = new Stack<>();
        buff.push(root);
        while (!buff.isEmpty()) {
            TreeNode temp = buff.pop();
            if (temp.left != null)
                buff.push(temp.left);
            if (temp.right != null)
                buff.push(temp.right);
            ansBuff.push(temp);
        }
        while (!ansBuff.isEmpty()) {
            ans.add(ansBuff.pop().val);
        }
        return ans;
    }


    /**
     * 145 后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        postorderTraversal(root,ans);
        return ans;
    }
    public List<Integer> postorderTraversal(TreeNode root ,List<Integer> ans) {
        if (root == null) {
            return ans;
        }
        postorderTraversal(root.left,ans);
        postorderTraversal(root.right,ans);
        ans.add(root.val);
        return ans;
    }

    /**
     * 最近公共祖先 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
     * Lowest Common Ancestor of a Binary Tree
     *
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined
     * between two nodes p and q as the lowest node in T that has both p and q as descendants
     * (where we allow a node to be a descendant of itself).”
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }
}
