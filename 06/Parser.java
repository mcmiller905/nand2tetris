import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public String aInst = "A_INSTRUCTION";
    public String cInst = "C_INSTRUCTION";
    public String lInst = "L_INSTRUCTION";

    List<String> lines = new ArrayList<>();
    int currentIndex = 0;
    Map<String, String> refTable = new HashMap<>();
    int nextRefTableIndex = 16;

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

        setupMap();
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

    public String instructionType(){
        String currentLine = getCurrentLine();
        if(currentLine.trim().startsWith("@")){
            return aInst;
        } else if (currentLine.trim().startsWith("(")){
            return lInst;
        } else {
            return cInst;
        }
    }

    public String symbol(){
        String currentLine = getCurrentLine();
        if(instructionType().equals(aInst)){
            String symbol = currentLine.substring(1);
            try{
                Integer.parseInt(symbol);
                return symbol;
            } catch (NumberFormatException e){
                String value = refTable.get(symbol);
                if(value == null){
                    refTable.put(symbol, String.valueOf(nextRefTableIndex++));
                }
                //System.out.println(symbol + " " + refTable.get(symbol));
                return refTable.get(symbol);
            }
        } else {
            return currentLine.substring(1, currentLine.length()-1);
        }
    }

    public String dest(){
        String currentLine = getCurrentLine();
        if(currentLine.contains("=")){
            return Code.dest(currentLine.split("=")[0]);
        }else{
            return Code.dest("");
        }
    }

    public String comp(){
        String currentLine = getCurrentLine();
        int startIndex = 0;
        int endIndex = 0;
        if(currentLine.contains("=")){
            startIndex = currentLine.indexOf("=") + 1;
        }
        if(currentLine.contains(";")){
            endIndex = currentLine.indexOf(";");
        }else{
            endIndex = currentLine.length();
        }
        return Code.comp(currentLine.substring(startIndex, endIndex));
    }

    public String jump(){
        String currentLine = getCurrentLine();
        if(currentLine.contains(";")){
            return Code.jump(currentLine.split(";")[1]);
        }else{
            return "000";
        }
    }

    public void printLine(){
        System.out.println(lines.get(currentIndex));
    }

    private String getCurrentLine(){
        String currentLine = lines.get(currentIndex).trim();
        if(currentLine.contains("//")){
            currentLine = currentLine.substring(0, currentLine.indexOf("//")).trim();
        }
        return currentLine;
    }

    private void setupMap(){
        refTable.put("R0", "0");
        refTable.put("R1", "1");
        refTable.put("R2", "2");
        refTable.put("R3", "3");
        refTable.put("R4", "4");
        refTable.put("R5", "5");
        refTable.put("R6", "6");
        refTable.put("R7", "7");
        refTable.put("R8", "8");
        refTable.put("R9", "9");
        refTable.put("R10", "10");
        refTable.put("R11", "11");
        refTable.put("R12", "12");
        refTable.put("R13", "13");
        refTable.put("R14", "14");
        refTable.put("R15", "15");
        //refTable.put("memory.0", "19");
        refTable.put("SP", "0");
        refTable.put("LCL", "1");
        refTable.put("ARG", "2");
        refTable.put("THIS", "3");
        refTable.put("THAT", "4");
        refTable.put("SCREEN", "16384");
        refTable.put("KBD", "24576");

        firstPass();
        //System.out.println(refTable);
    }

    private void firstPass(){
        int lineIndex = 0;
        for(String line : lines){
            line = line.trim();
            if(!(line.startsWith("//") || line.isEmpty())){
                if(line.startsWith("(")){
                    int firstIndex = line.indexOf("(");
                    int lastIndex = line.indexOf(")");
                    String symbol = line.substring(firstIndex+1, lastIndex);
                    //System.out.println(symbol);
                    refTable.put(symbol, String.valueOf(lineIndex));
                }else{
                    lineIndex++;
                }
            }
        }
    }

    // private int getNextRefTableIndex(){
    //     if(refTable.values().contains(String.valueOf(nextRefTableIndex))){
    //         nextRefTableIndex++;
    //         getNextRefTableIndex();
    //     }
    //     return nextRefTableIndex;
    // }
}
