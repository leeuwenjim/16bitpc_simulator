#include <iostream>
#include "file.hpp"
#include <string>
#include <map>
#include "translator.hpp"
//#include "errorHandler.hpp"

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

    //TODO FINAL COMPILING OF ALL LINES


    while (rawFile.next()) {
        line &l = rawFile.get();
        l.printInfo();
    }

    std::cout << std::endl;

    for (const auto &row : labelMap) {
        std::cout << "Key: " << row.first << "; Value: " << row.second << std::endl;
    }

}

int main(int argc, char *argv[]) {

    std::cout << "starting 16 bit pc assembler" << std::endl;
/*

    if (argc > 1) {

        std::cout << "assembling file: " << argv[1] << std::endl;

        int fileLength = amountOfLinesInFile(argv[1]);
        file rawFile(argv[1], fileLength);
        processFile(rawFile);

    } else {
        handleAssembleError(" argument count was not sufficient!");
    }
*/

    //std::cout << decodeTOGGLESIGN() << std::endl;


    //std::getchar();


    std::string str_dec = "a2001, A Space Odyssey";

    int i_dec = std::stoi (str_dec, nullptr, 0);

    std::cout << str_dec << ": " << i_dec << std::endl;

    return 0;

}
