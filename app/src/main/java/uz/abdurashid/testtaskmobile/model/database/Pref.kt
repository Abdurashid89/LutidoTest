package uz.abdurashid.testtaskmobile.model.database

import android.content.Context
import android.content.SharedPreferences

class Pref
{
    var context : Context? = null

   fun init(context: Context){
       this.context = context
   }

    fun start(){
        context?.getSharedPreferences("",0)

    }
}