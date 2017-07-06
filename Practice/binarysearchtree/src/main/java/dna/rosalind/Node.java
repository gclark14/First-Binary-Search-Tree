package dna.rosalind;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by gabe.clark on 6/14/17.
 */
public class Node {
    Node left, right;
    int data;

    public Node(int data) {
        this.data = data;
    }

    public Node() {

    }

    void insert(int value) {
        if (value <= data) {
            if (left == null) {
                left = new Node(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new Node(value);
            } else {
                right.insert(value);
            }
        }
    }

    boolean contains(int value) {
        if (value == data) {
            return true;
        } else if (value < data) {
            if (left == null) {
                return false;
            } else {
                return left.contains(value);
            }
        } else {
            if (right == null) {
                return false;
            } else {
                return right.contains(value);
            }
        }
    }

    void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.println(data);
        if (right != null) {
            right.printInOrder();
        }
    }

    int getSmallestValue() {
        Node n = new Node();
        if (left == null) {
            return this.data;
        } else {
            n = left;
            return n.getSmallestValue();
        }
    }

    int getLargestValue() {
        Node n = new Node();
        if (right == null) {
            return this.data;
        } else {
            n = right;
            return n.getLargestValue();
        }
    }

    int get(int input) throws ValueOutOfRangeException {
        Node n = new Node();

        if (!contains(input))
            throw new ValueOutOfRangeException();

        if (data == input) {
            return data;
        } else {
            if (input < data) {
                n = left;
                return n.get(input);
            }
            n = right;
            return n.get(input);
        }
    }

    Node getSmallestNode() {
        Node n = new Node();
        if (left == null) {
            return this;
        } else {
            n = left;
            return n.getSmallestNode();
        }
    }

    Node getLargestNode() {
        Node n = new Node();
        if (right == null) {
            return this;
        } else {
            n = right;
            return n.getLargestNode();
        }
    }

    @Override
    public String toString(){
        ArrayList<Integer> nodeList = new ArrayList<Integer>();
        getAllNodes(nodeList);
        Collections.sort(nodeList);
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < nodeList.size();i++){
           if(i != 0 && i % 20 == 0) {
               builder.append('\n');
            }
            builder.append(' ' + Integer.toString(nodeList.get(i)));
        }
        return builder.toString();
    }

    private void getAllNodes(ArrayList<Integer> nodeList){
        if(left != null){
            left.getAllNodes(nodeList);
        }
        nodeList.add(data);
        if(right != null){
            right.getAllNodes(nodeList);
        }
    }

    private void getNestedNodes(ArrayList<Node> nodeList){
        if(left != null){
            left.getNestedNodes(nodeList);
        }
        nodeList.add(this);
        if(right != null){
            right.getNestedNodes(nodeList);
        }
    }

    Node deleteNode(int key) throws ValueOutOfRangeException{
        if(!contains(key)){
            throw new ValueOutOfRangeException(key);
        }

        if(right == null && left == null){
            return null;
        }

        if(data == key){
            return deleteNodeIfNodeIsKey(key);
        }

        ArrayList<Node> nodeList = new ArrayList<>();
        deleteNode_AddChildNodes(key,nodeList);
        linkArrayListWithTreeAfterDeletion(nodeList);
        return this;
}

    private void linkArrayListWithTreeAfterDeletion(ArrayList<Node> nodeList) {
        extractNestedNodesFromNodeList(nodeList);
        for (Node aNodeList : nodeList) {
            insert(aNodeList.data);
        }
    }

    private void extractNestedNodesFromNodeList(ArrayList<Node> nodeList){
        for(int i = 0; i < nodeList.size(); i++){
            if(nodeList.get(i).left != null){
                nodeList.get(i).left.getNestedNodes(nodeList);
            }
            if(nodeList.get(i).right != null){
                nodeList.get(i).right.getNestedNodes(nodeList);
            }

        }
    }

    private Node deleteNodeIfNodeIsKey(int key){
        Node n;
        if (this.left != null) {
            n = this.left;
            ArrayList<Node> nodeList = new ArrayList<>();
            if (n.right != null) {
                n.deleteNode_AddChildNodesWhenDeletingRoot(n.right.data, nodeList);
            } else {
                n.deleteNode_AddChildNodesWhenDeletingRoot(n.left.data,nodeList);
            }
            linkArrayListWithTreeAfterDeletion(nodeList);
        } else {
            n = this.right;
            ArrayList<Node> nodeList = new ArrayList<>();
            if (n.right != null) {
                n.deleteNode_AddChildNodesWhenDeletingRoot(n.right.data, nodeList);
            } else {
                n.deleteNode_AddChildNodesWhenDeletingRoot(n.left.data,nodeList);
            }
            linkArrayListWithTreeAfterDeletion(nodeList);
        }
        return n;
    }

    private void deleteNode_AddChildNodes(int key, ArrayList<Node> nodeList){
        Node n;
        if (this.left != null && this.left.data == key) {
            n = this.left;
            // adding sub nodes
            if (n.left != null) {
                nodeList.add(n.left);
            }
            if (n.right != null) {
                nodeList.add(n.right);
            }
            this.left = null;
        } else if (this.right != null && this.right.data == key) {
            n = this.right;
            // adding sub nodes
            if (n.left != null) {
                nodeList.add(n.left);
            }
            if (n.right != null) {
                nodeList.add(n.right);
            }
            //nodeList.add(n); // this is what adds the larget delete back into the arraylist
            this.right = null;
        } else {
            // Move down the tree
            // If either of these hit, method will return null. This is the null case.
            if (key < data) {
                n = left;
                n.deleteNode_AddChildNodes(key, nodeList);
            } else {
                n = right;
                n.deleteNode_AddChildNodes(key, nodeList);
            }
        }
    }

    private Node deleteNode_AddChildNodesWhenDeletingRoot(int key, ArrayList<Node> nodeList){
        Node n;
        if (this.left != null && this.left.data == key) {
            n = this.left;
            // adding sub nodes
            if (n.left != null) {
                nodeList.add(n.left);
            }
            if (n.right != null) {
                nodeList.add(n.right);
            }
            nodeList.add(n);
            this.left = null;
            return this.left;
        } else if (this.right != null && this.right.data == key) {
            n = this.right;
            // adding sub nodes
            if (n.left != null) {
                nodeList.add(n.left);
            }
            if (n.right != null) {
                nodeList.add(n.right);
            }
            nodeList.add(n);
            this.right = null;
            return this.right;
        } else {
            // Move down the tree
            // If either of these hit, method will return null. This is the null case.
            if (key < data) {
                n = left;
                n.deleteNode_AddChildNodes(key, nodeList);
            } else {
                n = right;
                n.deleteNode_AddChildNodes(key, nodeList);
            }
        }
        return null;
    }
}
