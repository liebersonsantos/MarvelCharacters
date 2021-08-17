package br.com.liebersonsantos.marvelcharacters.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by lieberson on 8/16/21.
 * @author lieberson.xsantos@gmail.com
 */
data class Events (
    @SerializedName("available")
    val available : Int,
    @SerializedName("collectionURI")
    val collectionURI : String,
    @SerializedName("items")
    val items : List<Items>,
    @SerializedName("returned")
    val returned : Int
): Serializable