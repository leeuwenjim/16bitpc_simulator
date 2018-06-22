//
// Created by Jim van Leeuwen on 2-6-2018.
//

#include "file.hpp"
#include <fstream>
#include <iostream>
#include <string>

file::file(char *fileName, int amountOfLines) :
        lines(new line[amountOfLines]),
        amountOfLines(amountOfLines) {
    //READ FILE LINE FOR LINE AND AT THE LINES IN THE VECTOR
    std::string fline;
    std::ifstream file_to_open(fileName);
    if (file_to_open.is_open()) {
        int currentLine = 0;
        while (getline(file_to_open, fline)) {
            line current_line(fline, currentLine);

            //STORE CURRENT LINE
            lines[currentLine] = current_line;

            currentLine++;
        }

        std::cout << "amount of lines in the file: " << amountOfLines << std::endl;

        file_to_open.close();


    } else {
        handleAssembleError("Unable to open file: " + std::string(fileName));
    }
}

int file::getAmountOfFileLines() {
    return amountOfLines;
}

line &file::getLineAtLineNumber(int lineNumber) {

    if (lineNumber < 0 || lineNumber >= amountOfLines) {
        return errorLine;
    }
    return lines[lineNumber];
}

void file::toBegin() {
    currentPointer = -1;
}

bool file::next() {
    if (currentPointer < amountOfLines - 1) {
        ++currentPointer;
        return true;
    } else {
        toBegin();
        return false;
    }
}

line &file::get() {
    if (currentPointer < 0 || currentPointer >= amountOfLines) {
        return errorLine;
    }
    return lines[currentPointer];
}

int amountOfLinesInFile(char *fileName) {
    //READ FILE LINE FOR LINE AND AT THE LINES IN THE VECTOR
    std::string fline;
    std::ifstream file_to_open(fileName);
    if (file_to_open.is_open()) {
        int currentLine = 0;
        while (getline(file_to_open, fline)) {
            currentLine++;
        }

        file_to_open.close();
        return currentLine;

    } else {
        handleAssembleError("Unable to open file to check it length. File: " + std::string(fileName));
    }
}
