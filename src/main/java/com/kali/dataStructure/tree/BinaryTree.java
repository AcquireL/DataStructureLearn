package com.kali.dataStructure.tree;

/**
 *  实现二叉树的初始化
 * @param <Key>
 * @param <Value>
 */
public class BinaryTree<Key extends Comparable<Key>,Value> {
    // 记录根节点
    private Node root;
    // 记录树中元素的个数
    private int N;

    // 获取树的大小
    public int getSize(){
        return N;
    }

    // 向树中插入一个键值对
    public void put(Key key,Value value){
        root=put(root,key,value);
    }
    //  给指定树x上，添加一个键值对，并返回添加后的新树
    private Node put(Node x,Key key,Value value){
        // 如果x子树为空
        if(x==null){
            N++;
            return new Node(key,value,null,null);
        }
        // 如果x子树不为空 比较x节点的键和key的大小
        int cmp = key.compareTo((Key)x.key);
        if(cmp>0){
            // 如果key大于x节点的键，则继续找x节点的有子树
            x.right= put(x.right,key,value);
        } else if(cmp<0){
            // 如果key小于x结点的键，则继续找x节点的左子树
            x.left= put(x.left,key,value);
        } else {
            // 如果key等于x节点的键，则替换x结点的值value
            x.value=value;
        }
        return x;
    }

    //  查询树中指定key对应的value
    public Value get(Key key){
        return get(root,key);
    }
    // 从指定的树x中，查找key对应的值
    public Value get(Node x,Key key){
        // x树为null
        if(x==null){
            return null;
        }
        int cmp = key.compareTo((Key) x.key);
        // 如果key大于
        if(cmp>0){
           return (Value)get(x.right,key);
        }else if(cmp<0){
            return (Value)get(x.left,key);
        }else {
            // 如果key等于x节点的键，就找到了键为key的节点，只需要返回x节点的值即可
            return (Value)x.value;
        }
    }

    // 删除树中key对应的value
    public void delete(Key key){
        delete(root,key);
    }

    // 删除指定树x中的key对应的value，并返回删除后的新树
    public Node delete(Node x,Key key){
        // x树为null
        if(x==null){
            return null;
        }
        // x树不为null
        int cmp= key.compareTo((Key)x.key);
        if(cmp>0){
            x.right=delete(x.right,key);
        }else if(cmp<0){
            x.left=delete(x.left,key);
        }else {
            // 如果key等于x节点的键，完成真正的删除节点动作，要删除的节点就是x
            N--;
            // 得找到右子树中最小的节点
            if(x.right==null){
                return x.left;
            }
            if(x.left==null){
                return x.right;
            }

            Node minNode=x.right;
            while (minNode.left!=null){
                minNode=minNode.left;
            }
            // 删除右子树中最小的节点
            Node n= x.right;
            while (n.left!=null){
                if(n.left.left==null){
                    n.left=null;
                }else {
                    // 变换n节点即可
                    n=n.left;
                }
            }
            // 让x节点的左子树变成minNode的左子树
            minNode.left=x.left;
            // 让x节点的右子树成为minNode的右子树
            minNode.right=x.right;
            // 让x接点的父节点指向minNode
            x=minNode;
        }
        return x;
    }
    private class Node<Key,Value>{
        // 存储键值
        private Key key;
        // 存储值
        private Value value;
        // 记录左子节点
        private Node left;
        //  记录右子节点
        private Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer, String> tree = new BinaryTree<>();
        tree.put(8,"rose");
        tree.put(3,"wanger");
        tree.put(5,"zhangsan");
        tree.put(1,"aaa");
        tree.put(4,"bbb");
        System.out.println(tree.getSize());
        System.out.println(tree.get(3));
        tree.delete(3);
        System.out.println(tree.get(3));
        System.out.println(tree.get(4));
        System.out.println(tree.getSize());
    }
}
