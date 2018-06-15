//
// Created by Jim van Leeuwen on 1-6-2018.
//

#ifndef INC_16BITPC_LINE_HPP
#define INC_16BITPC_LINE_HPP

#include <string>

//namespace file {

    class line {
    private:
        std::string rawLine;
        int lineNumber;
        std::string label = "";
        std::string opcode = "";
        std::string parameter_1 = "";
        std::string parameter_2 = "";
        int linesOfInstructions = 0;
    public:
        line():
            rawLine(""), lineNumber(-1) {}
        line(std::string rawLine, int lineNumber) :
                rawLine(rawLine), lineNumber(lineNumber) {}

        void splitLine();
        void printInfo();
        std::string getRawLine();
    };

    static line errorLine;
//}

#endif //INC_16BITPC_LINE_HPP
