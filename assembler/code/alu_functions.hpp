//
// Created by Jim van Leeuwen on 22-6-2018.
//

#ifndef INC_16BITPC_ALU_FUNCTIONS_HPP
#define INC_16BITPC_ALU_FUNCTIONS_HPP


class alu_functions {
private:
    int code;
public:
    alu_functions(int code):
            code(code) {};
    int getCode();
};

static alu_functions addAB(7);
static alu_functions subtractAB(6);
static alu_functions invertA(5);
static alu_functions andAB(4);
static alu_functions orAB(3);
static alu_functions shiftRightA(2);
static alu_functions shiftLeftA(1);
static alu_functions compareAB(0);



#endif //INC_16BITPC_ALU_FUNCTIONS_HPP
