package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pr3.AddWordFragmentState
import com.example.pr3.R
import com.example.pr3.StateViewModel
import com.example.pr3.navigation
import com.example.pr3.databinding.FragmentAddWordBinding
import model.Word

class AddWordFragment : Fragment() {

    private var _binding: FragmentAddWordBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<StateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            val currentState = it.getParcelable<AddWordFragmentState>(STATE_KEY)!!
            viewModel.saveState(currentState)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddWordBinding.inflate(inflater, container, false)

        viewModel.state.observe(viewLifecycleOwner) {
            renderUi(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddWordConfirm.setOnClickListener {
            val wordEng = binding.tilWordEng.editText?.text.toString()
            val wordRu = binding.tilWordRu.editText?.text.toString()
            if (areFieldsValidate(wordEng, wordRu)) {
                val id = navigation().wordService.words.size
                val newWord = Word(id, wordEng, wordRu)
                setFragmentResult(REQUEST_KEY, bundleOf(WORD_DATA_KEY to newWord))
                findNavController().popBackStack()
            }
        }
    }

    private fun areFieldsValidate(wordEng: String, wordRu: String): Boolean {
        binding.tilWordEng.error = null
        binding.tilWordRu.error = null
        var res = true
        if (wordEng == "") {
            binding.tilWordEng.error = getString(R.string.inputError)
            res = false
        }
        if (wordRu == "") {
            binding.tilWordRu.error = getString(R.string.inputError)
            res = false
        }
        return res
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // with viewModel
        val state = AddWordFragmentState(
            binding.tilWordEng.editText?.text.toString(),
            binding.tilWordRu.editText?.text.toString()
        )
        outState.putParcelable(STATE_KEY, state)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderUi(state: AddWordFragmentState) {
        binding.apply {
            tilWordEng.editText?.setText(state.engWordInput)
            tilWordRu.editText?.setText(state.ruWordInput)
        }
    }

    companion object {
        const val STATE_KEY = "state_key"
        const val REQUEST_KEY = "request_key"
        const val WORD_DATA_KEY = "word_data_key"
    }
}
