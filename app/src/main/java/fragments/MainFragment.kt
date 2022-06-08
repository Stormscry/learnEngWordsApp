package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pr3.WordAdapter
import com.example.pr3.navigation
import com.example.pr3.databinding.FragmentMainBinding
import model.Word

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var wordAdapter = WordAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        wordAdapter.wordList = navigation().wordService.words

        wordAdapter.attachOnItemClickListener { pos ->
            navigation().launchWordDetailedFragment(wordAdapter.wordList[pos])
        }
        binding.rvWordsList.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = wordAdapter
        }
        binding.fab.setOnClickListener {
            navigation().launchAddWordFragment()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenAddWordFragmentResult()
    }

    private fun listenAddWordFragmentResult() {
        setFragmentResultListener(AddWordFragment.REQUEST_KEY) { _, word ->
            val newWord = word.getParcelable<Word>(AddWordFragment.WORD_DATA_KEY)
            newWord?.let {
                val newWordList = wordAdapter.wordList
                newWordList.add(0, it)
                wordAdapter.wordList = newWordList
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun Fragment.showToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}