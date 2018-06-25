//
// Created by Jim van Leeuwen on 22-6-2018.
//

#include "errorHandler.hpp"

void handleAssembleError(std::string errorMessage) {
    std::cout << errorMessage << std::endl;
    std::getchar();
    exit(1);
}