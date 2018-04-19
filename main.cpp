#include <iostream>
#include "BinaryCode.h"

int main() {
    Binary binarySet;
    std::cout << binarySet.NOOP.getBinaryCode() << std::endl;
    std::cout << binarySet.STRA.getBinaryCode() << std::endl;
    return 0;
}