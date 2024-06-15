package br.com.hellodev.movieapp.presenter.main.bottombar.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.hellodev.movieapp.R
import br.com.hellodev.movieapp.databinding.FragmentEditProfileBinding
import br.com.hellodev.movieapp.util.initToolbar
import br.com.hellodev.movieapp.util.showSnackBar

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}