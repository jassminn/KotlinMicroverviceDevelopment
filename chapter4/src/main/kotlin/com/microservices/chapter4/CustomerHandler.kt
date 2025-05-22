package com.microservices.chapter4

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.EntityResponse.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.badRequest
import org.springframework.web.reactive.function.server.ServerResponse.created
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.ServerResponse.status
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.onErrorResume
import java.net.URI

@Component
class CustomerHandler(var customerService: CustomerService) {
//    fun get(serverRequest: ServerRequest): Mono<ServerResponse> {
//        return ok().body(Customer(1, "functional web").toMono(), Customer::class.java)
//    }
//fun get(serverRequest: ServerRequest) = ok().body(Customer(1,"functional web").toMono(), Customer::class.java)
//fun get(serverRequest: ServerRequest) = ok().body(customerService.getCustomer(serverRequest.pathVariable("id").toInt()))
fun get(serverRequest: ServerRequest) = customerService.getCustomer(serverRequest.pathVariable("id").toInt())
    .flatMap {
        ok().body(BodyInserters.fromValue<Customer>(it))
    }.switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun search(serverRequest: ServerRequest) = ok().body(customerService.searchCustomers(
        serverRequest.queryParam("nameFilter").orElse("")), Customer::class.java)

    fun create(serverRequest: ServerRequest) =
        customerService.createCustomer(serverRequest.bodyToMono()).flatMap{
            //status(HttpStatus.CREATED).body(BodyInserters.fromValue<Any>(it))
            //status(HttpStatus.CREATED).bodyValue(it)
            created(URI.create("/functional/customer/${it.id}")).build();
        }.onErrorResume(Exception::class){
            badRequest().bodyValue(ErrorResponse("error creating customer", it.message?:"error"))
        }
}