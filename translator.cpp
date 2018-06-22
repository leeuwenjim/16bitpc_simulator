//
// Created by Jim van Leeuwen on 18-6-2018.
//

#include "translator.hpp"
#include <locale>
#include <algorithm>

void translator::assembleLine(line &l) {
    std::string opcode = l.getOpcode();
    std::transform(opcode.begin(), opcode.end(), opcode.begin(), ::toupper);
    if (opcode == "NOOP") {
        l.setMachineCode(decodeNOOP());
    } else if (opcode == "CALL") {
        l.setMachineCode(decodeCALL(l.getParameter1()));
    } else if (opcode == "RTN") {
        l.setMachineCode(decodeRETURN());
    } else if (opcode == "CLR") {
        l.setMachineCode(decodeCLEAR(l.getParameter1()));
    } else if (opcode == "MOV") {
        l.setMachineCode(decodeMOVE(l.getParameter1(), l.getParameter2()));
    } else if (opcode == "JMP") {
        //TODO FIGURE OUT PARAMETER 1 FORMAT
    } else if (opcode == "STR") {
        l.setMachineCode(decodeSTORELOAD(false, l.getParameter1(), l.getParameter2()));
    } else if (opcode == "LD") {
        l.setMachineCode(decodeSTORELOAD(true, l.getParameter1(), l.getParameter2()));
    } else if (opcode == "LDD") {
        l.setMachineCode(decodeLOADDIRECT(l.getParameter1(), l.getParameter2()));
    } else if (opcode == "TGSN") {
        l.setMachineCode(decodeTOGGLESIGN());
    } else if (opcode == "HALT") {
        l.setMachineCode(decodeHALT());
    } else if (opcode == "ADD") {
        l.setMachineCode(decodeALU(addAB));
    } else if (opcode == "SUB") {
        l.setMachineCode(decodeALU(subtractAB));
    } else if (opcode == "INV") {
        l.setMachineCode(decodeALU(invertA));
    } else if (opcode == "AND") {
        l.setMachineCode(decodeALU(andAB));
    } else if (opcode == "OR") {
        l.setMachineCode(decodeALU(orAB));
    } else if (opcode == "SFTL") {
        l.setMachineCode(decodeALU(shiftLeftA));
    } else if (opcode == "SFTR") {
        l.setMachineCode(decodeALU(shiftRightA));
    } else if (opcode == "COMP") {
        l.setMachineCode(decodeALU(compareAB));
    } else {
        handleAssembleError("opcode not recognized. opcode: " + l.getOpcode());
    }
}

std::string translator::decodeNOOP() {
    return std::bitset<16>(0x0000).to_string();
}

std::string translator::decodeMOVE(std::string parameter1, std::string parameter2) {
    if (parameter1 == "$a") {
        if (parameter2 == "$o") {
            return std::bitset<16> (0xc800).to_string();
        } else if (parameter2 == "$b") {
            return std::bitset<16> (0x0c00).to_string();
        } else {
            handleAssembleError("couldn't move data from register a to: " + parameter2);
        }
    } else if (parameter1 == "$b") {
        if (parameter2 == "$o") {
            return std::bitset<16> (0xcc00).to_string();
        } else if (parameter2 == "$a") {
            return std::bitset<16> (0x0800).to_string();
        } else {
            handleAssembleError("couldn't move data from register b to: " + parameter2);
        }
    } else {
        handleAssembleError("couldn't find register to retrieve data from: " + parameter1);
    }
}

std::string translator::decodeCLEAR(std::string registerCode) {
    uint16_t code = 0x1000;
    code |= (registerToInt(registerCode) << 10);
    return std::bitset<16>(code).to_string();
}

std::string translator::decodeLOADDIRECT(std::string parameter1, std::string parameter2) {
    //TODO FIGURE THIS ONE OUT
    return "";
}

std::string translator::decodeJUMP(bool carry, bool zero, bool equals, bool greater, std::string label) {
    uint16_t code = 0x4000;
    if (carry) {
        code |= (1 << 13);
    }
    if (zero) {
        code |= (1 << 12);
    }
    if (equals) {
        code |= (1 << 11);
    }
    if (greater) {
        code |= (1 << 10);
    }
    if (labels.count(label) == 0) {
        handleAssembleError("Unexcepted label found: " + label);
    }
    code |= labels[label];
    return std::bitset<16> (code).to_string();

}

std::string translator::decodeCALL(std::string label) {
    if (labels.count(label) == 0) {
        handleAssembleError("Unexcepted label found: " + label);
    }
    uint16_t callCode = 0x8c00;
    callCode |= labels[label];
    return std::bitset<16> (callCode).to_string();
}

std::string translator::decodeRETURN() {
    return std::bitset<16> (8800).to_string();
}

std::string translator::decodeSTORELOAD(bool write, std::string parameter1, std::string parameter2) {
    uint16_t code = 0x2000;
    code |= (stringToInteger(parameter2) + amountOfMachineInstructions);
    code |= registerToInt(parameter1) << 10;
    if (write) {
        code |= 1 << 12;
    }
    return std::bitset<16> (code).to_string();
}

std::string translator::decodeALU(alu_functions function) {
    uint16_t code = 0xa000;
    code |= (function.getCode() << 10);
    return std::bitset<16> (code).to_string();
}

std::string translator::decodeTOGGLESIGN() {
    return std::bitset<16> (0xc400).to_string();
}

std::string translator::decodeHALT() {
    return std::bitset<16> (0xfc00).to_string();
}

uint8_t registerToInt(std::string registerCode) {
    if (registerCode == "$a") {
        return 0;
    } else if (registerCode == "$b") {
        return 1;
    } else if (registerCode == "$o") {
        return 2;
    } else {
        handleAssembleError("Illigal register as parameter: " + registerCode);
    }
}

int stringToInteger(std::string value) {
    int number = -1;
    try {
        number = std::stoi(value, nullptr, 0);
    } catch (int e) {
        handleAssembleError("an illigal number was found during assembling. Found number: " + value);
    }
    return number;
}