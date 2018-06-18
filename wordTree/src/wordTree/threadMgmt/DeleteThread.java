package wordTree.threadMgmt;

import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;
import wordTree.store.Results;

class DeleteThread implements Runnable
{
    private Tree wordTree;
    private FileProcessor input;
    private Results output;
    private String delWord;

   /**     
    * This is the constructor for the DeleteThread       
    * class. Its purpose is to initialize the private variables    
    * of this class to ensure each Thread has the correct 
    * references to the objects it needs to read and modify.  
    *              
    * @param Tree - current wordTree to populate  
    * @param FileProcessor - file to read input from     
    * @param Results - used to output needed data  
    * @param String - word to be deleted by thread
    */
    public DeleteThread(Tree tree, FileProcessor inputTxt, Results outputTxt, String word)
    {
	wordTree = tree;
	input = inputTxt;
	output = outputTxt;
	delWord = word;
	MyLogger.writeMessage("DeleteThread constructor called", 4);
    }

   /** 
    * This is the DeleteThread implementation of run()  
    * from the Runnable class. This function will read input     
    * from the FileProcessor and delete the specified word
    * from wordTree.      
    *        
    * @return void - nothing is returned     
    */
    public void run()
    {
	wordTree.deleteWord(delWord);
    }
}
