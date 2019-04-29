package eu.invest.klk.neadearthobjects.ui.neo.list.recycler

import androidx.recyclerview.widget.DiffUtil
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject

class NearEarthObjectDiffUtilCallback : DiffUtil.ItemCallback<NearEarthObject>() {
    override fun areItemsTheSame(oldItem: NearEarthObject, newItem: NearEarthObject): Boolean {
        return oldItem.neoReferenceId == newItem.neoReferenceId
    }

    override fun areContentsTheSame(oldItem: NearEarthObject, newItem: NearEarthObject): Boolean {
        return oldItem.neoReferenceId == newItem.neoReferenceId
    }
}