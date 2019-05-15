package eu.invest.klk.neadearthobjects.ui.error_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import eu.invest.klk.neadearthobjects.R
import eu.invest.klk.neadearthobjects.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.something_went_wrong.*

class ErrorFragment:ScopedFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.something_went_wrong,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUi()
    }

    private fun bindUi(){
        home_button.setOnClickListener {
            Navigation.findNavController(it).navigate(ErrorFragmentDirections.actionErrorFragmentToPictureOfDayFragment())
        }
    }

}