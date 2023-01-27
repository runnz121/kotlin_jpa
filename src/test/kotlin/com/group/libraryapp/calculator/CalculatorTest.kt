package com.group.libraryapp.calculator

import kotlin.math.exp

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.divideTest()
    calculatorTest.divideExceptionTest()
}

class CalculatorTest {

    fun addTest() {
        // given
        val calculator = Calculator(5)
        // when
        calculator.add(3)

        // Data 클래스 사용
//        val expectedCalculator = Calculator(9)
//        if (calculator != expectedCalculator) {
//            throw IllegalStateException()
//        }

        // then
        // public var를 사용 && backing properties 사용
        if (calculator.number != 8) {
            throw IllegalStateException()
        }
    }

    fun minusTest() {
        // given
        val calculator = Calculator(5)
        // when
        calculator.minus(3)
        // then
        if (calculator.number != 2) {
            throw IllegalStateException()
        }
    }
    fun multiplyTest() {
        // given
        val calculator = Calculator(5)
        // when
        calculator.multiply(2)
        // then
        if (calculator.number != 10) {
            throw IllegalStateException()
        }
    }
    fun divideTest() {
        // given
        val calculator = Calculator(5)
        // when
        calculator.divide(5)
        // then
        if (calculator.number != 1) {
            throw IllegalStateException()
        }
    }

    fun divideExceptionTest() {
        // given
        val calculator = Calculator(5)

        // when
        try {
            calculator.divide(0)
        } catch (e: java.lang.IllegalArgumentException) {
            if (e.message != "0으로 나눌 수 없습니다") {
                throw IllegalStateException("메시지가 다릅니다")
            }
            //테스트 성공
            return
        } catch (e: Exception) {
            throw IllegalStateException()
        }
        throw IllegalStateException("기대하는 예외가 발생하지 않았습니다")
    }


}