package com.microservices.chapter2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * 명시적으로 빈 만들기
 * 조건으로 빈을 정의
 * 제어의 역전
 */
@SpringBootApplication
class Chapter2Application {
	@Bean
	@ConditionalOnExpression("#{'\${service.message.type}'=='simple'}")
	fun exampleService() : ServiceInterface = ExampleService()

	@Bean
	@ConditionalOnExpression("#{'\${service.message.type}'=='advance'}")
	fun advanceService() : ServiceInterface = AdvanceService()
}

fun main(args: Array<String>) {
	runApplication<Chapter2Application>(*args)
}

/**
 * Autowired로 의존관계 자동설정
 */
@Controller
class FirstController() {

//	@Autowired
//	lateinit var exampleService : ServiceInterface
	@Autowired
	lateinit var service : ServiceInterface
	@RequestMapping(value = arrayOf("/user"), method = arrayOf(RequestMethod.GET))
	@ResponseBody
	fun hello() = "hello world"

	@RequestMapping(value = arrayOf("/user/{name}"), method = arrayOf(RequestMethod.GET))
	@ResponseBody
	fun hello(@PathVariable name : String) = service.getHello(name)
}

/**
 * 사용할 Bean을 값으로 받아 사용
 */
/*
@Controller
class FirstController(val exampleService: ExampleService) {
	@RequestMapping(value = arrayOf("/user"), method = arrayOf(RequestMethod.GET))
	@ResponseBody
	fun hello() = "hello world"

	@RequestMapping(value = arrayOf("/user/{name}"), method = arrayOf(RequestMethod.GET))
	@ResponseBody
	fun hello(@PathVariable name: String) = exampleService.getHello(name)
}
*/