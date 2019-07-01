package cn.bluesky.dataarchitecture.sample.linkednode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 李金春
 * @Package cn.bluesky.dataarchitecture.sample
 * @date 2019/7/1 11:03
 */
@Slf4j
public class LinkedNodeTest {

    /**
     * 链表遍历
     * @param head
     */
    public static void traverse(LinkedNode head){
        LinkedNode curNode = head;
        while (curNode != null){
            log.info("node value: {}", curNode.getValue());
            curNode = curNode.getNext();
        }
        log.debug("遍历分隔线----------------------------------------------------------------------------------------------------------");
    }

    /**
     * 头节点插入
     * @param head
     * @param newHead
     * @return
     */
    public static LinkedNode headInsert(LinkedNode head, LinkedNode newHead){
        LinkedNode old = head;
        head = newHead;
        head.setNext(old);
        return head;
    }

    /**
     * 尾节点插入
     * @param anyNode
     * @param newTail
     * @return
     */
    public static void tailInsert(LinkedNode anyNode, LinkedNode newTail){
        LinkedNode cur = anyNode;
        while (cur != null){
            if(cur.getNext() == null){
                cur.setNext(newTail);
                newTail.setNext(null);
            }
            cur = cur.getNext();
        }
    }

    /**
     * 查找
     * @param head
     * @param value
     * @return
     */
    public static int find(LinkedNode head, Object value){
        int def = -1;
        if(head == null || value == null){
            return def;
        }
        int count = 0;
        LinkedNode cur = head;
        do{
            if(value.equals(cur.getValue())){
                return count;
            }
            count++;
        }while ((cur = cur.getNext()) !=  null);
        return def;
    }

    /**
     * 删除
     * @param head
     * @param value
     */
    public static void remove(LinkedNode head, Object value){
        LinkedNode cur = head;
        if(cur == null || value == null){
            return;
        }
        do{
            if(value.equals(cur.getValue())){
                if(cur.getNext() ==null){
                    cur = null;
                }else{
                    cur.setValue(cur.getNext().getValue());
                    cur.setNext(cur.getNext().getNext());
                }
            }
        }while ((cur = cur.getNext()) != null);
    }

    public static void reverse(LinkedNode head){
        LinkedNode pre = head;
        LinkedNode next = head.getNext();
    }
    public static void main(String[] args) {
        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(3);
        LinkedNode node4 = new LinkedNode(4);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        traverse(node1);

        traverse(headInsert(node1, new LinkedNode(20)));
        tailInsert(node1, new LinkedNode(21));
        traverse(node1);

        log.info("find: {}",find(node1, 2));

        remove(node1, 2);
        traverse(node1);


    }
}
