package functions;


public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

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
    private int count = 0;

    protected void addNode(double x, double y) {
        if (head == null) {
            head.next = head;
            head.prev = head;
            head.x = x;
            head.y = y;
        } else {
            Node addNode = new Node(x, y, head.next, head.prev);
            head.prev.next = addNode;
            head.next.prev = addNode;
            head = addNode;
        }
        count++;
    }

    ;

    protected Node getNode(int index) {
        Node node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    ;

    // конструктор 1
    LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        super();
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    // конструктор 2
    LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        super();
        if (xFrom > xTo) {
            double t = xFrom;
            xFrom = xTo;
            xTo = t;
        }

        double step = (xTo - xFrom) / (count - 1);
        double xCur = xFrom;
        for (int i = 0; i < count; i++) {
            addNode(xCur, source.apply(xCur));
            xCur += step;
        }

    }
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
            if (node.x == x)
                return i;
            node = node.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node node = this.head;
        for (int i = 0; i < this.count; i++) {
            if (node.y == y)
                return i;
            node = node.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    /**
     * Метод поиска индекса x, который, в отличие от обычного indexOfX(), не должен возвращать -1 (если x не найден),
     * а должен вернуть индекс максимального значения x, которое меньше заданного x.
     * Так, для набора значений x [-3., 4., 6.] – индексация начинается с нуля – метод, применённый к 4.5,
     * должен вернуть 1, так как 4 – максимальный x из всего массива, который меньше 4.5, и имеет индекс 1.
     * Если все x больше заданного, то метод должен вернуть 0;
     * если все x меньше заданного, то метод должен вернуть count.
     */
    @Override
    protected int floorIndexOfX(double x) {
        Node node = this.head;
        for (int i = 0; i < this.count; i++) {
            if (node.x == x)
                return i;
            else if (node.x > x) {
                return (i == 0) ? 0 : i - 1;
            }
            node = node.next;
        }
        return count;
    }

    private Node floorNodeOfX(double x){
        Node node = this.head;
        for (int i = 0; i < this.count; i++) {
            if (node.x == x)
                return node;
            else if (node.x > x) {
                return (i == 0) ? head : node.prev;
            }
            node = node.next;
        }
        return head;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node left = getNode(floorIndex);
        return interpolate(x, left);
    }

    private double interpolate(double x, Node floorNode){
        double leftX = floorNode.x;
        double leftY = floorNode.y;
        floorNode = floorNode.next;
        double rightX = floorNode.x;
        double rightY = floorNode.y;
        return this.interpolate(x, leftX, rightX, leftY, rightY);
    }

    @Override
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        double y = leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX);
        return y;
    }

    /** Метод apply() принимает на вход x.
     * Если этот x меньше левой границы, то нужно использовать левую интерполяцию.
     * Если он больше правой границы, то нужно использовать правую интерполяцию.
     * Если он внутри интервала, можно попытаться найти, а есть ли он в таблице, использовав метод indexOf() –
     * если вернулось не -1, то вернуть соответствующее y через метод getY().
     * В противном случае вызвать метод интерполяции с указанием индекса интервала,
     * предварительно отыскав его с помощью метода floorIndexOfX(double x) */
    @Override
    public double apply(double x) {
        if(x < leftBound()){
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        int idxx = indexOfX(x);
        if (idxx != -1)
            return getY(idxx);

        Node node = floorNodeOfX(x);
        return interpolate(x, node);

        //idxx = floorIndexOfX(x);
        //return interpolate(x, idxx);
    }
}
