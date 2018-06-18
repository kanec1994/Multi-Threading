import wordTree.store.Results;
import wordTree.threadMgmt.CreateWorkers;
import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;

public class Driver
{
    public static void main(String[] args)
    {
	if(args.length == 5)
	{
	    FileProcessor inputTxt = new FileProcessor(args[0]);
	    Results outputTxt = new Results(args[1]);
	    int NUM_THREADS = Integer.parseInt(args[2]);
	    String deleteWordsTxt = args[3];
	    MyLogger.setDebugValue(Integer.parseInt(args[4]));
	    
	    CreateWorkers createTree = new CreateWorkers(inputTxt, outputTxt);

	    if(NUM_THREADS == 3)
	    {
		createTree.startPopulateWorkers(NUM_THREADS);
		createTree.startDeleteWorkers(NUM_THREADS, deleteWordsTxt);
		outputTxt = createTree.getResults();
	    }

     	    outputTxt.close();
	}
	else
	{
	    System.err.println("Incorrect number of arguments");
	}
    }
}
