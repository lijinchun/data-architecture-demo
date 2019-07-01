package cn.bluesky.dataarchitecture.sample.linkednode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 李金春
 * @date 2019/7/1 11:03
 */
@Slf4j
public class LinkedNodeTest {

    /**
     * 链表遍历
     * @param head
     * @param msg
     */
    public static void traverse(LinkedNode head, String msg){
        LinkedNode curNode = head;
        log.debug("遍历:{}----------------------------------------------------------------------------------------------------------", msg);
        while (curNode != null){
            log.info("node value: {}", curNode.getValue());
            curNode = curNode.getNext();
        }
        log.debug("遍历分隔线----------------------------------------------------------------------------------------------------------\r\n");
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

    public static void insertAfter(LinkedNode targetNode, LinkedNode insertNode){
        if(targetNode == null|| insertNode == null){
            return;
        }
        if(targetNode.getNext() == null){
            targetNode.setNext(insertNode);
        }else{
            LinkedNode oldNext = targetNode.getNext();
            targetNode.setNext(insertNode);
            insertNode.setNext(oldNext);
        }
    }

    /**
     * 删除
     * @param head
     * @param value
     */
    public static boolean remove(LinkedNode head, Object value){
        LinkedNode cur = head;
        LinkedNode pre = null;
        if(cur == null || value == null){
            return false;
        }
        while (cur != null){
            if(value.equals(cur.getValue())){
                if(cur.getNext() != null){
                    cur.setValue(cur.getNext().getValue());
                    cur.setNext(cur.getNext().getNext());
                    return true;
                }else{
                    if(pre == null){
                        throw new IllegalArgumentException("链表只有一个节点不能删除");
                    }
                    pre.setNext(null);
                    return true;
                }
            }
            //如果不是最后一个节点，记录下前驱节点
            if(cur.getNext() != null) {
                pre = cur;
            }
            cur = cur.getNext();
        }
        return false;
    }

    /**
     * 实战链表的反转
     * O(n) O(1)
     * @param head
     */
    public static LinkedNode reverse(LinkedNode head){
        //前驱节点
        LinkedNode pre = null;
        //后继节点
        LinkedNode next;
        //当前节点
        LinkedNode cur = head;
        while (cur != null){
            //记录当前节点的后一个节点
            next = cur.getNext();

            //前驱设为后继
            cur.setNext(pre);

            //把当节点作为下一个节点的前驱节点
            pre = cur;

            //当前节点切换
            cur = next;
        }
        return pre;
    }
    public static void main(String[] args) {
        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(3);
        LinkedNode node4 = new LinkedNode(4);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        traverse(node1, "原始链表");

        LinkedNode newHead = new LinkedNode(20);
        traverse(headInsert(node1, newHead), "插入头节点" + newHead.getValue() + "后");

        LinkedNode newTail = new LinkedNode(21);
        tailInsert(node1, newTail);
        traverse(newHead, "插入尾节点" + newTail.getValue() + "后");

        LinkedNode insertNode = new LinkedNode(23);
        insertAfter(node3, insertNode);
        traverse(node1, "在节点" + node3.getValue() + "插入节点" + insertNode.getValue() + "后");

        Object findVal = 2;
        log.info("find: {}",find(newHead, findVal));

        Object delVal = 20;
        remove(newHead, delVal);
        traverse(newHead, "删除节点" + delVal + "后");


        traverse(reverse(newHead), "反转NewHead");






    }
}
