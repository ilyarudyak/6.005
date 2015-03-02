package calculator;

/*
 * TODO define your symbols and groups from problem 1 here
 */

/**
 * Token type.
 */
enum Type {

    LEFTPAREN,	// (
    RIGHTPAREN,	// )

    NUMBER,		// 1, 2, 3, 4...

    INCH,
    POINT,      // 1 inch = 72 points

    PLUS,		// +
    MINUS,		// -
    TIMES,		// *
    DIVIDE,		// /
    WHITESPACE,
    EOF,
}