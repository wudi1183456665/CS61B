public class LinkedListDeque <T>{

    private class TNode{
        public TNode pre;
        public T item;
        public TNode next;

        public TNode(TNode p, T x, TNode n){
            pre = p;
            item = x;
            next = n;
        }

    }

    private TNode sentinel;
    private int size;

    /** Create an empty list. */
    public LinkedListDeque(){
        sentinel = new TNode(null,null ,null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Return a copy of other. */
//    public LinkedListDeque(LinkedListDeque other){
//        sentinel = new TNode(null,null ,null);
//        sentinel.pre = sentinel;
//        sentinel.next = sentinel;
//        size = 0;
//        TNode p = other.sentinel.next;
//        while(p != other.sentinel){
//            this.addLast(p.item);
//            p = p.next;
//        }
//    }

    public void addFirst(T item){
        TNode node = new TNode(sentinel, item, sentinel.next);
        sentinel.next.pre = node;
        sentinel.next = node;
        size += 1;
    }

    public void addLast(T item){
        size += 1;
        TNode node = new TNode(sentinel.pre,item,sentinel);
        sentinel.pre.next = node;
        sentinel.pre = node;

    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        TNode p = sentinel;
        int my_size = 0;
        while(p.next != sentinel){
            System.out.print(p.next.item);
            my_size += 1;
            if (my_size == this.size){
                System.out.print("\n");
            }else{
                System.out.print(" ");
            }
            p = p.next;
        }
    }

    public T removeFirst(){
        if(sentinel.next == sentinel){
            return null;
        }
        T firstItem = sentinel.next.item;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return firstItem;
    }

    public T removeLast(){
        if (sentinel.next == sentinel){
            return null;
        }
        T lastItem = sentinel.pre.item;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        size -= 1;
        return lastItem;
    }

    public T get(int index){
        if (index > this.size || sentinel.next == sentinel){
            return null;
        }
        TNode p = sentinel.next;
        int i = 0;
        while (i != index){
            p = p.next;
            i += 1;
        }
        T ithItem = p.item;
        return ithItem;
    }
    private T getRecursive(int index, TNode node){
        if(index == 0){
            return node.item;
        }
        return getRecursive(index-1,node.next);
    }

    private T getRecursive(int index) {
        return getRecursive(index,sentinel.next);
    }
}
