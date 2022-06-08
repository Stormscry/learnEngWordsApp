package dialogFragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.LifecycleOwner
import com.example.pr3.DialogListValues

class SingleChoiceDialogFragment: DialogFragment() {
    val value
        get() = requireArguments().getInt(VALUE_KEY)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val values = DialogListValues.createListValues(value) // DialogListValues созданный мной класс
        val listItems = values.valuesList.map {
            "Значение: $it"
        }.toTypedArray()
        return AlertDialog.Builder(requireContext())
            .setTitle("Single choice dialog fragment")
            .setSingleChoiceItems(listItems, values.currentIndex, null)
            .setPositiveButton("Confirm"){ d, _ ->
                val index = (d as AlertDialog).listView.checkedItemPosition
                setFragmentResult(REQUEST_KEY, bundleOf(RESPONSE_KEY to values.valuesList[index]))
            }
            .create()

    }

    companion object{
        const val TAG = "SingleChoiceDialogFragment"
        const val REQUEST_KEY = "$TAG:response_key"
        const val RESPONSE_KEY = "response"
        const val VALUE_KEY = "value"

        fun show(manager: FragmentManager, value: Int){
            val dialog = SingleChoiceDialogFragment()
            dialog.arguments = bundleOf(VALUE_KEY to value)
            dialog.show(manager, TAG)
        }

        fun setupListener(manager: FragmentManager, lifecycleOwner: LifecycleOwner, listener: (Int) -> Unit){
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner){ _, res ->
                val chosenValue = res.getInt(RESPONSE_KEY)
                    listener(chosenValue)
            }
        }
    }
}