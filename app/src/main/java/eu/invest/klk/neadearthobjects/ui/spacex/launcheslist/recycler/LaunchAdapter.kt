package eu.invest.klk.neadearthobjects.ui.spacex.launcheslist.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.data.db.entity.spaceX.next.Launch
import kotlinx.android.synthetic.main.item_space_x_launch.view.*
import java.lang.ref.WeakReference

class LaunchAdapter:ListAdapter<Launch,LaunchAdapter.LaunchViewHolder>(LaunchDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val context = WeakReference<Context>(parent.context) //Todo Weak Reference try
        val inflater = LayoutInflater.from(context.get())
        return LaunchViewHolder(inflater.inflate(R.layout.item_space_x_launch, parent, false))
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
       getItem(position).apply {
           holder.name.text = name.replace("|","\n",true).replace("(","\n(",true)
           holder.date.text = date
       }
    }

    class LaunchViewHolder(root:View):RecyclerView.ViewHolder(root){
        val name:TextView = root.name
        val date:TextView = root.date
    }
}