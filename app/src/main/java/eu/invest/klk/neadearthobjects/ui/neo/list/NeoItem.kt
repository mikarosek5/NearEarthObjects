package eu.invest.klk.neadearthobjects.ui.neo.list



import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.data.db.entity.neo.list.NearEarthObject
import kotlinx.android.synthetic.main.item_neo.*

class NeoItem(private val nearEarthObject: NearEarthObject): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            nearEarthObject.apply {
                asteroid_name.text = name
                neo_id.text = neoReferenceId
                abs_magnitude.text = absoluteMagnitudeH.toString()
                est_min.text = estimatedDiameter.kilometers.estimatedDiameterMin.toString()
                est_max.text = estimatedDiameter.kilometers.estimatedDiameterMax.toString()
                is_dangerous.text = if (isPotentiallyHazardousAsteroid) "Yes" else "No"
                orbit_class.text = orbitalData.orbitClass.orbitClassRange
                textView.text = position.toString()
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_neo
    }
}