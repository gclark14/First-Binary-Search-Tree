package binarySearchTree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by gabe.clark on 6/14/17.
 */
public class NodeTest {

    Node node;

    @Before
    public void setup(){
        node = new Node(100);
    }

    @Test
    public void testConstructor() {
        assertEquals(100, node.data);
    }

    @Test
    public void testIfInputEqualToNodeDuplicates(){
        node.insert(100);
        assertNotNull(node.left);
    }

    @Test
    public void testIfInputLargerThanRootCreatesNewRightNode(){
        node.insert(101);
        assertNotNull(node.right);
    }

    @Test
    public void testIfTreeContainsValueReturnsTrue(){
        assertTrue(node.contains(100));
    }

    @Test
    public void testIfTreeDoesNotContainValueReturnsFalse(){
        assertFalse(node.contains(101));
    }

    @Test
    public void testSmallestValueReturn(){
        node.insert(20);
        node.insert(10);
        node.insert(200);
        assertEquals(10,node.getSmallestValue());
    }

    @Test
    public void testLargestValueReturn(){
        node.insert(200);
        node.insert(300);
        node.insert(250);
        assertEquals(300, node.getLargestValue());
    }

    @Test
    public void testGet(){
        node.insert(20);
        node.insert(120);
        assertEquals(120, node.get(120));
    }

    @Test(expected=ValueOutOfRangeException.class)
    public void throwExceptionWhenInputOutOfRange(){
        node.get(500);
    }

    @Test
    public void getsSmallestNode(){
        node.insert(3);
        node.insert(2);
        node.insert(17);
        Node testNode = node.getSmallestNode();
        assertEquals(2, testNode.data);
    }

    @Test
    public void getsLargestNode(){
        node.insert(500);
        node.insert(400);
        node.insert(450);
        Node testNode = node.getLargestNode();
        assertEquals(500,testNode.data);
    }

    @Test
    public void checkPerformanceOnToString(){
        // 10^6 == 1mil
        final int ROOT = (int)Math.pow(10,6);
        node.data = ROOT/2;
        for(int i = 0; i < ROOT; ++i){
            node.insert((int)(Math.random() * ROOT + 1));
        }
        String test = node.toString();
  }

    @Test
    public void deletesNodeLessThanRootWhenAdjacentToRoot() {
        node.insert(20);
        node.insert(15);
        node.insert(25);
        node.insert(55);
        node.deleteNode(20);
        node.printInOrder();
        assertFalse(node.contains(20));
    }

    @Test
    public void deletesNodeLessThanRootWhenNotAdjacentToRoot() {
        node.insert(15);
        node.insert(20);
        node.insert(55);
        node.deleteNode(20);
        node.printInOrder();
        assertFalse(node.contains(20));
    }

    @Test
    public void deletesNodeGreaterThanWhenNotAdjacentToRoot(){
        node.insert(440);
        node.insert(300);
        node.insert(275);
        node.insert(352);
        node.deleteNode(352);
        node.printInOrder();
        assertFalse(node.contains(352));
    }

    // Should be just as broken as the other adjacent node test
    @Test
    public void deletesNodeGreaterThanWhenAdjacentToRoot(){
        node.insert(440);
        node.insert(300);
        node.insert(275);
        node.insert(352);
        node.deleteNode(440);
        node.printInOrder();
        assertFalse(node.contains(440));
    }

    // Would be wise to give this the adj / nonAdj cases
    @Test
    public void deletesNodesWhenTreeHasNodesGreaterThanAndLessThanRoot() {
        node.insert(200);
        node.insert(150);
        node.insert(300);
        node.insert(15);
        node.insert(55);
        node.deleteNode(200); // 20 < 55 < 100 => 20 left of 100, 55 right of 20
        node.printInOrder();
        assertFalse(node.contains(200));
    }

    /** Testing different tree formations when nodes are less than root */
    @Test
    public void deletesRootNodeWithNodesLessThan_1(){
        node.insert(55);
        node.insert(44);
        node.insert(88);
        node = node.deleteNode(100);
        node.printInOrder();
        assertFalse(node.contains(100));
    }

    @Test
    public void deletesRootNodeWithNodesLessThan_2(){
        node.insert(44);
        node.insert(55);
        node.insert(88);
        node = node.deleteNode(100);
        node.printInOrder();
        assertFalse(node.contains(100));
    }

    @Test
    public void deletesRootNodeWithNodesLessThan_3(){
        node.insert(88);
        node.insert(44);
        node.insert(55);
        node = node.deleteNode(100);
        node.printInOrder();
        assertFalse(node.contains(100));
    }

    @Test
    public void deletesRootNodeWithNodesLessThan_4(){
        node.insert(88);
        node.insert(55);
        node.insert(44);
        node = node.deleteNode(100);
        node.printInOrder();
        assertFalse(node.contains(100));
    }
    // End nodes less than root tree formations

    /** Testing different tree formations when nodes are greater than root */
    @Test
    public void deletesRootNodeWithNodesGreaterThan_1(){
        node.insert(550);
        node.insert(440);
        node.insert(880);
        node = node.deleteNode(100);
        node.printInOrder();
        assertFalse(node.contains(100));
    }

    @Test
    public void deletesRootNodeWithNodesGreaterThan_2(){
        node.insert(440);
        node.insert(550);
        node.insert(880);
        node = node.deleteNode(100);
        node.printInOrder();
        assertFalse(node.contains(100));
    }

    @Test
    public void deletesRootNodeWithNodesGreatThan_3(){
        node.insert(880);
        node.insert(440);
        node.insert(550);
        node = node.deleteNode(100);
        node.printInOrder();
        assertFalse(node.contains(100));
    }

    @Test
    public void deletesRootNodeWithNodesGreaterThan_4(){
        node.insert(880);
        node.insert(550);
        node.insert(440);
        node = node.deleteNode(100);
        node.printInOrder();
        assertFalse(node.contains(100));
    }
    // End nodes greater than root tree formations

    @Test
    public void deletesRootWhenOnlyRootExists(){
        node = node.deleteNode(100);
        assertNull(node);
    }

}
