package eu.invest.klk.neadearthobjects.ui.neo.list.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import kotlinx.android.synthetic.main.item_neo.view.*
import java.lang.ref.WeakReference

class NeoListAdapter: PagedListAdapter<NearEarthObject, NeoListAdapter.NeoViewHolder>(NearEarthObjectDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeoViewHolder {
        val context = WeakReference<Context>(parent.context) //Todo Weak Reference try
        val inflater = LayoutInflater.from(context.get())
        return NeoViewHolder(inflater.inflate(R.layout.item_neo, parent, false))
    }

    override fun onBindViewHolder(holder: NeoViewHolder, position: Int) {
        /**
         * safe call for position cause of paging
         */
        getItem(position)?.apply {
            holder.apply {
                orbitClass.text = orbitalData?.orbitClass?.orbitClassRange
                asteroidName.text = name
                neoId.text = neoReferenceId
                absMagnitude.text = absoluteMagnitudeH.toString()
                estimatedDiameterMax.text = estimatedDiameter.kilometers.estimatedDiameterMax.toString()
                estimatedDiameterMin.text = estimatedDiameter.kilometers.estimatedDiameterMin.toString()
                isPotentiallyHazardous.text = if (isPotentiallyHazardousAsteroid) "Yes" else "No"

                //Todo add rest of the content
            }
        }
    }

    class NeoViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        val orbitClass:TextView = root.orbit_class
        val asteroidName:TextView = root.asteroid_name
        val neoId:TextView = root.neo_id
        val absMagnitude:TextView = root.abs_magnitude
        val estimatedDiameterMin:TextView = root.est_min
        val estimatedDiameterMax:TextView = root.est_max
        val isPotentiallyHazardous:TextView = root.is_dangerous

        //Todo add rest of the content //platform type check

    }
}