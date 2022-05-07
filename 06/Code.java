public class Code {
    
    public static String dest(String destSymbol){
        String dPart = "0";
        String aPart = "0";
        String mPart = "0";
        //System.out.println(destSymbol);
        if(destSymbol.contains("D")){
            dPart = "1";
        }
        if(destSymbol.contains("A")){
            aPart = "1";
        }
        if(destSymbol.contains("M")){
            mPart = "1";
        }
        return aPart + dPart + mPart;
    }

    public static String comp(String compSymbol){
        String compBin = "0000000";
        //System.out.println(compSymbol);
        if(compSymbol.equals("0")){
            compBin = "0101010";
        }else if(compSymbol.equals("1")){
            compBin = "0111111";
        }else if(compSymbol.equals("-1")){
            compBin = "0111010";
        }else if(compSymbol.equals("D")){
            compBin = "0001100";
        }else if(compSymbol.equals("A")){
            compBin = "0110000";
        }else if(compSymbol.equals("M")){
            compBin = "1110000";
        }else if(compSymbol.equals("!D")){
            compBin = "0001101";
        }else if(compSymbol.equals("!A")){
            compBin = "0110001";
        }else if(compSymbol.equals("!M")){
            compBin = "1110001";
        }else if(compSymbol.equals("-D")){
            compBin = "0001111";
        }else if(compSymbol.equals("-A")){
            compBin = "0110011";
        }else if(compSymbol.equals("-M")){
            compBin = "1110011";
        }else if(compSymbol.equals("D+1")){
            compBin = "0011111";
        }else if(compSymbol.equals("A+1")){
            compBin = "0110111";
        }else if(compSymbol.equals("M+1")){
            compBin = "1110111";
        }else if(compSymbol.equals("D-1")){
            compBin = "0001110";
        }else if(compSymbol.equals("A-1")){
            compBin = "0110010";
        }else if(compSymbol.equals("M-1")){
            compBin = "1110010";
        }else if(compSymbol.equals("D+A")){
            compBin = "0000010";
        }else if(compSymbol.equals("D+M")){
            compBin = "1000010";
        }else if(compSymbol.equals("D-A")){
            compBin = "0010011";
        }else if(compSymbol.equals("D-M")){
            compBin = "1010011";
        }else if(compSymbol.equals("A-D")){
            compBin = "0000111";
        }else if(compSymbol.equals("M-D")){
            compBin = "1000111";
        }else if(compSymbol.equals("D&A")){
            compBin = "0000000";
        }else if(compSymbol.equals("D&M")){
            compBin = "1000000";
        }else if(compSymbol.equals("D|A")){
            compBin = "0010101";
        }else if(compSymbol.equals("D|M")){
            compBin = "1010101";
        }
        return compBin;
    }

    public static String jump(String jumpSymbol){
        String jumpBin = "000";
        //System.out.println(jumpSymbol);
        if(jumpSymbol.equals("JGT")){
            jumpBin = "001";
        }else if(jumpSymbol.equals("JEQ")){
            jumpBin = "010";
        }else if(jumpSymbol.equals("JGE")){
            jumpBin = "011";
        }else if(jumpSymbol.equals("JLT")){
            jumpBin = "100";
        }else if(jumpSymbol.equals("JNE")){
            jumpBin = "101";
        }else if(jumpSymbol.equals("JLE")){
            jumpBin = "110";
        }else if(jumpSymbol.equals("JMP")){
            jumpBin = "111";
        }
        return jumpBin;
    }
}
