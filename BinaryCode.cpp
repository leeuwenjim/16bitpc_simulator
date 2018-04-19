//
// Created by Jim van Leeuwen on 19-4-2018.
//
#include "BinaryCode.h"
#include <string>

BinaryCode::BinaryCode(std::string binary):
    binary(binary){}

std::string BinaryCode::getBinaryCode() {
    return this->binary;
}
