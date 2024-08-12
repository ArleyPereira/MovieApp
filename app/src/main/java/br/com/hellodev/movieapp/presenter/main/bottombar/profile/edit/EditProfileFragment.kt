package br.com.hellodev.movieapp.presenter.main.bottombar.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.hellodev.movieapp.R
import br.com.hellodev.movieapp.databinding.FragmentEditProfileBinding
import br.com.hellodev.movieapp.domain.model.user.User
import br.com.hellodev.movieapp.util.FirebaseHelper
import br.com.hellodev.movieapp.util.StateView
import br.com.hellodev.movieapp.util.initToolbar
import br.com.hellodev.movieapp.util.showSnackBar
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EditProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar = binding.toolbar)

        getUser()

        initListeners()
    }

    private fun initListeners() {
        binding.btnUpdate.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val name = binding.editFirstName.text.toString()
        val surName = binding.editSurname.text.toString()
        val phone = binding.editPhone.text.toString()
        val genre = binding.editGenre.text.toString()
        val country = binding.editCountry.text.toString()

        if (name.isEmpty()) {
            showSnackBar(message = R.string.text_name_empty_edit_profile_fragment)
            return
        }

        if (surName.isEmpty()) {
            showSnackBar(message = R.string.text_surname_empty_edit_profile_fragment)
            return
        }

        if (phone.isEmpty()) {
            showSnackBar(message = R.string.text_phone_empty_edit_profile_fragment)
            return
        }

        if (genre.isEmpty()) {
            showSnackBar(message = R.string.text_genre_empty_edit_profile_fragment)
            return
        }

        if (country.isEmpty()) {
            showSnackBar(message = R.string.text_country_empty_edit_profile_fragment)
            return
        }

        val user = User(
            id = FirebaseHelper.getUserId(),
            firstName = name,
            surName = surName,
            email = FirebaseHelper.getAuth().currentUser?.email,
            phone = phone,
            genre = genre,
            country = country
        )

        update(user)
    }

    private fun update(user: User) {
        viewModel.update(user).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    showLoading(true)
                }

                is StateView.Success -> {
                    showLoading(false)
                    showSnackBar(message = R.string.text_update_profile_success_edit_profile_fragment)
                }

                is StateView.Error -> {
                    showLoading(false)
                    showSnackBar(
                        message = FirebaseHelper.validError(error = stateView.message ?: "")
                    )
                }
            }
        }
    }

    private fun getUser() {
        viewModel.getUser().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    showLoading(true)
                }

                is StateView.Success -> {
                    showLoading(false)
                    stateView.data?.let {
                        configData(user = it)
                    }
                }

                is StateView.Error -> {
                    showLoading(false)
                    showSnackBar(
                        message = FirebaseHelper.validError(error = stateView.message ?: "")
                    )
                }
            }
        }
    }

    private fun configData(user: User) {
        binding.editFirstName.setText(user.firstName)
        binding.editSurname.setText(user.surName)
        binding.editEmail.setText(FirebaseHelper.getAuth().currentUser?.email)
        binding.editPhone.setText(user.phone)
        binding.editGenre.setText(user.genre)
        binding.editCountry.setText(user.country)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            Glide
                .with(requireContext())
                .load(R.drawable.loading)
                .into(binding.progressLoading)

            binding.progressLoading.isVisible = true
        } else {
            binding.progressLoading.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}