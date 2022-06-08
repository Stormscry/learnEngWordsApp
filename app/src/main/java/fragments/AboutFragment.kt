package fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import com.example.pr3.DialogListValues
import com.example.pr3.R
import com.example.pr3.databinding.CustomAlertDialogBinding
import com.example.pr3.databinding.FragmentAboutBinding
import dialogFragments.SimpleDialogFragment
import dialogFragments.SingleChoiceDialogFragment
import kotlin.properties.Delegates


class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val b
        get() = _binding!!

    private var currValue = 7

    private var checkedSquares: BooleanArray = booleanArrayOf(false, true, true)

    private var seekBarValue = 25

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        b.tvChosenValue.text = b.tvChosenValue.text.toString().let {
            "$it $currValue"
        }
        return b.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b.btnSimpleDialogFragment.setOnClickListener {
            SimpleDialogFragment.show(parentFragmentManager)
        }
        SimpleDialogFragment.setupListener(parentFragmentManager, viewLifecycleOwner, requireContext())

        b.btnSingleChoiceDialogFragment.setOnClickListener {
            SingleChoiceDialogFragment.show(parentFragmentManager, currValue)
        }
        SingleChoiceDialogFragment.setupListener(parentFragmentManager, viewLifecycleOwner){
            currValue = it
            updateUi()
        }
    }

    private fun updateUi() {
        b.tvChosenValue.text = "Значение: $currValue"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}