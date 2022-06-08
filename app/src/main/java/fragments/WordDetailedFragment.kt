package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pr3.databinding.FragmentDetailedBinding


class WordDetailedFragment : Fragment() {
    private var _binding: FragmentDetailedBinding? = null
    private val binding get() = _binding!!

    private val wordDetailedFragmentArgs: WordDetailedFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedBinding.inflate(inflater, container, false)

        val word = wordDetailedFragmentArgs.word
        binding.apply {
            tvWordRu.text = word.word_ru
            tvWordEng.text = word.word_eng
            btnGoBack.setOnClickListener{ findNavController().popBackStack() }
        }
        return binding.root
    }


    companion object{
        private const val ARG_WORD = "ARG_WORD"

//        fun newInstance(word: Word): WordDetailedFragment {
//            val fragment = WordDetailedFragment()
//            fragment.arguments = bundleOf(ARG_WORD to word)
//            return fragment
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}