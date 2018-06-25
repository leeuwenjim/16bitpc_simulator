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

    std::string decodeNOOP();
    std::string decodeMOVE(const std::string & parameter1, const std::string & parameter2);
    std::string decodeCLEAR(const std::string & registerCode);
    std::string decodeLOADDIRECT(const std::string& parameter1, const std::string&  parameter2);
    std::string decodeJUMP(const bool &carry, const bool &zero, const bool &equals, const bool & greater, const std::string & label);
    std::string decodeCALL(const std::string & label);
    std::string decodeRETURN();
    std::string decodeSTORELOAD(bool write, const std::string & parameter1, const std::string & parameter2);
    std::string decodeALU(alu_functions & function);
    std::string decodeTOGGLESIGN();
    std::string decodeHALT();

public:
    translator(std::map<std::string, int> & labels, int amountOfMachineInstructions) :
            labels(labels), amountOfMachineInstructions(amountOfMachineInstructions) {};

    void assembleLine(line &l);
};

uint8_t registerToInt(const std::string & registerCode);
uint16_t stringToInteger(const std::string & value);


#endif //INC_16BITPC_TRANSLATOR_HPP
