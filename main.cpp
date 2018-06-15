#include <iostream>
#include "file.hpp"
#include <string>


void processFile(file rawFile){

    rawFile.toBegin();

    //TODO SPLIT ALL LINES IN LABELS, OPCODES AND PARAMETERS
    while(rawFile.next()) {
        line & l = rawFile.get();
        l.splitLine();
    }

    while(rawFile.next()) {
        line & l = rawFile.get();
        l.printInfo();
    }

    //TODO PRECOMPILE ALL LINES


    //TODO MATCH LABELS WITH LINE NUMBERS


    //TODO FINAL COMPILING OF ALL LINES


    //
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
