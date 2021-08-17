package br.com.liebersonsantos.marvelcharacters.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by lieberson on 8/16/21.
 * @author lieberson.xsantos@gmail.com
 */
data class Urls(
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
) : Serializable