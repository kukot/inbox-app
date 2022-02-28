package com.kukot.inboxapp.config

import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

@ConfigurationProperties(prefix = "datastax.astra")
@ConstructorBinding
data class DataStaxAstraProperties(val secureConnectBundle: File)

@Configuration
@EnableConfigurationProperties(DataStaxAstraProperties::class)
class DatastaxAstraConfig {

    @Bean
    fun cqlSessionBuilderCustomizer(dataStaxAstraProperties: DataStaxAstraProperties): CqlSessionBuilderCustomizer {
        val bundle = dataStaxAstraProperties.secureConnectBundle.toPath()
        return CqlSessionBuilderCustomizer { cqlSessionBuilder ->
            cqlSessionBuilder!!.withCloudSecureConnectBundle(
                bundle
            )
        }
    }
}
