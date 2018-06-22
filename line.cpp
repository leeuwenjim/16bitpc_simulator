//
// Created by Jim van Leeuwen on 1-6-2018.
//

#include "line.hpp"
#include <iostream>

void line::splitLine() {

    //const char *characters = rawLine.c_str();


    std::string token;

    for (const char &c : rawLine) {
        if (c == ':') {
            label = token;
            token.clear();
        } else if (isspace(c)) {
            if (token != "") {
                if (opcode == "") {
                    opcode = token;
                    token.clear();
                }
            }
        } else if (c == ',') {
            if (token != "") {
                parameter_1 = token;
                token.clear();
            } else {
                handleAssembleError(" Parameter 1 is unexpectedly empty at line " + lineNumber);
                std::getchar();
                exit(1);
            }
        } else if (c == '#') {
            if (token == "") {
                break;
            }
            if (opcode == "") {
                opcode = token;
                token.clear();
                break;
            }
            if (parameter_1 == "") {
                parameter_1 = token;
                token.clear();
                break;
            }
            if (parameter_2 == "") {
                parameter_2 = token;
                token.clear();
                break;
            }
        } else {
            token.append(1, c);
        }
    }

    if (token != "") {
        if (opcode == "") {
            opcode = token;
            token.clear();
        } else if (parameter_1 == "") {
            parameter_1 = token;
            token.clear();
        } else if (parameter_2 == "") {
            parameter_2 = token;
            token.clear();
        }
    }
}

void line::printInfo() {
    std::cout << "Linenumber: " << lineNumber << "; line text: " << rawLine << std::endl;
    std::cout << "{" << std::endl;
    std::cout << "label: " << label << std::endl;
    std::cout << "opcode: " << opcode << std::endl;
    std::cout << "parameter 1: " << parameter_1 << std::endl;
    std::cout << "parameter 2: " << parameter_2 << std::endl;
    std::cout << "}" << std::endl;
}

std::string line::getRawLine() {
    return rawLine;
}

std::string line::getOpcode() {
    return opcode;
}

std::string line::getLabel() {
    return label;
}

std::string line::getParameter1() {
    return parameter_1;
}

std::string line::getParameter2() {
    return parameter_2;
}

void line::setMachineCode(std::string code) {
    machineCode = code;
}