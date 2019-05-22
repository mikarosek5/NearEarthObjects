package eu.invest.klk.neadearthobjects.ui.spacex.next_launch.recycler.mission

import androidx.recyclerview.widget.DiffUtil
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details.Mission

class DiffUtilMission:DiffUtil.ItemCallback<Mission>() {
    override fun areItemsTheSame(oldItem: Mission, newItem: Mission): Boolean {
        return oldItem ==newItem
    }

    override fun areContentsTheSame(oldItem: Mission, newItem: Mission): Boolean {
        return oldItem ==newItem
    }
}