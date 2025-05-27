package com.microservices.chapter6_gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class Chapter6GatewayApplication

fun main(args: Array<String>) {
	runApplication<Chapter6GatewayApplication>(*args)
}
