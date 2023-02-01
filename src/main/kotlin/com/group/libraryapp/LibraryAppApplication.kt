package com.group.libraryapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LibraryAppApplication

// 바로 함수를 만들 경우 static으로 동작한다
fun main(args: Array<String>) {
    // 코틀린용 스프링 어플리케이션 확장 함수
    runApplication<LibraryAppApplication>(*args)
}