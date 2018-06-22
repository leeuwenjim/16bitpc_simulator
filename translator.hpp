//
// Created by Jim van Leeuwen on 18-6-2018.
//

#ifndef INC_16BITPC_TRANSLATOR_HPP
#define INC_16BITPC_TRANSLATOR_HPP

#include <map>
#include "line.hpp"
#include <bitset>
#include "alu_functions.hpp"
#include "errorHandler.hpp"

class translator {
private:
    std::map<std::string, int> labels;
    int amountOfMachineInstructions;
public:
    translator(std::map<std::string, int> & labels, int amountOfMachineInstructions) :
            labels(labels), amountOfMachineInstructions(amountOfMachineInstructions) {};

    void assembleLine(line &l);

    std::string decodeNOOP();
    std::string decodeMOVE(std::string parameter1, std::string parameter2);
    std::string decodeCLEAR(std::string registerCode);
    std::string decodeLOADDIRECT(std::string parameter1, std::string parameter2);
    std::string decodeJUMP(bool carry, bool zero, bool equals, bool greater, std::string label);
    std::string decodeCALL(std::string label);
    std::string decodeRETURN();
    std::string decodeSTORELOAD(bool write, std::string parameter1, std::string parameter2);
    std::string decodeALU(alu_functions function);
    std::string decodeTOGGLESIGN();
    std::string decodeHALT();

};

uint8_t registerToInt(std::string registerCode);
int stringToInteger(std::string value);


#endif //INC_16BITPC_TRANSLATOR_HPP
