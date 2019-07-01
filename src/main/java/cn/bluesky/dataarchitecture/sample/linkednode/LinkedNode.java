package cn.bluesky.dataarchitecture.sample.linkednode;

import lombok.Data;

/**
 * @author 李金春
 * @Package cn.bluesky.dataarchitecture.sample
 * @date 2019/7/1 10:55
 */
@Data
public class LinkedNode {
    private LinkedNode next;
    private LinkedNode pre;
    private Object value;

    public LinkedNode(Object value) {
        this.value = value;
    }
}
