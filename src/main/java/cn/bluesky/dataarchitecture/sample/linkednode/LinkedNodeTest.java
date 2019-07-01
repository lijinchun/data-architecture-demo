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

    /**
     * 取中间值
     * @param newHead
     * @return
     */
    private static Object getMid(LinkedNode newHead) {
        LinkedNode fast = newHead;
        LinkedNode slow = newHead;
        while (slow != null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow.getValue();
    }

    /**
     * 递归合并2个链表，不能合并2个相同链表
     * @param head1
     * @param head2
     * @return
     */
    public static LinkedNode mergeByRecursion(LinkedNode head1, LinkedNode head2){
        if(head1 == null && head2 == null){
            return null;
        }
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        LinkedNode newHead;
        if(head1.getValue().hashCode() > head2.getValue().hashCode()){
            newHead = head2;
            newHead.setNext(mergeByRecursion(head1, head2.getNext()));
        }else{
            newHead = head1;
            newHead.setNext(mergeByRecursion(head1.getNext(), head2));
        }
        return newHead;
    }


    /**
     * 非递归合并链表
     * @param head1
     * @param head2
     * @return
     */
    public static LinkedNode mergeByNoRecursion(LinkedNode head1, LinkedNode head2){
        if(head1 == null || head2 == null){
            return head1==null?head2:head1;
        }
        LinkedNode newHead = head1.getValue().hashCode()>head2.getValue().hashCode()?head2:head1;
        LinkedNode cur1 = newHead == head1?head1:head2;
        LinkedNode cur2 = newHead == head1?head2:head1;

        LinkedNode pre= null;
        LinkedNode next = null;
        while (cur1 != null && cur2 != null){
            if(cur1.getValue().hashCode()> cur2.getValue().hashCode()){
                pre = cur2;
                cur2 = cur2.getNext();
            }else{
                next = cur1.getNext();
                pre.setNext(cur1);
                pre = cur1;
                cur1 = next;
            }
        }
        pre.setNext(cur2==null? cur1: cur2);
        return newHead;
    }
    public static void main(String[] args) {
        testLinkedTrain();

//        testLinkedBaseOptions();
    }

    /**
     * 实战测试
     */
    private static void testLinkedTrain() {
        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node4 = new LinkedNode(4);
        LinkedNode node3 = new LinkedNode(3);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        LinkedNode node11 = new LinkedNode(11);
        LinkedNode node22 = new LinkedNode(22);
        LinkedNode node33 = new LinkedNode(33);
        LinkedNode node44 = new LinkedNode(44);
        LinkedNode node55 = new LinkedNode(55);
        node11.setNext(node22);
        node22.setNext(node33);
        node33.setNext(node44);
        node44.setNext(node55);

//        LinkedNode newHead = mergeByRecursion(node1, node11);
//        traverse(newHead, "递归合并");

        LinkedNode newHead = mergeByNoRecursion(node1, node11);
        traverse(newHead, "非递归合并");


        log.debug("newHead mid val: {}", getMid(newHead));



    }

    /**
     * 测试链表基本操作
     */
    private static void testLinkedBaseOptions() {
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

        remove(new LinkedNode(20), delVal);
        traverse(newHead, "删除节点" + delVal + "后");


        traverse(reverse(newHead), "反转NewHead");
    }
}
