package wordTree.util;

public class MyLogger{

   /*DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
     DEBUG_VALUE=3 [Print to stdout everytime a word is processed]
     DEBUG_VALUE=2 [Print to stdout everytime a thread is spawned]
     DEBUG_VALUE=1 [Print to stdout everytime a result is added]
     DEBUG_VALUE=0 [No output should be printed from the applicatio to stdout. It is ok to write to the output file though" ]
    */

    public static enum DebugLevel {RELEASE, RESULT,THREAD ,WORD , CONSTRUCTOR};

    private static DebugLevel debugLevel;

    public static void setDebugValue (int levelIn)
    {
	switch (levelIn)
	{
	    case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
	    case 3: debugLevel = DebugLevel.WORD; break;
	    case 2: debugLevel = DebugLevel.THREAD; break;
	    case 1: debugLevel = DebugLevel.RESULT; break;
	    case 0: debugLevel = DebugLevel.RELEASE; break;
	}
    }

    public static DebugLevel getDebugValueFromInt (int levelIn) {
	    switch (levelIn) {
	        case 4: return DebugLevel.CONSTRUCTOR; 
            case 3: return DebugLevel.WORD; 
            case 2: return DebugLevel.THREAD; 
            case 1: return DebugLevel.RESULT;
	        default: return DebugLevel.RELEASE; 
	    }
    }

    public static void setDebugValue (DebugLevel levelIn)
    {
	    debugLevel = levelIn;
    }

    public static void writeMessage (String  message, int lvl ) {
        DebugLevel levelIn = getDebugValueFromInt(lvl);
	    if (levelIn == debugLevel)
	        System.out.println(message);
    }

    // @return None
    public static void writeMessage (String  message, DebugLevel levelIn)
    {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

   /**
    * @return String
    */
    public String toString() {
	return "Debug Level is " + debugLevel;
    }
}
