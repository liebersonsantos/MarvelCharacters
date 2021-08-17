package br.com.liebersonsantos.marvelcharacters.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by lieberson on 8/16/21.
 * @author lieberson.xsantos@gmail.com
 */
class Items(
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
    val name: String
) : Serializable