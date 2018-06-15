//
// Created by Jim van Leeuwen on 2-6-2018.
//

#ifndef INC_16BITPC_FILE_HPP
#define INC_16BITPC_FILE_HPP

#include <array>
#include "line.hpp"

//namespace file {

    class file {
    private:
        line* lines;
        int amountOfLines = -1;
        int currentPointer = -1;
    public:
        file( char* fileName, int amountOfLines );
        int getAmountOfFileLines();
        line & getLineAtLineNumber( int lineNumber );
        bool next();
        void toBegin();
        line & get();
    };

    int amountOfLinesInFile( char* fileName );

//}


#endif //INC_16BITPC_FILE_HPP
