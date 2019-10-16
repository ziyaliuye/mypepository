package cn.cnm;

/**
 * @author lele
 * @version 1.0
 * @Description 作为Map接口的实现类， 拥有Map接口所有的方法， 并有以下特点：
 * 线程不安全的， 效率高， 可以存储 null 的 key 和 value
 * JDK1.7底层结构：数组 + 单向链表（只有next）
 * JDK1.8底层结构：数组 + 单向链表（只有next）  + 红黑树
 * 使用时要求 Key对应的类 要重写 HashCode()和 equals()方法
 * @Email 414955507@qq.com
 * @date 2019/10/11 21:41
 */
public class HashMapDemo {
    public static void main(String[] args) {
        /*
         * HashMap底层实现原理:
         *  JDK1.7：调用无参构造器后， 底层创建了一个长度16的一维数组（Entry[] table）, 添加数据， 以 map.put(key1, value1); 为例
         *      调用key1所在类的hashCode()计算哈希值， 次哈希值经过某种计算后得到这个Entry数组的存放位置
         *      如果此位置上的数据为空， 则此时Entry（key1-value1）【添加成功@1】
         *      如果此位置上的数据不为空， 意味着此位置上存在一个或多个数据（以单向链表的形式存在）， 比较key1和已经存在一个或者多个数据的哈希值：
         *          如果key1的哈希值与已经存在的数据的哈希值都不相同（所有数据都要比）， 此时key1-value1【添加成功@2】
         *          如果key1的哈希值和已经存在的某一个数据的哈希值相同， 调用key1所在类的equals方法比较：
         *              如果eques()返回false：此时key1-value1【添加成功@3】
         *              如果eques()返回true：使用value1【替换@4】value2值
         *      关于@2和@3的key1-value1存储情况， 是以单向链表的形式和原来的数据一起存储
         *      在不断的添加过程空， 会涉及扩容问题， 默认扩容原来容量的2倍， 并将原来的数据复制过来
         *          （默认扩容方式：添加数据时发现索引位置大于12并且索引位置的数据还不是空的就开始扩容）
         *  JDK1.8：调用无参构造器时， 底层没有创建长度16的一维数组， 并且JDK1.8中存储单位不是Entry[]而改名叫Node[]
         *      在首次调用 map.put() 时创建长度16的数组
         *      当数组上的某一个索引位置上的元素， 它以链表形式存放的数据（个数>8 且 当前数组的长度 >64）时：
         *          此时索引上的所有数据改为红黑树存储（方便快速查找）
         */


        /*
         *HashMap源码中的重要常量：
         *  DEFAULT_INITIAL_CAPACITY : HashMap的默认容量， 16
         *  MAXIMUM_CAPACITY ： HashMap的最大支持容量， 2^30
         *  DEFAULT_LOAD_FACTOR： HashMap的默认加载因子（准备扩容的系数， 默认0.75）
         *  TREEIFY_THRESHOLD： Bucket中链表长度大于该默认值，转化为红黑树
         *  UNTREEIFY_THRESHOLD： Bucket中红黑树存储的Node小于该默认值，转化为链表
         *  MIN_TREEIFY_CAPACITY： 桶中的Node被树化时最小的hash表容量。
         *      （当桶中Node的数量大到需要变红黑树时，若hash表容量小于MIN_TREEIFY_CAPACITY时，此时应执行resize扩容操作这个MIN_TREEIFY_CAPACITY的值至少是TREEIFY_THRESHOLD的4倍。）
         *  table： 存储元素的数组，总是2的n次幂（手动创建一个长度15的HashMap， 其实底层创建的是16， HashMap创建的永远是2^N次幂的数组）
         *  entrySet： 存储具体元素的集
         *  size： HashMap中存储的键值对的数量
         *  modCount： HashMap扩容和结构改变的次数。
         *  threshold： 扩容的临界值， =容量*填充因子
         *      比如数组长度16， 并不是15的时候开始扩， 而是添加数据时发现索引位置大于12并且索引位置的数据还不是空的就开始扩容
         *      当发现索引位置大于12但索引位置是空的就直接存储， 并不会进行扩容, 而是将现有数组进行扩容
         *  loadFactor： 填充因子
         */
    }
}
