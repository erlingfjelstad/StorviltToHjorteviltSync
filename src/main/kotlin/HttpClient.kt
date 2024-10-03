package org.example

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json


const val HJORTEVILT_BASE_API_URL = "https://hjortevilt2-test.miljodirektoratet.no/api/"
const val ACCESS_TOKEN =
    "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9zaWQiOiIyODIyMSIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiJpdEBzdGF0c2tvZy5ubyIsIkJydWtlclR5cGUiOiJJbnRlcm4iLCJqdGkiOiI1YzRjZDMxYS1mYTk2LTRiNzktOWZmNS1iY2JhYTNhNjVlMWQiLCJzdWIiOiJpdEBzdGF0c2tvZy5ubyIsImlhdCI6IjAyLjEwLjIwMjQgMDguMjcuNTgiLCJuYmYiOjE3Mjc4NTc2NzgsImV4cCI6MTcyNzg3NTY3OH0.hKS8BYiKInQZ-9wDDVJhpge34FA4ugEUPdD-XXvbuxc"
const val REFRESH_TOKEN = "QxIhTh3kvgOu8vZzUF49XKH6bAbbbzlqoVZtjNzQP4I="
val httpClient by lazy {
    HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        install(Auth) {
            bearer {
                loadTokens {
                    return@loadTokens BearerTokens(accessToken = ACCESS_TOKEN, refreshToken = REFRESH_TOKEN)
                }
                refreshTokens {
                    return@refreshTokens BearerTokens(accessToken = ACCESS_TOKEN, refreshToken = REFRESH_TOKEN)
                }
            }
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }

            }
            level = LogLevel.ALL
        }
    }
}