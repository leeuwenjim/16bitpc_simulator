//
// Created by Jim van Leeuwen on 1-6-2018.
//

#ifndef INC_16BITPC_LINE_HPP
#define INC_16BITPC_LINE_HPP

#include <string>
#include "errorHandler.hpp"

class line {
private:
    std::string rawLine;
    int lineNumber;
    std::string label = "";
    std::string opcode = "";
    std::string parameter_1 = "";
    std::string parameter_2 = "";
    std::string machineCode = "";
public:
    line() :
            rawLine(""), lineNumber(-1) {}

    line(std::string rawLine, int lineNumber) :
            rawLine(rawLine), lineNumber(lineNumber) {}

    void splitLine();

    void printInfo();

    std::string getRawLine();

    std::string getOpcode();

    std::string getLabel();

    std::string getParameter1();

    std::string getParameter2();

    void setMachineCode(std::string code);

    std::string getMachineCode();

};

static line errorLine;


#endif //INC_16BITPC_LINE_HPP
