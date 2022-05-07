import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class HackAssembler {
    public static void main(String args[]) throws IOException{
        String inputFileName = args[0];
        String outputFileName = inputFileName.replace(".asm", ".hack");
        Parser parser = new Parser(inputFileName);
        File outputFile = new File(outputFileName);
        PrintStream stream = new PrintStream(outputFile);
        System.setOut(stream);

        while(parser.hasMoreLines()){
            String outputLine = "";
            parser.advance();
            if(parser.instructionType().equals(parser.aInst)){
                String binaryValue = Integer.toBinaryString(Integer.parseInt(parser.symbol()));
                outputLine = ("0000000000000000" + binaryValue).substring(binaryValue.length());
                System.out.println(outputLine);
            }else if(parser.instructionType().equals(parser.lInst)){

            }else{
                outputLine = "111" + parser.comp() + parser.dest() + parser.jump();
                System.out.println(outputLine);
            }
            
        }
    }
}
