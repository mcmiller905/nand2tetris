import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    
    public static String C_ARITHMETIC = "arithmetic";
    public static String C_LABEL = "label";
    public static String C_GOTO = "goto";
    public static String C_IF = "if";
    public static String C_FUNCTION = "function";
    public static String C_RETURN = "return";
    public static String C_CALL = "call";
    
    List<String> lines = new ArrayList<>();
    int currentIndex = 0;

    public Parser(String filename){
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public boolean hasMoreLines(){
        return (lines.size()-1 > currentIndex);
    }

    public void advance(){
        boolean keepMoving = true;
        while(keepMoving && hasMoreLines()){
            currentIndex++;
            String currentLine = lines.get(currentIndex);
            if(!(currentLine.startsWith("//") || currentLine.isEmpty())){
                keepMoving = false;
            }
        }
    }

    // for this exercise, it looks like we only need push/pop and arithmetic
    public String commandType(){
        String currentLine = getCurrentLine();
        String commandSymbol = currentLine.split(" ")[0];
        String retval = commandSymbol;

        if(commandSymbol.equals("pop")){
            retval = CodeWriter.C_POP;
        }else if(commandSymbol.equals("push")){
            retval = CodeWriter.C_PUSH;
        }

        return retval;
    }

    public String arg1(){
        return getCurrentLine().split(" ")[1];
    }

    public int arg2(){
        String arg2 = getCurrentLine().split(" ")[2];
        return Integer.parseInt(arg2);
    }

    public String getCurrentLine(){
        String currentLine = lines.get(currentIndex).trim();
        if(currentLine.contains("//")){
            currentLine = currentLine.substring(0, currentLine.indexOf("//")).trim();
        }
        return currentLine;
    }
}
