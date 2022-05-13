import java.io.IOException;

public class VMTranslator {
    public static void main(String args[]) throws IOException{
        String fileName = String.valueOf(args[0]);
        Parser parser = new Parser(fileName);
        String outFileName = fileName.replace(".vm", ".asm");
        CodeWriter codeWriter = new CodeWriter(outFileName);
        while(parser.hasMoreLines()){
            parser.advance();
            //System.out.println(parser.getCurrentLine());
            if(parser.commandType().equals(CodeWriter.C_POP)||parser.commandType().equals(CodeWriter.C_PUSH)){
                codeWriter.writePushPop(parser.commandType(), parser.arg1(), parser.arg2());
            }else{
                codeWriter.writeArithmetic(parser.commandType());
            }
        }   
    }
}
