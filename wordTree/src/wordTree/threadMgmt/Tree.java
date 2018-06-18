package wordTree.threadMgmt;
import wordTree.util.MyLogger;
import java.util.ArrayList;

class Tree {
    private Node head;
    private ArrayList<Node> nodes;
    
    /**
     * Default constructor. Sets head to null and creates the arraylist
     * that contains all the nodes, for use in counting and printing
     */
    public Tree() {
        head = null;
        nodes = new ArrayList<Node>();
        MyLogger.writeMessage("Created ArrayList<Node> in Tree", 4);
    }



    /**
     * Public function for adding a word. If list is empty,
     * head is instantiated.  If head is already created, it calls
     * the recursive addWordWrap(String, Node) function.
     *
     * @param String - word to be inserted to the tree
     */
    public synchronized void addWord(String word) {
        if(head == null) {
            head = new Node(word);
            MyLogger.writeMessage("Created the head Node in tree", 4);
        } else {
            addWordWrap(word, head);
            MyLogger.writeMessage("Parsed a word in Tree", 3);
        }
    }

    /**
     * Private wrapper function for addWord(String).
     * Searches the BST as normal. If a word isnt found, a node
     * is created and its word is set there, along with its parent.
     * If a node exists with this word, its number is incremented.
     *
     * Runs with a complexity of O(log(N)), as BST trees do.
     *
     * @param String - word to be searched for
     * @param Node - current node to be searched
     */
    private void addWordWrap(String word, Node node) {
        while(node.isLocked()){}
        node.setLock();
        int compare = word.compareTo(node.getWord());
        if(compare == 0) {
            node.inc();
            node.freeLock();
        } else if(compare > 0) {
            //Right side    
            if(node != null && node.hasRight()) {
                node.freeLock();
                addWordWrap(word, node.getRight());
            } else {
                Node n = new Node(word, node);
                MyLogger.writeMessage("Constructed a new Node in Tree", 4);
                node.setRight(n);
                node.freeLock();
            }
        } else {
            //Left side
            if(node != null && node.hasLeft()) {
                node.freeLock();
                addWordWrap(word, node.getLeft());
            } else {
                Node n = new Node(word, node);
                MyLogger.writeMessage("Constructed a new Node in Tree", 4);
                node.setLeft(n);
                node.freeLock();
            }
        }
        node.freeLock();
    }

    /**
     * Public function for deleting a word in the list.
     * If head is null it returns, otherwise it calls the private
     * wrapper function deleteWordWrap(String, Node)
     *
     * @param String - word to be decremented
     */
    public synchronized void deleteWord(String word) {
        if(head == null) {
            return;
        } else {
            deleteWordWrap(word, head);
            MyLogger.writeMessage("Decremented a word Node in Tree", 3);
        }
    }

    /**
     * Private wrapper function for deleting/decrementing a word.
     * Searches the list in the same way as addWordWrap(String, Node),
     * but instead decrements the node instead of adding to it.
     * If a node cant be found with that word, nothing happens.
     */
    private void deleteWordWrap(String word, Node node) {
        while(node.isLocked()){}
        node.setLock();
        int compare = word.compareTo(node.getWord());
        if(compare == 0) {
            node.dec();
            node.freeLock();
        } else if(compare > 0 && node.hasRight()) {
            node.freeLock();
            deleteWordWrap(word, node.getRight());
        } else if(compare < 0 && node.hasLeft()) {
            node.freeLock();
            deleteWordWrap(word, node.getLeft());
        }
        node.freeLock();
    }

    /**
     * Public function for preparing an arraylist for output.
     * Runs a simple DFS search on the tree to add all the nodes.
     */
    public void prepareArray() {
        nodes.clear();
        if(head == null) return;    
        prepareArrayWrap(head);
    }

    /**
     * Private wrapper function for preparing array.
     * Runs a simple DFS on the tree.
     */
    private synchronized void prepareArrayWrap(Node n) {
        nodes.add(n);
        if(n.hasLeft())
            prepareArrayWrap(n.getLeft());
        if(n.hasRight())
            prepareArrayWrap(n.getRight());
    }

    /**
     * Gets the count of the total words inserted. 
     * Does NOT return unique words, use getUniqueWordCount() for that.
     *
     * @return total number of words in tree.
     */
    public int getWordCount() {
        int count = 0;
        for(Node n : nodes) {
            if(n != null) count += n.getCount();
        }
        return count;
    }

    /**
     * Gets the number of unique words in the tree.
     * A word is unquie if it exists in the tree and has a count
     * greater than 0.  If its count == 0, it is ignored.
     *
     * @return - total number of unique words, not including totally
     * deleted ones.
     */
    public int getUniqueWordCount() {
        int count = 0;
        for(Node n : nodes) {
            if(n != null && n.getCount() > 0) 
                ++count;
        }
        return count;
    }

    /**
     * Gets total number of characters in the tree.
     * Multiplys the count of words by the length of each word.
     * If a word count is 0, it will add 0.
     *
     * @return number of characters in tree.
     */
    public int getCharacterCount() {
        int count = 0;
        for(Node n : nodes) {
            if(n != null)
	    {
		String word = n.getWord();
                count += (word.length() * n.getCount());
	    }
        }
        return count;

    }

    


}
