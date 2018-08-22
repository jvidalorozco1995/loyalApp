package com.groupsoft.loyalapp.models

import android.graphics.drawable.Drawable

/**
 * Created by JORGE-PC on 12/08/2018.
 */
class Store {

    var nameStore : String
    var descriptionStore : String
    var image : Drawable

    constructor(nameStore: String, descriptionStore: String, image: Drawable) {
        this.nameStore = nameStore
        this.descriptionStore = descriptionStore
        this.image = image
    }

}