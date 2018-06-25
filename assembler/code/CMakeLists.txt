cmake_minimum_required(VERSION 3.10)
project(16bitpc)

set(CMAKE_CXX_STANDARD 14)

add_executable(16bitpc main.cpp file.cpp file.hpp line.cpp line.hpp translator.cpp translator.hpp alu_functions.cpp alu_functions.hpp errorHandler.hpp errorHandler.cpp)