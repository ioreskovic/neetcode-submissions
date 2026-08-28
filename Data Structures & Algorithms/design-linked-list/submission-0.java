class MyLinkedList {
    private static class Node {
        int val;
        Node prev;
        Node next;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node navigate(int index) {
        if (index > size) {
            return null;
        }

        Node curr = head;
        int i = 0;
        for (curr = head; curr != null && i++ < index; curr = curr.next) { }
        return curr;
    }

    public int get(int index) {
        Node found = navigate(index);
        if (found == null) {
            return -1;
        }

        return found.val;
    }
    
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }


    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        Node right = navigate(index);
        if (size == 0) {
            Node init = new Node(val);
            head = init;
            tail = init;
            size++;
            return;
        }

        Node init = new Node(val);
        if (right == null) {
            init.prev = tail;
            tail.next = init;
            tail = init;
        } else if (right == head) {
            init.next = head;
            head.prev = init;
            head = init;
        } else {
            init.prev = right.prev;
            init.next = right;
            right.prev.next = init;
            right.prev = init;
        }
        size++;
    }
    
    public void deleteAtIndex(int index) {
        if (index >= size) {
            return;
        }

        Node toDelete = navigate(index);
        Node left = toDelete.prev;
        Node right = toDelete.next;


        if (toDelete == tail) {
           tail = toDelete.prev;
        }

        if (toDelete == head) {
            head = toDelete.next;
        }

        if (left != null) {
            left.next = right;
        }

        if (right != null) {
            right.prev = left;
        }

        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */