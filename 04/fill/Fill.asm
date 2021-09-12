// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.
    // n = 0
    @n
    M=0
    // RAM[1] = max
    @24576
    D=A
    @R1
    M=D
    // RAM[0] = SCREEN
    @SCREEN
    D=A
    @R0
    M=D
(LOOP)
    // if (n == R1) goto END
    @n
    D=M
    @R0
    D=D+M
    @R1
    D=D-M
    @RESET
    D;JEQ
    // if no keypress goto WHITE
    @KBD
    D=M
    @WHITE
    D;JEQ
    // if keypress goto BLACK
(BLACK)
    // *(R0 + n) = -1
    @R0
    D=M
    @n
    A=D+M
    M=-1
    // n = n + 1
    @n
    M=M+1
    // goto LOOP
    @LOOP
    0;JMP
(WHITE)
    // *(R0 + n) = -1
    @R0
    D=M
    @n
    A=D+M
    M=0
    // n = n + 1
    @n
    M=M+1
    // goto LOOP
    @LOOP
    0;JMP
(RESET)
    // RAM[0] = SCREEN
    @SCREEN
    D=A
    @R0
    M=D
    @n
    M=0
    @LOOP
    0;JMP
(END)
    @END
    0;JMP