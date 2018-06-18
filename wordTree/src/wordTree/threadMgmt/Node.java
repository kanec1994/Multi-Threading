package wordTree.threadMgmt;

class Node {
    private String word;
    private int count;
    private boolean lock;

    private Node left;
    private Node right;
    private Node parent;

    /**
     * Constructor for the Node class. Its purpose is to
     * hold the words that we are counting/deleting from.
     *
     * @param String - word to be contained within node
     */
    public Node(String w) {
        this.word = w;
        this.count = 1;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.lock = false;
    }

    /**
     * Constructor for Node class. Same as main, but also accepts
     * a parent node. Calls Node(String) constructor
     *
     * @param String - word to be contained
     * @param Node - parent node of this current node
     */
    public Node(String w, Node p) {
        this(w);
        this.parent = p;
    }

    /**
     * Lock check for node. Checks is node is currently locked
     *
     * @return true if locked
     */
    public boolean isLocked() {
        return this.lock;
    }
    
    /**
     * Lock set for node. Sets the lock, and returns if true if successful
     *
     * @return true if successful, false if not successful
     */
    public boolean setLock() {
        if(this.lock) return false;
        this.lock = true;
        return true;
    }

    /**
     * Remove lock for node. If node was already unlocked, returns false
     *
     * @return status of free, if false node was already unlocked
     */
    public boolean freeLock() {
        if(!this.lock) return false;
        this.lock = false;
        return true;
    }

    /**
     *  Check for right child node.
     *
     *  @return state of right child node
     */
    public boolean hasRight() {
        return right != null; 
    }
    
    /** Gets the right child node
     * 
     * @return right child node, null if not set.
     */
    public Node getRight() {
        return right;
    }
    
    /** Sets the right child node.
     *
     * @param Node - right node to be set.
     */
    public void setRight(Node r) {
        this.right = r;
    }
    
    /**
     *  Check for left child node.
     *
     *  @return state of left child node
     */
    public boolean hasLeft() {
        return left != null; 
    }
    
    /** Gets the left child node
     * 
     * @return left child node, null if not set.
     */
    public Node getLeft() {
        return left;
    }
    
    /** Sets the left child node.
     *
     * @param Node - left node to be set.
     */
    public void setLeft(Node l) {
        this.left = l;
    }

    /**
     *  Check for parent child node.
     *
     *  @return state of parent child node
     */
    public boolean hasParent() {
        return parent != null; 
    }
    
    /** Gets the parent child node
     * 
     * @return parent child node, null if not set.
     */
    public Node getParent() {
        return parent;
    }
    
    /** Sets the parent child node.
     *
     * @param Node - parent node to be set.
     */
    public void setParent(Node p) {
        this.parent = p;
    }

    /**
     * Increments the word count.
     */
    public void inc() { ++count; }

    /**
     * Decrements the word count. Wont go below 0.
     */
    public void dec() { 
        if(count > 0)
            --count; 
    }

    /** 
     * Returns the word count.
     *
     * @return number of times this word has been added.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Returns the word this node contains
     * 
     * @return the word this node contains
     */
    public String getWord() { 
        return this.word;
    }
}
