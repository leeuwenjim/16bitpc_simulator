#include <iostream>
#include "file.hpp"
#include <string>
#include <map>

void processFile(file rawFile){

    rawFile.toBegin();

    while(rawFile.next()) {
        line & l = rawFile.get();
        l.splitLine();
    }

    //TODO MATCH LABELS WITH LINE NUMBERS
    std::map<std::string, int> labelMap;

    int currentMachineLine = 0;

    while(rawFile.next()) {
        line & l = rawFile.get();
        if (l.getLabel() != "") {
            labelMap[l.getLabel()] = currentMachineLine;
        }
        if (l.getOpcode() != "") {
            ++currentMachineLine;
        }
    }

    //TODO FINAL COMPILING OF ALL LINES


    while(rawFile.next()) {
        line & l = rawFile.get();
        l.printInfo();
    }

    std::cout << std::endl;

    for (const auto &row : labelMap) {
        std::cout << "Key: " << row.first << "; Value: " << row.second << std::endl;
    }

}

int main(int argc, char* argv[]) {

    std::cout << "starting 16 bit pc assembler" << std::endl;

    if (argc > 1) {

        std::cout << "assembling file: " << argv[1] << std::endl;

        int fileLength = amountOfLinesInFile( argv[1] );
        file rawFile( argv[1], fileLength );
        processFile( rawFile );

    } else {
        std::cout << " argument count was not sufficient!" << std::endl;
        std::getchar();
        return 1;
    }


    //std::getchar();
    return 0;

}
