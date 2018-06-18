package wordTree.threadMgmt;

import java.util.ArrayList;
import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;
import wordTree.store.Results;

public class CreateWorkers
{
    private Tree wordTree;
    private FileProcessor input;
    private Results output;
    private ArrayList<Thread> popThreads;
    private ArrayList<Thread> delThreads;
	
   /**
    * This is the constructor for the CreateWorkers class. Its purpose
    * is to populate the private variables of this class.
    *
    * @param FileProcessor - object containing input file to read from
    * @param Results - object to display and store results of tree usage
    */
    public CreateWorkers(FileProcessor inputTxt, Results outputTxt)
    {
	wordTree = new Tree();
	input = inputTxt;
	output = outputTxt;
	popThreads = new ArrayList<Thread>();
	delThreads = new ArrayList<Thread>();
	MyLogger.writeMessage("CreateWorkers constructor called", 4);
    }

   /**
    * This function creates numThreads instances of the PopulateThread
    * class and use the threads to populate wordTree.
    *
    * @param int - number of PopulateThread instances to create
    */
    public void startPopulateWorkers(int numThreads)
    {
	for(int i=0; i<numThreads; i++)
	{
            PopulateThread popThread = new PopulateThread(wordTree, input, output);
	    Thread t = new Thread(popThread);
	    popThreads.add(t);
	    t.start();
	    MyLogger.writeMessage("PopulateThread created", 2);
	}

	try
	{
	    for(Thread t:popThreads)
	    {
	        t.join();
	    }
	}
	catch(InterruptedException e)
	{
 	    System.out.println("Interrupted Exception generated in startPopulateWorkers method of CreateWorkers class");
	    e.printStackTrace();
	}
    }

   /**
    * This function creates numThreads instances of the DeleteThread
    * class in order to remove the specified instances from wordTree.
    *
    * @param int - number of DeleteThread instances to create
    */
    public void startDeleteWorkers(int numThreads, String delWords)
    {
        String words[] = delWords.split(" ");
	for(int i=0; i<numThreads; i++)
	{
	    DeleteThread delThread = new DeleteThread(wordTree, input, output, words[i]);
	    Thread t = new Thread(delThread);
	    delThreads.add(t);
	    t.start();
	    MyLogger.writeMessage("DeleteThread created", 2);
	}

	try
	{
	    for(Thread t:delThreads)
	    {
		t.join();
	    }
	}
	catch(InterruptedException e)
	{
	    System.out.println("Interrupted Exception generated in startPopulateWorkers method of CreateWorkers class");
	    e.printStackTrace();
	}
    }

   /**
    * This function returns the results instance to the driver
    *
    * @return Results - output result object
    */
    public Results getResults()
    {
	wordTree.prepareArray();

	int[] results = new int[3];
	results[0] = wordTree.getWordCount();
	results[1] = wordTree.getCharacterCount();
	results[2] = wordTree.getUniqueWordCount();
	output.storeNewResult(results);

	output.printResults();
	    
	return output;
    }
}
