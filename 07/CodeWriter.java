import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class CodeWriter {
    
    public static String C_PUSH = "push";
    public static String C_POP = "pop";
    int variableIndex = 16;

    PrintStream stream;
    
    public CodeWriter(String outFileName) throws IOException{
        File outFile = new File(outFileName);
        stream = new PrintStream(outFile);
        System.setOut(stream);
        
        // set the Stack Pointer
        // System.out.println("@256");
        // System.out.println("D=A");
        // System.out.println("@SP");
        // System.out.println("M=D");
    }

    public void writeArithmetic(String command){
        //System.out.println(command);
        if(command.equals("add")){
            System.out.println("@SP");          //go to Stack Pointer    
            System.out.println("A=M-1");        //set A to the value in SP - 1
            System.out.println("D=M");          //set D-reg to the value on top of stack
            System.out.println("@SP");          //go back to SP
            System.out.println("M=M-1");        //decrement stack value to remove top stack value
            System.out.println("A=M-1");        //set the Address to the new SP value
            System.out.println("M=D+M");        //add D-reg to the M value and keep on the stack

        }else if(command.equals("sub")){
            System.out.println("@SP");          //go to Stack Pointer    
            System.out.println("A=M-1");        //set A to the value in SP - 1
            System.out.println("D=M");          //set D-reg to the value on top of stack
            System.out.println("@SP");          //go back to SP
            System.out.println("M=M-1");        //decrement stack value to remove top stack value
            System.out.println("A=M-1");        //set the Address to the new SP value
            System.out.println("M=M-D");        //subtract D-reg from the M value and keep on the stack

        }else if(command.equals("neg")){
            System.out.println("@SP");          //go to Stack Pointer
            System.out.println("A=M-1");        //set A to the value of SP - 1
            System.out.println("M=0-M");        //make the value in M negative

        }else if(command.equals("eq")){

        }else if(command.equals("gt")){

        }else if(command.equals("lt")){

        }else if(command.equals("and")){

        }else if(command.equals("or")){

        }else if(command.equals("not")){

        }
    }

    public void writePushPop(String command, String segment, int index){

        if(command.equals(C_PUSH)){
            //////////
            // If the command is C_PUSH
            //////////

            printPointerLocation(segment, index);

            // set the value in D-reg onto stack
            System.out.println("@SP");          //go to the location containing the value of the Stack Pointer
            System.out.println("A=M");          //set Address register to the value stored in SP
            System.out.println("M=D");          //set the value stored in D-reg to RAM[A]
            System.out.println("@SP");          //go back to location containing value of Stack Pointer
            System.out.println("M=M+1");        //increment the value in SP
        
        
        }else{ //C_POP
            //////////
            // If the command is C_POP
            //////////

            // set the top stack value to the memory location segment+index
            System.out.println("@SP");          //go to location containing the value of the Stack Pointer
            System.out.println("A=M");          //set Address register to the value stored in SP
            System.out.println("D=M");          //set D=reg to the value in RAM[M]
            System.out.println("@LCL");         //go to location containing the value of the local pointer
            printPointerLocation(segment, index);
            System.out.println("@SP");          //go back to stack pointer value
            System.out.println("M=M-1");        //decrement value in stack pointer
        }
    }

    public void close(){
        stream.close();
    }

    private void printPointerLocation(String segment, int index){
        // Get memory location        
        if(segment.equals("constant")){
            // Put the contant value i into D-reg
            System.out.println("@" + String.valueOf(index));
            System.out.println("D=A");
        }else{
            if(segment.equals("local")){
                // Put the value stored in LCL+index into D-reg
                System.out.println("@LCL");
            }else if(segment.equals("argument")){
                // Put the value stored in ARG+index into D-reg
                System.out.println("@ARG");
            }else if(segment.equals("this")){
                // Put the value stored in THIS+index into D-reg
                System.out.println("@THIS");
            }else if(segment.equals("that")){
                // Put the value stored in THAT+index into D-reg
                System.out.println("@THAT");
            }else if(segment.equals("temp")){
                // Put the value stored in TEMP+index into D-reg
                System.out.println("@TEMP");
            }
            System.out.println("A=M+" + String.valueOf(index));
            System.out.println("D=M");
        }
    }
}
