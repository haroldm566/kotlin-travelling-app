package op.mobile.app.dev.mandha1.travelling.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.mandha1.travelling.R
import op.mobile.app.dev.mandha1.travelling.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentQuizBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quiz,
            container,
            false
        )

        /**
         * Get the Country object from the bundle. It is passed from the
         * HomeFragment to the QuizFragment when the user clicks on
         * a RecyclerView item
         */
        val viewModelFactory =
            /**
             * QuizViewModelFactory allows you to retrieve the Country object passed
             * from HomeFragment
             */
            QuizViewModelFactory(
                QuizFragmentArgs.fromBundle(requireArguments()).country
            )

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(QuizViewModel::class.java)

        /**
         * Observe when the quiz is over
         */
        viewModel.isFinished.observe(
            viewLifecycleOwner,
            Observer {
                if (it) isFinished()
            }
        )

        /**
         * Set the quiz questions and start the timer
         */
        viewModel.setQuestion()
        viewModel.startTimer()

        /**
         * This disables the back button. You can not go to the
         * previous Fragment (not working)
         */
        activity?.onBackPressedDispatcher?.addCallback(this) {}

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            quizViewModel = viewModel

            btnSubmit.setOnClickListener {
                val checkedRadioBtnId = binding.radioBtnGroupAnswers.checkedRadioButtonId
                /**
                 * The first answer is the correct answer
                 */
                val correctAnswer = viewModel.quiz.value?.answers?.get(0)

                /**
                 * If a RadioButton is checked
                 */
                if (checkedRadioBtnId != -1) {
                    var answerIdx = 0

                    /**
                     * When a RadioButton is checked, get the answer's index.
                     * It will be important in getting the index's value
                     */
                    when (checkedRadioBtnId) {
                        R.id.radio_btn_answer_two -> answerIdx = 1
                        R.id.radio_btn_answer_three -> answerIdx = 2
                        R.id.radio_btn_answer_four -> answerIdx = 3
                    }

                    /**
                     * If the answer is correct
                     */
                    if (viewModel.answers.value?.get(answerIdx)
                        == correctAnswer
                    ) {
                        /**
                         * - Add to the score
                         * - Display a message for a correct answer
                         */
                        viewModel.addScore()
                        Toast.makeText(activity, getString(R.string.correct), Toast.LENGTH_LONG).show()

                    } else {
                        /**
                         * Display a message for an incorrect answer
                         */
                        Toast.makeText(activity, getString(R.string.incorrect, correctAnswer), Toast.LENGTH_LONG).show()
                    }

                    viewModel.addQuestionIdx()

                    /**
                     * If the current question's index is less than the number of
                     * quiz question (five)
                     */
                    if (viewModel.questionIdx.value!! < viewModel.country.quiz.size) {
                        viewModel.setQuestion()
                        /**
                         * Clear the checked RadioButton from the previous question
                         */
                        binding.radioBtnGroupAnswers.clearCheck()
                        binding.invalidateAll()
                    } else {
                        isFinished() // Quiz is finished
                    }
                } else {
                    /**
                     * Self-directed learning:
                     *
                     * Display a message if a RadioButton is not clicked
                     */
                    Toast.makeText(activity, getString(R.string.please_select_an_answer), Toast.LENGTH_LONG).show()
                }
            }

            return root
        }
    }

    /**
     * When the quiz is finished, navigate to the
     * QuizResultsFragment with the final score value.
     * This is declared in mobile_navigation.xml using
     * the argument tag
     */
    private fun isFinished() {
        val action = QuizFragmentDirections
            .actionQuizFragmentToQuizResultsFragment(
                viewModel.score.value!!
            )
        findNavController().navigate(action)
    }


}