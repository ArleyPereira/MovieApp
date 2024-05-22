package br.com.hellodev.movieapp.presenter.main.bottombar.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.hellodev.movieapp.R
import br.com.hellodev.movieapp.databinding.FragmentProfileBinding
import br.com.hellodev.movieapp.domain.model.MenuProfile
import br.com.hellodev.movieapp.domain.model.MenuProfileType
import br.com.hellodev.movieapp.presenter.main.bottombar.profile.adapter.ProfileMenuAdapter

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: ProfileMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configData()

        initRecycler()
    }

    private fun initRecycler() {
        mAdapter = ProfileMenuAdapter(
            items = MenuProfile.items,
            context = requireContext(),
            onClick = { type ->
                when (type) {
                    MenuProfileType.PROFILE -> {

                    }

                    MenuProfileType.NOTIFICATION -> {

                    }

                    MenuProfileType.DOWNLOAD -> {

                    }

                    MenuProfileType.SECURITY -> {

                    }

                    MenuProfileType.LANGUAGE -> {

                    }

                    MenuProfileType.DARK_MODE -> {

                    }

                    MenuProfileType.HELPER -> {

                    }

                    MenuProfileType.PRIVACY_POLICY -> {

                    }

                    MenuProfileType.LOGOUT -> {

                    }
                }
            }
        )

        with(binding.recyclerItems) {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun configData() {
        binding.imageProfile.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.person_placeholder
            )
        )

        binding.textUsername.text = "Arley Santana"
        binding.textEmail.text = "andrew_ainsley@yourdomain.com"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}