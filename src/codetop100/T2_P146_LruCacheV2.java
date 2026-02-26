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
// Related Topics 设计 哈希表 链表 双向链表 👍 3609 👎 0


package codetop100;

import java.util.HashMap;

/**
 * LRU 缓存
 * TODO 这里有两种解法，一种是使用Java自带的LinkedHashMap，另一种是自己实现双向链表+哈希表。另一种写法第一次没有做
 * @author Jayden
 * @date 2025-10-12 12:48:03
 */
public class T2_P146_LruCacheV2 {
        public static void main(String[] args) {
            //测试代码
            T2_P146_LruCacheV2.LRUCache cache = new T2_P146_LruCacheV2.LRUCache(2);
            // 模拟["LRUCache","put","put","get","put","get","put","get","get","get"]
            // [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]操作
            cache.put(1, 1);
            cache.put(2, 2);
            System.out.println(cache.get(1));
            cache.put(3, 3);
            // 这里预期是-1
            System.out.println(cache.get(2));
            cache.put(4, 4);
            System.out.println(cache.get(1));
            System.out.println(cache.get(3));
            System.out.println(cache.get(4));
        }

        //力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
        static class LRUCache {

            HashMap<Integer, LRUCache.Node> cache = new HashMap<>();

            // 代表现在缓存里的元素数量
            Integer maxLen;
            Integer capacity;
            LRUCache.Node head;
            LRUCache.Node tail;

            public LRUCache(int capacity) {
                maxLen = 0;
                this.capacity = capacity;
                head = new LRUCache.Node();
                tail = new LRUCache.Node();
                head.next = tail;
                tail.prev = head;
            }

            public int get(int key) {
                LRUCache.Node node = cache.get(key);
                if (node == null){
                    return -1;
                }
                // 将元素移到链表的头部：需要做两件事，把元素当前位置删除，在头部添加该元素
                moveToHead(node);
                return node.value;
            }

            public void put(int key, int value) {
                LRUCache.Node node = cache.get(key);
                // 如果不存在创建新节点
                if(node == null){
                    LRUCache.Node newNode = new LRUCache.Node(key, value);
                    cache.put(key,newNode);
                    // TODO 这里最开始忘记写了
                    addToHead(newNode);
                    maxLen++;
                    if (maxLen > capacity){
                        LRUCache.Node tailNode = removeTail();
                        cache.remove(tailNode.key);
                        maxLen--;
                    }
                }
                // 如果存在更新下值，并且添加到头部
                else{
                    node.value = value;
                    moveToHead(node);
                }

            }

            /**
             *
             * @param node
             */
            public void moveToHead(LRUCache.Node node){
                removeNode(node);
                addToHead(node);
            }

            public void removeNode(LRUCache.Node node){
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            public void addToHead(LRUCache.Node node){
                node.next = head.next;
                node.prev = head;
                head.next.prev = node;
                head.next = node;
            }

            public LRUCache.Node removeTail(){
                LRUCache.Node removeNode = tail.prev;
                removeNode(removeNode);
                return removeNode;
            }

            static class Node {
                int key;
                int value;
                LRUCache.Node prev;
                LRUCache.Node next;

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
