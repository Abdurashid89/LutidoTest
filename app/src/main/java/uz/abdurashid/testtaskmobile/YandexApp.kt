package uz.abdurashid.testtaskmobile

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class YandexApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("522fb9ba-acc3-4c2a-ad64-371448cace44")
        MapKitFactory.initialize(this)
    }
}