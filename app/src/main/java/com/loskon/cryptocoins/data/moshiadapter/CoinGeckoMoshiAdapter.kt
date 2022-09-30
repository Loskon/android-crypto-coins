package com.loskon.cryptocoins.data.moshiadapter

import com.loskon.cryptocoins.data.dto.CoinDto
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader

class CoinGeckoMoshiAdapter {

    @FromJson
    fun fromJson(reader: JsonReader): CoinDto = with(reader) {
        var id: String? = null
        var symbol: String? = null
        var name: String? = null
        var imageUrl: String? = null
        var currentPrice: Double? = null
        var priceChangePercentage: Double? = null
        var categories: List<String>? = null
        var description: String? = null

        beginObject()

        while (hasNext()) {
            when (nextName()) {
                "id" -> id = nextString()
                "symbol" -> symbol = nextString()
                "name" -> name = nextString()

                "image" -> {
                    when (peek()) {
                        JsonReader.Token.BEGIN_OBJECT -> {
                            beginObject()
                            while (hasNext()) {
                                if (peek() == JsonReader.Token.NAME) {
                                    if (nextName() == "large") {
                                        imageUrl = nextString()
                                    } else {
                                        skipValue()
                                    }
                                } else {
                                    skipValue()
                                }
                            }
                            endObject()
                            continue
                        }
                        JsonReader.Token.STRING -> {
                            imageUrl = nextString()
                        }
                        else -> skipValue()
                    }
                }

                "current_price" -> currentPrice = nextDouble()
                "price_change_percentage_24h" -> priceChangePercentage = nextDouble()
                "categories" -> categories = readStringArray(this)

                "description" -> {
                    when (peek()) {
                        JsonReader.Token.BEGIN_OBJECT -> {
                            beginObject()
                            while (hasNext()) {
                                if (peek() == JsonReader.Token.NAME) {
                                    if (nextName() == "en") {
                                        description = nextString()
                                    } else {
                                        skipValue()
                                    }
                                } else {
                                    skipValue()
                                }
                            }
                            endObject()
                            continue
                        }

                        else -> skipValue()
                    }
                }

                else -> skipValue()
            }
        }

        endObject()

        CoinDto(
            id = id,
            symbol = symbol,
            name = name,
            imageUrl = imageUrl,
            currentPrice = currentPrice,
            priceChangePercentage = priceChangePercentage,
            categories = categories,
            description = description
        )
    }

    private fun readStringArray(reader: JsonReader): List<String> = with(reader) {
        return mutableListOf<String>().apply {
            beginArray()
            while (hasNext()) add(nextString())
            endArray()
        }
    }
}