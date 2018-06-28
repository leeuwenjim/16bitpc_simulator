#include <iostream>
#include "file.hpp"
#include <string>
#include <map>
#include "translator.hpp"

void processFile(file rawFile) {

    rawFile.toBegin();

    while (rawFile.next()) {
        line &l = rawFile.get();
        l.splitLine();
    }

    std::map<std::string, int> labelMap;

    int currentMachineLine = 0;

    while (rawFile.next()) {
        line &l = rawFile.get();
        if (l.getLabel() != "") {
            labelMap[l.getLabel()] = currentMachineLine;
        }
        if (l.getOpcode() != "") {
            ++currentMachineLine;
        }
    }

    translator lineTranslator(labelMap, currentMachineLine);

    while (rawFile.next()) {
        line &l = rawFile.get();
        lineTranslator.assembleLine(l);
    }

}

int main(int argc, char *argv[]) {

    std::cout << "starting 16 bit pc assembler" << std::endl;
    std::cout << "Created by Jim van Leeuwen on 28-6-2018" << std::endl;
    std::cout << "Use this program only for educational purposes" << std::endl;

    if (argc == 1)  {
        if (std::string(argv[1]) == "help") {
            std::cout << "16 bit assembler" << std::endl;
            std::cout << "Help menu" << std::endl;
            std::cout << "usage of assembler: " << std::endl;
            std::cout << "assemble16 [source file] [destination file]" << std::endl;
            std::cout << std::endl;

        } else {
            std::cout << "argument '" << argv[1] << "' was not recognized as valid" << std::endl;
        }
    } else if (argc > 2) {

        std::cout << "assembling file: " << argv[1] << std::endl;

        int fileLength = amountOfLinesInFile(argv[1]);
        file rawFile(argv[1], fileLength);
        processFile(rawFile);
        rawFile.writeToBinaryFile(argv[2]);

    } else {
        handleAssembleError(" argument count was not sufficient! Try: help");
    }
    std::getchar();
    return 0;

}
