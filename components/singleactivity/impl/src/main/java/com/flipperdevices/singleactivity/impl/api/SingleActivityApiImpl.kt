package com.flipperdevices.singleactivity.impl.api

import android.content.Context
import android.content.Intent
import com.flipperdevices.core.di.AppGraph
import com.flipperdevices.deeplink.model.Deeplink
import com.flipperdevices.singleactivity.api.SingleActivityApi
import com.flipperdevices.singleactivity.impl.LAUNCH_PARAMS_INTENT
import com.flipperdevices.singleactivity.impl.SingleActivity
import com.flipperdevices.singleactivity.impl.SingleActivityHolder
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@ContributesBinding(AppGraph::class)
class SingleActivityApiImpl @Inject constructor(
    private val context: Context
) : SingleActivityApi {
    override fun open(deeplink: Deeplink?) {
        val currentActivity = SingleActivityHolder.getSingleActivity()
        if (currentActivity != null) {
            currentActivity.openExistActivity(deeplink)
            return
        }
        context.startActivity(
            Intent(context, SingleActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(LAUNCH_PARAMS_INTENT, deeplink)
            }
        )
    }
}
