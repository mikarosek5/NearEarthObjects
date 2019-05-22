package eu.invest.klk.neadearthobjects.ui.spacex.next_launch.recycler.mission

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.details.Mission
import kotlinx.android.synthetic.main.item_mission.view.*
import java.lang.ref.WeakReference

class MissionAdapter:androidx.recyclerview.widget.ListAdapter<Mission,MissionAdapter.MissionViewHolder>(DiffUtilMission()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionViewHolder {
        val context = WeakReference<Context>(parent.context)
        val inflater = LayoutInflater.from(context.get())
        return MissionViewHolder(inflater.inflate(R.layout.item_mission,parent,false))
    }

    override fun onBindViewHolder(holder: MissionViewHolder, position: Int) {
        getItem(position).apply {
            holder.also {
                it.name.text = name
                it.description.text = if (description.length<500) description else description.substring(0,500) + "..."
                it.typeName.text = typeName
                it.count.text = "${position+1}/$itemCount"
            }
        }
    }

    class MissionViewHolder(root:View):RecyclerView.ViewHolder(root){
        val name:TextView = root.name
        val typeName:TextView = root.type_name
        val description:TextView = root.description
        val count:TextView = root.count
    }
}