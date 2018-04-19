//
// Created by Jim van Leeuwen on 19-4-2018.
//
#ifndef INC_16BITPC_BINARYSET_H
#define INC_16BITPC_BINARYSET_H

#include <string>

class BinaryCode {
private:
    std::string binary;
public:
    BinaryCode(std::string binary);
    std::string getBinaryCode();
};

struct Binary {
    BinaryCode NOOP = BinaryCode("000000");
    BinaryCode HALT = BinaryCode("000001");
    BinaryCode TGSM = BinaryCode("000010");
    BinaryCode CLRP = BinaryCode("000011");
    BinaryCode CLRA = BinaryCode("000100");
    BinaryCode CLRB = BinaryCode("000101");
    BinaryCode CLRO = BinaryCode("000110");
    BinaryCode CLRM = BinaryCode("000111");

    BinaryCode BTOA = BinaryCode("001000");
    BinaryCode LDA = BinaryCode("001001");
    BinaryCode LDAD = BinaryCode("001010");
    BinaryCode ATOB = BinaryCode("001100");
    BinaryCode LDB = BinaryCode("001101");
    BinaryCode LDBD = BinaryCode("001110");

    BinaryCode STRA = BinaryCode("010000");
    BinaryCode STRB = BinaryCode("010001");

    BinaryCode CALL = BinaryCode("011000");
    BinaryCode RTN = BinaryCode("011001");

    BinaryCode OUTA = BinaryCode("100000");
    BinaryCode OUTB = BinaryCode("100001");
    BinaryCode OUTR = BinaryCode("100010");
    BinaryCode OUTD = BinaryCode("100011");

    BinaryCode JMP = BinaryCode("101000");
    BinaryCode JMPC = BinaryCode("101001");
    BinaryCode JMPZ = BinaryCode("101010");
    BinaryCode JMPE = BinaryCode("101011");
    BinaryCode JMPG = BinaryCode("101100");

    BinaryCode FLAG = BinaryCode("110000");
    BinaryCode NOT = BinaryCode("110001");
    BinaryCode OR = BinaryCode("110010");
    BinaryCode AND = BinaryCode("110011");
    BinaryCode SFTL = BinaryCode("110100");
    BinaryCode SFTR = BinaryCode("110101");
    BinaryCode ADD = BinaryCode("110110");
    BinaryCode SUB = BinaryCode("110111");
};

#endif //INC_16BITPC_BINARYSET_H
