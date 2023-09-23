package functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction{

    protected class Node {
        public Node prev;
        public Node next;

        public double x;
        public double y;

        Node(double x, double y, Node next, Node prev) {
            this.next = next;
            this.prev = prev;
            this.x = x;
            this.y = y;
        }
    }

    private Node head = null;
    private Node last = head.prev;
    private int count = 0;

    protected void addNode(double x, double y) {
        if (head == null){
            head.next = head;
            head.prev = head;
            head.x = x;
            head.y = y;
            last = head;
        }
        else {
            Node addNode = new Node(x, y, head.next, head.prev);
            head.prev.next = addNode;
            head.next.prev = addNode;
            head = addNode;
            last.next = head;
        }
        count++;
    };

    protected Node getNode(int index) {
        Node node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    };

    //---------------

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node node = this.head;
        for (int i = 0; i < this.count; i++) {
            if(node.x == x)
                return i;
            node = node.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node node = this.head;
        for (int i = 0; i < this.count; i++) {
            if(node.y == y)
                return i;
            node = node.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return last.x;
    }

    @Override
    public double rightBound() {
        return last.y;
    }

}
