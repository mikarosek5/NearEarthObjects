package eu.invest.klk.neadearthobjects.ui.spacex.launcheslist.recycler

import androidx.recyclerview.widget.DiffUtil
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.next.Launch

class LaunchDiffUtilCallback : DiffUtil.ItemCallback<Launch>() {
    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.id == newItem.id
    }
}