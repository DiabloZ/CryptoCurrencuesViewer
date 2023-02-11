package com.suhov.cryptocurrencuesviewer.network.models.current

import com.google.gson.annotations.SerializedName

data class GraphList(

		@field:SerializedName("Response")
	val response: String? = null,

		@field:SerializedName("RateLimit")
	val rateLimit: RateLimit? = null,

		@field:SerializedName("Type")
	val type: Int? = null,

		@field:SerializedName("FirstValueInArray")
	val firstValueInArray: Boolean? = null,

		@field:SerializedName("ConversionType")
	val conversionType: ConversionType? = null,

		@field:SerializedName("Aggregated")
	val aggregated: Boolean? = null,

		@field:SerializedName("TimeFrom")
	val timeFrom: Int? = null,

		@field:SerializedName("TimeTo")
	val timeTo: Int? = null,

		@field:SerializedName("HasWarning")
	val hasWarning: Boolean? = null,

		@field:SerializedName("Data")
	val data: List<DataItem?>? = null
)

data class RateLimit(
	val any: Any? = null
)

data class ConversionType(

	@field:SerializedName("conversionSymbol")
	val conversionSymbol: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class DataItem(

	@field:SerializedName("high")
	val high: Double? = null,

	@field:SerializedName("low")
	val low: Double? = null,

	@field:SerializedName("conversionSymbol")
	val conversionSymbol: String? = null,

	@field:SerializedName("volumeto")
	val volumeto: Double? = null,

	@field:SerializedName("volumefrom")
	val volumefrom: Double? = null,

	@field:SerializedName("time")
	val time: Int? = null,//

	@field:SerializedName("conversionType")
	val conversionType: String? = null,

	@field:SerializedName("close")
	var close: Double? = null,//

	@field:SerializedName("open")
	val open: Double? = null,

	var timeConvert: String = ""//
)
