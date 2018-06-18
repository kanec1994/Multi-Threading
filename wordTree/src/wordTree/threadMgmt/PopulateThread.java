package wordTree.threadMgmt;

import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;
import wordTree.store.Results;

class PopulateThread implements Runnable
{
    private Tree wordTree;
    private FileProcessor input;
    private Results output;

   /**
    * This is the constructor for the PopulateThread
    * class. Its purpose is to initialize the private variables
    * of this class to ensure each Thread has the correct
    * references to the objects it needs to read and modify.
    *
    * @param Tree - current wordTree to populate
    * @param FileProcessor - file to read input from
    * @param Results - used to output needed data
    */
    public PopulateThread(Tree tree, FileProcessor inputTxt, Results outputTxt)
    {
        wordTree = tree;
	input = inputTxt;
	output = outputTxt;
	MyLogger.writeMessage("PopulateThread constructor called", 4);
    }
	
   /**
    * This is the PopulateThread implementation of run()
    * from the Runnable class. This function will read input
    * from the FileProcessor and Populate the Tree as needed.
    *
    * @return void - nothing is returned
    */
    public void run()
    {
	try
	{
	    String currLine = input.readLine();
	    while(currLine != null)
            {
		Thread.sleep(1);
	        processLine(currLine);
		currLine = input.readLine();
	    }
	}
	catch(InterruptedException e)
	{
	    System.err.println("Interrupted Exception generated in PopulateThread class!");
	    e.printStackTrace();
	}
    }

   /**
    * This function processes a line read by the PopulateThread.
    * Additionally this function adds the words processed by the
    * thread to the wordTree
    *
    * @param String - line to be processed
    * @return void - nothing to return
    */
    private void processLine(String line)
    {
	String[] words = line.split(" ");
	for(int i=0; i<words.length; i++)
	{
	    wordTree.addWord(words[i]);
	}
    }
}
