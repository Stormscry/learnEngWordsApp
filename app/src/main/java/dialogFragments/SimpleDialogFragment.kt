package dialogFragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.LifecycleOwner
import com.example.pr3.R
import fragments.showToast

class SimpleDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener = DialogInterface.OnClickListener { _, btnId ->
            setFragmentResult(REQUEST_KEY, bundleOf(RESPONSE_KEY to btnId))
        }
        return AlertDialog.Builder(requireContext())
            .setTitle("Simple dialog fragment")
            .setCancelable(true) //false не робит
            .setIcon(R.drawable.hamster)
            .setMessage("Simple dialog fragment message!")
            .setPositiveButton("Ok", listener)
            .setNegativeButton("Cancel", listener)
            .setNeutralButton("Ignore", listener)
            .create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.d(TAG, "SimpleDialogFragment was dismissed!")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        showToast("SimpleDialogFragment was canceled!")
    }

    companion object {
        const val TAG = "SimpleDialogFragment"
        const val REQUEST_KEY = "$TAG:response_key"
        const val RESPONSE_KEY = "response"

        fun show(manager: FragmentManager){
            val dialog = SimpleDialogFragment()
            dialog.show(manager, TAG)
        }

        fun setupListener(manager: FragmentManager, lifecycleOwner: LifecycleOwner, context: Context){
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner){ _, res ->
                val btnId = res.getInt(RESPONSE_KEY)
                when(btnId){
                    DialogInterface.BUTTON_POSITIVE -> Toast.makeText(context,  "The button Ok was clicked", Toast.LENGTH_SHORT)
                        .show()
                    DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(context,  "The button Cancel was clicked", Toast.LENGTH_SHORT).show()
                    DialogInterface.BUTTON_NEUTRAL -> Toast.makeText(context,  "The button Ignore was clicked", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}



