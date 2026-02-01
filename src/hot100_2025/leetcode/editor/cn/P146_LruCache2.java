//
// 请你设计并实现一个满足 
// LRU (最近最少使用) 缓存 约束的数据结构。
// 
//
// 
// 实现 
// LRUCache 类：
// 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 
//key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。 
// 
// 
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put 
// 
//
// Related Topics 设计 哈希表 链表 双向链表 👍 3701 👎 0


package hot100_2025.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU 缓存
 * @author Jayden
 * @date 2026-02-01 19:05:50
 */
public class P146_LruCache2 {
	 public static void main(String[] args) {
	 	 //测试代码
         LRUCache lruCache = new LRUCache(2);
         // [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
         lruCache.put(1,1);
         lruCache.put(2,2);
         System.out.println(lruCache.get(1));
         lruCache.put(3,3);
         // 这里预期是-1
         System.out.println(lruCache.get(2));

     }
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
static class LRUCache {

    // 用于判断数量是否超过限制
    int size;

    // 容量
    int capacity;

    // 搞好链表的头、尾
    Node head;
    Node tail;

    Map<Integer,Node> cache = new HashMap<>();

    public LRUCache(int capacity) {
        // TODO 这里构造的有问题，应该是0，要不数据根本存不进去
//        size = capacity;
        size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // 先从缓存里获取数据
        Node node = cache.get(key);

        // 数据是否为空？为空直接返回-1
        if (node == null){
            return -1;
        }

        // 数据如果不为空，数据需要移动到链表的头部
        moveToHead(node);

        // 返回数据的值
        return node.value;
    }

    /**
     * 将数据移动到头部，需要做两件事
     * 1. 移除节点
     * 2. 添加到头部
     * @param node
     */
    public void moveToHead(Node node){
        removeNode(node);
        addToHead(node);
    }

    /**
     * 在移除某个节点：将node的上一个节点跟下一个节点关联起来
     * @param node
     */
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 添加到头部:将节点放到head下面
     */
    public void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        // TODO 这里有严格顺序哦
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 添加数据到头部
     * // 有2种情况，一种是有key覆盖，一种是没key新增
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null){
            // 新增需要添加到头节点
            Node newNode = new Node(key, value);
            addToHead(newNode);
            cache.put(key,newNode);
            // TODO 这里次数忘记加了
            size++;
            // 判断是否超过了容量
            if(size > capacity){
                // 移除这个数据
                Node tailNode = moveTailNode();
                cache.remove(tailNode.key);
                // TODO 这里次数要减少
                size--;
            }
        }else {
            // 如果是新增需要更新值
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 获取到最后一个不常用的节点返回
     * @return
     */
    public Node moveTailNode(){
        Node tailNode = tail.prev;
        removeNode(tailNode);
        return tailNode;
    }

    public class Node{
        int key;
        int value;
        Node prev;
        Node next;

        public Node(){}

        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
