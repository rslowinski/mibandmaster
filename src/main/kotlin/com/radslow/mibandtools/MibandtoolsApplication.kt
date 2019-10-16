package com.radslow.mibandtools

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters
import java.util.*
import javax.annotation.PostConstruct


@SpringBootApplication
@EntityScan(
    basePackageClasses = [
        MibandtoolsApplication::class,
        Jsr310JpaConverters::class
    ]
)
class MibandtoolsApplication {
    @PostConstruct
    fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }
}

fun main(args: Array<String>) {
    runApplication<MibandtoolsApplication>(*args)
}
